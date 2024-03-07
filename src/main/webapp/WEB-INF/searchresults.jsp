<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<meta charset="UTF-8">
<title>Search Results</title>
</head>
<body>
	<h1>Search Results</h1>
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
    	<td><a href="hotel/${results.get('hotel_id').toString().replace('\"', '')}/${arrival}/${departure}/details"><c:out value="${results.get('property').get('name').toString().replace('\"', '')}"/></a></td>
   		<td>$<c:out value="${results.get('property').get('priceBreakdown').get('grossPrice').get('value').toString().replace('\"', '')}"/></td>
   		<td><c:out value="${results.get('property').get('reviewScoreWord').toString().replace('\"', '')}"/></td>
   		<td><c:out value="${results.get('property').get('reviewScore').toString().replace('\"', '')}"/></td>
   		</tr>
    </c:forEach>
    </tbody>
</table>
    
</body>
</html>