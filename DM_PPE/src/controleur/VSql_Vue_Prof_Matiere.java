package controleur;

public class VSql_Vue_Prof_Matiere {
	
	private int idPf,idM;
	private String nom,prenom,intitule;
	public VSql_Vue_Prof_Matiere(int idPf, int idM, String nom, String prenom, String intitule) {
		this.idPf = idPf;
		this.idM = idM;
		this.nom = nom;
		this.prenom = prenom;
		this.intitule = intitule;
	}
	public VSql_Vue_Prof_Matiere() {
		this.idPf = 0;
		this.idM = 0;
		this.nom = "";
		this.prenom = "";
		this.intitule = "";
	}
	public int getIdPf() {
		return this.idPf;
	}
	public void setIdPf(int idPf) {
		this.idPf = idPf;
	}
	public int getIdM() {
		return this.idM;
	}
	public void setIdM(int idM) {
		this.idM = idM;
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
	public String getIntitule() {
		return this.intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
}
