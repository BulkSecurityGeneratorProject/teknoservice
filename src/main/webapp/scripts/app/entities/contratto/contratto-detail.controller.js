'use strict';

angular.module('jhipsterdemoApp')
    .controller('ContrattoDetailController', function ($scope, $stateParams, Contratto, Cliente) {
        $scope.contratto = {};
        $scope.load = function (id) {
            Contratto.get({id: id}, function(result) {
              $scope.contratto = result;
            });
        };
        $scope.load($stateParams.id);
    });
