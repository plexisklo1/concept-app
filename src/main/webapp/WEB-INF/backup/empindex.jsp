<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee/Team list</title>
<link rel="stylesheet" type ="text/css" href="css/bootstrap.css"/>
</head>
<body>

<div class="container">
	<h3>Employees</h3>
	<hr>
	<table>
		<tr>
			<th>ID</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Team</th>
			<th>Details</th>
			<th>Action</th>
		</tr>

		<c:forEach var="emp" items="${employees}">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.team.name}</td>
				<c:url var="empdetail" value="/empdetail">
					<c:param name="empid" value="${emp.id}" />
				</c:url>
				<c:url var="empedit" value="/empedit">
					<c:param name="empid" value="${emp.id}" />
				</c:url>
				<c:url var="empremove" value="/empremove">
					<c:param name="empid" value="${emp.id}" />
				</c:url>
				<td><a href="${empdetail}">Details</a></td>
				<td><a href="${empedit}">Update</a> | <a href="${empremove}">Remove</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/addemployee">New
		employee</a>
	<br>
	<br>
	<hr>
	<br>
	<h3>Teams</h3>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Description</th>
			<th>Action</th>
		</tr>
		<c:forEach var="team" items="${teams}">
			<c:url var="teamedit" value="/teamedit">
				<c:param name="teamid" value="${team.id}" />
			</c:url>

			<tr>
				<td>${team.id}</td>
				<td>${team.name}</td>
				<td>${team.description}</td>
				<td><a href="${teamedit}">Update</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="${pageContext.request.contextPath}/teamcreate">New team</a>
</div>

</body>
</html>


