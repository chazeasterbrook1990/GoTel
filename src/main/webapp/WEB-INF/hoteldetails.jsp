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
			<li><button class="back-btn" onclick="history.back()">Back To Search</button></li>
			<li><a href="/logout">Logout</a></li>
		</ul>
	</div>
	<div class="d-flex flex-column container custom-container">
	<h1>
		<c:out
			value="${details.get('hotel_name').toString().replace('\"', '')}"></c:out>
	</h1>
	<p>
		<a
			href="${details.get('url').toString().replace('\"', '')}">Link to Hotel Website</a>
			</p>
			<p>
				Address:
				<c:out value="${details.get('address').toString().replace('\"', '')}"></c:out>
	</p>
	<p>
		City:
		<c:out
			value="${details.get('city').toString().replace('\"', '')}"></c:out>
			</p>
			<p>
				Postal Code:
				<c:out
					value="${details.get('zip').toString().replace('\"', '')}"></c:out>
	</p>
	<p>
		Distance to City Center:
		<c:out
			value="${details.get('distance_to_cc').toString().replace('\"', '')}"></c:out> KM
			</p>
			<p>
				Rooms Available During Your Stay:
				<c:out
					value="${details.get('available_rooms').toString().replace('\"', '')}"></c:out>
	</p>
	<p>Facilities:</p> <c:forEach var="facility"
		items="${details.get('facilities_block').get('facilities').iterator()}">
		<li><c:out
				value="${facility.get('name').toString().replace('\"', '')}"></c:out>
			</li>
			</c:forEach>
			<p>Important Information for Your Stay: </p>
			<c:forEach var="info" items="${importantInfo.iterator()}">
   		<li><c:out value="${info.get('phrase').toString().replace('\"', '')}"></c:out>
		</li>
	</c:forEach>
	<div class="">
		<form:form
			action="/gotel/create-booking/${details.get('hotel_name').toString().replace('\"', '').replace('/', '')}/${details.get('hotel_id').toString().replace('\"', '')}/${details.get('city').toString().replace('\"', '')}"
			method="POST" modelAttribute="newBooking" class="book">
			<form:errors path="checkInDate" class="errors"></form:errors>
			<form:input class = "checkin" path="checkInDate" type="text"
				placeholder="Check-In (yyyy-MM-dd)" value="${arrival}"></form:input>
			<form:errors path="checkOutDate" class="errors"></form:errors>
			<form:input class = "checkout" path="checkOutDate" type="text"
				placeholder="Check-Out (yyyy-MM-dd)" value="${departure}"></form:input>
			<input type="submit" value="Create Booking"
				class="bookbtn">
		</form:form>
	</div>
	</div>
	</body>
</html>