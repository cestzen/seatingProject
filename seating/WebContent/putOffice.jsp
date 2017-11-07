<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<canvas id="canvas" width="500" height="500" style="background: url(C:/wamp64/www/test/plan2.jpg) no-repeat center center;" > 
	<canvas id="canvas2" width="500" height="500" > 

		<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

	</canvas>
</canvas>


<script>

var array = [];
var canvas  = document.querySelector('#canvas');
var context = canvas.getContext('2d');


var canvas2  = document.querySelector('#canvas2');
var context2 = canvas2.getContext('2d');

var e=event || window.event;
var largeur = 40;
var hauteur = 40;

  var element = document.getElementById('canvas');
	
	element.onclick = function() {

		var aEffacer = false;
		

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["hor"] ;
			   var clientX1 = event.clientX -20 ;
			   var clientX2 = event.clientX +20 ;
			   
			   var ver = array[i]["ver"] ;
			   var clientY1 = event.clientY -20 ;
			   var clientY2 = event.clientY +20 ;
			   
			
			if(hor > clientX1 && hor < clientX2 && ver > clientY1  && ver < clientY2){

				aEffacer = true;
				context.clearRect(hor-(largeur/2), ver-(hauteur/2), largeur, hauteur);
				array.splice(i, 1);
			   }
			}
		
		if(aEffacer){
			
		}else{
			context.fillStyle = "pink";
			var x = event.clientX ;
			var y = event.clientY ;
			context.fillRect(x-(largeur/2), y-(hauteur/2), largeur, hauteur);
			person = {name:'table1',hor:x,ver:y};
		    array.push(person); 
		}
		

    };
    
    
</script>
</body>
</html>