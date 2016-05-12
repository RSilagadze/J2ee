package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.beans.AssociationEvent;
import com.itparis.b3.associations.metier.EventMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventDAOTest {
	
	@Parameter
	AssociationEvent event = new  AssociationEvent ();
	
	@Parameter
	List<AssociationEvent> lstEvent = new ArrayList<AssociationEvent> ();
	
	@Test
	public void T1_getListEventAssociation () {
		assertEquals(true, EventMetier.getListAssociationEvents(0, "", "", "").size() > 0);
		
		assertEquals(false, EventMetier.getListAssociationEvents(999, "", "", "").size() > 0);
	}
	
	@Test
	public void T2_getObjectEventAssociation () {
		assertEquals("Programmation", EventMetier.getAssociationEvent(1).getLibelleEvent());
		
		assertEquals(null, EventMetier.getAssociationEvent(999).getLibelleEvent());
	}
	
	@Test
	public void T3_getListEventParticipant () {
		assertEquals(true, EventMetier.getListParticipantEvents(0, 0, "", "").size() > 0);
		
		assertEquals(false, EventMetier.getListParticipantEvents(999, 999, "Dummy", "").size() > 0);
	}
	
	@Test
	public void T4_getObjectEventParticipant () {
		assertEquals("Jobs", EventMetier.getParticipantEvent(1,2,1).utilisateur.getNom());

		assertEquals(null, EventMetier.getParticipantEvent(999,999,999).utilisateur.getNom());
	}
	
	@Test
	public void T5_insertNewEvent () {
		assertEquals(1, EventMetier.insertNewEvent("2016-01-20", "Programmation", "Pratique de l'Objective C", 
													6, 2));
	}
	
	@Test
	public void T6_insertParticipantEvent() {
		event =  EventMetier.getListAssociationEvents(2, "Programmation", "2016-01-20", "").get(0);
		
		assertEquals(1, EventMetier.insertNewParticipantEvent(2, event.getId(), 4));	
	}
	
	@Test
	public void T7_deleteParticipantEvent () {
		event =  EventMetier.getListAssociationEvents(2, "Programmation", "2016-01-20", "").get(0);
		
		assertEquals(1, EventMetier.deleteParticipantEvent(4, event.getId(), 2));
	}
	
	@Test
	public void T8_deleteEvent () {
		event =  EventMetier.getListAssociationEvents(2, "Programmation", "2016-01-20", "").get(0);
		
		assertEquals(1, EventMetier.deleteAssociationEvent(event.getId()));
	}
	
	
	
	
	

}
