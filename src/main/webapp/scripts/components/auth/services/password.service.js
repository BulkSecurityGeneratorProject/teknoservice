'use strict';

angular.module('teknoserviceApp')
    .factory('Password', function ($resource) {
        return $resource('api/account/change_password', {}, {
        });
    });
