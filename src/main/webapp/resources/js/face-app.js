var app = angular.module("myApp", [ "xeditable", "ui.bootstrap", "ngDraggable" ]);

app.controller("loadData", function($scope, $http, $timeout) {
	var config = {
		headers : {
			'Accept' : 'application/json'
		}
	};

	$scope.loading = false;
	$scope.groupLoading = false;
	$scope.curPage = 1;
	$scope.pageSize = 15;
	$scope.numPages = 10;
	$scope.groupPageSize =5;
	$scope.groups = [];

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
		return $http.post('/faccount/update-account', val, config).success(
				function(data, status, headers, config) {
					if(data) $scope.formMessage = 'Cập nhật tài khoản thành công!';
					else $scope.formError = 'Không cập nhật được tài khoản!';
				}).error(function(data, status, headers, config) {
			$scope.formError = 'Không cập nhật được tài khoản!';
		});
	};
	
	/* drag & drop */
	$scope.onDropComplete = function(data, groupId, index, evt){
        addToGroup(data, groupId, index);
    }
	
    var inArray = function(array, obj) {
        var index = array.indexOf(obj);
    }
    
    var addToGroup = function(val, groupId, index) {
    	$scope.groupLoading = true;
		$http.post('/group/add-to-group?group=' + groupId, val, config).success(
			function(data) {
				loadData();
		        $scope.groups[index].group.nAccounts++;
		        $scope.groups[index].accounts.push(val);
			}).error(function(){
				$scope.groupLoading = false;
			});
	}
    
    $scope.removeAccount = function(val, groupId, key, groupIndex) {
    	var r = confirm("Tài khoản sẽ được chuyển về trạng thái không thuộc nhóm nào. Bạn có chắc muốn loại tài khoản này khỏi nhóm?");
		if (r == false)
			return;
		$scope.groupLoading = true;
		$http.post('/group/remove-face?group=' + groupId, val, config).success(
			function(data) {
				loadData();
				$scope.groups[groupIndex].accounts.splice(key, 1);
				$scope.groups[groupIndex].group.nAccounts--;
				if(!$scope.groupFilter.email && !$scope.groupFilter.password && !$scope.groupFilter.phone && !$scope.groupFilter.gName)
					return;
				if($scope.groups[groupIndex].accounts.length == 0)
					$scope.groups.splice(groupIndex,1);
			}).finally(function(){
				$scope.groupLoading = false;
			});
	}
    
    $scope.groupFilter = {
			'email' : '',
			'password' : '',
			'phone' : '',
			'gName' : ''
	};
	
     var loadAccountHaveGroup = function() {
    	var groupData = {
    		email : $scope.groupFilter.email,
    		password : $scope.groupFilter.password,
    		phone : $scope.groupFilter.phone,
    		gName : $scope.groupFilter.gName
    	};
    	
    	var config2 = {
    			params : groupData,
    			headers : {
    				'Accept' : 'application/json'
    			}
    	};

    	$scope.groupLoading = true;
    	$http.get('/faccount/list-face-have-group?page=' + $scope.curPage, config2).success(
    			function(data) {
    				$scope.groupAccounts = data;
    				divide(data);
    				$http.get('/faccount/face-count-have-group',config2).success(function(data2) {
    					$scope.groupTotalItem = data2;
    				}).finally(function(){
    					$scope.groupLoading = false;
    				});
    			}).error(function(){
    				$scope.groupLoading = false;
    		});
    }
     
     var containId = function(input, id){
    	 angular.forEach(input, function(value, key) {
    		 if(value.group.id == id)
    			 return key;
    	 });
    	 return -1;
     }
    
    var divide = function(data) {
    	$scope.groups = [];
    	angular.forEach(data, function(value, key) {
    		var index = containId($scope.groups, value.group);
    		if(index == -1){
    			var temp = {};
    	    	$http.get('/group/load?id=' + value.group, config).success(
    	    			function(gData) {
    	    				temp.group = gData;
    	    			});
    	    	temp.accounts = [];
    	    	temp.accounts.push(value);
    	    	$scope.groups.push(temp);
    		} else
    			$scope.groups[index].accounts.push(value);
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
			loadDataGroups();
		}, delayTime); // delay 1000 ms
	}, true);

	$scope.pageChanged = function() {
		loadData();
	};
	
	$scope.groupCurPage = 1;
	
	$scope.groupPageChanged = function() {
		loadDataGroups();
	};
	
	var loadDataGroups = function(){
		if(!$scope.groupFilter.email && !$scope.groupFilter.password && !$scope.groupFilter.phone && !$scope.groupFilter.gName)
			loadGroups();
		else
			loadAccountHaveGroup();
	}
	
	var loadGroups = function(){
		$scope.groupLoading = true;
		$http.get('/group/list-group?page='+$scope.groupCurPage,config).success(function(data) {
			$scope.groups = [];
	    	angular.forEach(data, function(value, key) {
	    		var temp = {};
	    	    temp.group = value;
	    	    $http.get('/faccount/list-face-by-group?gId=' + value.id, config).success(
	    	    		function(fData) {
	    	 	    	    temp.accounts = fData;
	    	 	    	});
	    	    $scope.groups.push(temp);
	    	});
			$http.get('/group/count-group',config).success(function(data2) {
				$scope.groupTotalItem = data2;
			}).finally(function(){
				$scope.groupLoading = false;
			});
		}).finally(function(){
			$scope.groupLoading = false;
		});
	}
	
	$scope.createGroup = function(val) {
		$scope.groupLoading = true;
		$http.post('/group/create-group', val, config).success(
			function(data) {
				loadGroups();
				$scope.newGroup = {};
			}).finally(function(){
				$scope.loading = false;
			});
	}
	
});

app.run(function(editableOptions, editableThemes) {
	editableThemes.bs3.inputClass = 'input-sm';
	editableThemes.bs3.buttonsClass = 'btn-sm';
	editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2',
	// 'default'
});
