<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<!-- 
<link
	href="http://vitalets.github.io/angular-xeditable/starter/angular-xeditable/css/xeditable.css"
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

<script
	src="http://vitalets.github.io/angular-xeditable/starter/angular-xeditable/js/xeditable.js"></script>
 -->
<link href="<c:url value='/resources/css/font-awesome.min.css'/>" rel="stylesheet">
<!-- <link href="resources/css/bootstrap.min.css" rel="stylesheet"> -->
<link href="<c:url value='/resources/css/xeditable.css'/>" rel="stylesheet">
<link rel="stylesheet"
 href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<link href="<c:url value='/resources/css/style.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/resources/js/angular.min.js'/>"></script>
<script	src="<c:url value='/resources/js/xeditable.js'/>"></script>
<script type="text/javascript"
 src="<c:url value='/resources/js/ui-bootstrap-tpls-0.14.2.min.js'/>"></script>
<script
 src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/email-app.js'/>"></script>
</head>
<body ng-app="myApp">
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</body>
</html>