<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container-fluid" style="width: 90%; margin: auto">
			<h2>Tài khoản Facebook</h2>
			
			<div class="row" ng-controller="load">
					<div class="col-xs-1 col-md-1 col-sm-1 column">#</div>
			 		<div class="col-xs-3 col-md-3 col-sm-3 column">Email</div>
					<div class="col-xs-3 col-md-3 col-sm-3 column">Mật khẩu</div>
					<div class="col-xs-3 col-md-3 col-sm-3 column">Số điện thoại</div>
					<div class="col-xs-2 col-md-2 col-sm-2 column">Nhóm nick</div>
			</div>
			<div class="row" ng-controller="load">
					<div class="col-xs-1 col-md-1 col-sm-1 column">{{student.email}}</div>
			 		<div class="col-xs-3 col-md-3 col-sm-3 column">{{student.password}}</div>
					<div class="col-xs-3 col-md-3 col-sm-3 column">.col-xs-6 .col-md-4</div>
					<div class="col-xs-3 col-md-3 col-sm-3 column">.col-xs-6 .col-md-4</div>
					<div class="col-xs-2 col-md-2 col-sm-2 column">.col-xs-6 .col-md-4</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>