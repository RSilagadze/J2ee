package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.Authentification;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.common.DB.Queries;
import com.itparis.b3.associations.dao.AuthDAO;

public class AuthMetier {
	
	public static boolean CheckAuthentification (String Log, String Pass){
		boolean check = false;
		try {
			check = AuthDAO.class.newInstance().checkUserData (Log,Pass);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return check;
	}
	
	public static Authentification getUserAuthData (String Log, String Pass) {
		Authentification auth = new Authentification();
		try {
			auth = AuthDAO.class.newInstance().getUserAuthData (Log,Pass);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return auth;
	}
	
	public static int deleteAuthentification (int idUser){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUser);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteAuthentificationPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateAuthentification  (int idUser, String login, String mdp){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(login);
		lstPValues.add(mdp);
		lstPValues.add(idUser);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateAuthentification;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	
	
	

}
