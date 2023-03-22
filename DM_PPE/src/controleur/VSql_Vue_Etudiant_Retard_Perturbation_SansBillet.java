package controleur;

public class VSql_Vue_Etudiant_Retard_Perturbation_SansBillet {
	
	//Vue Etudiants perturbés ET en retard (censés etre en cours actuellement) ET sans billet de la journée
	
	private int IdE;
	private String duree,nom,prenom;
	
	public VSql_Vue_Etudiant_Retard_Perturbation_SansBillet(int idE, String duree, String nom, String prenom) {
		IdE = idE;
		this.duree = duree;
		this.nom = nom;
		this.prenom = prenom;
	}
	public VSql_Vue_Etudiant_Retard_Perturbation_SansBillet() {
		IdE = 0;
		this.duree = "";
		this.nom = "";
		this.prenom = "";
	}
	public int getIdE() {
		return this.IdE;
	}
	public void setIdE(int idE) {
		this.IdE = idE;
	}
	public String getDuree() {
		return this.duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
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
	
	
	
}
