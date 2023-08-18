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
<style>
.table-frame {
	border: 2px solid #ccc;
	padding: 10px;
	border-radius: 5px;
	margin: 20px 0;
}
</style>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- cambiar para que coincida con Tu archivo/estructura de nombres -->
<link rel="stylesheet" href="/css/main.css">
<script type="text/javascript" src="/js/app.js"></script>

</head>
<body>
	<div class="container mt-5">

		<div class="text-end">
			<a href="/logout" class="btn btn-outline-danger">Logout</a>
		</div>

		<h1 class="display-3">
			Welcome,
			<c:out value="${usuario.nombre}"></c:out>
		</h1>

		<div class="row m-2">
			<div class="col-md-12">
				<h3 class="d-inline-block">Ideas:</h3>
				<div class="d-inline-block float-end">
					<a href="/ideas/low" class="btn btn-secondary">Low Likes</a> <a
						href="/ideas/hight" class="btn btn-secondary">High Likes</a>
				</div>
				<div class="table-responsive">
					<div class="table-frame">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Idea:</th>
									<th>Created By:</th>
									<th>Likes:</th>
									<th>Action:</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listaIdeas}" var="ideasUser">
									<tr>
										<td><a href="/ideas/${ideasUser.id}" class="text-primary"><c:out
													value="${ideasUser.idea}"></c:out></a></td>
										<td><c:out value="${ideasUser.creador.nombre}"></c:out></td>
										<td><c:out value="${ideasUser.likes.size()}"></c:out></td>
										<td><c:choose>
												<c:when test="${ideasUser.likes.contains(usuario)}">
													<a href="/ideas/${ideasUser.id}/${usuario.id}/dislike"
														class="btn btn-sm btn-outline-danger">Dislike</a>
												</c:when>
												<c:otherwise>
													<a href="/ideas/${ideasUser.id}/${usuario.id}/like"
														class="btn btn-sm btn-outline-success">Like</a>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="text-end">
			<a href="/ideas/new" class="btn btn-success">Create an Idea</a>
		</div>
	</div>
</body>
</html>