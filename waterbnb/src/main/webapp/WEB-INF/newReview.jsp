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
		<div class="row mt-3">
			<div class="col-6">
				<a href="/" class="text-decoration-none">Home</a> | <a
					href="/logout" class="text-decoration-none">Logout</a>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col">
				<h1>
					Review of
					<c:out value="${pool.direccion }"></c:out>
				</h1>

				<form:form method="POST" action="/pools/${pool.id}/review"
					modelAttribute="newcomment">
					<input type="hidden" name="autor" value="${usuario.id}" />
					<input type="hidden" name="evento" value="${pool.id}" />
					<div class="mb-3">
						<label for="comentario" class="form-label">Leave a
							comment:</label>
						<textarea class="form-control" id="comentario" name="comentario"></textarea>
						<form:errors class="text-danger" path="comentario" />
					</div>
					<div class="mb-3">
						<label for="rating" class="form-label">Rating</label> <select
							class="form-select" id="rating" name="rating">
							<option value="0">Select</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>

					<button type="submit" class="btn btn-primary">Submit
						Review</button>
				</form:form>
			</div>
		</div>
	</div>


</body>
</html>