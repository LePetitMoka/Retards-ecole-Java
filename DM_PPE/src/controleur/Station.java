package controleur;

public class Station {
	private String IdSt;
	private String nom;
    private String ville;
    
	public Station(String idSt, String nom, String ville) {
		this.IdSt = idSt;
		this.nom = nom;
		this.ville = ville;
	}
	public Station() {
		this.IdSt = "";
		this.nom = "";
		this.ville = "";
	}
	public String getIdSt() {
		return this.IdSt;
	}
	public void setIdSt(String idSt) {
		this.IdSt = idSt;
	}
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return this.ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
    
    
}
