package com.itparis.b3.associations.beans;
/**
 * Classe Authentification est un modele de donnees;<br>
 * Sert a representer les donnees d'authentification des utilisateurs;<br>
 * Elle a la meme structure que la table "authentification" dans la BDD;<br>
 * Proprietes de la Classe :<br> 
	- String Log;<br>
	- String Pass;<br>
	- int    idUser;<br>
 * */
public class Authentification {
	
	private String Log;
	private String Pass;
	private int    idUser;
	
	public String getLog() {
		return Log;
	}
	public void setLog(String log) {
		Log = log;
	}
	public String getPass() {
		return Pass;
	}
	public void setPass(String pass) {
		Pass = pass;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	
}
