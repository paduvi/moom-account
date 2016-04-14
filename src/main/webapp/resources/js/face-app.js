var app = angular.module("myApp", [ "xeditable", "ui.bootstrap", "ngDraggable" ]);

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
		
		$http.get('/faccount/list-face-none-group?page=' + $scope.curPage, config2).success(
			function(data) {
				$scope.accounts = data;
				$http.get('/faccount/face-count-none-group',config2).success(function(data2) {
					$scope.totalItem = data2;
				}).finally(function(){
					$scope.loading = false;
				});
			}).error(function(){
				$scope.loading = false;
		});
	}

	/* account filter */
	$scope.filter = {
			'email' : '',
			'password' : '',
			'phone' : '',
			'group'	: '',
	};

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
		return $http.post('/user/faccount/update-account', val, config).success(
				function(data, status, headers, config) {
					if(data) $scope.formMessage = 'Cập nhật tài khoản thành công!';
					else $scope.formError = 'Không cập nhật được tài khoản!';
				}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};
	
	/* drag & drop */
	$scope.onDropComplete = function(data, groupId, id, evt){
        addToGroup(data, groupId);
        $scope.loadGroupAccount(groupId, id);
    }
	
    $scope.onDragSuccess = function(data, groupId, id, evt){
    	 $scope.loadGroupAccount(groupId, id);
    }
    
    var inArray = function(array, obj) {
        var index = array.indexOf(obj);
    }
    
    var addToGroup = function(val, groupId) {
		$http.post('/group/add-to-group?group=' + groupId, val, config).success(
			function(data) {
				loadData();
			}).error(function(){
				$scope.loading = false;
			});
	}
    
	/* load list group */
/*
 * $scope.groupF = {'name' : ''};
 * 
 * var loadGroup = function(val) { if(val != null) val = val.name;
 * $http.get('/group/list-group?name=' + val, config).success( function(data) {
 * $scope.groups = data; }).error(function(){ $scope.loading = false; }); }
 * loadGroup(); var tempGFilter = {}, filterGTextTimeout, delayTime = 0;
 * $scope.$watch('groupF', function(val) { if (filterGroupTextTimeout){
 * $timeout.cancel(filterGTextTimeout); delayTime = 1000; }
 * 
 * tempGFilter = val; filterGTextTimeout = $timeout(function() { $scope.groupF =
 * tempGFilter; loadGroup($scope.groupF); }, delayTime); // delay 1000 ms },
 * true);
 */
    
    /* load list group account */
    
   /*
	 * $scope.loadGroupAccount = function(groupId, index) { var groupData = {
	 * email : $scope.groupFilter.email, password : $scope.groupFilter.password,
	 * phone : $scope.groupFilter.phone };
	 * 
	 * var config2 = { params : groupData, headers : { 'Accept' :
	 * 'application/json' } };
	 * 
	 * $http.get("/faccount/list-face-by-group?&gId=" + groupId,
	 * config2).success( function(data) { $scope.group1[index] = data; }) }
	 */
	
    $scope.groupFilter = {
			'email' : '',
			'password' : '',
			'phone' : '',
			'group' : ''
	};
	
     var loadAccountHaveGroup = function() {
    	var groupData = {
    		email : $scope.groupFilter.email,
    		password : $scope.groupFilter.password,
    		phone : $scope.groupFilter.phone
    	};
    	
    	var config2 = {
    			params : groupData,
    			headers : {
    				'Accept' : 'application/json'
    			}
    	};

    	$http.get('/faccount/list-face-have-group?page=' + $scope.curPage, config2).success(
    			function(data) {
    				$scope.groupAccounts = data;
    				test(data);
    				$http.get('/faccount/face-count-have-group',config2).success(function(data2) {
    					$scope.groupTotalItem = data2;
    				}).finally(function(){
    					$scope.loading = false;
    				});
    			}).error(function(){
    				$scope.loading = false;
    		});
    }
    
    var test = function(data) {
    	$scope.group1 = [];
    	angular.forEach(data, function(value, key) {
    		if($scope.group1[value.group] === undefined) {
    			$scope.group1[value.group] = [];
    		}
    		$scope.group1[value.group].push(value);
    	});
    }
    
    var tempGroupFilter = {}, filterGroupTextTimeout, delayTime = 0;
	$scope.$watch('groupFilter', function(val) {
		if (filterGroupTextTimeout){
			$timeout.cancel(filterGroupTextTimeout);
			delayTime = 1000;
		}

		tempGroupFilter = val;
		filterGroupTextTimeout = $timeout(function() {
			$scope.groupFilter = tempGroupFilter;
			 loadAccountHaveGroup();
		}, delayTime); // delay 1000 ms
	}, true);

	$scope.pageChanged = function() {
		loadData();
	};
	
	$scope.groupCurPage = 1;
	
	$scope.groupPageChanged = function() {
		loadAccountHaveGroup();
	};
	
	$scope.createGroup = function(val) {
		$http.post('/group/create-group', val, config).success(
			function(data) {
				$scope.group1[index] = data;
			});
	}
	
	$scope.createGroup = function(newGroup) {
		newGroup = {
			'name' : $scope.newGroup.name,
		};
		
		$http.post("/group/create-group", newGroup, config).success(
				function(data, status, headers, config) {
				}).error(function(data, status, headers, config) {
		});
	};
});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});
