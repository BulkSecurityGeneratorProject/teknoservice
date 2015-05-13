'use strict';

angular.module('teknoserviceApp')
    .factory('Contratto', function ($resource, $sce, $window) {
        return $resource('api/contrattos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'search': { 
            	method: 'POST', 
            	url : 'api/contrattos/search',
            	isArray: true/*,
            	transformRequest: function (data) {
            		 retruun data;
            	}*/
             },
             'searchChart': { 
             	method: 'POST', 
             	url : 'api/contrattos/search/chart',
             	isArray: true
              },
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.decorrenzaDate = new Date(data.decorrenzaDate);
                    data.scadenzaDate = new Date(data.scadenzaDate);
                    return data;
                }
            },
            'update': { method:'PUT' },
            'pdf': {  
            	url : 'api/contrattos/:id/pdf',
                method: 'GET',
                headers: {
                    accept: 'application/pdf'
                },
                responseType: 'arraybuffer',
                //responseType: 'text',
                cache: true,
                transformResponse: function (data) {
                    var pdf;
                    if (data) {
                        pdf = new Blob([data], {
                            type: 'application/pdf'
                        });
                    }
                    var url = URL.createObjectURL(pdf);
                    return { response : pdf , url : url};
                }
            },
            'pdfSend': {  
            	url : 'api/contrattos/:id/pdf/send',
                method: 'DELETE'
            }	
        });
    });
