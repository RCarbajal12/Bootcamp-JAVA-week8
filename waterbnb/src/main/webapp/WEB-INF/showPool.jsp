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
<style>
/* Tu código CSS aquí */
.message-list {
	max-height: 300px;
	overflow-y: auto;
}
</style>

</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col text-end">
				<c:choose>
					<c:when test="${usuario != null}">
						<a href="/" class="btn btn-secondary">Home</a>
						<a href="/logout" class="btn btn-secondary ms-2">Logout</a>
					</c:when>
					<c:otherwise>
						<a href="/guest/signin" class="btn btn-secondary">Sign in /
							Sign up</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<h1 class="text-center display-2 m-2">Details</h1>

		<div class="row mt-3">
			<div class="col-md-6">
				<p>
					<strong>Direccion:</strong>
					<c:out value="${pool.direccion }"></c:out>
				</p>
				<p>
					<strong>Descripcion:</strong>
					<c:out value="${pool.description}"></c:out>
				</p>
			</div>
			<div class="col-md-6">
				<p>
					<strong>Email:</strong>
					<c:out value="${pool.organizador.email}"></c:out>
				</p>
				<p>
					<strong>Name:</strong>
					<c:out
						value="${pool.organizador.nombre} ${pool.organizador.apellido}"></c:out>
				</p>
				<p>
					<strong>Pool size:</strong>
					<c:out value="${pool.poolsize}"></c:out>
				</p>
				<p>
					<strong>Cost:</strong>
					<c:out value="${pool.costo}"></c:out>
				</p>
			</div>
		</div>

		<div class="row mt-3">
			<div class="col-md-6">
				<div class="card mb-3">
					<div class="card-header">Muro de Reviews</div>
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
				<a href="/pools/${pool.id }/review" class="btn btn-primary mb-3">Leave
					a review <c:out value="${pool.rating }"></c:out>
				</a>
			</div>
		</div>
	</div>
</body>
</html>