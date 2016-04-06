<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%><%@ page
	contentType="text/html;charset=UTF-8"%>

<tiles:insertDefinition name="homeTemplate">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="well bs-component">
				<form class="form-horizontal">
					<fieldset>
						<legend>Thông tin tài khoản</legend>
							<div class="form-group is-empty">
							<label class="col-md-2 control-label">Tên</label>

							<div class="col-lg-4 col-md-5">
								<input class="form-control" id="inputFullName" placeholder="Tên"
									type="text">
							</div>
							<span class="material-input"></span>
						</div>
						<div class="form-group is-empty">
							<label class="col-md-2 control-label">Tài khoản</label>

							<div class="col-lg-4 col-md-5">
								<input class="form-control" id="inputUsername" placeholder="tài khoản"
									type="text">
							</div>
							<span class="material-input"></span>
						</div>
						<div class="form-group is-empty">
							<label class="col-md-2 control-label">Email</label>

							<div class="col-lg-4 col-md-5">
								<input class="form-control" id="inputEmail" placeholder="Email"
									type="email">
							</div>
							<span class="material-input"></span>
						</div>
						<div class="form-group is-empty">
							<label class="col-md-2 control-label">Mật khẩu</label>

							<div class="col-lg-4 col-md-5">
								<input class="form-control" id="inputPassword"
									placeholder="Mật khẩu" type="password">

							</div>
							<span class="material-input"></span>
						</div>
						<div class="form-group">
							<div class="col-lg-4 col-md-5 col-md-offset-2">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="button" class="btn btn-default">Cancel</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>