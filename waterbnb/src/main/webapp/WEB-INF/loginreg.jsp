<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formateo fechas (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- Formulario form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Registration</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>

	<div class="container mt-5">

		<div class="row mt-3">

			<div class="col-4 offset-md-1">
				<h2>Register!</h2>
				<div>
					<form:errors class="text-danger row mb-3" path="user.*" />
				</div>

				<form:form class="border p-3" method="POST" action="/registration"
					modelAttribute="user">
					<div class="form-group row mb-3">
						<form:label path="nombre">First Name:</form:label>
						<form:input type="text" path="nombre" class="form-control" />
					</div>
					<div class="form-group row mb-3">
						<form:label path="apellido">Last Name:</form:label>
						<form:input type="text" path="apellido" class="form-control" />
					</div>
					<div class="form-group row mb-3">
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" class="form-control"/>
					</div>

					<div class="form-group row mb-3">
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control"/>
					</div>
					<div class="form-group row mb-3">
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password path="passwordConfirmation" class="form-control"/>
					</div>
					<div class="form-group row mb-3 justify-content-end">
						<form:label path="rol">Rol:</form:label>
						<form:select path="rol" type="text" class="form-control">

							<form:option value="0"> Select</form:option>
							<form:option value="1"> Host</form:option>
							<form:option value="2">Guest</form:option>

						</form:select>

					</div>
					<input type="submit" value="Register!"  class="btn btn-primary" />
				</form:form>
				<p class="text-success">
					<c:out value="${registro }"></c:out>
				</p>
			</div>

			<div class="col-4 offset-md-1">
				<h2>Login!</h2>

				<div class="form-group row mb-3">
					<form:errors class="text-danger" path="login.*" />
				</div>

				<form:form class="border p-3" method="POST" action="/login"
					modelAttribute="login">
					<div class="form-group row mb-3">
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" class="form-control"/>
					</div>
					<div class="form-group row mb-3">
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control"/>
					</div>

					<input type="submit" value="Login!" class="btn btn-primary"/>
				</form:form>
			</div>
		</div>


	</div>
</body>
</html>