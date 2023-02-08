package controleur;

import java.util.ArrayList;

import modele.M_Cours;

public class C_Cours {
	public static void insertCours(Cours unCours) {
		M_Cours.insertCours(unCours);
	}
	public static ArrayList<Cours> selectAllCourss() {
		return M_Cours.selectAllCourss();
	}
	public static void supprimerCours(int IdPf, int IdCl, String dateTS) {
		M_Cours.supprimerCours(IdPf, IdCl, dateTS);
	}
	public static Cours selectWhereCours(int IdCl, int IdPf, String dateTS) {
		return M_Cours.selectWhereCours(IdCl, IdPf, dateTS);
	}
	public static void updateCours(Cours unCours) {
		M_Cours.updateCours(unCours);
	}
}
