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
		<div class="text-end">
			<a href="/ideas" class="btn btn-outline-danger">Volver</a>
		</div>
		<h1 class=" mb-2 display-4">Edit ${idea.idea }</h1>

		<div class="row m-3">
			<div class="col-md-6">
				<div class="card">
					<div class="card-body">
						<form:form method="POST" action="/ideas/${idea.id}/edit"
							modelAttribute="ideaEdit">
							<input type="hidden" name="_method" value="put" />
							<form:input type="hidden" path="creador" value="${usuario.id}" />
							<div class="form-group col-md-8">
								<form:label class="form-label" path="idea">Content:</form:label>
								<form:input type="text" class="form-control" path="idea" />
								<form:errors class="text-danger" path="idea" />
							</div>
							<input type="submit" value="Update" class="btn btn-success my-2" />
						</form:form>
						<form action="/ideas/${idea.id}/delete" method="post">
							<input type="hidden" name="_method" value="delete">
							<button type="submit" class="btn btn-danger">Delete</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>