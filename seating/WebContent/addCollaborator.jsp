<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="./ajoutCollab" method="POST">
Prenom: <input type="text" name="name">
<br />
Nom: <input type="text" name="lastname" />
<br />
Admin? <input type="checkbox" name="admin" />
<br />
Date d'arrivé: <input type="text" name="date" />
<input type="submit" value="Submit" />
</form>
</body>
</html>