package controleur;

public class Cours {
	
	private int IdCl;
    private int IdPf; 
    private String dateTS;
    private String matiere;
    private String dateC;
    private String heureDeb; 
    private String heureFin; 
    private String duree;
    private String salle;
    
	public Cours(int idCl, int idPf, String dateTS, String matiere, String dateC, String heureDeb, String heureFin,
			String duree, String salle) {
		this.IdCl = idCl;
		this.IdPf = idPf;
		this.dateTS = dateTS;
		this.matiere = matiere;
		this.dateC = dateC;
		this.heureDeb = heureDeb;
		this.heureFin = heureFin;
		this.duree = duree;
		this.salle = salle;
	}
    
    public Cours(int idCl, int idPf, String matiere, String dateC, String heureDeb, String heureFin, String salle) {
    	this.IdCl = 0;
		this.IdPf = 0;
		this.dateTS = "";
		this.matiere = matiere;
		this.dateC = dateC;
		this.heureDeb = heureDeb;
		this.heureFin = heureFin;
		this.duree = "";
		this.salle = salle;
    }
    public Cours() {
    	this.IdCl = 0;
		this.IdPf = 0;
		this.dateTS = "";
		this.matiere = "";
		this.dateC = "";
		this.heureDeb = "";
		this.heureFin = "";
		this.duree = "";
		this.salle = "";
    }

	public int getIdCl() {
		return this.IdCl;
	}

	public void setIdCl(int idCl) {
		this.IdCl = idCl;
	}

	public int getIdPf() {
		return this.IdPf;
	}

	public void setIdPf(int idPf) {
		this.IdPf = idPf;
	}

	public String getDateTS() {
		return this.dateTS;
	}

	public void setDateTS(String dateTS) {
		this.dateTS = dateTS;
	}

	public String getMatiere() {
		return this.matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}

	public String getDateC() {
		return this.dateC;
	}

	public void setDateC(String dateC) {
		this.dateC = dateC;
	}

	public String getHeureDeb() {
		return this.heureDeb;
	}

	public void setHeureDeb(String heureDeb) {
		this.heureDeb = heureDeb;
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
    
}
