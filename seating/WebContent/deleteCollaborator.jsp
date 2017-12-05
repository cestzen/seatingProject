<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	if (session.getAttribute("admin") == null || !session.getAttribute("admin").equals("admin")) {
		response.sendRedirect("login.jsp");
	}
%>
</head>
<body>
	<h3>Supprimer un collaborateur</h3>
	<br />
	<FORM action="./deleteCollab" method="POST">
		<SELECT name="collab" size="1" id="selectCollab">
			<c:forEach var="item" items="${collaborateurs}">
				<OPTION><c:out value="${item}" />
			</c:forEach>
		</SELECT> <input type="submit" id="submit" value="Submit" />
	</FORM>
</body>
</html>