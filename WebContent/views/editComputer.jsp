<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="Servlet"> Application - Computer
			Database </a>
	</div>
	</header>
	<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<div class="label label-default pull-right">id:
					${computeur.id}</div>
				<h1>Edit Computer</h1>

				<form method="POST">
					<input type="hidden" name="id" value="${computeur.id}" id="id" />
					<!-- TODO: Change this value with the computer id -->
					<fieldset>
						<div class="form-group">
							<label for="computerName">Computer name</label> <input
								type="text" class="form-control" name="computerName"
								id="computerName" placeholder="Computer name"
								value="${computeur.name}">
						</div>
						<div class="form-group">
							<label for="introduced">Introduced date</label> <input
								name="introduced" type="date" class="form-control"
								id="introduced" placeholder="Introduced date"
								value="${computeur.introduced}">
						</div>
						<div class="form-group">
							<label for="discontinued">Discontinued date</label> <input
								type="date" class="form-control" id="discontinued"
								placeholder="Discontinued date">
						</div>
						<div class="form-group">
							<label for="companyId">Company</label> <select
								class="form-control" id="companyId" name="companyID">
								<option selected="selected" value="${computeur.company.id}">${computeur.company.name}</option>
								<c:forEach items="${companies}" var="company">
									<option value="${company.id}">${company.name}</option>
								</c:forEach>
							</select>

						</div>
					</fieldset>
					<div class="actions pull-right">
						<a href="Servlet"><input type="submit" name="action"
							value="Edit" class="btn btn-primary"></a>or <a href="Servlet"
							class="btn btn-default">Cancel</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	</section>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>
<script src="js/addComputer.js"></script>
</html>