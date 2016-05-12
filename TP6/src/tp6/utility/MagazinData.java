package tp6.utility;

import tp6.beans.EntityList;
import tp6.entities.Article;
import tp6.entities.Client;

public class MagazinData {
		
	public static final EntityList <Article> lstArticles = new EntityList<Article> (true, Article.class);
	
	public static final EntityList <Client> lstClients = new EntityList <Client> (true, Client.class);
	
}
