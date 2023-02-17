package vue;

public class GREI {
	private static V_ConnexionGen uneVueConnexionGen;
	private static V_Accueil uneVueAccueil;
	private static V_Connexion uneConnexion;
	
	public static void main(String args[]) {
		uneVueConnexionGen = new V_ConnexionGen();
	}
	public static void rendreVisibleVueConnexionGen (boolean action) {
		uneVueConnexionGen.setVisible(action);
	}
	public static void creerDetruireVueGenerale (boolean action) {
		if(action == true) {
			uneVueAccueil = new V_Accueil();
			uneVueAccueil.setVisible(true);
		} else {
			uneVueAccueil.dispose();
		}
	}
	public static void connexion(String role) {
		uneConnexion = new V_Connexion(role);
	}
}
