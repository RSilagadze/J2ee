<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%><%@taglib
	uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="\Errorpage.jsp" %>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentification</title>
</head>
<body>
	<form method="POST" action="
	<%= request.getContextPath() + "/main?command=connection&action=login" %>">
		Login : <input name = "login"><br>
		MDP : <input type="password" name="mdp"><br>
		Session time-out : <select name = "timeout">
							<option value ="120">120 min</option>
							<option value ="60">60 min</option>
							<option value ="30">30 min</option>
							<option value ="-1" selected = "selected">Jamais</option>
						   </select>
		<br>
		<input value = "login" type="submit">
		<br>
	</form>
</body>
</html>