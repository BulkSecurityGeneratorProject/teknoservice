'use strict';

angular.module('jhipsterdemoApp')
    .controller('ClienteController', function ($scope, Cliente, Contratto, ParseLinks) {
        $scope.clientes = [];
        //$scope.contrattos = Contratto.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Cliente.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.clientes = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Cliente.update($scope.cliente,
                function () {
                    $scope.loadAll();
                    $('#saveClienteModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Cliente.get({id: id}, function(result) {
                $scope.cliente = result;
                $('#saveClienteModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Cliente.get({id: id}, function(result) {
                $scope.cliente = result;
                $('#deleteClienteConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Cliente.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteClienteConfirmation').modal('hide');
                    $scope.clear();
                } );
        };    

        $scope.clear = function () {
            $scope.cliente = {cognome: null, nome: null, codiceFiscale: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });
