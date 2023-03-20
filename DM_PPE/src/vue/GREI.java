package vue;

import java.awt.Color;

import controleur.Administrateur;

public class GREI {
	
	//couleurs
	
	public static Color color1 = new Color (249, 177, 4);
	
	private static V_Connexion uneVueConnexion;
	private static V_Generale uneVueGenerale;
	
	public static void main(String args[]) {
		uneVueConnexion = new V_Connexion();
	}
	public static void rendreVisibleVueConnexion(boolean action) {
		if(action == true) {
			uneVueConnexion.setVisible(true);
		} else {
			uneVueConnexion.dispose();
		}
	}
	public static void creerDetruireVueGenerale (boolean action, Administrateur unAdministrateur) {
		if(action == true) {
			uneVueGenerale = new V_Generale(unAdministrateur);
			uneVueGenerale.setVisible(true);
		} else {
			uneVueGenerale.dispose();
		}
	}
}
