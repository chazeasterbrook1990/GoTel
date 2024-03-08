<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<meta charset="UTF-8">
<title><c:out
		value="${details.get('hotel_name').toString().replace('\"', '')}"></c:out></title>
</head>
<body>
	<div class="text-right">
		<a href="/gotel/home" class="btn btn-primary fw-bold fs-5 mt-4 mx-2">Start a New Search</a>
	</div>
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
	<p>Facilities: </p> <c:forEach var="facility"
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

	</body>
</html>