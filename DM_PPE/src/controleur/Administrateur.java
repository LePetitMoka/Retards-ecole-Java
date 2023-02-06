package controleur;

public class Administrateur extends Utilisateur {
	private String urlSignature;

	public Administrateur() {
		super();
		this.urlSignature = "";
	}

	public Administrateur(String nom, String prenom, String adresse, String telephone, String email, String mdp, String urlSignature) {
		super(nom, prenom, adresse, telephone, email, mdp);
		this.urlSignature = urlSignature;
	}

	public Administrateur(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp, String urlSignature) {
		super(idU, nom, prenom, adresse, telephone, email, mdp);
		this.urlSignature = urlSignature;
	}

	public String getUrlSignature() {
		return urlSignature;
	}

	public void setUrlSignature(String urlSignature) {
		this.urlSignature = urlSignature;
	}
}
