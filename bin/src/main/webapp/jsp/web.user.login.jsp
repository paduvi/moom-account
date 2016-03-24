<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>">
<link rel="shortcut icon"
	href="<c:url value='/resources/images/favicon.ico'/>"
	type="image/x-icon">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>"
	type="image/x-icon">
</head>

<body>

	<div class="wrapper">
		<div class="container">
			<h1>Welcome</h1>

			<form class="form" id="loginForm"
				action="<c:url value='j_spring_security_check' />" method='POST'>
				<input name="username" id="username" type="text"
					placeholder="Username"> <input name="password"
					id="password" type="password" placeholder="Password">
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>

		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>

	</div>
	<c:if test="${not empty error}">
		<div class="alert-wrapper">
			<div class="alert-box error">
				<span>error: </span>${error}
			</div>
		</div>
	</c:if>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	<script type="text/javascript">
		$('#loginForm').submit(function(e) {
			$('form, .alert-box').fadeOut(500);
			$('.wrapper').addClass('form-success');
			var form = this;
			e.preventDefault();
			setTimeout(function() {
				form.submit();
			}, 600); // in milliseconds
		});
	</script>
</body>
</html>
