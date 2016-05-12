<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.itparis.b3.associations.utilities.*" %>
<%@ page import="com.itparis.b3.associations.beans.*" %>
<%  Association assoc = new Association (); %>
<% 	String imageHost = getServletContext().getInitParameter("imageHost"); %>
<% if (request.getAttribute("association") != null ){
	assoc = (Association) request.getAttribute("association");
}else DataManager.doForward(request, response, "/error"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion d'association</title>
</head>
<body>
	<form method="POST" action="<%=request.getContextPath()+"/main?command=association&action=modAssociation"%>">
	<p>Nom : <input type="text" name="assoc_name" value="<%= assoc.desc.getNomAssoc() %>"></p>
	<p>Libelle : <input type="text" name="assoc_libelle" value="<%= assoc.getLibelle() %>"></p>
	<p>Description :  <input type="text" name="assoc_desc"  value="<%= assoc.desc.getDescription() %>"></p>
	<input type="hidden" name="assoc_president" value="<%=assoc.desc.getIdPresident() %>"/>
	<input type="hidden" name="assoc_nbparticipant" value="<%=assoc.desc.getNbParticipant() %>"/>
	<input type="hidden" name="associationId" value="<%=assoc.getId() %>"/>
	<input type="submit" value="mettre a jour" />
	</form>
</body>
</html>