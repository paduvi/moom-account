<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container" ng-controller="loadData">
			<div class="container" style="width: 100%" ng-controller="loadData">
				<div class="alert-wrapper fade in">
					<div class="alert-box message" ng-show="formMessage">
						{{formMessage}}</div>
					<div class="alert-box error" ng-show="formError">
						{{formError}}</div>
				</div>
				<div class="well bs-component">
					<form class="form-horizontal">
						<fieldset>
							<legend>Thông tin tài khoản</legend>
							<div class="form-group">
								<label class="col-md-2 control-label">Tên</label>
								<div class="col-lg-4 col-md-5">
									<input class="form-control" ng-model="user.fullname"
										placeholder="Tên" type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Tài khoản</label>
								<div class="col-lg-4 col-md-5">
									<input class="form-control"
										placeholder="tài khoản" type="text" ng-model="user.username">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Mật khẩu</label>
								<div class="col-lg-4 col-md-5">
									<div class="input-group">
										<input class="form-control" ng-model="user.password"
											placeholder="Mật khẩu" type="{{inputType}}"> <a id="show"
											class="input-group-addon"
											style="background-color: white; cursor: pointer;"
											ng-click="showPassword()"><i class="{{showIcon}}"></i></a>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-lg-4 col-md-5 col-md-offset-2">
									<button type="submit" class="btn btn-primary"
										ng-click="updateUser(user)">Submit</button>
									<button type="button" class="btn btn-default">Cancel</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			<script type="text/javascript"
				src="<c:url value='/resources/js/user-app.js'/>"></script>
	</tiles:putAttribute>
</tiles:insertDefinition>