<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	if (session.getAttribute("admin") == null || !session.getAttribute("admin").equals("admin")) {
		response.sendRedirect("login.jsp");
	}
%>
</head>
<body>
	<form action="./ajoutEquipment" method="POST">
		Numéro de série: <input type="text" name="externalId"> <br />
		Type d'equipement : <SELECT name="type" size="1" id="typeEquipement">
			<c:forEach var="item" items="${typesEquipement}">
				<OPTION><c:out value="${item}" />
			</c:forEach>
		</SELECT> <br /> Collaborateur : <SELECT name="collab" size="1"
			id="selectCollab">
			<c:forEach var="item" items="${collaborateurs}">
				<OPTION><c:out value="${item}" />
			</c:forEach>
		</SELECT><br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>