<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Chat - ChatApp</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<body>

<%@include file="header.jspf" %>

	<div id="main">
	<% if (session.getAttribute("user")!=null) {
			User currentUser = (User) session.getAttribute("user");
	%>
			<form>
				<select name="users" id="chatPersonSelect" onchange="openChatWith(this.value)">
  					<option value="">Select an online friend to chat with...</option>
					<% for (User friend: currentUser.getFriends()) { 
							if (friend.getStatus()!="offline") { %>
	  					 		<option value="<%=friend.getUsername()%>"><%=friend.getUsername()%></option>
  					<%		}
						} %>
 				 </select>
			</form>
			<div id="sendMessageDiv"></div>
			<div id="chatDiv"></div>
	<%		
		} else { %>
		<p>Please log in first.</p>
	<%	} %>
	</div>
	
	<script>
		function openChatWith(friend) {
			$.ajax({
				  method: "POST",
				  url: "GetChatServlet",
				  data: { frienduser: friend  }
				})
				  .done(function(responseText) {
				    console.log("openChatSucces");
				    showSendMessage();
				    showChatConv(responseText);
				  });	
		}
		function showChatConv(responseText) {
			var chatDiv = document.getElementById("chatDiv");
		    var data = JSON.parse(responseText);
		    
		    var HTML = "<table>";
		    var i = 0;
		    if (data.length > 5 ) {
		    	i = data.length - 5;
		    }
			for(i; i < data.length; i++)
			{
				var msg = data[i]
			    HTML += "<tr><td>"+msg.sender+" (" + msg.datetime + ")"+"</td><td>"+msg.message+"</td></tr>";
			}
			HTML += "</table>";
			
			chatDiv.innerHTML = HTML;
			var friend = document.getElementById("chatPersonSelect").value;
			$.ajax({
				  method: "POST",
				  url: "GetChatServlet",
				  data: { frienduser: friend  }
				})
				  .done(function(responseText) {
					  console.log(responseText);
					  setTimeout(showChatConv(responseText), 5000);
				  });	
		}
		function showSendMessage() {
			var sendMessageDiv = document.getElementById("sendMessageDiv");
			var HTML = "<input type=\"text\" id=\"messageInput\" >"
				HTML += "<button type=\"button\" onclick=\"sendMessage()\" >Send</button>"
			sendMessageDiv.innerHTML = HTML;
			$("#sendMessageDiv").hide();
			$("#sendMessageDiv").fadeIn("slow");
		}
		function sendMessage() {
			var msg = document.getElementById("messageInput").value;
			var friend = document.getElementById("chatPersonSelect").value;
			$.ajax({
				  method: "POST",
				  url: "SendMessageServlet",
				  data: { message: msg, frienduser: friend }
				})
		}
	</script>

<%@include file="footer.jspf" %>

</body>
</html>