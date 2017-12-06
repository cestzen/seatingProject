<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<title>Plan</title>
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
		<label id="labelX"> </label>
		<label id="labelY"> </label>
		<div style="display:inline-block;vertical-align:top;">
			<div style="display:inline-block;vertical-align:top;">
				Nom bureau: <label type="text" name="nameEquipment" id="nameOffice"/></label><br />
			</div>
			<div style="display:inline-block;vertical-align:top;">
					Nom de l'équipement: <label type="text" name="nameEquipment" id="nameEquipment"/></label><br/>
					Type d'equipement<label type="text" name="typeEquipment" id="nameEquipment"/></label>
					    <br/><br/>
					 Nom de l'utilisateur<label type="text" name="nameUser" id="nameEquipment"/></label>
			</div>
		</div>
	
	
	<script>

	var array = [];
	var canvas  = document.querySelector('#canvas');
	var context = canvas.getContext('2d');


	//var e=event || window.event;

 	var element = document.getElementById('canvas');
 	
 	var inputName = document.getElementById('nameOffice');
 	var nameEquipment = document.getElementById('nameEquipment');
 	var nameTypeEquipment= document.getElementById("typeEquipment");
 	var nameUser = document.getElementById("nameUser");
 	
 	for(var i = 0; i < array.length; i++)
	{
	
	    var hor = array[i]["x"]-(largeur/2) ;
	    var ver = array[i]["y"] -(hauteur/2) ;
		
		context.strokeRect(hor, ver, largeur, hauteur);
	}
  
  	
 	element.onclick = function(e) {
	 	

		var rect = canvas.getBoundingClientRect();
		
		eX = e.clientX - rect.left;
		eY = e.clientY - rect.top

		var xval = eX - 10 ;
		var yval = eY -5;
		
		

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["x"] ;
			   var clientX1 = xval -(largeur/2) ;
			   var clientX2 = xval +(largeur/2) ;
			   
			   var ver = array[i]["y"] ;
			   var clientY1 = yval -(hauteur/2) ;
			   var clientY2 = yval +(hauteur/2) ;
			
				if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){
					
					inputName = array[i]["nom"]
					nameEquipment = array[i]["nomEquipement"]
					nameTypeEquipment = array[i]["typeEquipement"]
					nameUser = array[i]["nomUtilisateur"]
					
				}
			}

 	};
  
  
 
</script>
</body>
</html>