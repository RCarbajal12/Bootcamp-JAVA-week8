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
<title>WaterBnB</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5">
		<div class="row text-end">
			<div class="col">
				<a href="/guest/signin" class="text-decoration-none text-muted"> Sign in / Sign up</a>
			</div>
		</div>
		<div class="row justify-content-center mt-3">
		<div class="col text-center">
			<h3>Find places to swim and sleep on water bnb</h3>
			<form action="/search" method="post" class="mt-4">
				<div class="form-group row mb-3">
					<div class="col-sm-10">
						<input type="text" class="form-control" name="location" />
					</div>
					<div class="col-sm-1">
						<button class="btn btn-outline-primary">Search</button>
					</div>
				</div>
			</form>
		</div>

		</div>
	</div>

</body>
</html>