<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Employee editing</title>
<link rel="stylesheet" type ="text/css" href="css/bootstrap.css"/>
</head>
<body>

<div class="container">
	<h3>Emp edit</h3>
	<hr>
	
		<table>
			<form:form action="empupdate?detailid=${detailid}"
				modelAttribute="employee">
				<form:hidden path="id" value="${employee.id}" />
				<!-- "id" -->
				<tr>
					<td><label>First name:</label></td>
					<td><form:input path="firstName" value="${employee.firstName}" /></td>
				</tr>
				<tr>
					<td><label>Last name:</label></td>
					<td><form:input path="lastName" value="${employee.lastName}" />
				</tr>
				<tr>
					<td><label>Team:</label></td>
					<td><select name="teamid">
							<c:forEach var="teamsid" items="${teams}">
								<option value="${teamsid.id}"
									${teamsid.id==id ? "selected" : ""}>${teamsid.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="Send" />
				</tr>
			</form:form>
		</table>

	</div>
</body>
</html>