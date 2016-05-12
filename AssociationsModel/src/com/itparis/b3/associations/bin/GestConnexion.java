package com.itparis.b3.associations.bin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
 * Gestionnaire de Connexion a la BDD<br>
 * Ouvre le state automatiquement a la BDD<br>
 * Retourne l'objet de State<br>
 * Fermer le State et la Connexion<br>
 * 
 * Les proprietes de la Classe "GestConnexion" pointent vers les proprietes de la Classe "Connexion"<br>
 * @return st {@link Statement} <br>
 * @version 0.1 beta-test <br>
 */
public final class GestConnexion {

	private Connection con = null;
	private Statement st = null;
	private static GestConnexion instance = null;
	
	private Statement returnStatement () {
		try {
			instance = new GestConnexion ();
			con = Connexion.getConnection();
			st = con.createStatement();
		}
		catch (Exception e) {e.getMessage();e.printStackTrace();}
		return st;
	}
	
	public static Statement getStatement () {
		return instance.returnStatement();
	}
	
	private PreparedStatement returnPreparedStatement (String preparedQuery) {
		try {
			instance = new GestConnexion ();
			con = Connexion.getConnection();
	        st = (PreparedStatement) con.prepareStatement(preparedQuery);
		}
		catch (Exception e) {e.getMessage();e.printStackTrace();}
		return (PreparedStatement) instance.st;
		
	}
	
	public static PreparedStatement getPreparedStatement (String preparedQuery){
    	return instance.returnPreparedStatement(preparedQuery);
	}
	
	public static void closeConnection () {
		try {
		if (instance.st != null)
			instance.st.close();
		if (instance.con != null)
			instance.con.close();
		if (instance != null )
			instance.finalize();
		}
		catch (Throwable e){e.getMessage();e.printStackTrace();}
	}
}
