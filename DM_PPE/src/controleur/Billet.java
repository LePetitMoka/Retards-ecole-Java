package controleur;

public class Billet {
	private int idAd, idE;
	private String dateB, heureB, dureeRetard, urlSignature, dateheure, raison;
	
	public Billet(int idAd, int idE, String dateB, String heureB, String dureeRetard, String urlSignature,
			String dateheure, String raison) {
		this.idAd = idAd;
		this.idE = idE;
		this.dateB = dateB;
		this.heureB = heureB;
		this.dureeRetard = dureeRetard;
		this.urlSignature = urlSignature;
		this.dateheure = dateheure;
		this.raison = raison;
	}
	public Billet(int idAd, int idE, String raison) {
		this.idAd = idAd;
		this.idE = idE;
		this.dateB = "";
		this.heureB = "";
		this.dureeRetard = "";
		this.urlSignature = "";
		this.dateheure = "";
		this.raison = "";
	}
	public Billet() {
		this.idAd = 0;
		this.idE = 0;
		this.dateB = "";
		this.heureB = "";
		this.dureeRetard = "";
		this.urlSignature = "";
		this.dateheure = "";
		this.raison = "";
	}

	public int getIdAd() {
		return idAd;
	}

	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}

	public int getIdE() {
		return idE;
	}

	public void setIdE(int idE) {
		this.idE = idE;
	}

	public String getDateB() {
		return dateB;
	}

	public void setDateB(String dateB) {
		this.dateB = dateB;
	}

	public String getHeureB() {
		return heureB;
	}

	public void setHeureB(String heureB) {
		this.heureB = heureB;
	}

	public String getDureeRetard() {
		return dureeRetard;
	}

	public void setDureeRetard(String dureeRetard) {
		this.dureeRetard = dureeRetard;
	}

	public String getUrlSignature() {
		return urlSignature;
	}

	public void setUrlSignature(String urlSignature) {
		this.urlSignature = urlSignature;
	}

	public String getDateheure() {
		return dateheure;
	}

	public void setDateheure(String dateheure) {
		this.dateheure = dateheure;
	}

	public String getRaison() {
		return raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}
}
