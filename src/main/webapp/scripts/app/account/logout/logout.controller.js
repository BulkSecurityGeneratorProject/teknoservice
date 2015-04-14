'use strict';

angular.module('teknoserviceApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
