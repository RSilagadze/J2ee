package com.itparis.b3.associations.beans;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe AssociationEvent est un modele de donnees;<br>
 * Sert a representer l'evenement des associations;<br>
 * Elle a la meme structure que la table "associationevents" dans la BDD;<br>
 * Proprietes de la Classe : <br>
    - int id;<br>
    - int idAssoc;<br>
    - int nbParticipant;<br>
    - String dateEvent;<br>
    - String libelleEvent;<br>
    - String descEvent;
    + List<{@link ParticipantEvents}>lstParticipant;<br>
 * */
public class AssociationEvent {
	
	private int    id;
	private int    idAssoc;
    private int    nbParticipant;
    private String dateEvent;
    private String libelleEvent;
    private String descEvent;
    
    public List<ParticipantEvents> lstParticipant = new ArrayList<ParticipantEvents>();
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAssoc() {
		return idAssoc;
	}
	public void setIdAssoc(int idAssoc) {
		this.idAssoc = idAssoc;
	}
	public int getNbParticipant() {
		return nbParticipant;
	}
	public void setNbParticipant(int nbParticipant) {
		this.nbParticipant = nbParticipant;
	}
	public String getDateEvent() {
		return dateEvent;
	}
	public void setDateEvent(String dateEvent) {
		this.dateEvent = dateEvent;
	}
	public String getLibelleEvent() {
		return libelleEvent;
	}
	public void setLibelleEvent(String libelleEvent) {
		this.libelleEvent = libelleEvent;
	}
	public String getDescEvent() {
		return descEvent;
	}
	public void setDescEvent(String descEvent) {
		this.descEvent = descEvent;
	}
    
    
}
