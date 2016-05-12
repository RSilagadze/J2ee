package tp6.entities;

import tp6.beans.EntityList;

public final class ArticleCommand extends CommandEntity {
	
	public EntityList<Article> lstArticle = new EntityList <Article> ();

	public ArticleCommand(int newId, String newNom, String newDateCommand) {
		super(newId, newNom, newDateCommand);
	}

}
