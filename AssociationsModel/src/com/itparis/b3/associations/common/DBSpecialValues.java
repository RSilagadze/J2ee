package com.itparis.b3.associations.common;
/**
 * Classe DBSpecialValues <br>
 * Contient les sous-Classes qui retournent clauses particulieres telles que "LIKE '% %'", etc <br>
 * Les instances de la classe {@link PreparedLikeClause} sont utilisees dans les DAO avec requetes preparees<br>
 *
 */
public class DBSpecialValues {
	
      public static class PreparedLikeClause {
    	  
    	private String value;
    	
    	public PreparedLikeClause (String newValue) {
    		value = newValue;
    	}
    	
		@Override
		public String toString() {
			return "%"+value+"%";
		}  
    	  
      }
}
