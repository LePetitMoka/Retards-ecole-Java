package controleur;

public class VSql_Vue_TotalBilletEleve {
	private int IdE, nbBillets;
	private String nomprenom, dureeCumulee;
	public VSql_Vue_TotalBilletEleve(int idE, int nbBillets, String nomprenom, String dureeCumulee) {
		this.IdE = idE;
		this.nbBillets = nbBillets;
		this.nomprenom = nomprenom;
		this.dureeCumulee = dureeCumulee;
	}
	public int getIdE() {
		return this.IdE;
	}
	public void setIdE(int idE) {
		this.IdE = idE;
	}
	public int getNbBillets() {
		return this.nbBillets;
	}
	public void setNbBillets(int nbBillets) {
		this.nbBillets = nbBillets;
	}
	public String getNomprenom() {
		return this.nomprenom;
	}
	public void setNomprenom(String nomprenom) {
		this.nomprenom = nomprenom;
	}
	public String getDureeCumulee() {
		return this.dureeCumulee;
	}
	public void setDureeCumulee(String dureeCumulee) {
		this.dureeCumulee = dureeCumulee;
	}
	
	
}
