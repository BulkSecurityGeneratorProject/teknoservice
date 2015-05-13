'use strict';

angular.module('teknoserviceApp')
    .controller('ContrattoChartController',['$scope', '$timeout', 'Contratto', function ($scope, $timeout, Contratto) {

    	  $scope.labels = [];
    	  $scope.series = ['Series Importo'];
    	  
    	  $scope.data = [[]];
    	  
    	  Contratto.searchChart( $scope.$parent.contratto, function(result) {
              
    		  $scope.chartTable = result;
              
              angular.forEach($scope.chartTable, function(value, key) {
            	  $scope.labels.push( value[2] +' - &euro; ' + value[1] );
            	  $scope.data[0].push( value[0] );
              });
          });
    	  
    	  $scope.onClick = function (points, evt) {
    	    console.log(points, evt);
    	  };

    	  /* Simulate async data update
    	  $timeout(function () {
    	    $scope.data = [
    	      [28, 48, 40, 19, 86, 27, 90],
    	      [65, 59, 80, 81, 56, 55, 40]
    	    ];
    	  }, 3000);*/
    	}]);