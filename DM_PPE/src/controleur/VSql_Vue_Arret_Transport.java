package controleur;

public class VSql_Vue_Arret_Transport {
	private String IdSt,IdTp,NomArret,NomTransport,type,transporteur;

	public VSql_Vue_Arret_Transport(String idSt, String idTp, String nomArret, String nomTransport, String type,
			String transporteur) {
		this.IdSt = idSt;
		this.IdTp = idTp;
		this.NomArret = nomArret;
		this.NomTransport = nomTransport;
		this.type = type;
		this.transporteur = transporteur;
	}
	public VSql_Vue_Arret_Transport(String idSt, String idTp) {
		this.IdSt = idSt;
		this.IdTp = idTp;
		this.NomArret = "";
		this.NomTransport = "";
		this.type = "";
		this.transporteur = "";
	}
	public VSql_Vue_Arret_Transport() {
		this.IdSt = "";
		this.IdTp = "";
		this.NomArret = "";
		this.NomTransport = "";
		this.type = "";
		this.transporteur = "";
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

	public String getNomArret() {
		return this.NomArret;
	}

	public void setNomArret(String nomArret) {
		this.NomArret = nomArret;
	}

	public String getNomTransport() {
		return this.NomTransport;
	}

	public void setNomTransport(String nomTransport) {
		this.NomTransport = nomTransport;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransporteur() {
		return this.transporteur;
	}

	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}
	
	
}
