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
<title>Ideas</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-12 text-end">
 
				<a href="/ideas" class="btn btn-outline-danger">Volver</a>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-md-8 offset-md-2 text-center">
				<h1 class="display-4">
					<c:out value="${ideaSeleccionada.idea}"></c:out>
				</h1>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-md-8 offset-md-2 text-center">
				<h6 class="mb-0">Created By:</h6>
				<p class="lead">
					<c:out value="${ideaSeleccionada.creador.nombre}"></c:out>
				</p>
			</div>
		</div>

		<div class="row mt-5">
			<div class="col-md-8 offset-md-2">
				<h3 class="text-center mb-3">Users who liked your idea:</h3>
				<ul class="list-group">
					<c:forEach items="${personasQueDieronLike}" var="personas">
						<li class="list-group-item"><c:out value="${personas.nombre}"></c:out></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<c:choose>
			<c:when test="${ideaSeleccionada.creador.id == loginUser.id}">
				<div class="row mt-4">
					<div class="col-12 text-center">
						<a href="/ideas/${ideaSeleccionada.id}/edit"
							class="btn btn-outline-success">Edit Idea</a>
					</div>
				</div>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>


	</div>
</body>
</html>