package com.itparis.b3.associations.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itparis.b3.associations.beans.Authentification;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.exception.AssociationException;
import com.itparis.b3.associations.metier.AuthMetier;
import com.itparis.b3.associations.metier.UserMetier;
import com.itparis.b3.associations.utilities.DataManager;
import com.itparis.b3.associations.utilities.Prefs;

/**
 * Servlet implementation class LoginServlet
 */
public class ConnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String userID_tag;
	private String user_tag;
	private String sessionID_tag;
	private String action_tag;
	private String exception_tag;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionServlet() {
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
			case Prefs.action_login :
				
				String mdp = request.getParameter("mdp");
				String login  = request.getParameter("login");
				String timeoutStr = request.getParameter("timeout");
				int timeout = -1;
				
				if (Utilities.isParsableToInt(timeoutStr))
					timeout = Integer.parseInt(timeoutStr);
				
				Authentification auth = AuthMetier.getUserAuthData(login, mdp);
				try {
					if (auth.getIdUser() > 0){
						User user = UserMetier.getUser(auth.getIdUser());
						if (user.getId() > 0){
							
							HttpSession session = request.getSession (false);
							if (session == null)
								session = request.getSession(true);
							session.setAttribute(userID_tag, user.getId());
							session.setAttribute(user_tag, user);
							//!!! Timeout must be defined as the last property of the session
							// else the session will throw invalid state exception
							session.setMaxInactiveInterval(timeout * 60);
							
							Cookie cookie = new Cookie(sessionID_tag, session.getId());
							int cMaxAge = 0;
							if (timeout <= 0)
								cMaxAge = 31536000;
							else cMaxAge = timeout * 60;
							cookie.setMaxAge(cMaxAge);
							response.addCookie(cookie);
							
							DataManager.doRedirect(request, response, 
									"/main?command="+Prefs.command_user+"&action="+Prefs.action_viewUser+"");
						}
						else throw new AssociationException ();
					}
					else throw new AssociationException ("Login ou mot de passe incorrects");
				}
				catch (AssociationException e){
					addError (request, response, e, "/loginPage");
				}
			break;
			
			case Prefs.action_logout : 
				try {
					if (request.getSession(false) != null){
						request.getSession().invalidate();
							DataManager.doRedirect(request, response,"/loginPage");
					}
					else throw new AssociationException("Vous n'etês pas connecté");
				}
				catch (AssociationException e){
					addError (request, response, e, "/loginPage");
				}
				break;
				
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
