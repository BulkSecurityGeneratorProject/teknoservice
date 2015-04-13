'use strict';

angular.module('jhipsterdemoApp')
    .factory('TypeImpianto', function ($resource) {
        return $resource('api/typeimpianto/:id', {}, {
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
