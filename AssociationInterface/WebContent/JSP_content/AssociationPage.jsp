<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<%@ page import="com.itparis.b3.associations.beans.*" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<FicheParticipant> lstParticipant = new ArrayList<FicheParticipant>();  %>
<% Association assoc = new Association (); %>
<% String imageHost = getServletContext().getInitParameter("imageHost"); %>
<%! @SuppressWarnings("unchecked") %>
<% if (request.getAttribute("association") != null ){
	assoc = (Association) request.getAttribute("association");
	}else DataManager.doForward(request, response, "/error");
	if (request.getAttribute("listeParticipant") != null ){
		lstParticipant = (ArrayList<FicheParticipant>) request.getAttribute("listeParticipant");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Description d'association</title>
</head>
<body>
<%
	StringBuilder builder = new StringBuilder();

	builder.append("<img alt=\"logo\" width =\"150\" src=\"" + imageHost + assoc.getLogoImg()+"\"></img>");
	builder.append("<p> Nom d'association : "+ assoc.desc.getNomAssoc()+"</p>");
	builder.append("<p> Nom d'association : "+ assoc.getLibelle()+"</p>");
	builder.append("<p> Nom d'association : "+ assoc.desc.getNbParticipant()+"</p>");
	builder.append("<p> Nom d'association : "+ assoc.desc.getDescription()+"</p>");

	builder.append("<p> Liste des participants </p>");
	builder.append("<table>");
	builder.append("<tr>");
	builder.append("<td>Nom</td><td>Prenom</td><td>Date d'inscription</td><td>Notes</td><td>Anciennete</td>");
	builder.append("</tr>");
	for (FicheParticipant p : lstParticipant){
		builder.append("<tr>");
		builder.append("<td>" + p.utilisateur.getNom() + "</td>");
		builder.append("<td>" + p.utilisateur.getPrenom() + "</td>");
		builder.append("<td>" + p.getDateInscription() + "</td>");
		builder.append("<td>" + p.getNotes() + "</td>");
		builder.append("<td>" + p.getAnciennete() + "</td>");
		builder.append("</tr>");
	}
	builder.append("</table>");
	response.getWriter().append(builder);
	%>
</body>
</html>