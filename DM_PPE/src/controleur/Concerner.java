package controleur;

public class Concerner {
	
	private String IdSt;
    private String IdPt;
    
	public Concerner(String idSt, String idPt) {
		this.IdSt = idSt;
		this.IdPt = idPt;
	}
	public Concerner() {
		this.IdSt = "";
		this.IdPt = "";
	}
	public String getIdSt() {
		return this.IdSt;
	}
	public void setIdSt(String idSt) {
		this.IdSt = idSt;
	}
	public String getIdPt() {
		return this.IdPt;
	}
	public void setIdPt(String idPt) {
		this.IdPt = idPt;
	}
    
}
