<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="db.UserDB"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Users - ChatApp</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<%@include file="header.jspf" %>

<div id="main">
	<h2>List of Users</h2>
	<h3><a href="http://localhost:3000/users">Angular solution</a></h3>
	<ul>
		<% 	UserDB db = UserDB.getDB(); 
			for (User user: db.getAll()) { %>
		<li><%=user.getUsername() %></li>
	<% 		} %>
	</ul> 
</div>

<%@include file="footer.jspf" %>

</body>
</html>