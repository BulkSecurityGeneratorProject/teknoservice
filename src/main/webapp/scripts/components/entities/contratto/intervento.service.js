'use strict';

angular.module('teknoserviceApp')
    .factory('Intervento', function ($resource) {
        return $resource('api/intervento/:id', {}, {
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
