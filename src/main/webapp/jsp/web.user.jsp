<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container" style="width: 100%" ng-controller="loadData">
			<div class="alert-wrapper fade in">
				<div class="alert-box message" ng-show="formMessage">
					{{formMessage}}</div>
				<div class="alert-box error" ng-show="formError">
					{{formError}}</div>
			</div>
			<div class="row" style="width:90%; margin: auto">
				<div class="panel panel-default panel-table">
					<div class="panel-heading">
						<h3 class="panel-title">
							Tài khoản <i ng-if="loading == true"
								class="fa fa-spinner fa-spin"></i>
						</h3>
					</div>
					<div class="panel-body">
						<table class="table table-striped table-bordered table-list">
							<thead>
								<tr>
									<th>#</th>
									<th>Tên</th>
									<th>Tài khoản</th>
									<th>Mật khẩu</th>
									<th>Quyền</th>
									<th style="text-align: center"><span
										class="fa fa-align-justify"></span></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td><input class="form-control input-sm" type="text"
										ng-model="filter.username"></td>
									<td><input class="form-control input-sm" type="text"
										ng-model="filter.password"></td>
									<td><input class="form-control input-sm" type="text"
										ng-model="filter.fullname"></td>
									<td><input class="form-control input-sm" type="text"
										ng-model="filter.role"></td>
									<td></td>
								</tr>
								<tr>
									<form ng-submit="createUser(newUser)">
										<td></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="newUser.username"></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="newUser.password"></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="newUser.fullname"></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="newUser.role"></td>
										<td style="text-align: center"><button type="submit" class="btn btn-sm btn-success"
												style="margin: auto">Thêm mới</button></td>
									</form>
								</tr>
								<tr ng-repeat="(key, account) in accounts">
									<td align="center">{{$index+(curPage-1) * pageSize+1}}</td>
									<td><a class="edit" href="#" editable-text="account.username"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.username}}</a></td>
									<td><a class="edit" href="#" editable-text="account.password"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.password}}</a></td>
									<td><a class="edit" href="#" editable-text="account.fullname"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.fullname}}</a></td>
									<td><a class="edit" href="#" editable-select="account.role" e-ng-options="role.value as role.text for role in roles"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.role}}</a></td>
									<td><a ng-click="delUser(account)" class="btn btn-danger"><i
											class="fa fa-2 fa-trash"></i></a></td>
								</tr>
							</tbody>
						</table>

					</div>
					<div class="panel-footer">
						<div class="row" ng-show="accounts.length">
							<uib-pagination class="pull-right" total-items="totalItem"
								ng-model="curPage" max-size="numPages" class="pagination-md"
								items-per-page="pageSize" boundary-links="true"
								style="margin-right: 20px;" ng-change="pageChanged()"></uib-pagination>
						</div>
					</div>
				</div>

			</div>
		</div>
		<script type="text/javascript"
			src="<c:url value='/resources/js/user-app.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>