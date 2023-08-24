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
		<h1 class="text-center m-3 display-2">Edit Pool</h1>
		<div class="row">
			<div class="col text-end">
				<c:choose>
					<c:when test="${usuario != null }">
						<a href="/" class="m-2 btn btn-secondary">Home</a> |
                    <a href="/logout" class="m-2 btn btn-secondary">Logout</a>
					</c:when>
					<c:otherwise>
						<a href="/guest/signin" class="me-2">Sign in / Sign up</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-md-6">
				<p>
					<strong>Name:</strong>
					<c:out
						value="${pool.organizador.nombre} ${pool.organizador.apellido}"></c:out>
				</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Email:</strong>
					<c:out value="${pool.organizador.email}"></c:out>
				</p>

			</div>
		</div>

		<div class="container mt-4">
			<div class="row">
				<div class="col-md-6">
					<form:form method="POST" action="/host/pool/${pool.id}"
						modelAttribute="pool" class="row">
						<input type="hidden" name="_method" value="put" />
						<div class="col-md-6">
							<input type="hidden" name="organizador" value="${usuario.id}" />
							<div class="mb-3">
								<label for="direccion" class="form-label">Address:</label> <input
									type="text" class="form-control" id="direccion"
									name="direccion" />
								<form:errors class="text-danger" path="direccion" />
							</div>
							<div class="mb-3">
								<label for="description" class="form-label">Description:</label>
								<textarea class="form-control" id="description"
									name="description"></textarea>
								<form:errors class="text-danger" path="description" />
							</div>
							<button type="submit" class="btn btn-success">Save
								Changes</button>
						</div>
						<div class="col-md-6">
							<div class="mb-3">
								<label for="costo" class="form-label">Cost per night:</label> <input
									type="number" class="form-control" id="costo" name="costo" />
								<form:errors class="text-danger" path="costo" />
							</div>
							<div class="mb-3">
								<label for="poolsize" class="form-label">Pool Size:</label> <select
									class="form-select" id="poolsize" name="poolsize">
									<option value="small">Small</option>
									<option value="medium">Medium</option>
									<option value="large">Large</option>
								</select>
								<form:errors class="text-danger" path="poolsize" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<div class="row mt-4 ">
			<div class="col-3"></div>
		</div>

		<div class="row mt-3">
			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">
						<p>
							Reviews (
							<c:out value="${pool.rating }"></c:out>
							/5)
						</p>
					</div>

					<div class="card-body message-wall">
						<ul class="list-group message-list">
							<c:forEach items="${pool.comentarios }" var="comentario">
								<div class="col-12 mb-3">
									<p>
										<strong><c:out
												value="${comentario.autor.nombre} ${comentario.autor.apellido}"></c:out></strong>
									</p>
									<p>
										<strong>Rating:</strong>
										<c:out value="${comentario.rating }"></c:out>
									</p>
									<p>
										<strong>Comment:</strong>
										<c:out value="${comentario.comentario }"></c:out>
									</p>
								</div>
								<hr />
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-2 d-flex flex-column justify-content-between">
				<a href="/pools/${pool.id }/review" class="btn btn-danger mb-3">Leave
					a review <c:out value="${pool.rating }"></c:out>
				</a>
			</div>
		</div>
	</div>
</body>
</html>