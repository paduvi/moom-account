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
				<div class="col-xs-4 col-sm-4 col-md-4">
					<div class="input-group" style="margin-bottom: 10px;">
						<input class="form-control"
							aria-label="Text input with dropdown button" type="text">
						<span class="input-group-addon"><span class="fa fa-search"></span></span>
					</div>

					<div>
						<div class="panel-group" id="accordion">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										Nhóm {{group.name}} <span class="badge">{{groups.length}}</span>
									</h4>
								</div>
								<div class="panel-body"
									style="overflow-y: scroll; height: 700px">
									<div class="panel-group" id="accordion"
										ng-repeat="(id, group) in groups" style="margin-bottom: 10px;">
										<div class="panel panel-default" ng-drop="true"
											ng-drop-success="onDropComplete($data, group.id, id, $event)">
											<div class="panel-heading panel-header-group">
												<h4 class="panel-title" style="font-size: 13px">
													<a data-toggle="collapse" data-parent="#accordion"
														ng-href="#collapse-{{id}}"
														ng-click="loadGroupAccount(group.id, id)">Nhóm
														{{group.name}} <span class="badge">{{group.nAccounts}}</span>
													</a> <span class="pull-right">Lần chạy cuối:
														{{group.lastExecution | date:"dd/MM/yyyy 'lúc' h:mma"}}</span>
												</h4>
											</div>
											<div id="collapse-{{id}}" class="panel-collapse collapse">
												<div class="panel-body">
													<table
														class="table table-striped table-bordered table-list">
														<thead>
															<tr>
																<th>Email</th>
																<th>Tên</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td><input class="form-control input-sm"
																	type="text" ng-model="filter.email"></td>
																<td><input class="form-control input-sm"
																	type="text" ng-model="filter.password"></td>
															</tr>
															<tr ng-repeat="(key, groupAccount) in group1[id]"
																ng-drag="true" ng-drag-data="obj"
																ng-drag-success="onDragSuccess($data, group.id, id, $event)">
																<td>{{groupAccount.email}}</td>
																<td>{{groupAccount.password}}</td>
															</tr>
														</tbody>
														<div ng-show="group1.length">
															<uib-pagination class="pull-right"
																total-items="totalGroupItem[id]"
																ng-model="groupCurPage[id]" max-size="numPages"
																class="pagination-md" items-per-page="pageSize"
																boundary-links="true"
																ng-change="loadGroupAccount(group.id, id)"
																style="margin-top:2px;"></uib-pagination>
														</div>
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

				<div class="col-xs-8 col-sm-8 col-md-8">
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
										<td>{{account.email}}</td>
										<td>{{account.password}}</td>
										<td>{{account.phone}}</td>
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