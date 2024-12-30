<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Team</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
    	<a href="/" class="btn btn-secondary mb-3">Back to Dashboard</a>
        <div class="d-flex justify-content-between align-items-center mb-2">
		    <h1 class="mb-2 mt-2">Edit Team</h1>
	        <form method="post" action="/teams/${team.id}/delete" class="me-2">
	            <input type="hidden" name="teamId" value="${team.id}">
	            <button type="submit" class="btn btn-danger btn-sm">Delete Team</button>
	        </form>
		</div>
        <form:form method="post" action="/teams/edit/${team.getId()}" modelAttribute="team" class="mt-4">
            <form:hidden path="id"/>
            <div class="mb-3">
                <label for="name" class="form-label">Team Name</label>
                <form:input path="name" id="name" class="form-control" value="${team.getName()}"/>
                <form:errors path="name" class="text-danger" />
            </div>
            <div class="mb-3">
                <label for="skill" class="form-label">Skill Level (1-5)</label>
                <form:input path="skill" id="skill" type="number" step="1" class="form-control" value="${team.getSkill()}" />
                <form:errors path="skill" class="text-danger" />
            </div>
			<div class="mb-3">
                <label for="gameDay" class="form-label">Game Day</label>
                <form:input path="gameDay" id="gameDay" class="form-control" value="${team.getGameDay()}" />
                <form:errors path="gameDay" class="text-danger" />
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</body>
</html>