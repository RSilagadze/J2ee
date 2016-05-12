package tp6.entities;

public abstract class CommandEntity extends MagazinEntity {
	
	protected int id;
	protected String nom;
	protected String dateCommand;
	
	
	public CommandEntity (int newId, String newNom, String newDateCommand)
	{
		id	= newId;
		nom = newNom;
		nom = newNom;
		dateCommand = newDateCommand;
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


	public String getDateCommand() {
		return dateCommand;
	}


	public void setDateCommand(String dateCommand) {
		this.dateCommand = dateCommand;
	}


	@Override
	public String toString() {
		return "CommandEntity [id=" + id + ", nom=" + nom + ", dateCommand=" + dateCommand + "]";
	}
	
	

}
