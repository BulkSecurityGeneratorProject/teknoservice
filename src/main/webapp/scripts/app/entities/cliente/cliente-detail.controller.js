'use strict';

angular.module('jhipsterdemoApp')
    .controller('ClienteDetailController', function ($scope, $stateParams, Cliente, Contratto) {
        $scope.cliente = {};
        $scope.load = function (id) {
            Cliente.get({id: id}, function(result) {
              $scope.cliente = result;
            });
        };
        $scope.load($stateParams.id);
    });
