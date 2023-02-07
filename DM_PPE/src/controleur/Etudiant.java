package controleur;

public class Etudiant extends Utilisateur {
	private String diplome;
	private int idCl;

	public Etudiant() {
		super();
		this.diplome= "";
		this.idCl=0;
	}

	public Etudiant(String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome, int idCl) {
		super(nom, prenom, adresse, telephone, email, mdp);
		this.diplome = diplome;
		this.idCl = idCl;
	}

	public Etudiant(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome, int idCl) {
		super(idU, nom, prenom, adresse, telephone, email, mdp);
		this.diplome = diplome;
		this.idCl = idCl;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public int getIdCl() {
		return idCl;
	}

	public void setIdCl(int idCl) {
		this.idCl = idCl;
	}
	
	
}
