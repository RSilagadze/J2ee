package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameter;

import com.itparis.b3.associations.beans.FicheParticipant;
import com.itparis.b3.associations.beans.User;
import com.itparis.b3.associations.metier.UserMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDAOTest {
	
	@Parameter
	User user = new  User ();
	
	@Parameter
	FicheParticipant fParticipant = new  FicheParticipant ();

	@Parameter
	List<User> lstUser = new ArrayList<User> ();
	
	@Test
	public void T1_getObjectUserDAO() {
        assertEquals("Dupuis", UserMetier.getUser(8).getNom());
     
        assertEquals(null, UserMetier.getUser(999).getNom());
        
        assertEquals("Aloin", UserMetier.getUserForAdmin(7).getPrenom());
        
        assertEquals(null, UserMetier.getUserForAdmin(999).getPrenom());
	}

	@Test
	public void T2_getListUserDAO() {
		 assertEquals(true, UserMetier.getListUsers(0, 0, "").size() > 0);
		 
		 assertEquals(false, UserMetier.getListUsers(999, 999, "").size() > 0);
		 
		 assertEquals(true, UserMetier.getListUsersForAdmin(1, "", "").size() > 0);
		 
		 assertEquals(false, UserMetier.getListUsersForAdmin(999, "Monnom", "").size() > 0); 
	}
	
	@Test
	public void T3_getObjectUserTypeDAO() {
		 assertEquals("Administrateur Appli", UserMetier.getUserType(1).getLibelle());
		 
		 assertEquals(null, UserMetier.getUserType(999).getLibelle());
	}
	
	@Test
	public void T4_getListUserTypeDAO() {
		 assertEquals(true, UserMetier.getListUserType("", "").size() == 3);
		 
		 assertEquals(false, UserMetier.getListUserType("Administrateur reseau","").size() > 0);
	}
	
	@Test
	public void T5_getListFicheParticipantDAO() {
		 assertEquals(true, UserMetier.getListFicheParticipant(0, 0, 0, "", "").size() > 0);
		 
		 assertEquals(false, UserMetier.getListFicheParticipant(999, 999, 1, "", "").size() > 0);
	}
	
	@Test
	public void T6_getObjectFicheParticipantDAO() {
		 assertEquals("23-12-2015", UserMetier.getFicheParticipant(2).getDateInscription());
		 
		 assertEquals("30-12-2015", UserMetier.getFicheParticipantByIdUserAndIdAssoc(3, 1).getDateInscription());
	}
	
	@Test
	public void T7_insertNewUserDAO() {
		 assertEquals(1, UserMetier.insertNewUser(3, "Frost", "Jack", "12 av de Charles de Gaulle", 
				                                  "0122479628", -1, "login5", "mdp5"));
	}
	
	@Test
	public void T8_insertNewUserTypeDAO() {
		 assertEquals(1, UserMetier.insertNewTypeUserVerification(10, "Administrateur reseau"));
	}
	
	@Test
	public void T9_deleteUserTypeDAO() {
		 assertEquals(1, UserMetier.deleteTypeUser(10));
	}
	
	@Test
	public void T9A_deleteUserDAO() {
		 user = UserMetier.getListUsersForAdmin(-1, "Frost", "").get(0);
		 assertEquals(1, UserMetier.deleteUser(user.getId()));
	}
	
}
