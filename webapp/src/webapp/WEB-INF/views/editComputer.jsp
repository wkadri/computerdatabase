<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
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
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="computers"> Application - Computer
			Database </a>
	</div>
	</header>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id: ${computer.id}</div>
				<h1>Edit Computer</h1>
				<form:form commandName="computer" modelAttribute="computer" method="POST">
					<fieldset>
					  <spring:bind path="computerName">
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" id="computerName" name="name"
								placeholder="Computer name" value="${computer.name}">
						</div>
						   </spring:bind>
						   
                            <spring:bind path="introduced">
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								type="date" class="form-control" id="introduced"
								name="introduced" placeholder="Introduced date"
								value="${computer.introduced}">
						</div>
						   </spring:bind>
                            <spring:bind path="discontinuced">
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued"
								name="discontinued" placeholder="Discontinued date"
								value="${computer.discontinued}">
						</div>
						   </spring:bind>
						                               <spring:bind path="companyId">
						<div class="form-group">
							<label>Company</label>
							<form:select class="form-control" path="company.id">
								<form:options items="${ companies }" itemValue="id"
									itemLabel="name" />
							</form:select>

						</div>
 </spring:bind>
 
					</fieldset>
				
                        <div class="actions pull-right">
                            <input id="editComputerButton" type="submit" value="<spring:message code="cdb.form.add"/>" class="btn btn-primary">
                            <spring:message code="cdb.form.or"/>
                            <a href="springcdb" class="btn btn-default"><spring:message code="cdb.form.cancel"/></a>
                        </div>
				</form:form>
			</div>
		</div>
	</div>
	</section>
</body>
<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/dashboard.js"/>"></script>
<script src="<c:url value="/resources/js/addComputer.js"/>"></script>
</html>