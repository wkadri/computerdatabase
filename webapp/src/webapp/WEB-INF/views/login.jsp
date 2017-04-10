<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/font-awesome.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet"
	media="screen">
</head>
<body>
	
	<section id="main">
	<div class="container">
	<h1>Login</h1>
		<div class="row">
			<form:form commandName="login" modelAttribute="user" method="POST">
				<fieldset>
					<spring:bind path="login">
						<div class="form-group">
							<label for="login">First Name</label> <input type="text"
								class="form-control" id="login" name="login"
								placeholder="Login">
						</div>
					</spring:bind>

					
					<spring:bind path="password">
						<div class="form-group">
							<label for="password">Password</label> <input type="text"
								class="form-control" id="password" name="password"
								placeholder="Password" >
						</div>
					</spring:bind>
					
				</fieldset>
				<input id="Connect" type="submit" class="btn btn-primary">
			</form:form>
		</div>
	</div>
</body>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/dashboard.js"/>"></script>
<script src="<c:url value="/resources/js/addComputer.js"/>"></script>
</html>