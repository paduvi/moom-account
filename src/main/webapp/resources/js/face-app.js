var app = angular.module("faceApp", [ "xeditable", "ui.bootstrap", "ngDraggable" ]);

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

		var datas = {
			email : $scope.filter.email,
			password : $scope.filter.password,
			phone : $scope.filter.phone,
		};
			
		var config2 = {
			params : datas,
			headers : {
				'Accept' : 'application/json'
			}
		};
		
		$http.get('/face/list-face?page=' + $scope.curPage, config2).success(
			function(data) {
				$scope.accounts = data;
				$http.get('/face/face-count', config2).success(function(data2) {
					$scope.totalItem = data2;
				}).finally(function(){
					$scope.loading = false;
				});
			}).error(function(){
				$scope.loading = false;
		});
	}

	$scope.filter = {
			'email' : '',
			'password' : '',
			'phone' : '',
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
	
	$scope.createUser = function(newUser) {
		$scope.formError = null;
		$scope.formMessage = "Đợi nhé...";

		newUser = {
			'email' : $scope.newUser.email,
			'password' : $scope.newUser.password,
			'phone' : $scope.newUser.phone,
		};
		
		$http.post("/face/create-account", newUser, config).success(
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

		$http.post("/face/del-account", user, config).success(
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

		return $http.post('/face/update-account', val, config).success(
				function(data, status, headers, config) {
					$scope.formMessage = 'Cập nhật tài khoản thành công!';
				}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};
	
	$scope.droppedObjects = [];
	
	$http.get('/group/list-group', config).success(
		function(data) {
			$scope.groups = data;
		}).error(function(){
			$scope.loading = false;
	});
	
	var addToGroup = function(val, groupId) {
		$http.post('/group/add-to-group?group=' + groupId, val, config).success(
			function(data) {
				loadData();
			}).error(function(){
				$scope.loading = false;
			});
	}
	
	$scope.onDropComplete = function(data, id, evt){
        var index = $scope.droppedObjects.indexOf(data);
        if (index == -1)
        $scope.droppedObjects.push(data);
        addToGroup(data, id);
    }
	
    $scope.onDragSuccess = function(data, evt){
        var index = $scope.droppedObjects.indexOf(data);
        if (index > -1) {
            $scope.droppedObjects.splice(index, 1);
        }
    }
    
    var inArray = function(array, obj) {
        var index = array.indexOf(obj);
    }
});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});