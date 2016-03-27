var app = angular.module("myApp", [ "xeditable", "ui.bootstrap" ]);

app.controller("loadData", function($scope, $http, $timeout) {
	var config = {
		headers : {
			'Accept' : 'application/json'
		}
	};

	$scope.loading = false;
	$scope.curPage = 1;
	$scope.pageSize = 15;
	$scope.numPages = 10;

	var loadData = function() {
		$scope.loading = true;
		var birthday = Date.parse($scope.filter.birthday);
		if(isNaN(birthday)) birthday = $scope.filter.birthday;
		var datas = {
			username : $scope.filter.username,
			password : $scope.filter.password,
			email : $scope.filter.email,
			phone : $scope.filter.phone,
			birthday: birthday
		};
		
		var config2 = {
			params : datas,
			headers : {
				'Accept' : 'application/json'
			}
		};

		$scope.loading = true;
		$http.get('list-test?page=' + $scope.curPage, config2).success(
				function(data) {
					$scope.accounts = data;
					$http.get('count-test', config2).success(function(data2) {
						$scope.totalItem = data2;
					}).finally(function(){
						$scope.loading = false;
					});
				}).error(function(){
					$scope.loading = false;
				});

	}

	$scope.filter = {
			'username' : '',
			'password' : '',
			'email' : '',
			'phone' : '',
			'birthday' : ''
	};
	// Instantiate these variables outside the watch
	var tempFilter = {}, filterTextTimeout;
	$scope.$watch('filter', function(val) {
		if (filterTextTimeout)
			$timeout.cancel(filterTextTimeout);

		tempFilter = val;
		filterTextTimeout = $timeout(function() {
			$scope.filter = tempFilter;
			loadData();
		}, 2000); // delay 2000 ms
	}, true);

	$scope.pageChanged = function() {
		loadData();
	};

	$scope.newUser = {};

	$scope.createUser = function(newUser) {
		$scope.formError = null;
		$scope.formMessage = "Đợi nhé...";

		$http.post("/create-account", newUser, config).success(
				function(data, status, headers, config) {
					$scope.formMessage = data ? 'Tạo tài khoản thành công!'
							: null;
					$scope.formError = data ? null : 'Đã tồn tại tài khoản!';
					if (data) {
						$scope.newUser = {};
						loadData();
					}
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

		$http.post("/del-account", user, config).success(
				function(data, status, headers, config) {
					loadData();
					$scope.message = 'Xóa tài khoản thành công!';
				}).error(function(data, status, headers, config) {
			$scope.error = "Có lỗi xảy ra!";
		});
	};

	$scope.updateUser = function(val) {
		$scope.error = null;
		$scope.message = "Đợi nhé...";

		return $http.post('/update-account', val, config).success(
				function(data, status, headers, config) {
					$scope.formMessage = 'Cập nhật tài khoản thành công!';
				}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};

	$scope.addQuestion = function(val) {
		if (angular.isUndefined(val.questions) || val.questions === null)
			val.questions = [];
		var question = {};
		val.questions.push(question);
		$scope.updateUser(val);
	}

	$scope.removeQuestion = function(val, index) {
		var r = confirm("Bạn có chắc muốn xoá câu hỏi này?");
		if (r == false)
			return;

		val.questions.splice(index, 1);
		$scope.updateUser(val);
	}

});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});

app.directive('mySearch', function() {
	function link(scope, element, attrs) {
		jQuery(function() {
			// jQuery(".tags").autocomplete({
			// source : scope.userList
			// });
			jQuery('.createDay').datepicker({
				dateFormat : "dd/mm/yy",
				changeMonth : true,
				changeYear : true,
				yearRange : "-100:+0",
				maxDate : 0
			});
		});
	}

	return {
		link : link
	};
});