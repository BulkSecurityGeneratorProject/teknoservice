'use strict';

angular.module('teknoserviceApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


