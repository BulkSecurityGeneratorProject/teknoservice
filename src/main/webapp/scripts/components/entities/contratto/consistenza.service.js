'use strict';

angular.module('teknoserviceApp')
    .factory('Consistenza', function ($resource) {
        return $resource('api/consistenza/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });
