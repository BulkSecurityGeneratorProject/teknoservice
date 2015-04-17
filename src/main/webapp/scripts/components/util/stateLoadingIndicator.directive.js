angular.module('teknoserviceApp')
  .directive('stateLoadingIndicator', function($rootScope) {
  return {
    restrict: 'E',
    template: "<div ng-show='isStateLoading' class='pull-left loading'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Loading...</div>",
    replace: true,
    link: function(scope, elem, attrs) {
      scope.isStateLoading = false;
      
      scope.$on('broadcast.loading.begin', function(event, data) {
    	  scope.isStateLoading = true;
      });
      
      scope.$on('broadcast.loading.end', function(event, data) {
    	  scope.isStateLoading = false;
      });
      /*
       $rootScope.$on('$stateChangeStart', function() {
        scope.isStateLoading = true;
      });
      $rootScope.$on('$stateChangeSuccess', function() {
        scope.isStateLoading = false;
      });*/
      
    }
  };
});