<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Team ${team.name}</title>
<link rel="stylesheet" type ="text/css" href="css/bootstrap.css"/>
</head>
<body>

<div class="container text-center mx-auto">
	<table class="table">
		
		<form:form action="processteam" modelAttribute="team">
		<thead class="thead-light">
			<form:hidden path="id" value="${team.id}"/>
			<tr>
				<th>Team name</th>
				<th>Team description</th>
				<th> </th>
			</tr>
		</thead>
			<tr>
				<td><form:input path="name" value="${team.name}" /></td><td><form:errors path="name"/></td>
				<td><form:input path="description" value="${team.description}" /></td><td><form:errors path="description"/></td>
				<td><input type="submit" value="Add team" class="btn btn-dark"/></td>
			</tr>
		</form:form>
	</table>
</div>
</body>
</html>