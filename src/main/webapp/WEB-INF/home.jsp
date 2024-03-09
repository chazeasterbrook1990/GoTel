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
			<c:out value="${userName}"></c:out>
			!
		</p>
	</div>
	<div class="container custom-container text-center">
		<form:form action="/search" method="get" modelAttribute="searchForm">
			<input type="text" name="q" placeholder="Search by Destination" />
			<input type="text" id="arrival" name="arrival"
				pattern="\d{4}-\d{2}-\d{2}" required
				placeholder="Arrival Date (yyyy-MM-dd)">
			<input type="text" id="departure" name="departure"
				pattern="\d{4}-\d{2}-\d{2}" required
				placeholder="Departure Date (yyyy-MM-dd)">
			<button type="submit" class="btn btn-primary fw-bold fs-5">Search</button>
		</form:form>
	</div>
	<h2>Your Bookings</h2>
	<table class="table table-striped table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>City</th>
				<th>Hotel</th>
				<th>Check-In</th>
				<th>Check-Out</th>
				<th>Update</th>
				<th>Cancel</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bookings" items="${bookings}">
				<tr>
					<td><c:out value="${bookings.city}"></c:out></td>
					<td><c:out value="${bookings.hotelName}"></c:out></td>
					<td><c:out value="${bookings.checkInDate}"></c:out></td>
					<td><c:out value="${bookings.checkOutDate}"></c:out></td>
					<td class="align-middle"><a
						href="/gotel/booking/${bookings.id}/edit" class="btn btn-primary">Edit</a></td>
					<td class="align-middle"><form:form
							action="/gotel/booking/${bookings.id}/delete" method="POST"
							modelAttribute="bookingDelete">
							<button type="submit" class="btn btn-warning">Cancel</button>
						</form:form></td>
				</tr>
			</c:forEach>
</body>
</html>