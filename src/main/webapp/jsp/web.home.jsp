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
			<div class="panel panel-default panel-table">
				<div class="panel-heading">
					<h3 class="panel-title">Tài khoản Facebook</h3>
				</div>
				<div class="panel-body" style="padding: 0 15px;">
					<div class="row">
						<div class="col-xs-1 col-md-1 col-sm-1 cell header">#</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell header">Email</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell header">Mật khẩu</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell header">Số điện
							thoại</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell header">Nhóm
							nick</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell header">
							<span class="glyphicon glyphicon-option-horizontal"
								aria-hidden="true"></span>
						</div>
					</div>
					<div class="row" style="clear: both">
						<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-3 col-md-3 col-sm-3 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell">
							<input class="form-control input-sm" type="text">
						</div>
						<div class="col-xs-1 col-md-1 col-sm-1 cell header" align="center">
							<a class="search" href="#"><span
								class="glyphicon glyphicon-search" ng-click="submitFilter()"></span></a>
						</div>
					</div>
					<form ng-submit="submitUser(newUser)">
						<div class="row" style="clear: both">
							<div class="col-xs-1 col-md-1 col-sm-1 cell"></div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.username">
							</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.password">
							</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">
								<input class="form-control input-sm" type="text"
									ng-model="newUser.email">
							</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell">
								<input class="form-control input-sm" type="text">
							</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell header"
								align="center">
								<button type="button" class="btn btn-sm btn-success"
									style="margin: auto">Thêm mới</button>
							</div>
						</div>
					</form>
					<div ng-repeat="account in accounts">
						<div class="row" ng-repeat="(key, val) in account">
							<div class="col-xs-1 col-md-1 col-sm-1 cell">{{val.id}}</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">{{val.username}}</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">{{val.password}}</div>
							<div class="col-xs-3 col-md-3 col-sm-3 cell">{{val.phone}}</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell">{{val.group}}</div>
							<div class="col-xs-1 col-md-1 col-sm-1 cell" align="center">
								<a class="btn btn-default" style="margin-right: 10px;"><i
									class="fa fa-2 fa-pencil"></i></a><a class="btn btn-danger"><i
									class="fa fa-2 fa-trash"></i></a>
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