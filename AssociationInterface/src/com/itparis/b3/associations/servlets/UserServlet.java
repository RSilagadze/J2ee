package com.itparis.b3.associations.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.exception.AssociationException;
import com.itparis.b3.associations.metier.AssociationMetier;
import com.itparis.b3.associations.metier.UserMetier;
import com.itparis.b3.associations.utilities.DataManager;
import com.itparis.b3.associations.utilities.Prefs;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String userID_tag;
	private String user_tag;
	private String sessionID_tag;
	private String action_tag;
	private String exception_tag;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		sessionID_tag = getServletContext().getInitParameter("sessionId_tag");
		user_tag = getServletContext().getInitParameter("user_tag");
		userID_tag = getServletContext().getInitParameter("userId_tag");
		exception_tag = getServletContext().getInitParameter("exception_tag");
		action_tag = getServletContext().getInitParameter("action_tag");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = "";
		if (request.getAttribute(action_tag) != null){
			action = request.getAttribute(action_tag).toString();
		}
		try {
			switch (action){
			
			case Prefs.action_viewUser :
				if (DataManager.hasValidConnection(request, sessionID_tag)){
					User user = (User) request.getSession().getAttribute(user_tag);
					if (user.type.getId()== Prefs.AdminAppli){
						ArrayList<Association> listAssoc = AssociationMetier.getListAssociations(0, "", "", "");
						ArrayList<User> lstUsers = UserMetier.getListUsersForAdmin(1, "", "");
						request.setAttribute("listeAssociation", listAssoc);
						request.setAttribute("listeUtilisateurs", lstUsers);
						DataManager.doForward(request, response, "/adminProfile");
					}
					else
						DataManager.doRedirect(request, response, "/userProfile");
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
				break;
			
			case Prefs.action_modUser :
				if (DataManager.hasValidConnection(request, sessionID_tag)){
					try {
						User user = (User) request.getSession().getAttribute(user_tag);
						String userPrenom = request.getParameter("user_name");
						String userNom = request.getParameter("user_lastname");
						String userAdresse = request.getParameter("user_adresse");
						String userTel = request.getParameter("user_tel");
						
						int rows = UserMetier.updateUser(userNom, userPrenom, userAdresse, userTel, user.getId());
						if (rows != -1){
							user = UserMetier.getUser(user.getId());
							request.getSession().setAttribute(user_tag, user);
							DataManager.doForward(request, response, "/main?command=user&action=viewUser");
						}
						else throw new AssociationException();
					}
					catch(AssociationException e){
						DataManager.doForward(request, response, "/error");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			case Prefs.action_deleteUser :
				
			break;
			
			case Prefs.action_addUser : 
				
			default : throw new AssociationException();
			}
		}
		catch (AssociationException e){
			DataManager.doForward (request,response, "/error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void addError (HttpServletRequest request, HttpServletResponse response, 
			AssociationException e, String urlPattern) throws ServletException, IOException{
		request.setAttribute(exception_tag, e);
		request.getRequestDispatcher("/error").include(request, response); 
		request.getRequestDispatcher(urlPattern).include(request, response);
	}

}
