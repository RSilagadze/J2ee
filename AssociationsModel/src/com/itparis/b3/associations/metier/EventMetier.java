package com.itparis.b3.associations.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.itparis.b3.associations.beans.AssociationEvent;
import com.itparis.b3.associations.beans.ParticipantEvents;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.common.ReqMetier;
import com.itparis.b3.associations.common.Utilities;
import com.itparis.b3.associations.dao.EventDAO;
import com.itparis.b3.associations.common.DB.Queries;
import com.itparis.b3.associations.common.DBSpecialValues.*;

public class EventMetier {
	
	public static ArrayList <AssociationEvent> getListAssociationEvents (int idAssoc, String libEvent, String dateEvent,
			                                                         String OrderBy) {
		ArrayList <AssociationEvent> lstEvents = new ArrayList<AssociationEvent>();
		List<Object>lstPValues = new ArrayList <Object>();
		String filtre = "";
		if (idAssoc > 0){
	        lstPValues.add(idAssoc);
			filtre += " AND "+DB.AssociationEvents.alias+".idAssociation = ? ";
		}
		if (!Utilities.isNullOrEmptyString(libEvent)){
			lstPValues.add(new PreparedLikeClause(libEvent));
			filtre += " AND " +DB.AssociationEvents.alias+".LibelleEvent LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(dateEvent)){
			lstPValues.add(dateEvent);
			filtre += " AND " +DB.AssociationEvents.alias+".dateEvent =  ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}	
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues); 
		
		try {
			lstEvents = EventDAO.class.newInstance().getListAssociationEvents(filtre,params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstEvents;	
	}
	
	public static AssociationEvent getAssociationEvent (int id) {
		AssociationEvent ae = new AssociationEvent ();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(id);
		filtre += " AND "+DB.AssociationEvents.alias+".id = ? ";
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues) ;
		
		try{
			ae = EventDAO.class.newInstance().getAssociationEvent(filtre, params);
		}
		catch (InstantiationException | IllegalAccessException e) {}
		
		return ae;
	}
	
	public static ArrayList<ParticipantEvents> getListParticipantEvents (int idAssoc, int idEvent, String 
			                                                             UserName,String OrderBy) {
		ArrayList <ParticipantEvents> lstParticipant = new ArrayList<ParticipantEvents>();
		String filtre = "";
		List<Object> lstPValues = new ArrayList<Object> ();
		if (idAssoc > 0){
			lstPValues.add(idAssoc);
		    filtre += " AND "+DB.ParticipantEvents.alias+".idAssociation = ?";
		}
		if (idEvent > 0){
			lstPValues.add(idEvent);
			filtre += " AND "+DB.ParticipantEvents.alias+".idEvenement = ? ";
		}
		if (!Utilities.isNullOrEmptyString(UserName)){
			lstPValues.add(new PreparedLikeClause(UserName));
			filtre += " AND " +DB.Utilisateurs.alias+".nomUtilisateur LIKE ? ";
		}
		if (!Utilities.isNullOrEmptyString(OrderBy)){
			filtre += " ORDER BY "+OrderBy;
		}	
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
			lstParticipant = EventDAO.class.newInstance().getListParticipantEvents(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
		
		return lstParticipant;
	}

	public static ParticipantEvents getParticipantEvent (int idAssoc, int idUser, int idEvent) {
	    ParticipantEvents pe = new ParticipantEvents();
     	String filtre = "";
     	List<Object> lstPValues = new ArrayList<Object>();
     	
		lstPValues.add(idAssoc);
	    filtre += " AND "+DB.ParticipantEvents.alias+".idAssociation = ? ";
		
		lstPValues.add(idUser);
	    filtre += " AND "+DB.ParticipantEvents.alias+".idUtilisateur = ? ";
	
		lstPValues.add(idEvent);
	    filtre += " AND " +DB.ParticipantEvents.alias+".idEvenement = ? ";
		
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		
		try {
		    pe = EventDAO.class.newInstance().getParticipantEvents(filtre, params);
		} catch (InstantiationException | IllegalAccessException e) {}
	
	    return pe;
	}
	
	public static int insertNewEvent (String dateEvent, String LibelleEvent, String descriptionEvent,
			int nbParticipant, int idAssociation) {
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(dateEvent);
		lstPValues.add(LibelleEvent);
		lstPValues.add(descriptionEvent);
		lstPValues.add(nbParticipant);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.InsertNewEvent;
	    int rows = ReqMetier.executePreparedQuery(req, params);
	    return rows;	
	}
	
	public static int insertNewParticipantEvent (int idAssociation, int idEvenement, int idUtilisateur) {
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idAssociation);
		lstPValues.add(idEvenement);
		lstPValues.add(idUtilisateur);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.InsertNewParticipantEvents;
	    int rows = ReqMetier.executePreparedQuery(req, params);
	    return rows;	
	}
	
	public static int deleteAssociationEvent (int idEvent){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idEvent);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteAssociationEventPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int deleteParticipantEvent (int idUtilisateur, int idEvenement, int idAssociation){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(idUtilisateur);
		lstPValues.add(idEvenement);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.DeleteParticipantEventPQuery;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	public static int updateEvent (String dateEvent, String libelleEvent, String descEvent, 
									int nbParticipant, int id){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(dateEvent);
		lstPValues.add(libelleEvent);
		lstPValues.add(descEvent);
		lstPValues.add(nbParticipant);
		lstPValues.add(id);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateAssociationEvents;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	
	public static int updateParticipantEvent (int presenceInt, int idUtilisateur, int idEvenement,
			                                   int idAssociation){
		List<Object> lstPValues = new ArrayList<Object>();
		lstPValues.add(presenceInt);
		lstPValues.add(idUtilisateur);
		lstPValues.add(idEvenement);
		lstPValues.add(idAssociation);
		HashMap<Integer,Object> params = Utilities.putParams(lstPValues);
		String req = Queries.UpdateParticipantsEvents;
		int rows = ReqMetier.executePreparedQuery(req, params);
		return rows;
	}
	
	
	
	
	
	

}
