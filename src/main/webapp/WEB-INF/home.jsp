<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Login & Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
</head>

<body class="bg-gradient bg-primary text-light min-vh-100 d-flex align-items-center justify-content-center">
    <div class="container text-center">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <!-- Main Content -->
                <div class="p-5 rounded shadow-lg bg-light text-dark">
                    <h1 class="fw-bold mb-3">Welcome, ${loggedUser.userName}</h1>
                    <p class="fs-5">This is your home page. Nothing to see here yet.</p>
                    <a href="/logout" class="btn btn-danger mt-4 px-5">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>
