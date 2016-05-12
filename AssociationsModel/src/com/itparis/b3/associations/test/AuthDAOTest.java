package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.itparis.b3.associations.metier.AuthMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthDAOTest {

	@Test
	public void T1_getStatusAuthDAOTest() {
		assertEquals (true, AuthMetier.CheckAuthentification("log", "mdp"));
	}
	
	@Test
	public void T2_getUserAuthDataDAOTest() {
		assertEquals (2, AuthMetier.getUserAuthData("login1", "mdp1").getIdUser());
	}
}
