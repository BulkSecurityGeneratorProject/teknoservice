'use strict';

angular.module('jhipsterdemoApp')
    .factory('TypeAttivita', function ($resource) {
        return $resource('api/typeattivita/:id', {}, {
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
