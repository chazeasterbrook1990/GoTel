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
		<title>Search Results</title>
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
			<h1 class="text-center">Search Results</h1>
			<p class="text-center font-italic"><c:out value="${hotels.size()}"/> hotels available based on your search</p>
			<div class="search-table">
				<table class="table table-striped table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>Hotel</th>
							<th>Price</th>
							<th>Review</th>
							<th>Rating</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="results" items="${hotels}">
							<tr>
								<td><a href="hotel/${results.get('hotel_id').toString().replace('\"', '')}/${arrival}/${departure}/details"><c:out value="${results.get('property').get('name').toString().replace('\"', '')}" /></a></td>
								<td>$<c:out value="${results.get('property').get('priceBreakdown').get('grossPrice').get('value').toString().replace('\"', '')}"/></td>
			   					<td> <c:out value="${results.get('property').get('reviewScoreWord').toString().replace('\"', '')}" /></td>
								<td><c:out value="${results.get('property').get('reviewScore').toString().replace('\"', '')}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</div>
	</body>
</html>