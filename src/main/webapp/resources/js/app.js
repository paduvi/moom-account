angular.module("myaccount", []).controller("load", function($scope,$http) {
	var config = {
			headers: {'accept' : 'application/json' }
	};

	$http.get('http://localhost:8080/json', config).success(function(data) {
		$scope.student = data;
	});
});



