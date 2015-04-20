'use strict';

angular.module('teknoserviceApp')
    .factory('Cliente', function ($resource) {
        return $resource('api/clientes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'getClienti': { 
            	method: 'GET', isArray: true, url: 'api/clientes/clienti' 
            }
        });
    });
