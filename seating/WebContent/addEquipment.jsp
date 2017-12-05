<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<form action="./" method="POST">
		Nom de l'équipement: <input type="text" name="name"> <br />

		Date d'achat: <input type="text" name="lastname" /> <br /> Type
		d'equipement <SELECT name="nom" size="1">
			<OPTION>Ordinateur
			<OPTION>Téléphone
		</SELECT> <br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>