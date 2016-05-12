package com.itparis.b3.associations.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.itparis.b3.associations.bin.Connexion;
import com.itparis.b3.associations.common.Utilities;

public class CommonDAO {

	
public int existsTableField (HashMap<Integer,Object> params, String req) {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
		    con = Connexion.getConnection();
		    st = con.prepareStatement(req);

		    Utilities.setSQLParams(st, params);
		    
		    rs = st.executeQuery();
		    if (rs.next()) {		            
		    	row = rs.getInt("Existence");
	        }
		}
		catch (Exception e){
			e.getMessage();
			e.printStackTrace();
		}
	    try {
	    	if (rs != null) rs.close();
	    	if (st != null) st.close();
	    	if (con != null) con.close();
	    }
	    catch (Exception e){}
		return row;
	}
	
	
}
