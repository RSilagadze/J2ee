package com.itparis.b3.associations.bin;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Classe Connexion;<br>
 * Contient les methodes et parametres de connexion a la BDD;<br>
 * @return instance {@link Connection}<br>
 * */
public final class Connexion  {
	
	private static final String host = "localhost";       //"localhost";
	private static final String log = "root";        //"root";
	private static final String pass = "";       //"";
	private static final String db = "dbassociations";         //"dbassociations";
	private static final String driver = "jdbc:mysql:";     //"jdbc:mysql:";
	private static final String driverClass = "com.mysql.jdbc.Driver";//"com.mysql.jdbc.Driver";
	private static final String dbparams = "?convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&allowMultiQueries=true";

	private static Connexion instance = new Connexion();
	
    private Connection createConnection () 
	{
    	Connection con = null;
    	try{
    		Class.forName(driverClass);
	        try {
	            con = DriverManager.getConnection(driver + "//" + host + "/" + db + dbparams, log, pass);
	        }
	        catch (Exception e) {
	        	e.getMessage();
	        	System.out.println("connection could not be opened ");
	        }
    	}
    	catch (Exception e){
    		e.getMessage();
    		System.out.println ("Driver not found");
    	}
        return con;
	}
    
    public static Connection getConnection ()
    {
    	return instance.createConnection();
    }  
}
