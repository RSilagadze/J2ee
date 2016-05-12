package com.itparis.b3.associations.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itparis.b3.associations.exception.AssociationException;
import com.itparis.b3.associations.utilities.DataManager;
import com.itparis.b3.associations.utilities.Prefs;

/**
 * Servlet implementation class ServletControl
 */

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String command_tag;
	private String action_tag;
	private String exception_tag;
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
		command_tag = getServletContext().getInitParameter("command_tag");
		exception_tag = getServletContext().getInitParameter("exception_tag");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = "";
		String action = "";
		
		if (request.getParameter(command_tag) != null){
			command = request.getParameter(command_tag);
		}
		if (request.getParameter(action_tag) != null){
			action = request.getParameter(action_tag);
			request.setAttribute(action_tag, action);
		}
		
		try {
			switch (command){
				case Prefs.command_connection : 
					DataManager.doForward (request, response, "/connection");
				break;
				
				case Prefs.command_association : 
					DataManager.doForward (request, response, "/association");
				break;
				
				case Prefs.command_event : 
					DataManager.doForward (request, response, "/event");
				break;
				
				case Prefs.command_user : 
					DataManager.doForward (request, response, "/user");
				break;

				default : throw new AssociationException ();
			}
		}
		catch (AssociationException e){
			request.setAttribute(exception_tag, e);
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
	
}
