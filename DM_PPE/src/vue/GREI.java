package vue;

import java.awt.Color;
import java.net.URL;

import controleur.Administrateur;
import modele.BDD;

public class GREI {
	
	//couleurs
	
	public static Color color1 = new Color (249, 177, 4);
	public static URL url; 
	
	private static V_Connexion uneVueConnexion;
	private static V_Generale uneVueGenerale;
	private static V_Config uneVueConfig;
	
	public static void main(String args[]) {
		BDD uneBDD = new BDD();
		System.out.println(BDD.mdp);
		uneVueConnexion = new V_Connexion();
	}
	public static void rendreVisibleVueConnexion(boolean action) {
		if(action == true) {
			uneVueConnexion = new V_Connexion();
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
	public static void creerDetruireVueConfig(boolean action) {
		if(action == true) {
			uneVueConfig = new V_Config();
			uneVueConfig.setVisible(true);
		} else {
			uneVueConfig.dispose();
		}
	}
}
