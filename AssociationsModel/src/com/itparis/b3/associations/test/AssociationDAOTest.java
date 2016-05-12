package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.beans.Association;
import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.metier.AssociationMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AssociationDAOTest {
	
	@Parameter
	Association assoc = new Association ();
	
	@Parameter
	List<Association> lstAssoc = new ArrayList<Association> ();
	
	@Parameter
	FicheParticipant fiche = new FicheParticipant();
	
	@Test
	public void T1_getObjectAssociationDAO() {
		assertEquals("Microsoft" ,AssociationMetier.getAssociation(1).getLibelle());
		
		assertEquals(null ,AssociationMetier.getAssociation(999).getLibelle());
	}
		
    @Test
	public void T2_getListAssociationDAO() {
	    assertEquals(true, AssociationMetier.getListAssociations(0, "", "", "").size() > 0);
	    
	    assertEquals(false,AssociationMetier.getListAssociations(999, "BDS", "Bureau des sports", "").size() > 0);
	}
    
    @Test
    public void T3_insertAssociation() {
    	assertEquals (1, AssociationMetier.insertAssociation("BDA", 3, "Bureau des arts", 50, 
    			     "Association des arts et creativite"));	  
    }
    
    @Test 
    public void T4_deleteAssociation () {	
    	lstAssoc = AssociationMetier.getListAssociations(0, "BDA", "Bureau des arts", "");
    	assoc = lstAssoc.get(0);
    	assertEquals (1,AssociationMetier.deleteAssociationAndDesc(assoc.getId()));	
    }
    
    @Test 
    public void T5_insertNewUserIntoAssociation () {	
    	assertEquals (1,AssociationMetier.insertNewUserIntoAssociation(8, "2016-01-20", 1));	
    }
    
    @Test 
    public void T6_deleteUserFromAssociation () {	
    	assertEquals (1,AssociationMetier.deleteUserFromAssociation(8, 1));	
    }
	
}
