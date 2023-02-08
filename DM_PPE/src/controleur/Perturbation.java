package controleur;

public class Perturbation {
	private String IdPt;
    private String raisonCourte;
    private String raisonLongue;
    private String dateDebMessage;
    private String dateFinMessage;
	
    public Perturbation(String idPt, String raisonCourte, String raisonLongue, String dateDebMessage,
			String dateFinMessage) {
		IdPt = idPt;
		this.raisonCourte = raisonCourte;
		this.raisonLongue = raisonLongue;
		this.dateDebMessage = dateDebMessage;
		this.dateFinMessage = dateFinMessage;
	}
    public Perturbation() {
		IdPt = "";
		this.raisonCourte = "";
		this.raisonLongue = "";
		this.dateDebMessage = "";
		this.dateFinMessage = "";
	}
	public String getIdPt() {
		return this.IdPt;
	}
	public void setIdPt(String idPt) {
		this.IdPt = idPt;
	}
	public String getRaisonCourte() {
		return this.raisonCourte;
	}
	public void setRaisonCourte(String raisonCourte) {
		this.raisonCourte = raisonCourte;
	}
	public String getRaisonLongue() {
		return this.raisonLongue;
	}
	public void setRaisonLongue(String raisonLongue) {
		this.raisonLongue = raisonLongue;
	}
	public String getDateDebMessage() {
		return this.dateDebMessage;
	}
	public void setDateDebMessage(String dateDebMessage) {
		this.dateDebMessage = dateDebMessage;
	}
	public String getDateFinMessage() {
		return this.dateFinMessage;
	}
	public void setDateFinMessage(String dateFinMessage) {
		this.dateFinMessage = dateFinMessage;
	}
    
}
