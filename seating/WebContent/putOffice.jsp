<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<canvas id="canvas" width="500" height="500" style="background: url(C:/wamp64/www/test/plan2.jpg) no-repeat center center;background-size:contain;" > 

		<p>Désolé, votre navigateur ne supporte pas Canvas.</p>

</canvas>


<script>

var array = [];
var canvas  = document.querySelector('#canvas');
var context = canvas.getContext('2d');


var e=event || window.event;
var largeur = 40;
var hauteur = 40;

  var element = document.getElementById('canvas');
	
  element.onclick = function() {

		var aEffacer = false;

		var x = event.clientX - 10 ;
		var y = event.clientY -5;

		for (var i = 0; i < array.length; i++) {
			
			   var hor = array[i]["hor"] ;
			   var clientX1 = x -(largeur/2) ;
			   var clientX2 = x +(largeur/2) ;
			   
			   var ver = array[i]["ver"] ;
			   var clientY1 = y -(hauteur/2) ;
			   var clientY2 = y +(hauteur/2) ;
			   
			
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
				
				context.fillRect(hor, ver, largeur, hauteur);
				}
					
			   
		}else{
			context.fillStyle = "grey";
			context.fillRect(x-(largeur/2), y-(hauteur/2), largeur, hauteur);
			bureau = {name:'table1',hor:x,ver:y};
		    array.push(bureau); 
		}
		

  };
    
</script>
</body>
</html>