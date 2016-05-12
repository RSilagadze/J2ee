
<%@ page import="java.util.prefs.Preferences"%>
<%@ page import="com.itparis.b3.associations.exception.AssociationException" %>
<%@ page import="com.itparis.b3.associations.utilities.Prefs" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isErrorPage="true" pageEncoding="ISO-8859-1" %>
<% 	String exception_tag = getServletContext().getInitParameter("exception_tag");
	String eMsg = "Une erreur s'est reproduite";
	int eCode = -1;
	AssociationException e = (AssociationException) request.getAttribute(exception_tag);
 	if (e != null){
 		if (!e.getMessage().equals(""))
			eMsg = e.getMessage();
 		if (e.getHttpCode() != -1)
			eCode = e.getHttpCode();
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Erreur</title>
</head>
<body>
	<p><%= eMsg %></p>
	<p><% if (eCode != -1) response.getWriter().append("Code d'erreur : " + eCode); %></p>
</body>
</html>
	
	