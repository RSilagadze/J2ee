package com.itparis.b3.associations.utilities;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DataManager {
	
	public static boolean hasCookieByKeyValue (String key, String value, Cookie[] cArray){
		for (Cookie c : cArray){
			if (c.getName().equals(key) && c.getValue().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	public static Cookie getCookieByKeyValue (String key, String value, Cookie[] cArray){
		for (Cookie c : cArray){
			if (c.getName().equals(key) && c.getValue().equals(value)){
				return c;
			}
		}
		return null;
	}
	
	public static Cookie getCookieByKey (String key, Cookie[] cArray){
		for (Cookie c : cArray){
			if (c.getName().equals(key)){
				return c;
			}
		}
		return null;
	}
	
	public static Cookie getCookieByValue (String value, Cookie[] cArray){
		for (Cookie c : cArray){
			if (c.getValue().equals(value)){
				return c;
			}
		}
		return null;
	}
	
	public static void doForward (HttpServletRequest request, HttpServletResponse response,String urlPattern)throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher(urlPattern);
		dispatcher.forward(request, response);
	}
	
	public static void removeAllCookies (HttpServletRequest request){
		for (Cookie c : request.getCookies()){
			c.setMaxAge(0);
		}
	}
	
	public static void doRedirect (HttpServletRequest request, HttpServletResponse response, String urlPattern) throws IOException{
		response.sendRedirect(request.getContextPath() + urlPattern);
	}
	
	public static boolean hasValidConnection (HttpServletRequest request, String sessionId_tag){
		if (request.getSession(false) == null ||
				!hasCookieByKeyValue(sessionId_tag, request.getSession(false).getId(), request.getCookies())){
			return false;
		}
		return true;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
