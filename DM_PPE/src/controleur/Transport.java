package controleur;

public class Station {
	
	private String IdSt;
	private String nom;
	private String type;
	private String transporteur;
	private String pictogramme;
	private String etat;
	
	public Station(String IdSt, String nom, String type, String transporteur, String pictogramme, String etat) {
		this.IdSt  = IdSt;
		this.nom = nom;
		this.type = type;
		this.transporteur = transporteur;
		this.pictogramme = pictogramme;
		this.etat = etat;
	}
	public Station() {
		this.IdSt  = "";
		this.nom = "";
		this.type = "";
		this.transporteur = "";
		this.pictogramme = "";
		this.etat = "";
	}
	public String getIdSt() {
		return this.IdSt;
	}
	public void setIdSt(String IdSt) {
		this.IdSt = IdSt;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTransporteur() {
		return this.transporteur;
	}
	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}
	public String getPictogramme() {
		return this.pictogramme;
	}
	public void setPictogramme(String pictogramme) {
		this.pictogramme = pictogramme;
	}
	public String getEtat() {
		return this.etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	
}
