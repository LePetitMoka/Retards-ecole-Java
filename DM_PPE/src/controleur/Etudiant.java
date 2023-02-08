package controleur;

public class Etudiant extends Utilisateur {
	private int idCl;

	public Etudiant() {
		super();
		this.idCl=0;
	}

	public Etudiant(String nom, String prenom, String adresse, String telephone, String email, String mdp, int idCl) {
		super(nom, prenom, adresse, telephone, email, mdp);
		this.idCl = idCl;
	}

	public Etudiant(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp, int idCl) {
		super(idU, nom, prenom, adresse, telephone, email, mdp);
		this.idCl = idCl;
	}

	public int getIdCl() {
		return idCl;
	}

	public void setIdCl(int idCl) {
		this.idCl = idCl;
	}
	
	
}
