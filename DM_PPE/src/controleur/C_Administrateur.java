package controleur;

import java.util.ArrayList;

import modele.M_Administrateur;

public class C_Administrateur {
	public static void insertAdministrateur(Administrateur unAdministrateur) {
		M_Administrateur.insertAdministrateur(unAdministrateur);
	}
	
	public static ArrayList<Administrateur> selectAllAdministrateurs(){
		return M_Administrateur.selectAllAdministrateurs();
	}
	
	public static void deleteAdministrateur (int idAd) {
		M_Administrateur.supprimerAdministrateur(idAd);
	}
	
	public static Administrateur selectWhereAdministrateur(int idAd) {
		return M_Administrateur.selectWhereAdministrateur(idAd);
	}
	
	public static Administrateur selectWhereAdministrateur(String email, String mdp) {
		return M_Administrateur.selectWhereAdministrateur(email, mdp);
	}
	
	public static void updateAdministrateur(Administrateur unAdministrateur) {
		M_Administrateur.updateAdministrateur(unAdministrateur);
	}
}
