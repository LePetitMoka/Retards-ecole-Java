package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.M_Cours;

public class C_Cours {
	public static String insertCours(Cours unCours) {
		return M_Cours.insertCours(unCours);
	}
	public static ArrayList<Cours> selectAllCourss() {
		return M_Cours.selectAllCourss();
	}
	public static String supprimerCours(int IdPf, int IdCl, int IdM, String dateTS) {
		return M_Cours.supprimerCours(IdPf, IdCl, IdM, dateTS);
	}
	public static Cours selectWhereCours(int IdCl, int IdPf, String dateTS) {
		return M_Cours.selectWhereCours(IdCl, IdPf, dateTS);
	}
	public static String updateCours(Cours unCours) {
		return M_Cours.updateCours(unCours);
	}
}
