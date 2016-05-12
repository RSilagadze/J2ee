package tp6.myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ErrorServlet
 */
@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession userSession = request.getSession(false);
		if (userSession != null){
			String errorMsg = (String) userSession.getAttribute("error");
			generatePage (pw, errorMsg);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void generatePage (PrintWriter pw, String errorMsg){
		pw.append(
				"<!DOCTYPE html>"
				+"<html>"
				+"<head>"
				+"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
				+"<title>Erreur</title>"
				+"</head>"
				+"<body>"
				+"<p>&#x25CF;&nbsp;"+errorMsg+"</p>"
				+"</body>"
				+"</html>"
		);
	}

}
