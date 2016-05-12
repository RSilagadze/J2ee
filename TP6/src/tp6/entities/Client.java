package tp6.entities;

import tp6.beans.EntityList;

public final class Client extends PersonEntity{
	
	public EntityList<ArticleCommand> lstCommand = new EntityList<ArticleCommand> ();
	
	public Client(int newId, String newNom, String newPrenom, String newDateInscription, 
					String newLogin, String newMdp) {
		super(newId, newNom, newPrenom, newDateInscription, newLogin, newMdp);
	}
	
}
