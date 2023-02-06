package controleur;

public class Enseigner {
	private int idM, idPf;
	
	public Enseigner (int idM, int idPf) {
		this.idM = idM;
		this.idPf = idPf;
	}

	public int getIdM() {
		return idM;
	}

	public void setIdM(int idM) {
		this.idM = idM;
	}

	public int getIdPf() {
		return idPf;
	}

	public void setIdPf(int idPf) {
		this.idPf = idPf;
	}
}
