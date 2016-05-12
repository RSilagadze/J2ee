package tp6.myservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tp6.beans.EntityList;
import tp6.entities.Client;
import tp6.utility.MagazinData;

	public class LoginServlet extends HttpServlet {
	
	private EntityList <Client> lstClients = MagazinData.lstClients;
	
	private static final long serialVersionUID = 1L;
	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        // read form fields
		        String userLog = request.getParameter("login");
		        String userPass = request.getParameter("mdp");
		        String path = request.getContextPath();
				
		        // searching for login information
		        Client client = searchClient(userLog, userPass);
		       
		        // redirecting to articleServlet
		        if (client.getId() != -1){
		        	HttpSession userSession = request.getSession(true);
		        	
	            	setSessionParams (userSession, client);
	            	
	            	addCookie(response, userSession);
	  
	            	response.sendRedirect(path + "/article");
		        }
		        else {    
		        	 addError(request,response, "Erreur d'authentification");
		        }   
	    }

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String path = request.getContextPath();
			HttpSession userSession = request.getSession(false);
			if (userSession == null){
			    response.sendRedirect(path + "/index.html");
			}
			else {
				Cookie[] cArr = request.getCookies();
				if (checkCookies(cArr, userSession))
					response.sendRedirect(path + "/article");	
			}
		}

		private Client searchClient (String log, String pass) {
			Client foundClient = null;
			boolean isFound = false;
			for (Client c : lstClients){
				if (c.getLogin().equals(log) && c.getMdp().equals(pass)){
					foundClient = c;
					isFound = true;
					break;
				}
			}
			if (!isFound){
				foundClient = new Client (-1, null,null,null,null,null);
			}
			return foundClient;
		}
		
		private void setSessionParams (HttpSession session, Object client) {
			session.setAttribute("user", client);
			session.setAttribute("sessionId", session.getId());
			session.setMaxInactiveInterval(360);
		}
		
		private void addCookie (HttpServletResponse resp, HttpSession session){
			Cookie c = new Cookie ("sessionId", session.getId());
			c.setMaxAge(200);
        	resp.addCookie(c);
		}
		
		private boolean checkCookies (Cookie[] cArr, HttpSession session) {
			boolean foundFlag = false;
			if (cArr.length > 0 && session != null){
				for (Cookie c : cArr){
					if (c.getName().equals("sessionId")){
						if (c.getValue().equals(session.getId())){
							foundFlag = true;
							break;
						}
					}
				}
			}
			return foundFlag;
		}
		
		private void addError (HttpServletRequest request, HttpServletResponse response, String msg) throws ServletException, IOException{
        	HttpSession err = request.getSession(true);
			err.setAttribute("error", msg);			
			request.getRequestDispatcher("/error").include(request, response);
			request.getRequestDispatcher("/index.html").include(request, response);
			err.invalidate();
			err = null;
		}
	}
