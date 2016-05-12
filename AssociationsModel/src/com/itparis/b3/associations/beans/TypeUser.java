package com.itparis.b3.associations.beans;
/**
 * Classe TypeUser est un modele de donnees;<br>
 * Sert a representer le type d'utilisateur;<br>
 * Elle a la meme structure que la table "typeutilisateur" dans la BDD;<br>
 * Proprietes de la Classe : <br>
	- int    id;<br>
	- String Libelle;<br>
 * */
public class TypeUser {
	
	private int    id;
	private String Libelle;
	
	public int getId() {
		return id;
	}
	public void setId(int idType) {
		this.id = idType;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
}
