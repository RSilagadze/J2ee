package tp6.myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import tp6.utility.MagazinData;

public class ArticleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private EntityList <Article> lstArticles = MagazinData.lstArticles;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession userSession = request.getSession(false);
		Cookie[] cArr = request.getCookies();
		PrintWriter wr = response.getWriter();
		Client cl = null;
		String path = request.getContextPath();
		
		if (userSession != null && checkCookies (cArr, userSession)) {
			wr.append(
						"<!DOCTYPE html>"+
						"<html>"+
						"<head>"+
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"+
						"</head>"+
						"<body>");
			
			 cl = (Client) userSession.getAttribute("user");
			 
			 wr.append("Session id = " + userSession.getId() + "\n"
					 	+ "Cookie name = " + cArr[0].getName() + " Cookie value = " + cArr[0].getValue() + "\n"
					 	+ "User = " + cl.getId() + " " + cl.getNom()+ " "+ cl.getPrenom() + "\n"
			 );	
	
			 generateHeader (wr, cl);
			 generateArticleTable (wr);
			 generateFooter(wr);
			 
			 if (request.getParameterValues("chbx") != null && request.getParameterValues("qteChoisi") != null)
				 ajoutAuPanier(request, response, cl);
		}
		else {
		    	response.sendRedirect(path + "/index.html" );
		}
	}
	
	private void generateArticleTable (PrintWriter wr){
		int i = -1;
		wr.append(		
				"<div>"+ 
					 "<form action=\"/TP6/article\" method=\"POST\" name = \"submitArticle\">" +
					 "<p><h3>Articles disponibles</h3>" +
						"<table border=\"1\">"+
								"<tr align=\"center\">"
								+ "<td colspan=\"1\"></td>"
								+ "<td><label>Nom</label></td>"
								+ "<td><label>Prix</label></td>"
								+ "<td><label>Quantite disp.</label></td>"
								+ "<td><label>Quantite choisie</label></td>"
								+ "</tr>");
		
		for (Article a : lstArticles){
			i++;
			wr.append(
					"<tr align=\"center\">"+
					"<td><input name=\"chbx\" type=\"checkbox\" value="+ i +"></td>"+
					"<td><label>"+a.getNom()+"</label></td>"+
					"<td><label>"+a.getPrix()+"</label></td>"+
					"<td><label>"+a.getQuantite()+"</label></td>"+
					"<td><input type=\"text\"name=\"qteChoisi\" size =\"2\" maxlength=\"2\" value = \"0\"></td>"+
					"<td><a href = \"articleProfile?articleId="+ i +"\" >Voir l'article</a></td>"+
					"</tr>");
		}
		
		wr.append("</table>"+
					"</div>");
	}
	
	private void generateFooter (PrintWriter wr){
		wr.append(
				"<div>"
				+ "<input type=\"submit\" value=\"Ajouter au panier\" >  "
				+ "<input type=\"button\" value=\"Voir le Panier\" onclick=\"window.location='/TP6/panier';\"/>  " 
				+ "<input type=\"reset\" value = \"Annuler\">  "
				+ "</form>"
				+ "</div>"
				);
	}
	
	private void generateHeader (PrintWriter wr, Client cl){
		wr.append(
				"<div>"
				+ "<form action=\"/TP6/logout\" method=\"POST\" name = \"logoutForm\">"
				+ "<p> Bienvenue, "+cl.getNom()+ " " +cl.getPrenom()
				+ "<br><p><input type=\"submit\" value=\"Deconnecter\">"
				+ "</form>"
				+ "</div>"
				);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession userSession = request.getSession(false);
	    String path = request.getContextPath();
		if (userSession == null)
	    		response.sendRedirect(path + "/index.html" );
	    else doPost(request,response);
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
	
	private void ajoutAuPanier (HttpServletRequest req, HttpServletResponse res, Client c) throws IOException{
		
		DateFormat formatDat = new SimpleDateFormat("dd/MM/yyyy");
		Date dat = new Date ();
		String strDat = formatDat.format(dat);
		int idCmd = generateIdCommand (c.lstCommand);	
		
		ArticleCommand cmd = new ArticleCommand (idCmd, "Commande d'articles", strDat);
		
		String[] checkedValues = req.getParameterValues("chbx");
		String[] qteChoisiValues = req.getParameterValues("qteChoisi");
		
		for (int i = 0;i < checkedValues.length; i++){
			
			int indexArt = Integer.parseInt(checkedValues[i]);			
			int qteChoisi = 0;
			
			if (!qteChoisiValues[indexArt].trim().equals(""))
			    qteChoisi = Integer.parseInt(qteChoisiValues[indexArt]);
			
			Article aChecked = lstArticles.get(indexArt);
		
			Article aCmd = new Article (aChecked.getId(), aChecked.getQuantite() - qteChoisi, 
										aChecked.getPrix(), aChecked.getNom());
					aCmd.setQteCommande(qteChoisi);
			
			cmd.lstArticle.add(aCmd);
		}
		c.lstCommand.add(cmd);
		
		req.getSession().setAttribute("ArticleCommandList", c.lstCommand);
	
		PrintWriter wr = res.getWriter();
		for (ArticleCommand cmdlst : c.lstCommand){
			for (Article art : cmdlst.lstArticle){
				wr.append(
						""
						+ "<br>id art : " +art.getId()
						+ "<br>nom art :" +art.getNom()
						+ "<br>prix art :" + art.getPrix()
						+ "<br>qte choisie art :" + art.getQteCommande()
						+ "<br>qte dispo : " +art.getQuantite()
						+ "<br>==================================="
						);
			}	
		}
	}
	
	private int generateIdCommand (EntityList<ArticleCommand> lstCmd){
		return lstCmd.size() + 1;
	}

}
