package tp6.entities;

public abstract class PersonEntity extends MagazinEntity {
	
	protected int id;
	protected String nom;
	protected String prenom;
	protected String dateInscription;
	protected String login;
	protected String mdp;
	
	public PersonEntity (int newId, String newNom, String newPrenom, String newDateInscription, 
							String newLogin,String newMdp)
	{
		id	= newId;
		nom = newNom;
		prenom = newPrenom;
		dateInscription = newDateInscription;
		login = newLogin;
		mdp = newMdp;
	}

	@Override
	public String toString() {
		return "PersonEntity [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateInscription=" + dateInscription
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
