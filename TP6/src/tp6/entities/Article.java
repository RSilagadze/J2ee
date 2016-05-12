package tp6.entities;

public final class Article extends MagazinEntity{
	
	private int id;
	private int quantite;
	private int prix;
	private String nom;
	private int qteCommande = 0;
	
	public Article (int newId, int newQuantite, int newPrix, String newNom)
	{
		id	= newId;
		quantite = newQuantite;
		prix = newPrix;
		nom = newNom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public int getQteCommande() {
		return qteCommande;
	}

	public void setQteCommande(int qteCommande) {
		this.qteCommande = qteCommande;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", quantite=" + quantite + ", prix=" + prix + ", nom=" + nom + "]";
	}
	
	
	
	

}
