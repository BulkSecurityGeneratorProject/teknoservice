'use strict';

angular.module('teknoserviceApp')
    .controller('ContrattoController', function (
    		$scope, ParseLinks, $stateParams, $location, $sce, $window, $document,
    		Contratto, Cliente, Consistenza, Impianto, Intervento, Attivita,
    		TypeConsistenza, TypeImpianto, TypeIntervento, TypeAttivita
    		) {
        $scope.contrattos = [];
        $scope.clientes = Cliente.query();
        $scope.page = 1;
        $scope.contratto = {decorrenzaDate: null, scadenzaDate: null, importo: null, discountPercent: null, id: null,
        		search : { scadenzaDateBegin: null, scadenzaDateEnd: null, importoBegin: null, importoEnd: null } 
        };
        
        $scope.$on('broadcast.init.all', function(event, data) {
            $scope.loadAll();
        });
        
        $scope.$on('broadcast.init.one', function(event, data) {
            $scope.load(data);
        });
        
        $scope.loadAll = function() {
            Contratto.query({page: $scope.page, per_page: 20},
            		function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.contrattos = result;
            });
        };
        
        $scope.search = function(contratto) {
            Contratto.search( contratto ,
            		function(result) {
                $scope.contrattos = result;
            });
        };
        
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };

        $scope.load = function (id) {
            Contratto.get({id: id}, function(result) {
              $scope.contratto = result;
            });
        };
        
        //$scope.loadAll();
        
        //if ( $stateParams.id != null ) $scope.load($stateParams.id);
        
        $scope.create = function () {
            Contratto.update($scope.contratto,
                function () {
                    $scope.loadAll();
                    $('#saveContrattoModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Contratto.get({id: id}, function(result) {
                $scope.contratto = result;
                $('#saveContrattoModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Contratto.get({id: id}, function(result) {
                $scope.contratto = result;
                $('#deleteContrattoConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Contratto.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteContrattoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function (id) {
            $scope.contratto = {decorrenzaDate: null, scadenzaDate: null, importo: null, discountPercent: null, id: null};

            Consistenza.get({id:id}, function(result) {
            	$scope.contratto.consistenzas = [];
            	$scope.contratto.consistenzas.push( result );
            });   
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
        
        // remove consistenze:
        $scope.removeConsistenza = function(id, consistenza){
        	
        	var rowIndex = $scope.contratto.consistenzas.indexOf(consistenza);
        	
        	if(id==null) {
        		$scope.contratto.consistenzas.splice(rowIndex,1);
        	}else{
        		Consistenza.delete({id:id}, function(result) {
        			$scope.contratto.consistenzas.splice(rowIndex,1);
                });   
        	}
        };
        
        $scope.removeImpianto = function(id, consistenza, impianto){
        	
    		var consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
    		var rowIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos.indexOf(impianto);
        	
        	if(id==null) {
        		$scope.contratto.consistenzas[consistenzaIndex].impiantos.splice(rowIndex, 1);
        	}else{
        		Impianto.delete({id:id}, function(result) {
        			$scope.contratto.consistenzas[consistenzaIndex].impiantos.splice(rowIndex, 1);
                });   
        	}
        };
        
        
        $scope.removeIntervento = function(id, consistenza, impianto, intervento){
        	
        	var consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
        	var impiantoIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos.indexOf(impianto);
    		var rowIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos.indexOf(intervento);
        	
        	if(id==null) {
        		$scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos.splice(rowIndex, 1);
        	}else{
        		Intervento.delete({id:id}, function(result) {
        			$scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos.splice(rowIndex, 1);
                });   
        	}
        };
        
        $scope.removeAttivita= function(id, consistenza, impianto, intervento, attivita){
        	
        	var consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
        	var impiantoIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos.indexOf(impianto);
        	var interventoIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos.indexOf(intervento);
    		var rowIndex = $scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos[interventoIndex].attivitas.indexOf(attivita);
        	        	
        	if(id==null) {
        		$scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos[interventoIndex].attivitas.splice(rowIndex, 1);
        	}else{
        		Attivita.delete({id:id}, function(result) {
        			$scope.contratto.consistenzas[consistenzaIndex].impiantos[impiantoIndex].interventos[interventoIndex].attivitas.splice(rowIndex, 1);
                });   
        	}
        };
        
        // add type:
        // type consistenza:
        $scope.loadAllTypeConsitenza = function(){
        	TypeConsistenza.query({}, function(result, headers) {
        		$scope.typeConsistenzas = result;
            });
        };
        
        $scope.addTypeConsistenza = function(typeConsistenzaId){
        	Consistenza.get({id:typeConsistenzaId}, function(result) {
        		if($scope.contratto.consistenzas == undefined){
        			$scope.contratto.consistenzas = [];
        		}
            	$scope.contratto.consistenzas.push( result );
            	$('#loadAllTypeConsitenzaModal').modal('hide');
            });  
        };
        
        // type impianto:
        $scope.loadAllTypeImpianto = function(typeConsistenzaId,consistenza){
        	TypeImpianto.query({id:typeConsistenzaId}, function(result, headers) {
        		$scope.typeImpiantos = result;
        		$scope.consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
            });
        };
        
        $scope.addTypeImpianto = function(typeImpiantoId){
        	Impianto.get({id:typeImpiantoId}, function(result) {
        		if($scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos == undefined){
        			$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos = [];
        		}
            	$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos.push( result );
            	$('#loadAllTypeImpiantoModal').modal('hide');
            });  
        };
        
        // type intervento:
        $scope.loadAllTypeIntervento = function(typeImpiantoId, consistenza, impianto){
        	TypeIntervento.query({id:typeImpiantoId}, function(result, headers) {
        		$scope.typeInterventos = result;
        		$scope.consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
         		$scope.impiantoIndex = $scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos.indexOf(impianto);;
            });
        };
        
        $scope.addTypeIntervento = function(typeInterventoId){
        	Intervento.get({id:typeInterventoId}, function(result) {
        		if($scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos == undefined){
        			$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos = [];
        		}
            	$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos.push( result );
            	$('#loadAllTypeInterventoModal').modal('hide');
            });  
        };

        // type attivita:
        $scope.loadAllTypeAttivita = function(typeInterventoId, consistenza, impianto, intervento){
        	TypeAttivita.query({id:typeInterventoId}, function(result, headers) {
        		$scope.typeAttivitas = result;
        		$scope.consistenzaIndex = $scope.contratto.consistenzas.indexOf(consistenza);
        		$scope.impiantoIndex = $scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos.indexOf(impianto);;
        		$scope.interventoIndex = $scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos.indexOf(intervento);
            });
        };
        
        $scope.addTypeAttivita = function(typeAttivitaId){
        	Attivita.get({id:typeAttivitaId}, function(result) {
        		if($scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos[$scope.interventoIndex].attivitas == undefined){
        			$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos[$scope.interventoIndex].attivitas = [];
        		}
            	$scope.contratto.consistenzas[$scope.consistenzaIndex].impiantos[$scope.impiantoIndex].interventos[$scope.interventoIndex].attivitas.push( result );
            	$('#loadAllTypeAttivitaModal').modal('hide');
            });  
        };

        
        $scope.pdf = function(id){
        	Contratto.pdf({id:id}, function(result) {
        		$scope.pdf = result.pdf;
        		$scope.url = $sce.trustAsResourceUrl(result.url);
            });  
        };
        
        
        $scope.pdfSend = function(id){
        	Contratto.pdfSend({id:id}, function(result) {

            });  
        };
    });
