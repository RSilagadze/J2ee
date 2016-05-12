package com.itparis.b3.associations.beans;

/**
 * Classe Association est un modele de donnees;<br>
 * Sert a representer les associations;<br>
 * Elle a la meme structure que la table "association" dans la BDD;<br>
 * Proprietes de la classe : <br>
     - int id;<br>
     - String Libelle;<br>
     + AssociationDesc desc;<br>
 */
public class Association {
	
	private int    id;
	private String Libelle;
	private String logoImg;
	
	public AssociationDesc desc = new AssociationDesc();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
}
