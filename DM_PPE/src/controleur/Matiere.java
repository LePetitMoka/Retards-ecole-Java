package controleur;

public class Matiere {
	private int idM;
	private String intitule;
	
	public Matiere () {
		this.idM = 0;
		this.intitule = "";
	}
	
	public Matiere (String intitule) {
		this.idM = 0;
		this.intitule = intitule;
	}
	
	public Matiere (int idM, String intitule) {
		this.idM = idM;
		this.intitule = intitule;
	}

	public int getIdM() {
		return idM;
	}

	public void setIdM(int idM) {
		this.idM = idM;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
}
