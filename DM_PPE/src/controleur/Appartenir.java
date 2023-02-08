package controleur;

public class Appartenir {
	
	private String IdSt;
	private String IdTp;
	
	public Appartenir(String idSt, String idTp) {
		this.IdSt = idSt;
		this.IdTp = idTp;
	}
	public Appartenir() {
		this.IdSt = "";
		this.IdTp = "";
	}
	public String getIdSt() {
		return this.IdSt;
	}
	public void setIdSt(String idSt) {
		this.IdSt = idSt;
	}
	public String getIdTp() {
		return this.IdTp;
	}
	public void setIdTp(String idTp) {
		this.IdTp = idTp;
	}
	
}
