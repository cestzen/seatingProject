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
	String userName = null;
	boolean admin = false;
	if (session.getAttribute("user") == null) {
		response.sendRedirect("login.jsp");
	} else
		userName = (String) session.getAttribute("user");
	if (session.getAttribute("admin") != null && session.getAttribute("admin").equals("admin"))
		admin = true;

	String array = (String) request.getAttribute("array");
%>
</head>
<body>
	<c:choose>
		<c:when test='${admin=="admin"}'>
			<canvas id="canvas" width="500" height="500"
				style="background: url(${path}) no-repeat center center; background-size: contain;">

	<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

	</canvas>
			<p>INSTRUCTIONS: Cliquez sur le canvas. Un carré apparaitra.
				Choissisez ce carré pour renseigner les infos pour le bureau.</p>
			<p>Laissez l'id d'equipement vide si aucune equipement n'est
				associé à ce bureau.</p>
			<label id="labelX"> </label>
			<label id="labelY"> </label>
			<div style="display: inline-block; vertical-align: top;">
				<div style="display: inline-block; vertical-align: top;">
					Nom bureau: <input type="text" name="name" id="inputName"><br />
				</div>
				<div style="display: inline-block; vertical-align: top;">
					Id de l'équipement: <input type="text" name="nameEquipment"
						id="nameEquipment" /><br /> Type d'equipement <SELECT name="nom"
						size="1" id="nameTypeEquipment">
						<c:forEach var="item" items="${typesEquipements}">
							<OPTION><c:out value="${item}" />
						</c:forEach>
					</SELECT> <br /> <br /> Nom de l'utilisateur <SELECT name="nom" size="1"
						id="nameUser">
						<c:forEach var="item" items="${collaborateurs}">
							<OPTION><c:out value="${item}" />
						</c:forEach>
					</SELECT>
				</div>

				<div style="display: inline-block; vertical-align: top;">
					<button style="margin-left: 30px;" id="submit"
						onclick="delOffice();">Delete</button>
				</div>
				<form action="./addBureaux" method="POST">
					<input type="hidden" id="array" name="array" /> <input
						type="hidden" id="planId" name="planId" value="${planId}" /><input
						type="submit" value="Submit" onclick="upload();" />
				</form>
			</div>


			<script>

	var array = JSON.parse(${array});
	var canvas  = document.querySelector('#canvas');
	var context = canvas.getContext('2d');


	//var e=event || window.event;
	var largeur = 40;
	var hauteur = 40;

 	var element = document.getElementById('canvas');
 	var inputName = document.getElementById('inputName');
 	var nameEquipment = document.getElementById('nameEquipment');
 	
 	var nameTypeEquipmentSelect = document.getElementById("nameTypeEquipment");
 	
 	var nameUserSelect = document.getElementById("nameUser");
 	
 	var bureauSelectionner = null ;
 	var indexBureauSelectionner = null ;
  
 	for(var i = 0; i < array.length; i++)
		{
		
	    var hor = array[i]["x"]-(largeur/2) ;
	    var ver = array[i]["y"] -(hauteur/2) ;
		
		context.strokeRect(hor, ver, largeur, hauteur);
		}
  	
  	function upload(){

		var nameTypeEquipment = nameTypeEquipmentSelect.options[nameTypeEquipmentSelect.selectedIndex].text;
		var nameUser = nameUserSelect.options[nameUserSelect.selectedIndex].text;
		
		if(bureauSelectionner != null){
			
			array[indexBureauSelectionner]["nom"] = inputName.value;
			array[indexBureauSelectionner]["nomEquipment"] = nameEquipment.value;
			array[indexBureauSelectionner]["nomTypeEquipment"] = nameTypeEquipment;
			array[indexBureauSelectionner]["nomUtilisateur"] = nameUser;
		}
  		document.getElementById("array").value=JSON.stringify(array);
  	}
 	
 	
  	function delOffice(){

  		array.splice(indexBureauSelectionner, 1);
  		bureauSelectionner = null;
  		indexBureauSelectionner = null;
  		
  		context.clearRect(0, 0, canvas.width, canvas.height);
  		for(var i = 0; i < array.length; i++)
  		{
  		
  	    var hor = array[i]["x"]-(largeur/2) ;
  	    var ver = array[i]["y"] -(hauteur/2) ;
  		
  		context.strokeRect(hor, ver, largeur, hauteur);
  		}
  		
    }
  	
  	 function selectItemByValue(elmnt, value){

  		  for(var i=0; i < elmnt.options.length; i++)
  		  {
  		    if(elmnt.options[i].value === value) {
  		      elmnt.selectedIndex = i;
  		      break;
  		    }
  		  }
  		}
	
  	element.onclick = function(e) {
		var aModifier = false;
		
		var nameTypeEquipment = nameTypeEquipmentSelect.options[nameTypeEquipmentSelect.selectedIndex].text;
		var nameUser = nameUserSelect.options[nameUserSelect.selectedIndex].text;

		var rect = canvas.getBoundingClientRect();
		eX = e.clientX - rect.left;
		eY = e.clientY - rect.top

		var xval = eX - 10 ;
		var yval = eY -5;
		if(bureauSelectionner != null){
			
			array[indexBureauSelectionner]["nom"] = inputName.value;
			array[indexBureauSelectionner]["nomEquipment"] = nameEquipment.value;
			array[indexBureauSelectionner]["nomTypeEquipment"] = nameTypeEquipment;
			array[indexBureauSelectionner]["nomUtilisateur"] = nameUser;
			
			inputName.value = "";
			nameEquipment.value = "";
			nameTypeEquipmentSelect.selectedIndex = 0;
			nameUserSelect.selectedIndex = 0;
			context.clearRect(0, 0, canvas.width, canvas.height);
			
			for(var i = 0; i < array.length; i++)
			{
			
		    var hor = array[i]["x"]-(largeur/2) ;
		    var ver = array[i]["y"] -(hauteur/2) ;
			
			context.strokeRect(hor, ver, largeur, hauteur);
			}

			
		}

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["x"] ;
			   var clientX1 = xval -(largeur) ;
			   var clientX2 = xval +(largeur) ;
			   
			   var ver = array[i]["y"] ;
			   var clientY1 = yval -(hauteur) ;
			   var clientY2 = yval +(hauteur) ;
			
				if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){
					aModifier = true;
					bureauSelectionner = {nom:array[i]["nom"],x:hor,y:ver};
					indexBureauSelectionner = i;
					inputName.value = array[i]["nom"];
					nameEquipment.value =array[indexBureauSelectionner]["nomEquipment"];

					selectItemByValue(nameTypeEquipmentSelect,array[indexBureauSelectionner]["nomTypeEquipment"]) ;
					selectItemByValue(nameUserSelect ,array[indexBureauSelectionner]["nomUtilisateur"]);
					
					
				}
			}
		
		if(aModifier){
			

			var bureauX = bureauSelectionner['x'] -(largeur/2);
			var bureauY = bureauSelectionner['y'] -(hauteur/2);
			context.fillRect(bureauX, bureauY, largeur, hauteur);
			
					
		}else{
			bureauSelectionner = null;
			context.fillStyle = "grey";
			context.strokeRect(xval-(largeur/2), yval-(hauteur/2), largeur, hauteur);
			bureau = {nom:inputName.value,x:xval,y:yval,nomEquipment:nameEquipment.value,nomTypeEquipment:nameTypeEquipment,nomUtilisateur:nameUser};
			array.push(bureau);
			inputName.value = "";
			nameEquipment.value = "";
			nameTypeEquipmentSelect.selectedIndex = 0;
			nameUserSelect.selectedIndex = 0;
		}
		
		
  };
  
  
  
 
</script>
		</c:when>
	</c:choose>

	<c:choose>
		<c:when test='${admin!="admin"}'>
			<canvas id="canvas" width="500" height="500"
				style="background: url(${path}) no-repeat center center; background-size: contain;">

	<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

	</canvas>
			<p>INSTRUCTIONS: Cliquez sur le canvas. Un carré apparaitra.
				Choissisez ce carré pour renseigner les infos pour le bureau.</p>
			<p>Laissez l'id d'equipement vide si aucune equipement n'est
				associé à ce bureau.</p>
			<label id="labelX"> </label>
			<label id="labelY"> </label>
			<div style="display: inline-block; vertical-align: top;">
				<div style="display: inline-block; vertical-align: top;">
					Nom bureau: <input type="text" name="name" id="inputName" readonly="readonly" ><br />
				</div>
				<div style="display: inline-block; vertical-align: top;">
					Id de l'équipement: <input type="text" name="nameEquipment"
						id="nameEquipment" readonly="readonly"  /><br /> Type d'equipement <SELECT
						name="nom" size="1" id="nameTypeEquipment">
						<c:forEach var="item" items="${typesEquipements}">
							<OPTION><c:out value="${item}" />
						</c:forEach>
					</SELECT> <br /> <br /> Nom de l'utilisateur <SELECT name="nom" size="1"
						id="nameUser">
						<c:forEach var="item" items="${collaborateurs}">
							<OPTION><c:out value="${item}" />
						</c:forEach>
					</SELECT>
				</div>
				<form action="./" method="GET">
					<input type="hidden" id="array" name="array" /> <input
						type="hidden" id="planId" name="planId" value="${planId}" /><input
						type="submit" value="Back" />
				</form>
			</div>


			<script>

	var array = JSON.parse(${array});
	var canvas  = document.querySelector('#canvas');
	var context = canvas.getContext('2d');


	//var e=event || window.event;
	var largeur = 40;
	var hauteur = 40;

 	var element = document.getElementById('canvas');
 	var inputName = document.getElementById('inputName');
 	var nameEquipment = document.getElementById('nameEquipment');
 	
 	var nameTypeEquipmentSelect = document.getElementById("nameTypeEquipment");
 	
 	var nameUserSelect = document.getElementById("nameUser");
 	
 	var bureauSelectionner = null ;
 	var indexBureauSelectionner = null ;
  
 	for(var i = 0; i < array.length; i++)
		{
		
	    var hor = array[i]["x"]-(largeur/2) ;
	    var ver = array[i]["y"] -(hauteur/2) ;
		
		context.strokeRect(hor, ver, largeur, hauteur);
		}
  	
 	
  	function delOffice(){

  		array.splice(indexBureauSelectionner, 1);
  		bureauSelectionner = null;
  		indexBureauSelectionner = null;
  		
  		context.clearRect(0, 0, canvas.width, canvas.height);
  		for(var i = 0; i < array.length; i++)
  		{
  		
  	    var hor = array[i]["x"]-(largeur/2) ;
  	    var ver = array[i]["y"] -(hauteur/2) ;
  		
  		context.strokeRect(hor, ver, largeur, hauteur);
  		}
  		
    }
  	
  	 function selectItemByValue(elmnt, value){

  		  for(var i=0; i < elmnt.options.length; i++)
  		  {
  		    if(elmnt.options[i].value === value) {
  		      elmnt.selectedIndex = i;
  		      break;
  		    }
  		  }
  		}
	
  	element.onclick = function(e) {
		var aModifier = false;
		
		var nameTypeEquipment = nameTypeEquipmentSelect.options[nameTypeEquipmentSelect.selectedIndex].text;
		var nameUser = nameUserSelect.options[nameUserSelect.selectedIndex].text;

		var rect = canvas.getBoundingClientRect();
		eX = e.clientX - rect.left;
		eY = e.clientY - rect.top

		var xval = eX - 10 ;
		var yval = eY -5;
		if(bureauSelectionner != null){
			
			array[indexBureauSelectionner]["nom"] = inputName.value;
			array[indexBureauSelectionner]["nomEquipment"] = nameEquipment.value;
			array[indexBureauSelectionner]["nomTypeEquipment"] = nameTypeEquipment;
			array[indexBureauSelectionner]["nomUtilisateur"] = nameUser;
			
			inputName.value = "";
			nameEquipment.value = "";
			nameTypeEquipmentSelect.selectedIndex = 0;
			nameUserSelect.selectedIndex = 0;
			context.clearRect(0, 0, canvas.width, canvas.height);
			
			for(var i = 0; i < array.length; i++)
			{
			
		    var hor = array[i]["x"]-(largeur/2) ;
		    var ver = array[i]["y"] -(hauteur/2) ;
			
			context.strokeRect(hor, ver, largeur, hauteur);
			}

			
		}

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["x"] ;
			   var clientX1 = xval -(largeur) ;
			   var clientX2 = xval +(largeur) ;
			   
			   var ver = array[i]["y"] ;
			   var clientY1 = yval -(hauteur) ;
			   var clientY2 = yval +(hauteur) ;
			
				if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){
					aModifier = true;
					bureauSelectionner = {nom:array[i]["nom"],x:hor,y:ver};
					indexBureauSelectionner = i;
					inputName.value = array[i]["nom"];
					nameEquipment.value =array[indexBureauSelectionner]["nomEquipment"];

					selectItemByValue(nameTypeEquipmentSelect,array[indexBureauSelectionner]["nomTypeEquipment"]) ;
					selectItemByValue(nameUserSelect ,array[indexBureauSelectionner]["nomUtilisateur"]);
					
					
				}
			}
		
		if(aModifier){
			

			var bureauX = bureauSelectionner['x'] -(largeur/2);
			var bureauY = bureauSelectionner['y'] -(hauteur/2);
			context.fillRect(bureauX, bureauY, largeur, hauteur);
			
					
		}else{
			bureauSelectionner = null;
			context.fillStyle = "grey";
			context.strokeRect(xval-(largeur/2), yval-(hauteur/2), largeur, hauteur);
			bureau = {nom:inputName.value,x:xval,y:yval,nomEquipment:nameEquipment.value,nomTypeEquipment:nameTypeEquipment,nomUtilisateur:nameUser};
			array.push(bureau);
			inputName.value = "";
			nameEquipment.value = "";
			nameTypeEquipmentSelect.selectedIndex = 0;
			nameUserSelect.selectedIndex = 0;
		}
		
		
  };
  
  
  
 
</script>
		</c:when>
	</c:choose>
</body>
</html>