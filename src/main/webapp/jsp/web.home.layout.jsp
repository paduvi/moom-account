<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link rel="shortcut icon"
	href="<c:url value='/resources/images/favicon.ico'/>"
	type="image/x-icon">
<link rel="icon" href="<c:url value='/resources/images/favicon.ico'/>"
	type="image/x-icon">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://getbootstrap.com/examples/sticky-footer/sticky-footer.css"
	rel="stylesheet">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>
<script
	src="https://getbootstrap.com/assets/js/ie10-viewport-bug-workaround.js"></script>
<style>
.cell {
	border: 1px solid #ddd;
	padding: 8px;
	height: 48px;
}

.header {
	font-weight: bold;
}
</style>
</head>
<body ng-app="myaccount">
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>