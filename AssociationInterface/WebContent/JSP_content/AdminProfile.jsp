<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<%@ page import="com.itparis.b3.associations.beans.*" %>
<%@ page import="java.util.ArrayList" %>
<% String imageHost = getServletContext().getInitParameter("imageHost"); %>
<%! @SuppressWarnings("unchecked") %>
<%  ArrayList<Association> lstAssoc = new ArrayList<Association>();
    ArrayList<User> lstUser = new ArrayList<User>();
	if (request.getAttribute("listeAssociation") != null ){
		lstAssoc = (ArrayList<Association>) request.getAttribute("listeAssociation");
	}
	else DataManager.doForward(request, response, "/error");
	if (request.getAttribute("listeUtilisateurs") != null ){
		lstUser = (ArrayList<User>) request.getAttribute("listeUtilisateurs");
	}
	else DataManager.doForward(request, response, "/error");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
</head>
<body>
<% 
	StringBuilder builder = new StringBuilder ();
	
	builder.append("<p>Liste des associations</p>");
	builder.append("<table border=\"1\">");
	builder.append("<tr>");
	builder.append("<td colspan=\"2\"></td>");
	builder.append("<td>Nom d'association</td>");
	builder.append("<td>Libelle d'association</td>");
	builder.append("</tr>");
	for (Association a : lstAssoc){
		builder.append("<tr>");
		builder.append("<td><img alt=\"AssocLogo\" width = \"50\" src = \"" +imageHost + a.getLogoImg()+ "\"/><td>");
		builder.append("<td>"+ a.desc.getNomAssoc()+"</td>");
		builder.append("<td>"+ a.getLibelle()+"</td>");
		builder.append("<td><form method=\"POST\" action=\""+request.getContextPath() +"/main?command=association&action=viewAssociation\"/>"+
					  "<input type=\"hidden\" name=\"associationId\" value=\""+a.getId()+"\"/>"+
					  "<input type=\"submit\" value=\"voir l'association\">"+
					  "</form></td>");
		builder.append("<td><form method=\"POST\" action=\""+request.getContextPath() +"/main?command=association&action=deleteAssociation\"/>"+
					  "<input type=\"hidden\" name=\"associationId\" value=\""+a.getId()+"\"/>"+
					  "<input type=\"submit\" value=\"supprimer l'association\">"+
					  "</form></td>");
		builder.append("</tr>");
	} 
	builder.append("</table>");
	
	builder.append("<p> Ajouter une association </p>");
	builder.append("<form method=\"POST\" action=\""+ request.getContextPath() +"/main?command=association&action=addAssociation\">");
	builder.append("<p>Nom d'association : <input type=\"text\" name=\"assoc_name\"/></p>");
	builder.append("<p>Description d'association : <input type=\"text\" name=\"assoc_desc\"/></p>");
	builder.append("<p>Libelle d'association : <input type=\"text\" name=\"assoc_libelle\"/></p>");
	builder.append("<p>President d'association : <select name =\"assoc_president\"</p>");
	for (User u : lstUser){
		builder.append("<option value =\""+u.getId()+"\">"+u.getNom()+" "+u.getPrenom()+"</option>");
	}
	builder.append("</select>");
	builder.append("<input type=\"submit\" value=\"Ajouter une association\"/>");
	builder.append("</form>");
	builder.append("<p><form method=\"POST\" action=\""+ request.getContextPath() + "/main?command=connection&action=logout\"></p>");
	builder.append("<input value =\"Deconnexion\" type=\"submit\"/>");
	builder.append("</form>");
	response.getWriter().append(builder);
 %>
</body>
</html>