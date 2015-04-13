'use strict';

angular.module('jhipsterdemoApp')
    .factory('Impianto', function ($resource) {
        return $resource('api/impianto/:id', {}, {
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
