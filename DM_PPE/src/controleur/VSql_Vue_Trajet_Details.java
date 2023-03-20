package controleur;

public class VSql_Vue_Trajet_Details {
	private String nom, prenom, arret, transports, idst;
	private int ide;
	
	public VSql_Vue_Trajet_Details(String nom, String prenom, String arret, String transports, int ide, String idst) {
		this.nom = nom;
		this.prenom = prenom;
		this.arret = arret;
		this.transports = transports;
		this.ide = ide;
		this.idst = idst;
	}
	public VSql_Vue_Trajet_Details(int ide, String idst) {
		this.nom = "";
		this.prenom = "";
		this.arret = "";
		this.transports = "";
		this.ide = 0;
		this.idst = "";
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getArret() {
		return this.arret;
	}
	public void setArret(String arret) {
		this.arret = arret;
	}
	public String getTransports() {
		return this.transports;
	}
	public void setTransports(String transports) {
		this.transports = transports;
	}
	public int getIde() {
		return this.ide;
	}
	public void setIde(int ide) {
		this.ide = ide;
	}
	public String getIdst() {
		return this.idst;
	}
	public void setIdst(String idst) {
		this.idst = idst;
	}
	
}
