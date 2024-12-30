<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Team Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
    	<a href="/" class="btn btn-secondary mb-3">Back to Home</a>
        <div class="d-flex justify-content-between align-items-center mb-2">
		    <h1 class="mb-0">${team.name}</h1>
		    <div class="d-flex align-items-center">
		        <form method="post" action="/teams/${team.id}/delete" class="me-2">
		            <input type="hidden" name="teamId" value="${team.id}">
		            <button type="submit" class="btn btn-danger btn-sm">Delete Team</button>
		        </form>
		        <a href="/teams/${team.id}/edit" class="btn btn-secondary btn-sm">Edit</a>
		    </div>
		</div>

        <p><strong>Team Name:</strong> ${team.name}</p>
        <p><strong>Added By:</strong> ${team.creator.userName}</p>
        <p><strong>Skill Level:</strong> ${team.skill}</p>
        <p><strong>Game Day:</strong> ${team.gameDay}</p>

        <div class="container mt-3">
		    <h3>Players</h3>
		    <table class="table table-striped table-bordered">
		        <thead class="table-dark">
		            <tr>
		                <th scope="col">#</th>
		                <th scope="col">Player Name</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="player" items="${team.players}" varStatus="status">
		                <tr>
		                    <td>${status.count}</td>
		                    <td>${player}</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		    <c:if test="${empty team.players}">
		        <div class="alert alert-info">
		            No players have joined this team yet.
		        </div>
		    </c:if>
		</div>
		<c:if test="${team.players.size() == 9}">
			<p><strong>The team is full!</strong></p>
		</c:if>
        <h3 class="mt-4">Add Player</h3>
		<form method="post" action="/teams/${team.id}/addPlayer" class="mt-4 mb-3">
			<label for="name" class="form-label">Player Name</label>
		    <div class="d-flex align-items-center">
		        <input type="text" name="name" id="name" class="form-control me-2" required <c:if test="${team.players.size() == 9}"> disabled </c:if> />
		        <button type="submit" class="btn btn-primary" <c:if test="${team.players.size() == 9}"> disabled </c:if>>Add</button>
		    </div>
		</form>
    </div>
</body>
</html>