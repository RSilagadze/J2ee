package com.itparis.b3.associations.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.exception.AssociationException;
import com.itparis.b3.associations.metier.AssociationMetier;
import com.itparis.b3.associations.metier.UserMetier;
import com.itparis.b3.associations.utilities.DataManager;
import com.itparis.b3.associations.utilities.Prefs;

/**
 * Servlet implementation class AssociationServlet
 */
public class AssociationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String action_tag;
	private String exception_tag;
	@SuppressWarnings("unused")
	private String userID_tag;
	private String user_tag;
	private String sessionID_tag;
	private String assocId_tag;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssociationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// TODO Auto-generated method stub
		action_tag = getServletContext().getInitParameter("action_tag");
		exception_tag = getServletContext().getInitParameter("exception_tag");
		sessionID_tag = getServletContext().getInitParameter("sessionId_tag");
		user_tag = getServletContext().getInitParameter("user_tag");
		userID_tag = getServletContext().getInitParameter("userId_tag");
		assocId_tag = getInitParameter("associationId_tag");
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
			
			case Prefs.action_addAssociation : 
				if(DataManager.hasValidConnection(request, sessionID_tag )){
					try{
						String assocName;
						String assocLib;
						String assocDesc;
						if (!Utilities.isNullOrEmptyString(request.getParameter("assoc_name")))
							assocName = (request.getParameter("assoc_name"));
						else throw new AssociationException("Entrez le nom");
						
						if (!Utilities.isNullOrEmptyString(request.getParameter("assoc_libelle")))
							assocLib = (request.getParameter("assoc_libelle"));
						else throw new AssociationException("Entrez le libelle");
						
						if (!Utilities.isNullOrEmptyString(request.getParameter("assoc_desc")))
							assocDesc = (request.getParameter("assoc_desc"));
						else throw new AssociationException("Entrez la description");

						int idPres = Integer.parseInt(request.getParameter("assoc_president"));
						
						int rows = AssociationMetier.insertAssociation(assocLib, idPres, assocName, 0, assocDesc);
						if (rows != -1){
							DataManager.doForward(request, response, "/main?command=user&action=viewUser");
						}
						else throw new AssociationException ();
						
					}
					catch(AssociationException e){
						addError (request, response, e ,"/adminProfile");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			case Prefs.action_modAssociation : 
				if(DataManager.hasValidConnection(request, sessionID_tag )){
					try{
						if (Utilities.isParsableToInt(request.getParameter(assocId_tag))){
							int idAssoc = Integer.parseInt(request.getParameter(assocId_tag));
							String assocName = request.getParameter("assoc_name");
							String assocLib = request.getParameter("assoc_libelle");
							String assocDesc = request.getParameter("assoc_desc");
							int idPres = Integer.parseInt(request.getParameter("assoc_president"));
							int nbParticipant = Integer.parseInt(request.getParameter("assoc_nbparticipant"));
							int rows = AssociationMetier.updateAssociation(idAssoc, assocLib, idPres, assocName, nbParticipant, assocDesc);
							if (rows != -1){
								request.setAttribute(assocId_tag, idAssoc);
								DataManager.doForward(request, response, "/main?command=association&action=viewAssociation");
							}
							else throw new AssociationException();
						}
						else throw new AssociationException();
					}catch(AssociationException e){
						DataManager.doForward(request, response, "/error");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			case Prefs.action_deleteAssociation : 
				if(DataManager.hasValidConnection(request, sessionID_tag )){
					try{
						if (Utilities.isParsableToInt(request.getParameter(assocId_tag))){
							int idAssoc = Integer.parseInt(request.getParameter(assocId_tag));
							int rows = AssociationMetier.deleteAssociationAndDesc(idAssoc);
							if (rows != -1){
								DataManager.doForward(request, response, "/main?command=user&action=viewUser");
							}
							else throw new AssociationException();
						}
						else throw new AssociationException ();
					}
					catch (AssociationException e){
						DataManager.doForward(request, response, "/error");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			case Prefs.action_manageAssoc : 
				if (DataManager.hasValidConnection(request, sessionID_tag)){
					try {
						if (Utilities.isParsableToInt(request.getParameter("assoc_president"))){
							int idPres = Integer.parseInt(request.getParameter("assoc_president"));
							Association assoc = AssociationMetier.getListAssociations(idPres, "", "", "").get(0);
							request.setAttribute("association", assoc);
							DataManager.doForward(request, response, "/ManageAssociationPage");
						}
						else throw new AssociationException ();
					}
					catch (AssociationException e){
						DataManager.doForward(request, response, "/error");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			case Prefs.action_viewAssociation : 
				if (DataManager.hasValidConnection(request, sessionID_tag)){	
					
					if (!Utilities.isNullOrEmptyString(request.getParameter(assocId_tag)) &&
							Utilities.isParsableToInt(request.getParameter(assocId_tag))){
						int idAssoc = Integer.parseInt(request.getParameter(assocId_tag));
						Association assoc = AssociationMetier.getAssociation(idAssoc);
						ArrayList<FicheParticipant> lstParticipant= UserMetier.getListFicheParticipant(idAssoc, 0, 0, "", "");
						request.setAttribute("association", assoc);
						request.setAttribute("listeParticipant", lstParticipant);
						DataManager.doForward(request, response, "/associationPage");
					}
					else {
						User user = (User) request.getSession().getAttribute(user_tag);
						user.assoc = AssociationMetier.getListAssociations(user.getId(), "", "", DB.Association.alias +".id" );
						DataManager.doRedirect(request, response, "/listeAssociationsPage");
					}
				}
				else addError (request, response, new AssociationException ("Vous n'etes pas connecté"),"/loginPage");
			break;
			
			default : throw new AssociationException();
			
			}
		}
		catch (AssociationException e){
			DataManager.doForward(request, response, "/error");
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
