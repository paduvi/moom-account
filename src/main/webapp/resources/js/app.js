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
	
	$scope.newUser = {};
	
	$scope.createUser = function(newUser) {
		$scope.formError = null;
		$scope.formMessage = "Đợi nhé...";
		
		$http.post("/create-account", newUser, config)
		.success(function(data, status, headers, config) {
			$scope.formMessage = data ? 'Tạo tài khoản thành công!'
					: null;
			$scope.formError = data ? null
					: 'Đã tồn tại tài khoản!';
		}).error(function(data, status, headers, config) {
			$scope.formError = 'Có lỗi xảy ra!';
		});
	};
	
	$scope.delUser = function(user, index) {
		var r = confirm("Bạn có chắc muốn xoá tài khoản này?");
		if (r == false)
			return;

		$scope.error = null;
		$scope.message = "Đợi nhé...";
		
		$http.post("/del-account", user, config)
		.success(function(data, status, headers, config) {
			$scope.accounts.splice(index, 1);
			$scope.message = 'Xóa tài khoản thành công!';
		}).error(function(data, status, headers, config) {
			$scope.error = "Có lỗi xảy ra!";
		});
	};
	
	$scope.updateUser = function(val) {
		$scope.error = null;
		$scope.message = "Đợi nhé...";
		
		return $http.post('/update-account', val, config).success(function(data, status, headers, config) {
			$scope.formMessage = 'Cập nhật tài khoản thành công!';
		}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};
	
	$scope.addQuestion = function(val) {
		if(angular.isUndefined(val.questions) || val.questions === null) val.questions = [];
		var question = {};
		val.questions.push(question);
		$scope.updateUser(val);
	}
	
	$scope.removeQuestion = function(val, index){
		var r = confirm("Bạn có chắc muốn xoá câu hỏi này?");
		if (r == false)
			return;
		
		val.questions.splice(index,1);
		$scope.updateUser(val);
	}
	
});

app.run(function(editableOptions, editableThemes) {
	  editableThemes.bs3.inputClass = 'input-sm';
	  editableThemes.bs3.buttonsClass = 'btn-sm';
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});