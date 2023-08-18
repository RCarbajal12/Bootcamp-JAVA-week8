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
<div class="container">
<h1 class=" text-center display-3 m-4">Welcome!</h1>
    <div class="row">
        <div class="col-md-4 offset-md-2">
            <h2>Register!</h2>
            <form:errors class="text-danger" path="user.*" />

            <form:form method="POST" action="/registration" modelAttribute="user">
                <div class="form-group">
                    <form:label path="nombre">Name:</form:label>
                    <form:input type="text" path="nombre" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="email">Email:</form:label>
                    <form:input type="email" path="email" class="form-control" />
                </div>

                <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:password path="password" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="passwordConfirmation">Password Confirmation:</form:label>
                    <form:password path="passwordConfirmation" class="form-control" />
                </div>
                <input type="submit" value="Register!" class="btn btn-primary m-3" />
            </form:form>
            <p class="text-success">
                <c:out value="${registro }"></c:out>
            </p>
        </div>

        <div class="col-md-4 offset-md-1">
            <h2>Login!</h2>
            <form:errors class="text-danger" path="login.*" />

            <form:form method="POST" action="/login" modelAttribute="login">
                <div class="form-group">
                    <form:label path="email">Email:</form:label>
                    <form:input path="email" class="form-control" />
                </div>
                <div class="form-group">
                    <form:label path="password">Password:</form:label>
                    <form:password path="password" class="form-control" />
                </div>

                <input type="submit" value="Login!" class="btn btn-primary m-3" />
            </form:form>
        </div>
    </div>
</div>

</body>
</html>