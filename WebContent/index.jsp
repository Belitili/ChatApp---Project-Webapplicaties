<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<meta charset="UTF-8">
	<title>Home - ChatApp</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body onload="openSocket()" onunload="closeSocket()">

	<%@include file="header.jspf" %>
	
<div id="main">
	<% 	if (session.getAttribute("user")!=null) {%>
		<p>Welcome <%=session.getAttribute("username")%></p>
		<form method="post" action="LogOutServlet">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
	<%	} else { %>
		<form method="post" action="LogInServlet">
			<p>
				<label for="username">Your username</label>
				<input type="text" id="username" name="username" value="Test">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" value="test">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
		</form>
		<p>
			New user?			
			<form action="register.jsp">
			    <input type="submit" value="Register" />
			</form>
		</p>
	<%	} %>
	
	<% 	if(request.getAttribute("errors")!=null) {
			ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
			if (errors.size() > 0) { 
				for (String error: errors) {%>
					<p class="alert"><%=error%></p>
				<%	} 	
				}
		}%>
		
		<article id="blogPost1" class="blogPost">
			<h2>Plans for today</h2>
			<p>- Making the Angular part of this site</p>
			<p>- Going to the store, need new cola, 'cause I need my caffeine and the strawberries are on sale.</p>
			<p>- Going to make a strawberry tart, and I'm thinking vol-au-vent, because cooking is basically my favorite thing to do.</p>
			<p>- If possible, some more work for school, 'cause I'm hopelessly behind, fun stuff.</p>
			<input type="text" id="usernameInput1" value="Anonymous">
			<input type="text" id="commentInput1" value="Your comment">
			<button type="button" onclick="send(1);" >Comment</button>
			<div class="commentSection">
				<h3>Comments</h3>
				<div id="comments1"></div>
			</div>
		</article>
		<article id="blogPost2" class="blogPost">
			<h2>Projectweek Mei 2017</h2>
			<p> <h3>Positief</h3>
				<ul>
					<li>Veel afwisseling</li>
					<li>1 vrij interessante lezing (van mobile vikings)</li>
					<li>Groepen niet zelf kiezen</li>
					<li>Uitdagende programmeeropdracht (voor eerstejaars)</li>
				</ul> </p>
			<p> <h3>Negatief (Algemeen)</h3>
				<ul>
					<li>Veel lezingen, waarvan ik buiten 1 niks uit heb bijgeleerd, omdat ik hiervoor al vakken heb gehad.</li>
					<li>Niet vele programmeertijd uiteindelijk</li>
					<li>Geen peer review</li>
				</ul> </p>
			<p> <h3>Negatief (Persoonlijk)</h3>
				Ik kan moeilijkheidsniveau van de opdracht niet goed inschatten aangezien ik al enkele programmeervakken en jaren voorloop op eerstejaars, maar mijn teamgenoten hadden er duidelijk moeilijkheden mee.
				Wat tot gevolg had dat ik uiteindelijk ca. 80% van de opdracht heb gemaakt, al dan niet met iemand naast mij. Het merendeel van mijn team heeft wel een paar dingen bijgedragen, of althans toch geprobeerd.
				Een aantal hebben zover ik weet niks bijgedragen. Wat zonder peer review of projectweek feedback uiteindelijk de betere optie bleek.
				Persoonlijk vond ik de projectweek een beetje frustrerend omdat ik er niet echt iets heb uit bijgeleerd, en dus uiteindelijk voornamelijk tijdverspilling was. Het was op zich wel leuk, al had een team dat iets meer mee was fijn geweest. Maar met dat het zo laat in het semester viel, had ik de tijd zeker beter kunnen gebruiken voor andere vakken, waaruit ik wel iets nieuw kan leren.
			
			</p>
			<input type="text" id="usernameInput2" value="Anonymous">
			<input type="text" id="commentInput2" value="Your comment">
			<button type="button" onclick="send(2);" >Comment</button>
			<div class="commentSection">
				<h3>Comments</h3>
				<div id="comments2"></div>
			</div>
		</article>
		<article id="blogPost3" class="blogPost">
			<h2>Music</h2>
			<p>After cooking, I most enjoy either reading or singing, so listening to les miserables (and singing along). It's a good things it's weekend, so there's a limited number of roommates to annoy.</p>
			<input type="text" id="usernameInput3" value="Anonymous">
			<input type="text" id="commentInput3" value="Your comment">
			<button type="button" onclick="send(3);" >Comment</button>
			<div class="commentSection">
				<h3>Comments</h3>
				<div id="comments3"></div>
			</div>
		</article>
</div>
	
	<%@include file="footer.jspf" %>
	
	<script>
	var webSocket;            
	var path = "ChatApp";
	
	function openSocket(){
	    webSocket = new WebSocket("ws://localhost:8080/"+path+"/echoComment");              
		webSocket.onmessage = function(event){
			var msg = JSON.parse(event.data);
			var nr = msg.blognr;
			var text = msg.name + " (" + new Date(msg.date).toLocaleTimeString() + ")" + "<br>" + msg.comment;
			var comments = document.getElementById("comments" + nr);                       
			comments.innerHTML += "<p class=\"comment\">" + text + "</p>";              
		};
	}
	function send(nr){
		var text = document.getElementById("commentInput" + nr).value;
		var username = document.getElementById("usernameInput" + nr).value;
		var msg = {comment: text, blognr: nr, date: Date.now(), name: username};
	    webSocket.send(JSON.stringify(msg));
	}
	function closeSocket(){
	    webSocket.close();
	}
	</script>

</body>
</html>