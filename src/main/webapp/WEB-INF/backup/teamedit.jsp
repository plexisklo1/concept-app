<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Team editing</title>
<link rel="stylesheet" type ="text/css" href="css/bootstrap.css"/>
</head>
<body>

<div class="container">
	<table>
		<form:form action="processteam" modelAttribute="team">
			<form:hidden path="id" value="${team.id}"/>
			<tr>
				<td>Team name:</td>
				<td><form:input path="name" value="${team.name}" /></td><td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td>Team description:</td>
				<td><form:input path="description" value="${team.description}" /></td><td><form:errors path="description"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="Add team" /></td>
			</tr>
		</form:form>
	</table>
</div>
</body>
</html>