package controleur;

public class Enseigner {
	private int idM, idPf;
	
	public Enseigner (int idM, int idPf) {
		this.idM = idM;
		this.idPf = idPf;
	}
	public Enseigner () {
		this.idM = 0;
		this.idPf = 0;
	}
	public int getIdM() {
		return this.idM;
	}

	public void setIdM(int idM) {
		this.idM = idM;
	}

	public int getIdPf() {
		return this.idPf;
	}

	public void setIdPf(int idPf) {
		this.idPf = idPf;
	}
}
