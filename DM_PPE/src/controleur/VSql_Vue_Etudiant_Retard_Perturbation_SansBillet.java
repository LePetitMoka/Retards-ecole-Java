package controleur;

public class VSql_Vue_Etudiant_Retard_Perturbation_SansBillet {
	
	//Vue Etudiants perturbés ET en retard (censés etre en cours actuellement) ET sans billet de la journée
	
	private int IdE;
	private String duree;
	
	public VSql_Vue_Etudiant_Retard_Perturbation_SansBillet(int idE, String duree) {
		this.IdE = idE;
		this.duree = duree;
	}
	public VSql_Vue_Etudiant_Retard_Perturbation_SansBillet() {
		this.IdE = 0;
		this.duree = "";
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
	
}
