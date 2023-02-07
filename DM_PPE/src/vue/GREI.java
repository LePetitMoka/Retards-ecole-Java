package vue;

public class GREI {
	private static V_Connexion uneVueConnexion;
	private static V_Accueil uneVueAccueil;
	
	public static void main(String args[]) {
		uneVueConnexion = new V_Connexion();
	}
	public static void rendreVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void creerDetruireVueGenerale (boolean action) {
		if(action == true) {
			uneVueAccueil = new V_Accueil();
			uneVueAccueil.setVisible(true);
		} else {
			uneVueAccueil.dispose();
		}
	}
}
