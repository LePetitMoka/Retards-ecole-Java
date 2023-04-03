package controleur;

public class BDD_Conf {
	private String host, bdd, user, mdp;
	
	BDD_Conf(String host, String bdd, String user, String mdp){
		this.host = host;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBdd() {
		return bdd;
	}

	public void setBdd(String bdd) {
		this.bdd = bdd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
