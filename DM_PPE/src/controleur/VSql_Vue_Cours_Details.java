package controleur;

public class VSql_Vue_Cours_Details {
	private String matiere, nomCl, nomPf, dateTS, dateC, heureDebut, heureFin, duree, salle;
	private int IdCl,IdPf,IdM;
	
	public VSql_Vue_Cours_Details(String matiere, String nomCl, String nomPf, String dateTS, String dateC, String heureDebut,
			String heureFin, String duree, String salle, int idCl, int idPf, int idM) {
		this.matiere = matiere;
		this.nomCl = nomCl;
		this.nomPf = nomPf;
		this.dateTS = dateTS;
		this.dateC = dateC;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.duree = duree;
		this.salle = salle;
		this.IdCl = idCl;
		this.IdPf = idPf;
		this.IdM = idM;
	}
	public VSql_Vue_Cours_Details() {
		this.matiere = "";
		this.nomCl = "";
		this.nomPf = "";
		this.dateTS = "";
		this.dateC = "";
		this.heureDebut = "";
		this.heureFin = "";
		this.duree = "";
		this.salle = "";
		this.IdCl = 0;
		this.IdPf = 0;
		this.IdM = 0;
	}
	public String getMatiere() {
		return this.matiere;
	}
	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	public String getNomCl() {
		return this.nomCl;
	}
	public void setNomCl(String nomCl) {
		this.nomCl = nomCl;
	}
	public String getDateTS() {
		return this.dateTS;
	}
	public void setDateTS(String dateTS) {
		this.dateTS = dateTS;
	}
	public String getDateC() {
		return this.dateC;
	}
	public void setDateC(String dateC) {
		this.dateC = dateC;
	}
	public String getHeureDebut() {
		return this.heureDebut;
	}
	public void setHeureDebut(String heureDebut) {
		this.heureDebut = heureDebut;
	}
	public String getHeureFin() {
		return this.heureFin;
	}
	public void setHeureFin(String heureFin) {
		this.heureFin = heureFin;
	}
	public String getDuree() {
		return this.duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getSalle() {
		return this.salle;
	}
	public void setSalle(String salle) {
		this.salle = salle;
	}
	public int getIdCl() {
		return this.IdCl;
	}
	public void setIdCl(int idCl) {
		this.IdCl = idCl;
	}
	public int getIdPf() {
		return IdPf;
	}
	public void setIdPf(int idPf) {
		this.IdPf = idPf;
	}
	public int getIdM() {
		return this.IdM;
	}
	public void setIdM(int idM) {
		this.IdM = idM;
	}
	public String getNomPf() {
		return this.nomPf;
	}
	public void setNomPf(String nomPf) {
		this.nomPf = nomPf;
	}
	
	
}
