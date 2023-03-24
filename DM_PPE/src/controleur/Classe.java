package controleur;

public class Classe {
	private int idCl, nbEtudiants;
	private String nom, diplomePrepare, promotion, email;
	
	public Classe() {
		this.idCl = 0;
		this.nbEtudiants = 0;
		this.nom = "";
		this.diplomePrepare = "";
		this.promotion = "";
		this.email = "";
	}
	
	public Classe(String nom, String diplomePrepare, String promotion, String email) {
		this.idCl = 0;
		this.nbEtudiants = 0;
		this.nom = nom;
		this.diplomePrepare = diplomePrepare;
		this.promotion = promotion;
		this.email = email;
	}
	public Classe(int idCl, String nom, String diplomePrepare, String promotion, String email) {
		this.idCl = idCl;
		this.nbEtudiants = 0;
		this.nom = nom;
		this.diplomePrepare = diplomePrepare;
		this.promotion = promotion;
		this.email = email;
	}
	
	public Classe(int idCl, int nbEtudiants, String nom, String diplomePrepare, String promotion, String email) {
		this.idCl = idCl;
		this.nbEtudiants = nbEtudiants;
		this.nom = nom;
		this.diplomePrepare = diplomePrepare;
		this.promotion = promotion;
		this.email = email;
	}

	public int getIdCl() {
		return this.idCl;
	}

	public void setIdCl(int idCl) {
		this.idCl = idCl;
	}

	public int getNbEtudiants() {
		return this.nbEtudiants;
	}

	public void setNbEtudiants(int nbEtudiants) {
		this.nbEtudiants = nbEtudiants;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDiplomePrepare() {
		return this.diplomePrepare;
	}

	public void setDiplomePrepare(String diplomePrepare) {
		this.diplomePrepare = diplomePrepare;
	}

	public String getPromotion() {
		return this.promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
