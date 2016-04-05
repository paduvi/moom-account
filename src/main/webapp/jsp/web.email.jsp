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
					<h3 class="panel-title">Quản lý mail <i ng-if="loading == true" class="fa fa-spinner fa-spin"></i></h3>
				</div>
				<div class="panel-body" style="padding: 0 15px;">
					<div class="row">
						<div class="col-xs-1 col-md-1 col-sm-1 header">#</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Tên đăng nhập</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Mật khẩu</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Email xác
							minh</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Số điện thoại</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Ngày sinh</div>
						<div class="col-xs-1 col-md-1 col-sm-1 header" style="text-align:center">
							<span class="fa fa-align-justify"></span>
						</div>
					</div>
					<div class="row" style="clear: both">
						<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text"
								ng-model="filter.username">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text"
								ng-model="filter.password">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text"
								ng-model="filter.email">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text"
								ng-model="filter.phone">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm createDay" type="text" ng-model="filter.birthday" my-search>
						</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell" align="center">
						</div>
					</div>
					<form ng-submit="createUser(newUser)">
						<div class="row" style="clear: both">
							<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.username">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.password">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.retrieveEmail">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text" ng-model="newUser.phone">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm createDay" type="text" ng-model="newUser.birthday" my-search>
							</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell"
								align="center">
								<button type="submit" class="btn btn-sm btn-success"
									style="margin: auto">Thêm mới</button>
							</div>
						</div>
					</form>
					<div class="row"
						ng-repeat="(key, account) in accounts">
						<div class="col-xs-1 col-md-1 col-sm-1 cell">{{$index+(curPage-1)
							* pageSize+1}}</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a class="edit" href="#" editable-text="account.username"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.username}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a class="edit" href="#" editable-text="account.password"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.password}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a class="edit" href="#" editable-email="account.retrieveEmail"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.retrieveEmail}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a class="edit" href="#" editable-tel="account.phone"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.phone}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a class="edit" href="#" e-placeholder="dd-mm-yyyy" editable-text="account.birthday"
								onaftersave="updateUser(account)" e-style="width: 100%">{{account.birthday}}</a>
						</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell" align="center">
							<a data-toggle="modal" data-target="#userInfo-{{key}}"
								class="btn btn-default" style="margin-right: 5px;"><i
								class="fa fa-2 fa-ellipsis-h"></i></a><a ng-click="delUser(account)"
								class="btn btn-danger"><i class="fa fa-2 fa-trash"></i></a>
						</div>
						<div class="modal fade" id="userInfo-{{key}}" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content" style="border-radius: 1px">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Câu hỏi bảo mật</h4>
									</div>
									<div class="modal-body">
										<button class="btn btn-default"
											ng-click="addQuestion(account)">
											<i class="fa fa-2 fa-plus"></i>
										</button>
										<div ng-repeat="(index, q) in account.questions">
											<button class="btn btn-default"
												ng-click="removeQuestion(account, index)">
												<i class="fa fa-2 fa-minus"></i>
											</button>
											
											<span>Câu hỏi:</span>
											<div style="width:100%; height: 34px">
												<a class="edit" href="#" editable-text="q.question"
													onaftersave="updateUser(account)" e-style="width: 100%">{{q.question}}</a>
											</div>
											<span>Câu trả lời:</span>
											<div style="width:100%; height: 34px">
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
					</div>
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

	</tiles:putAttribute>
</tiles:insertDefinition>