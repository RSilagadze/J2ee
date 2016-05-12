<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<%@ page import="com.itparis.b3.associations.beans.*" %>
<% 	String imageHost = getServletContext().getInitParameter("imageHost"); %>
<% 	String userTag = getServletContext().getInitParameter("user_tag"); %>
<% 	String sessionIdTag = getServletContext().getInitParameter("sessionId_tag"); %>
<%  User user = new User (); %>
<% if (DataManager.hasValidConnection(request, sessionIdTag)){
   user = (User) request.getSession().getAttribute("user");
 } 
 else DataManager.doForward(request, response, "/loginPage"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier les Informations</title>
</head>
<body>
<%
	StringBuilder builder = new StringBuilder();
	builder.append("<form method=\"POST\" action=\""+request.getContextPath()+"/main?command=user&action=modUser\">");
	builder.append("<p>Nom : <input name=\"user_lastname\" type=\"text\" value = \""+user.getNom()+"\"/> ");
	builder.append("<p>Prenom : <input name=\"user_name\" type=\"text\" value = \""+user.getPrenom()+"\"/> ");
	builder.append("<p>Adresse : <input name=\"user_adresse\" type=\"text\" value = \""+user.getAdresse()+"\"/> ");
	builder.append("<p>Telephone : <input name=\"user_tel\" type=\"text\" value = \""+user.getTelephone()+"\"/> ");
	builder.append("<p><input type=\"submit\" value=\"Maj\"></p>");
	builder.append("</form>");
	response.getWriter().append(builder);

%>
</body>
</html>