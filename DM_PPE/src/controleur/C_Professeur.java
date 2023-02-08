package controleur;

import java.util.ArrayList;

import modele.M_Professeur;

public class C_Professeur {
	public static void insertProfesseur(Professeur unProfesseur) {
		M_Professeur.insertProfesseur(unProfesseur);
	}
	public static ArrayList<Professeur> selectAllProfesseurs() {
		return M_Professeur.selectAllProfesseurs();
	}
	public static void supprimerProfesseur(int idPf) {
		M_Professeur.supprimerProfesseur(idPf);
	}
	public static Professeur selectWhereProfesseur(int idPf) {
		return M_Professeur.selectWhereProfesseur(idPf);
	}
	public static void updateProfesseur(Professeur unProfesseur) {
		M_Professeur.updateProfesseur(unProfesseur);
	}
}
