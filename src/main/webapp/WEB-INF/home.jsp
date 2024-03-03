<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<meta charset="UTF-8">
<title>GoTel Home</title>
</head>
<body>
	<div class="text-right">
            <a href="/logout" class="btn btn-primary fw-bold fs-5 mt-4 mx-2">Log Out</a>
        </div>
        <div class="fw-bold fs-5 mt-4">
            <p>Welcome, <c:out value="${userName}"></c:out>!</p>
        </div>
</body>
</html>