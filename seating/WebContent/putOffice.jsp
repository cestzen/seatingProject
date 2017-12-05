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
		style="background: url(C:/Users/Sylvain/Desktop/Capture.png) no-repeat center center; background-size: contain;">

	<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

	</canvas>
	<form action="./addBureaux" method="POST">
		<input type="hidden" id="data" name="data"/> 
		<input type="submit" id="submit"
			value="Submit" />
	</form>
	<script>

	var array = [];
	var canvas  = document.querySelector('#canvas');
	var context = canvas.getContext('2d');


	var e=event || window.event;
	var largeur = 40;
	var hauteur = 40;

 	var element = document.getElementById('canvas');
  
  	var submit = document.getElementById('submit');
  	submit.onclick = function(){
		$('#data').val(JSON.stringify(array));
	  
  	};
	
  	element.onclick = function() {
		var aEffacer = false;

		var xval = event.clientX - 10 ;
		var yval = event.clientY -5;

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["hor"] ;
			   var clientX1 = xval -(largeur/2) ;
			   var clientX2 = xval +(largeur/2) ;
			   
			   var ver = array[i]["ver"] ;
			   var clientY1 = yval -(hauteur/2) ;
			   var clientY2 = yval +(hauteur/2) ;
			   
			
				if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){
	
					aEffacer = true;
					array.splice(i, 1);
					
	
					
				}
			}
		
		if(aEffacer){
				context.clearRect(0, 0, canvas.width, canvas.height);
				for(var i = 0; i < array.length; i++)
				{
				
			    var hor = array[i]["hor"]-(largeur/2) ;
			    var ver = array[i]["ver"] -(hauteur/2) ;
				
				context.strokeRect(hor, ver, largeur, hauteur);
				}
					
			   
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