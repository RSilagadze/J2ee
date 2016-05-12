package com.itparis.b3.associations.utilities;

public abstract class Prefs {
	
	public static final String command_connection = "connection";
	public static final String action_login = "login";
	public static final String action_logout = "logout";
	
	public static final String command_association = "association";
	public static final String action_addAssociation = "addAssociation";
	public static final String action_deleteAssociation = "deleteAssociation";
	public static final String action_viewAssociation = "viewAssociation";
	public static final String action_modAssociation = "modAssociation";
	public static final String action_manageAssoc = "chargergestionnaire";
	
	public static final String command_user = "user";
	public static final String action_addUser = "addUser";
	public static final String action_deleteUser = "deleteUser";
	public static final String action_viewUser = "viewUser";
	public static final String action_modUser = "modUser";
	
	public static final String command_event = "event";
	public static final String action_addEvent = "addEvent";
	public static final String action_viewEvent = "viewEvent";
	
	public static final int AdminAppli = 1;
	public static final int AdminAssoc = 2;
	public static final int User = 3;	
}
