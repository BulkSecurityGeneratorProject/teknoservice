'use strict';

angular.module('teknoserviceApp')
    .factory('RemoteLogsService', function ($resource) {
        return $resource('api/logs', {}, {
        	'info': {
            	url : 'api/logs/info',
            	method: 'PUT'
            },
            'error': {
            	url : 'api/logs/error',
            	method: 'PUT'
            }
        });
    });
