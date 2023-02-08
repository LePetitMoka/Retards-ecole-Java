package controleur;

import java.util.ArrayList;

import modele.M_Appartenir;

public class C_Appartenir {
	public static void insertAppartenir(Appartenir uneAppartenance) {
		M_Appartenir.insertAppartenir(uneAppartenance);
	}
	public static ArrayList<Appartenir> selectAllAppartenirs() {
		return M_Appartenir.selectAllAppartenirs();
	}
	public static void supprimerAppartenir(int IdSt, int IdTp) {
		M_Appartenir.supprimerAppartenir(IdSt, IdTp);
	}
	public static Appartenir selectWhereAppartenir(int IdSt, int IdTp) {
		return M_Appartenir.selectWhereAppartenir(IdSt, IdTp);
	}
}
