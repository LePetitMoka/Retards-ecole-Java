package vue;

public class GREI {
	private static V_Connexion uneVueConnexion;
	private static V_Acceuil uneVueAcceuil;
	
	public static void main(String args[]) {
		uneVueConnexion = new V_Connexion();
	}
	public static void rendreVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void creerDetruireVueGenerale (boolean action) {
		if(action == true) {
			uneVueAcceuil = new V_Acceuil();
			uneVueAcceuil.setVisible(true);
		} else {
			uneVueAcceuil.dispose();
		}
	}
}
