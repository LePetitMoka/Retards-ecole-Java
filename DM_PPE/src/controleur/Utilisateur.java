package controleur;

public class Utilisateur {
	protected int idU;
	protected String nom, prenom, adresse, telephone, email, mdp;
	
	public Utilisateur() {
		this.idU = 0;
		this.nom = "";
		this.prenom = "";
		this.adresse = "";
		this.telephone = "";
		this.email = "";
		this.mdp = "";
	}
	
	public Utilisateur(String nom, String prenom, String adresse, String telephone, String email, String mdp) {
		this.idU = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
	}
	
	public Utilisateur(int idU, String nom, String prenom, String adresse, String telephone, String email, String mdp) {
		this.idU = idU;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
	}

	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
