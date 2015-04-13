'use strict';

angular.module('teknoservicedemoApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


