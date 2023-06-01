package controleur;

import java.util.ArrayList;

import modele.M_Concerner;

public class C_Concerner {
	public static void insertConcerner(Concerner unConcern) {
		M_Concerner.insertConcerner(unConcern);
	}
	public static ArrayList<Concerner> selectAllConcerners() {
		return M_Concerner.selectAllConcerners();
	}
	public static String supprimerConcerner(int IdSt, int IdPt) {
		return M_Concerner.supprimerConcerner(IdSt, IdPt);
	}
	public static Concerner selectWhereConcerner(int IdSt, int IdPt) {
		return M_Concerner.selectWhereConcerner(IdSt, IdPt);
	}
}
