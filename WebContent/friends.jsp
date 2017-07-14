<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<title>Friends - ChatApp</title>
	<script type="text/javascript" src="getListFriends.js"></script>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body onload="getFriends()">

<script type="text/javascript" src="addFriend.js"></script>

	<%@include file="header.jspf" %>
	
	<div id="main">
	<% if (session.getAttribute("user")!=null) {%>
		<form id="addFriendForm">
			<label for="friendToAdd">Add friend(username): </label> 
			<input type="text" id="friendToAdd" name="friendToAdd"/>
			<input type="submit" id="addFriendButton" value="Add Friend" onclick="addFriend();"/>
		</form>
		<p class="response"><span id="addFriendResponse"></span></p>
	
		<div id="friendlist"></div>
	<%	} else { %>
		<p>Please log in first.</p>
	<%	} %>
	
	</div>
	
	<!--  Add friend method with jquery, now done by addFriend.js with xmlhttprequest object
	// WITH THIS I GET RESPONSE !! (But needed to be non jquery...)
	<script>
		// Attach a submit handler to the form
		$( "#addFriendForm" ).submit(function( event ) {
		 
		  // Stop form from submitting normally
		  event.preventDefault();
		 
		  // Put the inputs of form in array
		  var postData = $("#addFriendForm > :input").serialize();
		 
		  // Send the data using post
		  var posting = $.post( "AddFriendServlet", postData );
		 
		  // Put the results in the addFriendResponse p
		  posting.done(function( responseText ) { 
		    $("#addFriendResponse").text(responseText);
		  });
		});
	</script> --->
	
	<%@include file="footer.jspf" %>

</body>
</html>