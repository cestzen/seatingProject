<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<title>Insert title here</title>
<%
if (session.getAttribute("admin") == null || !session.getAttribute("admin").equals("admin")) {
	response.sendRedirect("login.jsp");
}

%>
</head>
<body>

	<canvas id="canvas" width="500" height="500"
		style="background: url(${path}) no-repeat center center; background-size: contain;">

	<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

	</canvas>
	<form action="./" method="POST">
		<label id="labelX"> </label>
		<label id="labelY"> </label>
		Nom bureau: <input type="text" name="name"><br />
		Nom de l'équipement: <input type="text" name="nameEquipment" /><br/>
		Type d'equipement
		    <SELECT name="nom" size="1">
			    <c:forEach var="item" items="${typesEquipements}" >
				    <OPTION><c:out value="${item}" />
				</c:forEach>
		    </SELECT>
		<input type="submit" id="submit" value="Submit" />
	</form>
	<script>

	var array = [];
	var canvas  = document.querySelector('#canvas');
	var context = canvas.getContext('2d');


	var e=event || window.event;
	var largeur = 40;
	var hauteur = 40;

 	var element = document.getElementById('canvas');
 	var bureauSelectionner ;
  
  	var submit = document.getElementById('submit');
  	submit.onclick = function(){
		$('#data').val(JSON.stringify(array));
	  
  	};
	
  	element.onclick = function() {
		var aModifier = false;

		var xval = event.clientX - 10 ;
		var yval = event.clientY -5;

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["x"] ;
			   var clientX1 = xval -(largeur/2) ;
			   var clientX2 = xval +(largeur/2) ;
			   
			   var ver = array[i]["y"] ;
			   var clientY1 = yval -(hauteur/2) ;
			   var clientY2 = yval +(hauteur/2) ;
			
				if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){
					alert("coucou");
					aModifier = true;
					bureauSelectionner = {nom:'table1',x:hor,y:ver};
					context.fillStyle = "#FF0000";
					//context.strokeRect(clientX1, clientY1, largeur, hauteur);
					
				}
			}
		
		if(aModifier){
			

			var bureauX = bureauSelectionner["x"] -(largeur/2);
			var bureauY = bureauSelectionner["y"] -(hauteur/2);
			context.fillStyle = "pink";
			context.strokeRect(bureauX, bureauY, largeur, hauteur);
			
					
		}else{
			context.fillStyle = "grey";
			context.strokeRect(xval-(largeur/2), yval-(hauteur/2), largeur, hauteur);
			bureau = {nom:'table1',x:xval,y:yval};
		    array.push(bureau); 
		}
		

  };
    
</script>
</body>
</html>