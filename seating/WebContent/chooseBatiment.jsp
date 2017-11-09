<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="com.project.java.seating.model.Plan"%>
<%@ page import="java.util.List, java.util.ArrayList "%>
<%@ page import="com.project.java.seating.model.Batiment "%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	}
%>
</head>
<body>

	<label>Veuillez choisir un batiment</label>
	<br>
	<br>
	<FORM action="./showSeating" method="POST">
		<SELECT name="nomBatiment" size="1" id="selectBatiment">
			<c:forEach var="item" items="${nomsBatiments}">
				<OPTION><c:out value="${item}" />
			</c:forEach>
		</SELECT> <input type="submit" id="submit" value="Submit" />
	</FORM>
	<br>

</body>
</html>