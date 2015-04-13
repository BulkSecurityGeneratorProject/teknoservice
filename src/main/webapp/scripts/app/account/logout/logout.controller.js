'use strict';

angular.module('teknoservicedemoApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
