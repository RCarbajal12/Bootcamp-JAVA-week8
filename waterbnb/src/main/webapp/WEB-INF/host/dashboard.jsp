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
<title>Water BnB</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container">
		<h1 class="my-4">
			Welcome,
			<c:out value="${usuario.nombre }"></c:out>
		</h1>

		<div class="row justify-content-between m-2">
			<div class="col-6">
				<p>Current listings</p>
			</div>
			<div class="col-6 d-flex justify-content-end">
				<a href="/logout" class="me-3">Logout</a> <a href="/search">Search</a>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col-12">
				<table class="table table-hover border">
					<thead>
						<tr>
							<th>Address</th>
							<th>Pool Size</th>
							<th>Cost per night</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usuario.eventosOrganizados}" var="evento">
							<tr>
								<td><c:out value="${evento.direccion}"></c:out></td>
								<td><c:out value="${evento.poolsize}"></c:out></td>
								<td><c:out value="${evento.costo}"></c:out></td>
								<td><a href="/host/pool/${evento.id}"> <c:out
											value="${evento.rating}"></c:out>/5 - edit
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">New Listing</div>
					<div class="card-body">
						<form:form method="POST" action="/new/pool"
							modelAttribute="newpool">
							<form:input type="hidden" path="organizador"
								value="${usuario.id}" />

							<div class="mb-3">
								<label for="direccion" class="form-label">Address:</label>
								<form:input type="text" path="direccion" class="form-control" />
								<form:errors class="text-danger" path="direccion" />
							</div>

							<div class="mb-3">
								<label for="description" class="form-label">Description:</label>
								<form:input type="text" path="description" class="form-control" />
								<form:errors class="text-danger" path="description" />
							</div>

							<div class="mb-3">
								<label for="costo" class="form-label">Cost per night:</label>
								<form:input type="number" path="costo" class="form-control" />
								<form:errors class="text-danger" path="costo" />
							</div>

							<div class="mb-3">
								<label for="poolsize" class="form-label">Pool Size:</label>
								<form:select path="poolsize" class="form-select">
									<form:option value="small">Small</form:option>
									<form:option value="medium">Medium</form:option>
									<form:option value="large">Large</form:option>
								</form:select>
								<form:errors class="text-danger" path="poolsize" />
							</div>

							<button type="submit" class="btn btn-primary">Add
								Listing!</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>