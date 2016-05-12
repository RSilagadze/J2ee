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
<title>Profil d'utilisateur</title>
</head>
<body>
 <img alt="Avatar" width="100" src= "<%= imageHost + user.getLogoImg() %>" />
 <p>Nom : <%= user.getNom() %> </p>
 <p>Prenom : <%= user.getPrenom() %> </p>
 <p>Adresse : <%= user.getAdresse() %> </p>
 <p>Telephone : <%= user.getTelephone() %> </p>
 <p>Type d'utilisateur : <%= user.type.getLibelle() %> </p>
 <table>
	 <tr>
	 	<td>
			<form method="POST" action="<%= request.getContextPath() + "/main?command=connection&action=logout" %>">
					<input value ="Deconnexion" type="submit"/>
			</form>
		</td>
		<td>
			<form method="POST" action="<%= request.getContextPath() + "/main?command=association&action=viewAssociation" %>">
					<input value ="Voir mes associations" type="submit"/>
			</form>
		</td>
		<% if (user.type.getId() == Prefs.AdminAssoc) {
			StringBuilder builder = new StringBuilder();
			builder.append(
			"<td><form method=\"POST\" action=\""+request.getContextPath() +
			 "/main?command=association&action="+Prefs.action_manageAssoc+"\">"+
					"<input value =\"Gerer mon Association\" type=\"submit\"/>"+
			"<input type=\"hidden\" name=\"assoc_president\" value=\""+user.getId()+"\"/>"+
			"</form></td>"+
			"<td><button onclick=\"window.location.href='"+request.getContextPath()+"/userModPage"+"'\">Modifier le Profil</button></td>"
			);      
			response.getWriter().append(builder);
		}
		%>
		</tr>		
	</table>
</body>
</html> 