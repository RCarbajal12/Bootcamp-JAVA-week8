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
<title>Hight Likes</title>
<!-- BOOTSTRAP  -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>

	<div class="container mt-5">
		<h1 class="mb-4 text-center">High Likes</h1>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">Nombre de la Idea</th>
					<th scope="col">Likes</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ideasHight}" var="idea">
					<tr>
						<td>${idea.idea}</td>
						<td>${idea.likes.size()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>