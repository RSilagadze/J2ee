package com.itparis.b3.associations.beans;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe User est un modele de donnees;<br>
 * Sert a representer l'utilisateur;<br>
 * Elle a la meme structure que la table "utilisateurs" dans la BDD;<br>
 * Proprietes de la Classe : <br>
	- int    id;<br>
	- String Nom;<br>
	- String Prenom;<br>
	- String Adresse;<br> 
	- String Telephone;<br>
	
	+ List<{@link Association}> assoc;<br>
	+ TypeUser type;<br>
 * */
public class User {
	
	private int    id;
	private String Nom;
	private String Prenom;
	private String Adresse;
	private String Telephone;
	private int Statut;
	private String StatutString;
	private String logoImg;
	
	public List<Association> assoc =  new ArrayList<Association>();
	public TypeUser type = new TypeUser ();
	
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
	}
	
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	public String getTelephone() {
		return Telephone;
	}
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	public int getId() {
		return id;
	}
	public void setId(int idUser) {
		this.id = idUser;
	}

	public int getStatut() {
		return Statut;
	}

	public void setStatut(int statut) {
		this.Statut = statut;	
		switch (statut) {
		case -1:
			StatutString = "Inactif";
	    	break;
		case 0:
			StatutString = "Desactivee";
		    break;
		case 1:
			StatutString = "Actif";
	     	break;
	   }
	}

	public String getStatutString() {
		return StatutString;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
}
