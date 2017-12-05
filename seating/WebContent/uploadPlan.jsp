<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Enregistrer plan</title>
<%
	if (session.getAttribute("admin") == null || !session.getAttribute("admin").equals("admin")) {
		response.sendRedirect("login.jsp");
	}
%>
</head>

<body>
	<h3>Ajouter un plan</h3>
	Choisir batiment : <br /> 
	<form action="./uploadPlan" method="post" enctype="multipart/form-data">
		<SELECT name="nomBatiment" size="1" id="selectBatiment">
			<c:forEach var="item" items="${nomsBatiments}">
				<OPTION><c:out value="${item}" />
			</c:forEach>
		</SELECT> 
	<br /> 
		Nom de plan : <br /> <input type="text" name="planName" size="50" />
		<br /> 
		Longeur: <br /> <input type="text" name="hauteur"
			size="2" /> 
		<br />
		Largeur : <br /> <input type="text" name="largeur"
			size="4" /> 
		<br />
		Selectionner un plan : <br /> <input type="file" name="file"
			size="50" /> 
		<br /> 
		
		<input type="submit" value="Upload File" />
	</form>
</body>

</html>