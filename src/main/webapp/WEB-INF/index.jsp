<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/style.css" />
<title>Login and Registration</title>
</head>
<body class = "page">
	<section>
		<div class="header">
			<img style="width: 300px" src="../images/logogotel.png">
		</div>
	</section>
	<section>
		<div class="nav">
			<ul class="main-nav">
				<li><a href="#">About</a></li>
				<li><a href="#">Feedback</a></li>
			</ul>
		</div>
	</section>
	<section>
		<div class="body-main">
			<div>
				<img src="<c:url value='/images/sunsetbeach.png' />" alt="background image" class="background-image">
				<h4>Experience America's Leading Hotel Resource!</h4>
			</div>	
			<div class="login">
				<form:form action="/login" method="POST" modelAttribute="newLogin">
					<div>
						<p class="login-text1">User Login</p>
						<form:label path="email"></form:label>
						<form:errors path="email" class="errors"></form:errors>
						<form:input class = "input1" path="email" placeholder="Email"></form:input>
					</div>
					<div>
						<form:label path="password"></form:label>
						<form:errors path="password" class="errors"></form:errors>
						<form:input class = "input1" path="password" type="password" 
							placeholder="Password"></form:input>
					</div>
					<input type="submit" value="Submit" class="custom-btn"/>
				</form:form>
			</div>
			
			<div class="register">
				<form:form action="/register" method="POST" modelAttribute="newUser">
					<div>
						<p class="login-text1">Register Today</p>
						<form:label  path="userName"></form:label>
						<form:errors path="userName" class="errors"></form:errors>
						<form:input class = "input1" path="userName" placeholder="User Name"></form:input>
					</div>
					<div>
						<form:label  path="email"></form:label>
						<form:errors path="email" class="errors"></form:errors>
						<form:input class = "input1" path="email" placeholder="Email"></form:input>
					</div>
					<div>
						<form:label  path="password"></form:label>
						<form:errors path="password" class="errors"></form:errors>
						<form:input class = "input1" path="password" type="password" 
							placeholder="Password"></form:input>
					</div>
					<div>
						<form:label  path="confirm"></form:label>
						<form:errors path="confirm" class="errors"></form:errors>
						<form:input class = "input1" path="confirm" type="password" 
							placeholder="Confirm Password"></form:input>
					</div>
					<input type="submit" value="Submit" class="custom-btn">
				</form:form>
			</div>
		</div>
	</section>
	<section>
		<div class="footer">
			<p class="footer-text">* Some hotels require you to cancel more than 24 hours before check-in. Details on site.
**OneKeyCash is not redeemable for cash and can only be used on Hotels.com, Expedia and Vrbo.
© 2024 GoTel.com is an Expedia Group company. All rights reserved.
GoTel.com and the GoTel.com logo are trademarks or registered trademarks of GoTel.com, LP in the United States. All other trademarks are the property of their respective owners.</p>
		</div>
	</section>
</body>
</html>