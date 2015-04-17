'use strict';

angular.module('teknoserviceApp', ['LocalStorageModule', 'tmh.dynamicLocale',
    'ngResource', 'ui.router', 'ngCookies', 'pascalprecht.translate', 'ngCacheBuster', 'infinite-scroll'])

    .run(function ($rootScope, $location, $window, $http, $state, $translate, Auth, Principal, Language, ENV, VERSION) {
        $rootScope.ENV = ENV;
        $rootScope.VERSION = VERSION;
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;

            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }

            // Update the language
            Language.getCurrent().then(function (language) {
                $translate.use(language);
            });
        });

        $rootScope.$on('$stateChangeSuccess',  function(event, toState, toParams, fromState, fromParams) {
            var titleKey = 'global.title';

            $rootScope.previousStateName = fromState.name;
            $rootScope.previousStateParams = fromParams;

            // Set the page title key to the one configured in state or use default one
            if (toState.data.pageTitle) {
                titleKey = toState.data.pageTitle;
            }
            $translate(titleKey).then(function (title) {
                // Change window title with translated one
                $window.document.title = title;
            });
        });

        $rootScope.back = function() {
            // If previous state is 'activate' or do not exist go to 'home'
            if ($rootScope.previousStateName === 'activate' || $state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        };
    })
    .constant('toastr', toastr)
    .config(function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider, $translateProvider, 
    		tmhDynamicLocaleProvider, httpRequestInterceptorCacheBusterProvider,
    		$compileProvider, $provide, toastr) {
    	
    	toastr.options.closeButton = true;
    	toastr.options.timeOut = 2 * 1000;
    	
    	$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|tel|file|blo??b):/);

    	// exception handling overriding
    	$provide.decorator("$exceptionHandler", function($delegate, $injector ){
    		return function(exception, cause){
    			//var $rootScope = $injector.get("$rootScope");
    	        //$rootScope.addError({message:"Exception", reason:exception});
    			toastr.error(exception.message);
    			toastr.error(exception.stack);
    			
    			var remoteLogsService = $injector.get("RemoteLogsService"); 
    			remoteLogsService.error(exception.stack);
    			
    	        $delegate(exception, cause);
    	    };
    	});
    	
    	//request/response interceptor
    	$httpProvider.interceptors.push(function($q, toastr, $rootScope) {
    	//$httpProvider.interceptors.push(function($q, $rootScope) {
    	    return {
    	      'request': function(config) {
    	        console.log('I will send a request to the server');
    	        //toastr.info("sending...");
    	        //$rootScope.loading = true;
    	        $rootScope.$broadcast('broadcast.loading.begin');
    	        
    	        return config; 
    	      },

    	      'response': function(response) {
    	        // called if HTTP CODE = 2xx 
    	        console.log('I got a sucessfull response from server');
    	        //toastr.info("sent...");
    	        //$rootScope.loading = false;
    	        
    	        $rootScope.$broadcast('broadcast.loading.end');
    	        
    	        return response;
    	      },

    	      'responseError': function(rejection) {
    	        // called if HTTP CODE != 2xx
    	        console.log('I got an error from server');
    	        toastr.error(rejection.data.message)
    	        //$rootScope.loading = false;
    	        
    	        $rootScope.$broadcast('broadcast.loading.end');
    	        return $q.reject(rejection);
    	      }
    	    };
    	  });

        //enable CSRF
        $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';
        $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';

        //Cache everything except rest api requests
        httpRequestInterceptorCacheBusterProvider.setMatchlist([/.*api.*/, /.*protected.*/], true);

        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: 'scripts/components/navbar/navbar.html',
                    controller: 'NavbarController'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('global');
                    $translatePartialLoader.addPart('language');
                    return $translate.refresh();
                }]
            }
        });


        // Initialize angular-translate
        $translateProvider.useLoader('$translatePartialLoader', {
            urlTemplate: 'i18n/{lang}/{part}.json'
        });

        $translateProvider.preferredLanguage('en');
        $translateProvider.useCookieStorage();

        tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
        tmhDynamicLocaleProvider.useCookieStorage('NG_TRANSLATE_LANG_KEY');
    });
