package controleur;

import java.util.ArrayList;

import modele.M_Billet;

public class C_Billet {
	public static void insertBillet(Billet unBillet) {
		M_Billet.insertBillet(unBillet);
	}
	public static ArrayList<Billet> selectAllBillets() {
		return M_Billet.selectAllBillets();
	}
	public static void supprimerBillet(int IdAd, int IdE, String dateheure) {
		M_Billet.supprimerBillet(IdAd, IdE, dateheure);
	}
	public static Billet selectWhereBillet(int IdAd, int IdE, String dateheure) {
		return M_Billet.selectWhereBillet(IdAd, IdE, dateheure);
	}
	public static void updateBillet(Billet unBillet) {
		M_Billet.updateBillet(unBillet);
	}
}
