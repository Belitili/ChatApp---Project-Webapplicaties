<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register New User - ChatApp</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<%@include file="header.jspf" %>

<div id="main">
	<% 	if (session.getAttribute("user")!=null) {%>
		<p>Log out first.</p>
		<form method="post" action="LogOutServlet">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
	<%	} else { %>
		<form method="post" action="RegistNewUserServlet" id="registerForm">
			<p>
				<label for="username">Username</label> <br>
				<input type="text" id="username" name="username" required>
			</p> 
			<p>
				<label for="email">E-mail</label> <br>
				<input type="email" id="email" name="email" required>
			</p>
			<p>
				<label for="password">Your password</label> <br>
				<input type="password" id="password" name="password" required>
			</p>
			<p>
				<label for="passwordRepeat">Repeat password</label> <br>
				<input type="password" id="passwordRepeat" name="passwordRepeat" required>
			</p>
			<p>
				<label for="name">Name</label> <br>
				<input type="text" id="name" name="name" required>
			</p>
			<p>
				<label for="fname">Firstname</label> <br>
				<input type="text" id="fname" name="fname" required>
			</p>
			<p>
				<label for="age">Age</label> <br>
				<input type="number" id="age" name="age" required>
			</p>		
			<p>
				<label for="selectGender">Gender</label> <br>
				<select form="registerForm" name="selectGender" id="selectGender">
  					<option value="Female" selected>Female</option>
  					<option value="Male">Male</option>
 				</select>
			</p> <br>
			<p>
				<input type="submit" id="registerbutton" value="Register">
			</p>
		</form>
	<%	} %>
	
	<% 	if(request.getAttribute("errors")!=null) {
			ArrayList<String> errors = (ArrayList<String>) request.getAttribute("errors");
			if (errors.size() > 0) { 
				for (String error: errors) {%>
					<p class="alert"><%=error%></p>
				<%	} 	
				}
		}%>
</div>

<%@include file="footer.jspf" %>

</body>
</html>