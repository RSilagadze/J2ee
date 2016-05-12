package com.itparis.b3.associations.common;
import com.itparis.b3.associations.common.Table.*;

/** Classe BD;<br>
 * Contient les Requetes parametrees<br>
 * Contient Objets {@link Table}<br>
 * */
public final class DB {
	
	public static final String DBNullValue = "NULL";
	public static final String DBName = "dbassociations";
	
	public static final Table Utilisateurs = new UserTable ("utilisateurs","u");
	public static final Table TypeUtilisateurs = new TypeUserTable ("typeutilisateur","t");
	public static final Table Association = new AssociationTable ("association","a");
	public static final Table AssociationEvents = new AssociationEventsTable ("associationevents","e");
	public static final Table AssociationDesc = new AssociationDescTable ("associationdescription","d");
	public static final Table Authentification= new AuthentificationTable ("authentification","n");
	public static final Table FicheParticipant = new FicheParticipantTable ("ficheparticipant","f");
	public static final Table ParticipantEvents = new ParticipantEventsTable ("participantsevents","p");
	/**
	 * Example of usage : makeJoin ("INNER", Utilisateurs, TypeUtilisateurs, idType, id) <br>
	 * will return : "INNER JOIN typeutilisateurs t ON utilisateurs.idType = t.id " <br>
	 * First table must be specified in SQL query before calling this method <br>
	 * @param JoinType : String
	 * @param Table1 : Table
	 * @param Table2 : Table
	 * @param idKey1 : String
	 * @param idKey2 : String
	 * @return str : String
	 */
	public static String makeJoin (String JoinType, Table Table1, Table Table2, String idKey1, String idKey2) {
		String str = " " + JoinType.toUpperCase() + " JOIN " + Table2 + " " + Table2.alias + " ON " +
			               Table1.alias + "." + idKey1 + "="+Table2.alias + "." + idKey2 + " ";
		return str;
	}
	
	public static class Queries {

		public static final String GetUserQuery = 
				" SELECT "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
		        " "+Utilisateurs.alias+".adrUtilisateur, "+Utilisateurs.alias+".telUtilisateur," +
				" "+Utilisateurs.alias+".idType, "+Utilisateurs.alias+".id, "+Utilisateurs.alias+".LogoImg, " + 
		        " "+TypeUtilisateurs.alias+".Libelle" + 
		        " FROM " +Utilisateurs+" "+Utilisateurs.alias +
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id") +
				" Where 1=1 AND " +Utilisateurs.alias+".Statut = 1 ";
		
		public static final String GetLoginPassPQuery = 
				" SELECT Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ; ";
		
        public static final String GetAssociationQuery = 
        		" SELECT "+Association.alias+".id, "+Association.alias+".LibelleAssociation, "+Association.alias+".LogoImg ," +
                " "+AssociationDesc.alias+ ".id as idDesc,"+
                " "+AssociationDesc.alias+ ".idPresident,"+AssociationDesc.alias+ ".nomAssoc,"+
                " "+AssociationDesc.alias+ ".nbParticipant,"+AssociationDesc.alias+ ".Description"+
        		" FROM "+Association+" "+Association.alias +
        		" "+makeJoin("LEFT", Association, AssociationDesc, "id", "idAssociation") +
        		" Where 1=1 " ;
        	
		public static final String GetUserDataPQuery = 
				" SELECT idUtilisateur, Login, MDP From " + Authentification + " Where Login = ? and MDP = ? ; ";
				
		public static final String GetEventQuery = 
				" SELECT "+AssociationEvents.alias+".id,"+AssociationEvents.alias+".dateEvent," +
		        " "+AssociationEvents.alias+".LibelleEvent, "+AssociationEvents.alias+".descriptionEvent," +
		        " "+AssociationEvents.alias+".nbParticipant,"+AssociationEvents.alias+".idAssociation" +
		        " FROM "+AssociationEvents+" "+AssociationEvents.alias+
		        " WHERE 1=1 ";
		
		public static final String GetEventParticipantQuery = 
				" SELECT "+ParticipantEvents.alias+".idAssociation,"+ParticipantEvents.alias+".idUtilisateur,"+
				" "+ParticipantEvents.alias+".Presence,"+ParticipantEvents.alias+".idEvenement,"+
				" "+Utilisateurs.alias+".nomUtilisateur,"+Utilisateurs.alias+".prenomUtilisateur,"+
				" "+TypeUtilisateurs.alias+".Libelle"+
				" FROM "+ParticipantEvents+" "+ParticipantEvents.alias +
				" "+makeJoin("LEFT", ParticipantEvents, Utilisateurs, "idUtilisateur", "id") +
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id") +
				" WHERE 1=1 AND " +Utilisateurs.alias+".Statut = 1 ";
		
		public static final String GetFicheParticipantQuery =
				" SELECT "+FicheParticipant.alias+".id,"+FicheParticipant.alias+".idUtilisateur,"+
				" "+FicheParticipant.alias+".idAssociation,"+FicheParticipant.alias+".dateInscription,"+
				" "+FicheParticipant.alias+".dateDesinscription,"+FicheParticipant.alias+".Notes,"+
				" "+FicheParticipant.alias+".Anciennete,"+
				" "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
		        " "+Utilisateurs.alias+".adrUtilisateur, "+Utilisateurs.alias+".telUtilisateur," +
		        " "+TypeUtilisateurs.alias+".Libelle"+
				" FROM "+FicheParticipant+" "+FicheParticipant.alias+
				" "+makeJoin("LEFT", FicheParticipant, Utilisateurs, "idUtilisateur", "id")+
				" "+makeJoin("LEFT", Utilisateurs, TypeUtilisateurs, "idType", "id")+
				" WHERE 1=1 AND " +Utilisateurs.alias+".Statut = 1 ";
		
		public static final String GetUserSimple = 
				" SELECT "+Utilisateurs.alias+".nomUtilisateur, "+Utilisateurs.alias+".prenomUtilisateur," +
				" "+Utilisateurs.alias+".id,"+Utilisateurs.alias+".Statut"+ 
				" FROM " +Utilisateurs+" "+Utilisateurs.alias +
				" Where 1=1 ";
		
		public static final String GetTypeUserQuery = 
				" SELECT " + TypeUtilisateurs.alias + ".id, " + TypeUtilisateurs.alias + ".Libelle " +
				" FROM " + TypeUtilisateurs + " " + TypeUtilisateurs.alias + " WHERE 1=1 ";

		public static final String DeleteTypeUserPQuery = 
				" DELETE FROM "+TypeUtilisateurs+" WHERE "+TypeUtilisateurs+".id = ? ;";
		
		public static final String DeleteParticipantEventPQuery = 
				" DELETE FROM "+ParticipantEvents+" WHERE "+ParticipantEvents+".idUtilisateur = ? "+
		        " AND "+ParticipantEvents+".idEvenement = ? AND "+ParticipantEvents+".idAssociation = ? ;";
				
		public static final String DeleteFicheParticipantPQuery = 
				" DELETE FROM "+FicheParticipant+" WHERE "+FicheParticipant+".id = ? ;";
		
		public static final String DeleteUserFromAssociationPQuery = 
				" DELETE FROM "+FicheParticipant+" WHERE "+FicheParticipant+".idUtilisateur = ? " + 
		        " AND "+FicheParticipant+".idAssociation = ? ;";
		
		public static final String DeleteAuthentificationPQuery = 
				" DELETE FROM "+Authentification+" WHERE "+Authentification+".idUtilisateur = ? ;";
		
		public static final String DeleteAssociationEventPQuery = 
				" DELETE FROM "+AssociationEvents+" WHERE "+AssociationEvents+".id = ? ;";
		
		public static final String DeleteAssociationAndDescPQuery = 
				" DELETE FROM "+AssociationDesc+" WHERE "+AssociationDesc+".idAssociation = ? ;" +
				" DELETE FROM "+Association+" WHERE "+Association+".id = ? ;" ;
		
		public static final String DeleteUserAndAuthPQuery =  
			    " DELETE FROM "+Authentification+" WHERE "+Authentification+".idUtilisateur = ? ;" +
				" DELETE FROM "+Utilisateurs+" WHERE "+Utilisateurs+".id = ? ; ";
		
		public static final String InsertNewUser = 
			    " INSERT INTO "+Utilisateurs+" (idType, nomUtilisateur, prenomUtilisateur, adrUtilisateur"+
			    " , telUtilisateur, Statut) "+
			    " VALUES (?, ?, ?, ?, ?, ?); " +
				" SET @id = LAST_INSERT_ID(); " +
				" INSERT INTO "+Authentification+" (idUtilisateur, Login, MDP) " +
				" VALUES (@id, ?, ?); ";
		
		public static final String InsertNewAssociation = 
			    " INSERT INTO " + Association + " (LibelleAssociation) VALUES ( ? ); " + 
				" SET @id = LAST_INSERT_ID(); " +
				" INSERT INTO " + AssociationDesc + " (idAssociation, idPresident, nomAssoc ," + 
				" nbParticipant, Description) " +
				" VALUES (@id, ?, ?, ?, ?); ";
				
		public static final String InsertNewTypeUser = 
				"INSERT INTO " + TypeUtilisateurs + " (id, Libelle) VALUES (? , ? );"; 
		
		public static final String InsertNewEvent = 
				"INSERT INTO " + AssociationEvents + " (dateEvent, LibelleEvent, descriptionEvent,"+
				"nbParticipant, idAssociation) "+
			    "VALUES (?, ?, ?, ?, ? );";
		
		public static final String InsertNewUserIntoAssociation = 
				"INSERT INTO " + FicheParticipant + " (idUtilisateur, dateInscription, idAssociation)"+
			    "VALUES (?, ?, ? );";
		
		public static final String InsertNewParticipantEvents = 
				"INSERT INTO " + ParticipantEvents + " (idAssociation, idEvenement, idUtilisateur)"+
			    "VALUES (?, ?, ? );";

		public static final String CheckExistingFieldPQuery (Table table, String field)
		{
		  return " SELECT IF (EXISTS " +
		  		 "(SELECT " + field + " FROM " + table + " WHERE " + field + " = ? ), 1, 0) " +
		  		 " AS Existence; ";
		}

		public static final String UpdateAssociation = 
				" SET @id = ?;" + 
				" UPDATE "+Association+" SET libelleAssociation = ? WHERE id = @id; " +
				" UPDATE "+AssociationDesc+" SET idPresident = ?, nomAssoc = ?, nbParticipant = ?, "+
			    " Description = ? WHERE id = @id; ";	
		
		public static final String UpdateAssociationEvents = 
				" UPDATE " +AssociationEvents+" SET dateEvent = ?, LibelleEvent = ?, "+
			    " descriptionEvent = ?, nbParticipant = ? " +
				" WHERE id = ? ";
		
		public static final String UpdateAuthentification = 
				" UPDATE "+Authentification+" SET Login = ?, MDP = ? WHERE idUtilisateur = ? ";
		
		public static final String UpdateFicheParticipantById = 
				" UPDATE " +FicheParticipant+" SET dateInscription = ?, dateDesinscription = ?, "+
			    " Notes = ?, Anciennete = ? " +
				" WHERE id = ? ";
		
		public static final String UpdateFicheParticipantByIdUserAndIdAssoc = 
				" UPDATE " +FicheParticipant+" SET dateInscription = ?, dateDesinscription = ?, "+
			    " Notes = ?, Anciennete = ? " +
				" WHERE idUtilisateur = ? AND idAssociation = ? ";
		
		public static final String UpdateParticipantsEvents = 
				" UPDATE "+ParticipantEvents+" SET Presence = ? WHERE " +
				" idUtilisateur = ? AND idEvenement = ? AND idAssociation = ? ";
		
		public static final String UpdateTypeUtilisateur = 
				" UPDATE "+TypeUtilisateurs+" SET Libelle = ? WHERE id = ? ";
		
		public static final String UpdateUtilisateur = 
				" UPDATE "+Utilisateurs+" SET nomUtilisateur = ?, prenomUtilisateur = ?, " +
				" adrUtilisateur = ?, telUtilisateur = ? " +
		        " WHERE id = ? And Statut = 1 ";
		
		public static final String UpdateUtilisateurForAdmin = 
				" UPDATE "+Utilisateurs+" SET idType = ?, nomUtilisateur = ?, prenomUtilisateur = ?, " +
				" adrUtilisateur = ?, telUtilisateur = ?, Statut = ? " +
		        " WHERE id = ? ";
	}
}
