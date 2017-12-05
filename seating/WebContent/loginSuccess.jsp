<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<%
		String userName = null;
		boolean admin = false;
		if (session.getAttribute("user") == null) {
			response.sendRedirect("login.jsp");
		} else
			userName = (String) session.getAttribute("user");
		if (session.getAttribute("admin") != null && session.getAttribute("admin").equals("admin"))
			admin = true;
	%>
	<h3>
		Bonjour
		<%=userName%>.
	</h3>
	<br>
	<c:choose>
		<c:when test='${admin=="admin"}'>
			<h4>Panel administrateur</h4>
			<a href="addCollaborator.jsp">Ajouter collaborateur</a>
			<br />
			<a href="deleteCollab">Supprimer collaborateur</a>
			<br />
			<a href="uploadPlan">Ajouter plans</a>
			<br />
			<a href="ajoutEquipment">Ajouter equipement</a>
			<br />
			<a href="showSeating">Ajouter bureaux</a>
			<br />
		</c:when>
	</c:choose>
	<h4>Panel utilisateur</h4>
	<c:choose>
		<c:when test='${user!=null}'>
			<a href="https://html.com/attributes/a-href/">Visualiser les plans</a>
		</c:when>
	</c:choose>
	<form action="./logout" method="GET">
		<input type="submit" id="logout" value="Logout" />
	</form>
</body>
</html>