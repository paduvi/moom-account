var app = angular.module("myApp", ["xeditable"]);

app.filter('accountFilter',function() {
	return function(input, selectedFilter) {
		if (!angular.isUndefined(input)
				&& !angular.isUndefined(selectedFilter)) {
			var tempOutput = [];
			for (i = 0; i < input.length; i++) {
				user = input[i];
				if (!account.username
						.match(selectedFilter.username))
					continue;
				if (!account.password
						.match(selectedFilter.password))
					continue;

				tempOutput.push(user);
			}
			return tempOutput;
		} else {
			return input;
		}
		alert(input + "//" + selectedFilter);
	};
});

app.controller("loadData", function($scope, $filter, $http, $timeout) {
	var config = {
			headers: {'accept' : 'application/json' }
	};
	
	$scope.curPage = 1;
	$scope.pageSize = 15;
	$scope.numPages = 15;
	$scope.filter = [];
	$scope.filter.username = '';
	$scope.filter.password = '';

	$scope.loading = true;
	
	var loadData = function() {
		$http.get('list-test', config).success(function(data) {
			$scope.accounts = data;
		});
		
		$scope.numberOfPages = function() {
			return Math.ceil($filter('accountFilter')($scope.accounts, $scope.filter).length / $scope.pageSize);
		};
	}
	
	loadData();
	
	/*$scope.newUser = {};*/
	
	/*$http.post("create-account", newUser, config).success(
			function(data, status, headers, config) {
				alert(data);
	}).error(function(data, status, headers, config) {
	})*/
});

app.run(function(editableOptions) {
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});

app.controller('editable', function($scope) {
	  $scope.user = {};  
	  
	  $scope.updateUser = function() {
		    return $http.post('/updateUser', $scope.user);
		  };
});
