package controleur;

public class Trajet {
	private String idSt;
	private int idE;
	
	public Trajet(String idSt, int idE) {
		this.idSt = idSt;
		this.idE = idE;
	}
	public Trajet() {
		this.idSt = "";
		this.idE = 0;
	}
	public String getIdSt() {
		return this.idSt;
	}
	public void setIdSt(String idSt) {
		this.idSt = idSt;
	}
	public int getIdE() {
		return this.idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	
}
