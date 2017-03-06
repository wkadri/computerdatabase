<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
		<h1 id="homeTitle">${nbInstances}Computersfound</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" name="action" id="searchsubmit" value="Filter"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="ServletComputer">Add
					Computer</a> <a class="btn btn-default" id="editComputer"
					onclick="$.fn.toggleEditMode();">Edit</a>
			</div>
		</div>
	</div>

	<form id="deleteForm">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;"><input
						type="checkbox" id="selectall" name="action" value="delete" /> <span
						style="vertical-align: top;"> - <a id="deleteSelected"
							onclick="$.fn.deleteSelected();"> <i
								class="fa fa-trash-o fa-lg"></i>
						</a>
					</span></th>
					<th>Id</th>
					<th>Computer name</th>
					<th>Introduced date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued date</th>
					<!-- Table header for Company -->
					<th>Company</th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">

				<c:forEach items="${allComputers}" var="computer">
					<tr>
						<td class="editMode"><input type="checkbox" class="cb" /><a
							href="Servlet?computerID=${computer.id}" onclick=""></a></td>
						<td><a href="EditComputerServlet?id=${computer.id}"
							onclick="">${computer.id}</a></td>
						<td><a href="EditComputerServlet?id=${computer.id}"
							onclick="">${computer.name}</a></td>
						<td>${computer.introduced}</td>
						<td></td>
						<td>${computer.company.name}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>
	</section>

	<footer class="navbar-fixed-bottom">
	<div class="container text-center">
		<ul class="pagination">
			<li><a href="Servlet?id=${precedent}&&nb=${nb}"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
			<c:forEach items="${allPages}" var="page">
				<li><a href="Servlet?id=${page}&&nb=${nb}">${page+1}</a></li>

			</c:forEach>
			<li><a href="Servlet?id=${suivant}&&nb=${nb}" aria-label="Next">
					<span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<a href="Servlet?id=${id}&&nb=10">
				<button type="button" class="btn btn-default">10</button>
			</a> <a href="Servlet?id=${id}&&nb=50"><button type="button"
					class="btn btn-default">50</button></a> <a
				href="Servlet?id=${id}&&nb=100"><button type="button"
					class="btn btn-default">100</button></a>
		</div>
	</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>