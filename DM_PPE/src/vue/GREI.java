package vue;

import java.awt.Color;

public class GREI {
	
	//couleurs
	
	public static Color color1 = new Color (249, 177, 4);
	
	private static V_Connexion uneVueConnexion;
	private static V_Generale uneVueGenerale;
	
	public static void main(String args[]) {
		uneVueGenerale = new V_Generale();
		uneVueGenerale.setVisible(true);

		//uneVueConnexion = new V_Connexion();
	}
	public static void rendreVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void creerDetruireVueGenerale (boolean action) {
		if(action == true) {
			uneVueGenerale = new V_Generale();
			uneVueGenerale.setVisible(true);
		} else {
			uneVueGenerale.dispose();
		}
	}
}
