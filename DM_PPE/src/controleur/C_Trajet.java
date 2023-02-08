package controleur;

import java.util.ArrayList;

import modele.M_Trajet;

public class C_Trajet {
	
	public static void insertTrajet(Trajet unTrajet) {
		M_Trajet.insertTrajet(unTrajet);
	}
	public static ArrayList<Trajet> selectAllTrajets() {
		return M_Trajet.selectAllTrajets();
	}
	public static void supprimerTrajet(int IdSt, int IdE) {
		M_Trajet.supprimerTrajet(IdSt, IdE);
	}
	public static Trajet selectWhereTrajet(int IdSt, int IdE) {
		return M_Trajet.selectWhereTrajet(IdSt, IdE);
	}
}
