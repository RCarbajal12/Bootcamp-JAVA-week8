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
<title>Water Bnb</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col text-end">
				<c:choose>
					<c:when test="${usuario != null }">
						<a href="/"> Home</a>  | 
						<a href="/logout"> Logout</a>
					</c:when>
					<c:otherwise>
						<a href="/guest/signin"> Sign in / Sign up</a>

					</c:otherwise>
				</c:choose>

			</div>
		</div>
		<div class="row justify-content-center">
			<form action="/search" method="post">
				<label> Find your pool!</label>
				<div class="form-group row mb-3">
					<div class="col-sm-10">
						<input type="text" class="form-control" name="location" />
					</div>
					<div class="col-sm-2">
						<button class="btn btn-outline-primary">Search</button>
					</div>
				</div>
			</form>

		</div>
		<div class="row">
			<table class="table">
				<thead>
					<th>Addres</th>
					<th>Pool Size</th>
					<th>Cost per night</th>
					<th>Details</th>
				</thead>
				<tbody>
					<c:forEach items="${ pools}" var="lugar">
						<tr>
							<td><c:out value="${lugar.direccion }"></c:out></td>
							<td><c:out value="${lugar.poolsize }"></c:out></td>
							<td><c:out value="${lugar.costo }"></c:out></td>
							<td><a href="/pools/${lugar.id }"> <c:out
										value="${lugar.rating }"></c:out> - See more
							</a></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>

		</div>
	</div>

</body>
</html>