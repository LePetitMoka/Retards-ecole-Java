package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.M_Billet;

public class C_Billet {
	public static String insertBillet(Billet unBillet) {
		return M_Billet.insertBillet(unBillet);
	}
	public static ArrayList<Billet> selectAllBillets() {
		return M_Billet.selectAllBillets();
	}
	public static String supprimerBillet(int IdAd, int IdE, String dateheure) {
		return M_Billet.supprimerBillet(IdAd, IdE, dateheure);
	}
	public static Billet selectWhereBillet(int IdAd, int IdE, String dateheure) {
		return M_Billet.selectWhereBillet(IdAd, IdE, dateheure);
	}
	public static String updateBillet(Billet unBillet) {
		return M_Billet.updateBillet(unBillet);
	}
	public static ArrayList<Billet> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_Billet.selectSearch(attribut, mot);
	}
}
