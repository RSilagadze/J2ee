<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<%@ page import="com.itparis.b3.associations.beans.*" %>
<% 	String imageHost = getServletContext().getInitParameter("imageHost"); %>
<% 	String userTag = getServletContext().getInitParameter("user_tag"); %>
<% 	String sessionIdTag = getServletContext().getInitParameter("sessionId_tag"); %>
<%  User user = new User (); %>
<% if (DataManager.hasValidConnection(request, sessionIdTag)){
   user = (User) request.getSession().getAttribute(userTag);
 } 
 else DataManager.doForward(request, response, "/loginPage"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mes associations</title>
</head>
<body>
<%
StringBuilder builder = new StringBuilder ();
builder.append("<table border=\"1\">");
builder.append("<tr>");
builder.append("<td colspan=\"2\"></td>");
builder.append("<td>Nom d'association</td>");
builder.append("<td>Libelle d'association</td>");
builder.append("</tr>");
for (Association a : user.assoc){
	builder.append("<tr>");
	builder.append("<td><img alt=\"AssocLogo\" width = \"50\" src = \"" +imageHost + a.getLogoImg()+ "\"/><td>");
	builder.append("<td>"+ a.desc.getNomAssoc()+"</td>");
	builder.append("<td>"+ a.getLibelle()+"</td>");
	builder.append("<td><form method=\"POST\" action=\""+request.getContextPath() +"/main?command=association&action=viewAssociation\">"+
				  "<input type=\"hidden\" name=\"associationId\" value=\""+a.getId()+"\"/>"+
				  "<input type=\"submit\" value=\"Voir l'association\">"+
				  "</form></td>");
	builder.append("</tr>");
} 
builder.append("</table>");
response.getWriter().append(builder);
%>
</body>
</html>