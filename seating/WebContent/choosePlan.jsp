

<%@page import="com.project.java.seating.model.Plan"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <%@ page import="java.util.List, java.util.ArrayList " %>
    <%@ page import="com.project.java.seating.services.ShowSeatingPlanService " %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%  
//JSON.parse();
//String[] windows = new String[]{"Plan 1", "Plan 2", "Plan 3", "Plan 4"};
//pageContext.setAttribute("windows", windows);
ShowSeatingPlanService show = new ShowSeatingPlanService();
Plan plan = new Plan();
plan.setNom("Plan 1");
show.addPlan(plan);
String[] plans = show.getFloorPlans();
%>


<label>Veuillez choisir un plan</label>
<br><br>
    <FORM>
    <SELECT name="nom" size="1">
	    <c:forEach var="item" items="${plans}" >
		    <OPTION><c:out value="${item}" />
		</c:forEach>
    </SELECT>
    </FORM>
<br><br>
<button>Valider</button>
</body>
</html>