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
			<h2 style="text-align: center; margin-bottom: 30px;">Quản lý tài
				khoản Facebook</h2>
			<div class="row">
				<div class="col-xs-6 col-sm-6 col-md-6">

					<div>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title"
										style="display: inline-block; width: 100%">
										<span>Nhóm</span>
										<i ng-if="groupLoading == true"
									class="fa fa-spinner fa-spin"></i>
										<div class="input-group pull-right" style="width: 60%;">
											<input class="form-control" ng-model="newGroup.name"
												type="text" placeholder="Điền tên nhóm cần tạo mới"> <span class="input-group-addon"><a
												href="#" ng-click="createGroup(newGroup)"><span
													class="fa fa-plus-circle"></span></a></span>
										</div>
									</h4>
								</div>
								<div class="panel-body"
									style="overflow-y: scroll; height: 1025px">
									<div class="row" style="margin-bottom: 10px">
										<div class="col-sm-12">
											<input class="form-control input-sm" type="text"
												ng-model="groupFilter.gName" placeholder="Tên nhóm">
										</div>
									</div>
									<div class="row" style="margin-bottom: 10px">
										<div class="col-sm-4">
											<input class="form-control input-sm" type="text"
												ng-model="groupFilter.email" placeholder="Email">
										</div>
										<div class="col-sm-4">
											<input class="form-control input-sm" type="text"
												ng-model="groupFilter.password" placeholder="Mật khẩu">
										</div>
										<div class="col-sm-4">
											<input class="form-control input-sm" type="text"
												ng-model="groupFilter.phone" placeholder="Số điện thoại">
										</div>
									</div>

									<div class="row" ng-show="accounts.length">
										<uib-pagination class="pull-right"
											total-items="groupTotalItem" ng-model="groupCurPage"
											max-size="numPages" class="pagination-md"
											items-per-page="groupPageSize" boundary-links="true"
											style="margin-right: 20px;" ng-change="groupPageChanged()"></uib-pagination>
									</div>

									<div class="panel-group" id="accordion"
										ng-repeat="(index, item) in groups"
										style="margin-bottom: 10px;">
										<div class="panel panel-default" ng-drop="true"
											ng-drop-success="onDropComplete($data, item.group.id, index, $event)">
											<div class="panel-heading panel-header-group">
												<a data-toggle="collapse" data-parent="#accordion"
													href="#collapse-{{index}}">
													<h4 class="panel-title" style="font-size: 13px">
														Nhóm {{item.group.name}} <span class="badge">{{item.group.nAccounts}}
														</span>
													</h4>
												</a>
											</div>
											<div id="collapse-{{index}}" class="panel-collapse collapse">
												<div class="panel-body">
													<table
														class="table table-striped table-bordered table-list">
														<thead>
															<tr>
																<th>Email</th>
																<th>Password</th>
																<th>Số điện thoại</th>
																<th></th>
															</tr>
														</thead>
														<tbody>
															<tr ng-repeat="(key, account) in item.accounts">
																<td><a class="edit" href="#"
																	editable-text="account.email"
																	onaftersave="updateUser(account)" e-style="width: 100%">{{account.email}}</a></td>
																<td><a class="edit" href="#"
																	editable-text="account.password"
																	onaftersave="updateUser(account)" e-style="width: 100%">{{account.password}}</a></td>
																<td><a class="edit" href="#"
																	editable-text="account.phone"
																	onaftersave="updateUser(account)" e-style="width: 100%">{{account.phone}}</a></td>
																<td><a
																	ng-click="removeAccount(account, item.group.id, key, index)"
																	class="btn btn-danger"><i class="fa fa-2 fa-trash"></i></a></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xs-6 col-sm-6 col-md-6">
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
										<th>Email</th>
										<th>Mật khẩu</th>
										<th>Số điện thoại</th>
										<th style="text-align: center"><span
											class="fa fa-align-justify"></span></th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="filter.email"></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="filter.password"></td>
										<td><input class="form-control input-sm" type="text"
											ng-model="filter.phone"></td>
										<td></td>
									</tr>
									<tr>
										<form ng-submit="createUser(newUser)">
											<td></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.email"></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.password"></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.phone"></td>
											<td><button type="submit" class="btn btn-sm btn-success"
													style="margin: auto">Thêm mới</button></td>
										</form>
									</tr>
									<tr ng-repeat="(key, account) in accounts" ng-drag="true"
										ng-drag-data="account" data-allow-transform="true">
										<td align="center">{{$index+(curPage-1) * pageSize+1}}</td>
										<td><a class="edit" href="#"
											editable-text="account.email"
											onaftersave="updateUser(account)" e-style="width: 100%">{{account.email}}</a></td>
										<td><a class="edit" href="#"
											editable-text="account.password"
											onaftersave="updateUser(account)" e-style="width: 100%">{{account.password}}</a></td>
										<td><a class="edit" href="#"
											editable-text="account.phone"
											onaftersave="updateUser(account)" e-style="width: 100%">{{account.phone}}</a></td>
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
		</div>
		<script type="text/javascript"
			src="<c:url value='/resources/js/face-app.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>