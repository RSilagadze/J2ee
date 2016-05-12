package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.common.DBSpecialValues.*;
import com.itparis.b3.associations.common.DB.Queries;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.dao.AssociationDAO;

public class AssociationMetier {
		
	public static ArrayList<Association> getListAssociations (int idUser, String libAssoc, String nomAssoc, String OrderBy){
		ArrayList<Association>lstAssoc = new ArrayList <Association> ();
		List<Object> lstPValues = new ArrayList<Object> ();
		String filtre = "";
		if (idUser > 0){
			lstPValues.add(idUser);
			filtre += " AND "+DB.Association.alias+".id IN (Select "+DB.FicheParticipant.alias+".idAssociation "+
					  " From "+DB.FicheParticipant+" "+DB.FicheParticipant.alias +
					  " Where "+DB.FicheParticipant.alias+".idUtilisateur = ? )";
		}
		if (!Utilities.isNullOrEmptyString(nomAssoc))
		{
			lstPValues.add(new PreparedLikeClause (nomAssoc));
			filtre += " AND " +DB.AssociationDesc.alias+".nomAssoc LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(libAssoc))
		{
			lstPValues.add(new PreparedLikeClause (libAssoc));
			filtre += " AND " +DB.Association.alias+".LibelleAssociation LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+ OrderBy;
		}
		
		HashMap <Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstAssoc = AssociationDAO.class.newInstance().getListAssociation(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstAssoc;
	}
	
	public static Association getAssociation (int id) {
		Association a = new Association ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		filtre += " AND " +DB.Association.alias+".id = ? ";
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			a = AssociationDAO.class.newInstance().getAssociation(filtre, params);
		} 
	    catch (IllegalAccessException | InstantiationException e) {}
        return a;
	}
	
	public static int insertAssociation (String Libelle, int idPresident, String nomAssoc,
                                         int nbParticipant, String Description) {
		
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(Libelle);
		lstPValues.add(idPresident);
		lstPValues.add(nomAssoc);
		lstPValues.add(nbParticipant);
		lstPValues.add(Description);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.InsertNewAssociation;
	    int rows = ReqMetier.executePreparedQuery(req, params);
	    return rows;	
	}
	
	public static int insertNewUserIntoAssociation (int idUtilisateur, String dateInscription,
													int idAssociation){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUtilisateur);
		lstPValues.add(dateInscription);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.InsertNewUserIntoAssociation;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}	
	
	
	public static int deleteAssociationAndDesc (int idAssociation){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idAssociation);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteAssociationAndDescPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int deleteUserAssocationFiche (int idFiche){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idFiche);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteFicheParticipantPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int deleteUserFromAssociation (int idUser, int idAssociation){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUser);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteUserFromAssociationPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateAssociation (int id, String libelle, int idPresident, String nomAssoc,
										 int nbParticipant, String description){	
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		lstPValues.add(libelle);
		lstPValues.add(idPresident);
		lstPValues.add(nomAssoc);
		lstPValues.add(nbParticipant);
		lstPValues.add(description);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateAssociation;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateParticipantByIdFicheAndIdAssoc (String dateInscription, String dateDesinscription, String notes,
															int anciennete, int idUser, int idAssoc){	
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(dateInscription);
		lstPValues.add(dateDesinscription);
		lstPValues.add(notes);
		lstPValues.add(anciennete);
		lstPValues.add(idUser);
		lstPValues.add(idAssoc);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateFicheParticipantByIdUserAndIdAssoc;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	public static int updateParticipantAssoc (String dateInscription, String dateDesinscription, String notes,
			int anciennete, int idFiche){		
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(dateInscription);
		lstPValues.add(dateDesinscription);
		lstPValues.add(notes);
		lstPValues.add(anciennete);
		lstPValues.add(idFiche);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateFicheParticipantById;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	
	
	
	
	
	
	
}
