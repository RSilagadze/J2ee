package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.TypeUser;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.dao.UserDAO;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.common.DB.Queries;
import com.itparis.b3.associations.common.DBSpecialValues.*;

public class UserMetier {
	
	public static ArrayList <User> getListUsers (int idAssoc, int idType, String OrderBy) {	
		ArrayList<User> lstUser = new ArrayList<User>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object> ();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
			filtre += " AND "+DB.Utilisateurs.alias+".id IN (Select "+DB.FicheParticipant.alias+".idUtilisateur "+
					  " From "+DB.FicheParticipant+" "+DB.FicheParticipant.alias +
					  " Where "+DB.FicheParticipant.alias+".idAssociation = ? )";
		}
		if (idType > 0) {
			lstPValues.add(idType);
			filtre += " AND "+DB.Utilisateurs.alias+".idType = ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstUser = UserDAO.class.newInstance().getListUser(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return lstUser;
	}
	
	public static User getUser (int id) {
		User u = new User ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		
		filtre += " AND "+DB.Utilisateurs.alias+".id = ? ";
			
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			u = UserDAO.class.newInstance().getUser(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return u;
	}
	
	public static ArrayList <FicheParticipant> getListFicheParticipant (int idAssoc, int idUser, int typeUser,
			                                                            String userName, String OrderBy) {	
		ArrayList<FicheParticipant> lstFiche = new ArrayList<FicheParticipant>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
			filtre += " AND "+DB.FicheParticipant.alias+".idAssociation = ? ";
		}
		if (idUser > 0) {
			lstPValues.add(idUser);
			filtre += " AND "+DB.FicheParticipant.alias+".idUtilisateur = ? ";
		}
		if (typeUser > 0) {
			lstPValues.add(typeUser);
			filtre += " AND "+DB.TypeUtilisateurs.alias+".id = ? ";
		}
		if (!Utilities.isNullOrEmptyString(userName)){
			lstPValues.add(new PreparedLikeClause (userName));
			filtre += " AND "+DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstFiche = UserDAO.class.newInstance().getListFicheParticipant(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return lstFiche;
	}
	
	public static FicheParticipant getFicheParticipantByIdUserAndIdAssoc (int idUser, int idAssoc) {	
		FicheParticipant fp = new FicheParticipant();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUser);
		lstPValues.add(idAssoc);
		
	    filtre += " AND "+DB.FicheParticipant.alias+".idUtilisateur = ? " +
	    		  " AND "+DB.FicheParticipant.alias+".idAssociation = ? ";
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			fp = UserDAO.class.newInstance().getFicheParticipant(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return fp;
	}
	
	public static FicheParticipant getFicheParticipant (int idFiche) {	
		FicheParticipant fp = new FicheParticipant();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idFiche);
		
	    filtre += " AND "+DB.FicheParticipant.alias+".id = ? ";
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			fp = UserDAO.class.newInstance().getFicheParticipant(filtre, params);
		} 
		catch (InstantiationException | IllegalAccessException e) {}
		return fp;
		}
	
	public static ArrayList<User> getListUsersForAdmin (int status, String userName, String OrderBy) {
		ArrayList <User> lstUser = new ArrayList<User> ();
		String filtre = "";
		List <Object> lstPValues = new ArrayList<Object> ();
		lstPValues.add(status);
		
        filtre += " AND " +DB.Utilisateurs.alias+".Statut = ? ";
        
		if (!Utilities.isNullOrEmptyString(userName)){
			lstPValues.add(new PreparedLikeClause (userName));
			filtre += " AND "+DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
        if (!Utilities.isNullOrEmptyString(OrderBy)){
        	filtre += " ORDER BY "+OrderBy;
        }
        
        HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
        
		try {
			lstUser = UserDAO.class.newInstance().getListUsersSimple(filtre, params);
		}
		catch (InstantiationException | IllegalAccessException e) {}
		
		return lstUser;
	}
	
	public static User getUserForAdmin (int id) {
		User u = new User ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		
		filtre += " AND "+DB.Utilisateurs.alias+".id = ? ";
		
		HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			u = UserDAO.class.newInstance().getUserSimple(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return u;
	}
	
	public static ArrayList<TypeUser> getListUserType (String Libelle, String OrderBy) {
		ArrayList <TypeUser> lstType = new ArrayList<TypeUser> ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		if (!Utilities.isNullOrEmptyString(Libelle)){
			lstPValues.add(new PreparedLikeClause (Libelle));
			filtre += " AND "+DB.TypeUtilisateurs.alias+".Libelle LIKE ? ";
		}
        if (!Utilities.isNullOrEmptyString(OrderBy)){
        	filtre += " ORDER BY "+OrderBy;
        }
		HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstType = UserDAO.class.newInstance().getListTypeUser(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return lstType;
	}

	public static TypeUser getUserType (int id) {
		TypeUser t = new TypeUser ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		
		filtre += " AND "+DB.TypeUtilisateurs.alias+".id = ? ";

		HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			t = UserDAO.class.newInstance().getTypeUser(filtre, params);
		} 
		catch (InstantiationException  | IllegalAccessException e) {}
        return t;
	}
	
	public static int insertNewUser (int idType, String nomUtilisateur, String prenomUtilisateur, 
			String adrUtilisateur, String telUtilisateur, int Statut, String login, String mdp){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idType);
		lstPValues.add(nomUtilisateur);
		lstPValues.add(prenomUtilisateur);
		lstPValues.add(adrUtilisateur);
		lstPValues.add(telUtilisateur);
		lstPValues.add(Statut);
		lstPValues.add(login);
		lstPValues.add(mdp);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.InsertNewUser;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int insertNewTypeUserVerification (int id, String Libelle){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		lstPValues.add(Libelle);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		int rows = 0;
		try {
			rows = UserDAO.class.newInstance().insertIntoUserType(params);
		} 
		catch (InstantiationException | IllegalAccessException e) {e.printStackTrace();}
		return rows;
	}

	public static int deleteUser (int idUser){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUser);
		lstPValues.add(idUser);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteUserAndAuthPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int deleteTypeUser (int idType){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idType);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteTypeUserPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateTypeUser (String libelleType, int id){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(libelleType);
		lstPValues.add(id);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateTypeUtilisateur;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateUser (String nomUser, String prenomUser, String adrUser, String telUser,
								  int id){
								 
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(nomUser);
		lstPValues.add(prenomUser);
		lstPValues.add(adrUser);
		lstPValues.add(telUser);
		lstPValues.add(id);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateUtilisateur;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateUserForAdmin (int idType, String nomUser, String prenomUser,
											String adrUser, String telUser, int statut, int id){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idType);
		lstPValues.add(nomUser);
		lstPValues.add(prenomUser);
		lstPValues.add(adrUser);
		lstPValues.add(statut);
		lstPValues.add(id);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateUtilisateurForAdmin;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}

	
	
	



}
