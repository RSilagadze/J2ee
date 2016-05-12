package tp6.myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tp6.beans.EntityList;
import tp6.entities.Article;
import tp6.entities.ArticleCommand;
import tp6.entities.Client;

public class CartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		String path = request.getContextPath();
		if (userSession == null)
    	    response.sendRedirect(path + "/index.html" );
		else doGet (request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		Client client = null;
		Cookie[] cArr = request.getCookies();
		PrintWriter pw = response.getWriter();
		EntityList<ArticleCommand> lstCommand = null;
		
		if (userSession != null){
			client = (Client) userSession.getAttribute("user");
			lstCommand = (EntityList <ArticleCommand>) userSession.getAttribute("ArticleCommandList");
		}
		
		if (checkCookies (cArr, userSession)){
			pw.append("Session id = " + userSession.getId() + "\n"
					 + "Cookie name = " + cArr[0].getName() + " Cookie value = " + cArr[0].getValue() + "\n"
					 + "User = " + client.getId() + " " + client.getNom()+ " "+ client.getPrenom() + "\n <br><br>"
					);
		}
		
		pw.append("<p><h3>Espace Panier</h3></p>");
		
		if (lstCommand != null && lstCommand.size() > 0){
			for (ArticleCommand cmd : lstCommand){
			pw.append("<div>"
					+ "<table border = \"1\">"
					+ "<tr align =\"center\">"
					+ "<td>#No de Commande</td><td>Description de Commande</td><td>Date de Commande</td></tr>"
					+ "<tr align =\"center\">"
					+ "<td> " + cmd.getId() + " </td>"
					+ "<td> " + cmd.getNom() + " </td>"
					+ "<td> " + cmd.getDateCommand() + " </td></tr>"
					);
				if (cmd.lstArticle.size() > 0){
					pw.append("<tr align=\"center\" bgcolor=\"green\"><td colspan=\"4\">Articles Commandes : </td></tr>");
					pw.append("<tr align = \"center\"  bgcolor=\"yellow\">"
							+ "<td> Article </td>"
							+ "<td> Qte Commandee </td>"
							+ "<td> Prix unitaire </td>"
							+ "<td> Total </td>"
							+ "</tr>");	
					for (Article art : cmd.lstArticle){
						pw.append("<tr align = \"center\">"
								+ "<td> "+art.getNom()+" </td>"
								+ "<td> "+art.getQteCommande()+" </td>"
								+ "<td> "+art.getPrix()+" </td>"
								+ "<td> "+ art.getPrix() * art.getQteCommande() +" </td>"
								+ "</tr>");	
					}
				}
				pw.append("</table></div><br>");
			}
		}
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

}
