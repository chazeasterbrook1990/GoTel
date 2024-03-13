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
<body class = "page">
	<div class="header">
		<img style="width: 300px" src="../images/logogotel.png">
	</div>
	<div>
		<ul class="main-nav">
			<li><a href="/gotel/home">Home</a></li>
			<li><a href="/logout">Logout</a></li>
		</ul>
	</div>
	<div>
		<p class="intro-welcome">
			Welcome,
			<c:out value="${userName}"></c:out>
			!
		</p>
	</div>
	<div class="container1 custom-container text-center">
		<form:form action="/search" method="get" modelAttribute="searchForm">
			<input class = "city" type="text" name="q" placeholder="Destination: US City" />
			<input class = "checkin" type="text" id="arrival" name="arrival"
				pattern="\d{4}-\d{2}-\d{2}" required
				placeholder="Check In (YYYY-MM-DD)">
			<input class = "checkout" type="text" id="departure" name="departure"
				pattern="\d{4}-\d{2}-\d{2}" required
				placeholder="Check Out (YYYY-MM-DD)">
			<button class="searchbtn" type="submit" class="btn btn-primary">Search</button>
		</form:form>
	</div>
	<h2 class="intro-booking">Your Bookings</h2>
	<div class = "bookings-table">
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
						<td class="align-middle">
							<form:form
								action="/gotel/booking/${bookings.id}/delete" method="POST"
								modelAttribute="bookingDelete">
								<button type="submit" class="btn btn-warning">Cancel</button>
							</form:form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>