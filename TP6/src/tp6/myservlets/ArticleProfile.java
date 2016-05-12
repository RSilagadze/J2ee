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
import tp6.entities.Client;
import tp6.utility.MagazinData;

/**
 * Servlet implementation class ArticleProfile
 */

public class ArticleProfile extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private EntityList <Article> lstArticles = MagazinData.lstArticles;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Cookie[] cArr = request.getCookies();
		HttpSession userSession = request.getSession(false);
		String idArticleStr = request.getParameter("articleId");
		Client client = null;
		Article article = null;
		int articleId = -1;
		
		if (tryParseInt (idArticleStr))
			articleId = Integer.parseInt(idArticleStr);
		
		if (userSession != null)
			client = (Client) userSession.getAttribute("user");
		
		if (checkCookies (cArr, userSession)){
			pw.append("Session id = " + userSession.getId() + "\n"
					 + "Cookie name = " + cArr[0].getName() + " Cookie value = " + cArr[0].getValue() + "\n"
					 + "User = " + client.getId() + " " + client.getNom()+ " "+ client.getPrenom() + "\n <br><br>"
					);
		}
		
		if (articleId != -1){
			try{
			article = lstArticles.get(articleId);
			pw.append(
					"<div>"
					+ "<table border = \"1\">"
					+ "<tr align=\"center\"><td>Nom d'article</td><td>Quantite dispo.</td><td>Prix</td>"
					+ "<tr align=\"center\"><td>"+article.getNom()+"</td>"
					+ "<td>"+article.getQuantite()+"</td>"
					+ "<td>"+article.getPrix()+"</td>"
					+ "</div>"
					);
			}
			catch (Exception e){
				pw.append("<p>Article n'existe pas </p>");
			}
			
		}	
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession userSession = request.getSession(false);
	    String path = request.getContextPath();
		if (userSession != null)
					doGet(request, response);
		else response.sendRedirect(path + "/index.html" );
	}
	
	private boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
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
