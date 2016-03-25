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
			<div class="alert-wrapper">
				<div class="alert-box message" ng-show="formMessage">
					{{formMessage}}</div>
				<div class="alert-box error" ng-show="formError">
					{{formError}}</div>
			</div>
			<div class="panel panel-default panel-table">
				<div class="panel-heading">
					<h3 class="panel-title">Tài khoản Facebook</h3>
				</div>
				<div class="panel-body" style="padding: 0 15px;">
					<div class="row">
						<div class="col-xs-1 col-md-1 col-sm-1 header">#</div>
						<div class="col-xs-3 col-md-3 col-sm-3 header">Tên</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Mật khẩu</div>
						<div class="col-xs-3 col-md-3 col-sm-3 header">Email</div>
						<div class="col-xs-2 col-md-2 col-sm-2 header">Số điện thoại</div>
						<div class="col-xs-1 col-md-1 col-sm-1 header">
							<span class="glyphicon glyphicon-option-horizontal"
								aria-hidden="true"></span>
						</div>
					</div>
					<div class="row" style="clear: both">
						<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell header" align="center">
							<a class="search" href="#"><span
								class="glyphicon glyphicon-search" ng-click="submitFilter()"></span></a>
						</div>
					</div>
					<form ng-submit="createUser(newUser)">
						<div class="row" style="clear: both">
							<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.username">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.password">
							</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.retrieveEmail">
							</div>
							<div class="col-xs-2 col-md-2 col-sm-2 cell">
								<input class="form-control input-sm" type="text">
							</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell header"
								align="center">
								<button type="submit" class="btn btn-sm btn-success"
									style="margin: auto">Thêm mới</button>
							</div>
						</div>
					</form>
					<div class="row" ng-repeat="(key, val) in accounts">
						<div class="col-xs-1 col-md-1 col-sm-1 cell">{{val.id}}</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<a href="#" editable-text="val.username"
								onaftersave="updateUser(val)">{{val.username}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">
							<a href="#" editable-text="val.password"
								onaftersave="updateUser(val)">{{val.password}}</a>
						</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<a href="#" editable-email="val.retrieveEmail"
								onaftersave="updateUser(val)">{{val.retrieveEmail}}</a>
						</div>
						<div class="col-xs-2 col-md-2 col-sm-2 cell">{{val.phone}}</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell" align="center">
							<a data-toggle="modal" data-target="#userInfo-{{key}}"
								class="btn btn-default" style="margin-right: 5px;"><i
								class="fa fa-2 fa-ellipsis-h"></i></a><a ng-click="delUser(val)"
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
										<button class="btn btn-default" ng-click="addQuestion(val)">
											<i class="fa fa-2 fa-plus"></i>
										</button>
										<div ng-repeat="(index, q) in val.questions">
											<button class="btn btn-default" ng-click="removeQuestion(val, index)">
												<i class="fa fa-2 fa-minus"></i>
											</button>
											<span>Câu hỏi:</span> <input class="form-control input-sm"
												type="text" ng-model="q.question" style="">
											<button class="btn btn-success" ng-click="updateUser(val)">Save</button>
											<span>Câu trả lời:</span> <input
												class="form-control input-sm" type="text"
												ng-model="q.answer">
											<button class="btn btn-success" ng-click="updateUser(val)">Save</button>
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
					<ul class="pagination" style="margin: 0px">
						<li><a href="#">1</a></li>
					</ul>
				</div>
			</div>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>