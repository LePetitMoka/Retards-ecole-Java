package controleur;

public class Etudiant extends Utilisateur {
	private String diplome;

	public Etudiant() {
		super();
		this.diplome= "";
	}

	public Etudiant(String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome) {
		super(nom, prenom, adresse, telephone, email, mdp);
		this.diplome = diplome;
	}

	public Etudiant(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome) {
		super(idU, nom, prenom, adresse, telephone, email, mdp);
		this.diplome = diplome;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	
}
