'use strict';

angular.module('teknoserviceApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cliente', {
                parent: 'entity',
                url: '/cliente',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'teknoserviceApp.cliente.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cliente/clientes.html',
                        controller: 'ClienteController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cliente');
                        return $translate.refresh();
                    }]
                }
            })
            .state('clienteDetail', {
                parent: 'entity',
                url: '/cliente/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'teknoserviceApp.cliente.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cliente/cliente-detail.html',
                        controller: 'ClienteDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cliente');
                        return $translate.refresh();
                    }]
                }
            });
    });
