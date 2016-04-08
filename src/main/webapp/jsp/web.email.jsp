<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="width: 90%; margin: auto"
			ng-controller="loadData">
			<div class="alert-wrapper fade in">
				<div class="alert-box message" ng-show="formMessage">
					{{formMessage}}</div>
				<div class="alert-box error" ng-show="formError">
					{{formError}}</div>
			</div>
			<div class="panel panel-default panel-table">
				<div class="panel-heading">
					<h3 class="panel-title">
						Quản lý mail <i ng-if="loading == true"
							class="fa fa-spinner fa-spin"></i>
					</h3>
				</div>
				<div class="panel-body" style="padding: 15px 15px;">
					<table class="table table-striped table-bordered table-list">
						<thead>
							<tr>
								<th>#</th>
								<th>Tên đăng nhập</th>
								<th>Mật khẩu</th>
								<th>Email xác minh </th>
								<th>Số điện thoại</th>
								<th>Ngày sinh</th>
								<th style="text-align:center"><span class="fa fa-align-justify"></span></th>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td><input class="form-control input-sm" type="text"
									ng-model="filter.username"></td>
								<td><input class="form-control input-sm" type="text"
									ng-model="filter.password"></td>
								<td><input class="form-control input-sm" type="text"
									ng-model="filter.email"></td>
								<td><input class="form-control input-sm" type="text"
									ng-model="filter.phone"></td>
								<td><input class="form-control input-sm createDay"
									type="text" ng-model="filter.birthday" my-search></td>
								<td>
									<form ng-submit="createUser(newUser)">
										<tr>
											<td></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.username"></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.password"></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.retrieveEmail"></td>
											<td><input class="form-control input-sm" type="text"
												ng-model="newUser.phone"></td>
											<td><input class="form-control input-sm createDay"
												type="text" ng-model="newUser.birthday" my-search></td>
											<td>
												<button type="submit" class="btn btn-sm btn-success"
													style="margin: auto">Thêm mới</button>
											</td>
										</tr>
									</form>
							<tr ng-repeat="(key, account) in accounts">
								<td>{{$index+(curPage-1) * pageSize+1}}</td>
								<td><a class="edit" href="#"
									editable-text="account.username"
									onaftersave="updateUser(account)" e-style="width: 100%">{{account.username}}</a>
								</td>
								<td><a class="edit" href="#"
									editable-text="account.password"
									onaftersave="updateUser(account)" e-style="width: 100%">{{account.password}}</a>
								</td>
								<td><a class="edit" href="#"
									editable-email="account.retrieveEmail"
									onaftersave="updateUser(account)" e-style="width: 100%">{{account.retrieveEmail}}</a>
								</td>
								<td><a class="edit" href="#" editable-tel="account.phone"
									onaftersave="updateUser(account)" e-style="width: 100%">{{account.phone}}</a>
								</td>
								<td><a class="edit" href="#" e-placeholder="dd-mm-yyyy"
									editable-text="account.birthday"
									onaftersave="updateUser(account)" e-style="width: 100%">{{account.birthday}}</a>
								</td>
								<td><a data-toggle="modal" data-target="#userInfo-{{key}}"
									class="btn btn-default" style="margin-right: 5px;"><i
										class="fa fa-2 fa-ellipsis-h"></i></a><a
									ng-click="delUser(account)" class="btn btn-danger"><i
										class="fa fa-2 fa-trash"></i></a></td>
								<div class="modal fade" id="userInfo-{{key}}" role="dialog">
									<div class="modal-dialog">
										<div class="modal-content" style="border-radius: 1px">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Câu hỏi bảo mật</h4>
											</div>
											<div class="modal-body">
												<button class="btn btn-default"
													ng-click="addQuestion(account)" style="margin-bottom: 5px;">
													<i class="fa fa-2 fa-plus"></i>
												</button>
												<div ng-repeat="(index, q) in account.questions">
													<button class="btn btn-default"
														ng-click="removeQuestion(account, index)">
														<i class="fa fa-2 fa-minus"></i>
													</button>
													<br> <span>Câu hỏi:</span>
													<div style="width: 100%; height: 34px">
														<a class="edit" href="#" editable-text="q.question"
															onaftersave="updateUser(account)" e-style="width: 100%">{{q.question}}</a>
													</div>
													<span>Câu trả lời:</span>
													<div style="width: 100%; height: 34px">
														<a class="edit" href="#" editable-text="q.answer"
															onaftersave="updateUser(account)" e-style="width: 100%">{{q.answer}}</a>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>

									</div>
								</div>
						</tbody>
					</table>
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
			<script type="text/javascript"
				src="<c:url value='/resources/js/email-app.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>