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
			number : $scope.filter.phone,
			group : ''
		};
			
		var config2 = {
			params : datas,
			headers : {
				'Accept' : 'application/json'
			}
		};
		
		$http.get('/faccount/list-face?page=' + $scope.curPage, config2).success(
			function(data) {
				$scope.accounts = data;
				$http.get('/faccount/face-count',config2).success(function(data2) {
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
			'group'	: '',
	};
	// Instantiate these variables outside the watch
	var tempFilter = {}, filterTextTimeout, delayTime = 0;
	$scope.$watch('filter', function(val) {
		if (filterTextTimeout){
			$timeout.cancel(filterTextTimeout);
			delayTime = 1000;
		}

		$scope.loading = true;
		tempFilter = val;
		filterTextTimeout = $timeout(function() {
			$scope.filter = tempFilter;
			loadData();
		}, delayTime); // delay 1000 ms
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
		
		$http.post("/faccount/create-account", newUser, config).success(
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

		$http.post("/faccount/del-account", user, config).success(
				function(data, status, headers, config) {
					loadData();
					if(data) $scope.formMessage = 'Xóa tài khoản thành công!';
					else $scope.formError = 'Có lỗi xảy ra!';
				}).error(function(data, status, headers, config) {
					$scope.formError = "Có lỗi xảy ra!";
		});
	};

	$scope.updateUser = function(val) {
		return $http.post('/face/update-account', val, config).success(
				function(data, status, headers, config) {
					if(data) $scope.formMessage = 'Cập nhật tài khoản thành công!';
					else $scope.formError = 'Không cập nhật được tài khoản!';
				}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};
	
//	$scope.droppedObjects = [];
	
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
	
	$scope.onDropComplete = function(data, groupId, id, evt){
//        var index = $scope.droppedObjects.indexOf(data);
//        if (index == -1)
//        $scope.droppedObjects.push(data);
        addToGroup(data, groupId);
        $scope.loadGroupAccount(groupId, id);
    }
	
    $scope.onDragSuccess = function(data, groupId, id, evt){
//        var index = $scope.droppedObjects.indexOf(data);
//        if (index > -1) {
//            $scope.droppedObjects.splice(index, 1);
//        }
    	 $scope.loadGroupAccount(groupId, id);
    }
    
    var inArray = function(array, obj) {
        var index = array.indexOf(obj);
    }
    
    $scope.group1 = [];
    $scope.totalGroupItem = [];
    
    $scope.loadGroupAccount = function(groupId, index) {
    	$http.get('/group/list-face?page=' + $scope.curPage + "&group=" + groupId, config).success(
    			function(data) {
    				$scope.group1[index] = data;
    				$http.get('/group/face-count?group=' + groupId, config).success(function(data2) {
    					if($scope.groupCurPage[index] === null || angular.isUndefined($scope.groupCurPage[index]))
    						$scope.groupCurPage[index] = 1;
    					$scope.totalGroupItem[index] = data2;
    				}).finally(function(){
    					$scope.loading = false;
    				});
    			})
    }
});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});
