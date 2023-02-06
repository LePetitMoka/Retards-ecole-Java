package controleur;

public class Professeur extends Utilisateur {
	private String diplome;

	public Professeur() {
		super();
		this.diplome = "";
	}

	public Professeur(String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome) {
		super(nom, prenom, adresse, telephone, email, mdp);
		this.diplome = diplome;
	}

	public Professeur(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp, String diplome) {
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
