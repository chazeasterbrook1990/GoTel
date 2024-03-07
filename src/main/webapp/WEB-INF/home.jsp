<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<meta charset="UTF-8">
<title>GoTel Home</title>
</head>
<body>
	<div class="text-center mt-4">
		<img style="width: 300px" src="../images/logogotel.png">
	</div>
	<div class="text-right">
		<a href="/logout" class="btn btn-primary fw-bold fs-5 mt-4 mx-2">Log
			Out</a>
	</div>
	<div class="fw-bold fs-5 mt-4">
		<p>
			Welcome,
			<c:out value="${userName}"></c:out>!
		</p>
	</div>
	<div class="container custom-container text-center">
		<form:form action="/search" method="get" modelAttribute="searchForm">
			<input type="text" name="q" placeholder="Search for Hotels" />
			<input type="text" id="arrival" name="arrival"
				pattern="\d{4}-\d{2}-\d{2}" required placeholder="Arrival Date (yyyy-MM-dd)">
			<input type="text" id="departure" name="departure"
				pattern="\d{4}-\d{2}-\d{2}" required placeholder="Departure Date (yyyy-MM-dd)">
			<button type="submit" class="btn btn-primary fw-bold fs-5">Search</button>
		</form:form>
	</div>
</body>
</html>