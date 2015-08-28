	var values;
	var timeValues;

app.controller('EfficiencyController', function($scope, $http, $location) {
	
	$scope.callEnergyConsumption = function(){
//		$location.path("/energyConsumption");
		$location.path('/energyConsumption');	
	}
	
	var x = ["Mon", "Tue", "Wed", "Thur"];
	$scope.pageLoad=function(){

			$http({
				method  : 'GET',
				url     : "rest/jsonservices/readLastFiveValues/Timestamp-AC",
				headers : { 'Content-Type': 'text/plain '}
			}).success(function (data) {
				beforeMyFunction(data);
			})
			.error(function(data) {	
				$scope.storeValues = "Error!!!"
					console.log('error');
			});				
	}
	
	function beforeMyFunction(dataTimeStamp){
		$http({
			method  : 'GET',
			url     : "rest/jsonservices/readLastFiveValues/Temperature-AC",
			headers : { 'Content-Type': 'text/plain '}
		}).success(function (data) {
//			console.log(data1," : ",data)
			getVoltageValues(dataTimeStamp,data);
		})
		.error(function(data) {	
			$scope.storeValues = "Error!!!"
				console.log('error');
		});				
		
	}
	
	function getVoltageValues(dataTimeStamp, dataTemp){
		$http({
			method  : 'GET',
			url     : "rest/jsonservices/readLastFiveValues/Voltage-AC",
			headers : { 'Content-Type': 'text/plain '}
		}).success(function (data) {
//			console.log(data1," : ",data)
			myfunction(dataTimeStamp, dataTemp, data ); // data = temperature, data1 = timestamp
		})
		.error(function(data) {	
			$scope.storeValues = "Error!!!"
				console.log('error');
		});
	}
	
	
	function myfunction(dataTimeStamp, dataTemp, dataVoltage){
	$(function () {
	    $('#container').highcharts({
	        title: {
	            text: 'Visualization Charts',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Source: SAMs Distributive IOT System',
	            x: -20
	        },
	        xAxis: {
	        	 categories: dataTimeStamp
	        	 
//	        	 categories: values
	        },
	        yAxis: {
	            title: {
	                text: 'Temperature in Celsius, Voltages in mV'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: ''
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: 'TEMPERATURE',
	            data: dataTemp
	        }, {
	            name: 'VOLTAGE',
	            data: dataVoltage
	        }  
	        ]
	    });
	}

	); 
	}	

})
