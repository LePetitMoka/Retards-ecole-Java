package controleur;

public class VSql_Vue_Perturbation_Ligne {
	
	private String IdTp, nom,type,transporteur,pictogramme,etat,raison;
	
	public VSql_Vue_Perturbation_Ligne(String idTp, String nom, String type, String transporteur, String pictogramme,
			String etat, String raison) {
		this.IdTp = idTp;
		this.nom = nom;
		this.type = type;
		this.transporteur = transporteur;
		this.pictogramme = pictogramme;
		this.etat = etat;
		this.raison = raison;
	}
	public VSql_Vue_Perturbation_Ligne() {
		this.IdTp = "";
		this.nom = "";
		this.type = "";
		this.transporteur = "";
		this.pictogramme = "";
		this.etat = "";
		this.raison = "";
	}

	public String getIdTp() {
		return this.IdTp;
	}

	public void setIdTp(String idTp) {
		this.IdTp = idTp;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransporteur() {
		return transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}

	public String getPictogramme() {
		return this.pictogramme;
	}

	public void setPictogramme(String pictogramme) {
		this.pictogramme = pictogramme;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getRaison() {
		return this.raison;
	}

	public void setRaison(String raison) {
		this.raison = raison;
	}
	
	
}
