package com.itparis.b3.associations.test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import com.itparis.b3.associations.common.DB;
import com.itparis.b3.associations.metier.CommonMetier;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonDAOTest {

	@Test
	public void DAOTest () {
	    
		assertEquals(1, CommonMetier.checkExistingField(DB.TypeUtilisateurs, "id", 1));
		
		assertEquals(0, CommonMetier.checkExistingField(DB.TypeUtilisateurs, "id", -1));
		
	}

}
