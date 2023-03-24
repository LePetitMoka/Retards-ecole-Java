package controleur;

public class Cours {
	
	private int IdCl, IdPf, IdM;
    private String dateTS, dateC, heureDeb, heureFin, duree, salle;
    
	public Cours(int idCl, int idPf, int idM, String dateTS, String dateC, String heureDeb, String heureFin,
			String duree, String salle) {
		this.IdCl = idCl;
		this.IdPf = idPf;
		this.IdM = idM;
		this.dateTS = dateTS;
		this.dateC = dateC;
		this.heureDeb = heureDeb;
		this.heureFin = heureFin;
		this.duree = duree;
		this.salle = salle;
	}
    
    public Cours(int idCl, int idPf, int idM, String dateC, String heureDeb, String heureFin, String salle) {
    	this.IdCl = idCl;
		this.IdPf = idPf;
		this.IdM = idM;
		this.dateTS = "";
		this.dateC = dateC;
		this.heureDeb = heureDeb;
		this.heureFin = heureFin;
		this.duree = "";
		this.salle = salle;
    }

	public Cours() {
    	this.IdCl = 0;
		this.IdPf = 0;
		this.IdM = 0;
		this.dateTS = "";
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

	public int getIdM() {
		return this.IdM;
	}
	
	public void setIdM(int idM) {
		this.IdM = idM;
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
