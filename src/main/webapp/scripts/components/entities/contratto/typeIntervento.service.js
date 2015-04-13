'use strict';

angular.module('jhipsterdemoApp')
    .factory('TypeIntervento', function ($resource) {
        return $resource('api/typeintervento/:id', {}, {
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
