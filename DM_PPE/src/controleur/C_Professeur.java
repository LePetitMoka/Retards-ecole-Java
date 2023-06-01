package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.M_Professeur;

public class C_Professeur {
	public static String insertProfesseur(Professeur unProfesseur){
		return M_Professeur.insertProfesseur(unProfesseur);
	}
	public static ArrayList<Professeur> selectAllProfesseurs() {
		return M_Professeur.selectAllProfesseurs();
	}
	public static String supprimerProfesseur(int idPf) {
		return M_Professeur.supprimerProfesseur(idPf);
	}
	public static Professeur selectWhereProfesseur(int idPf) {
		return M_Professeur.selectWhereProfesseur(idPf);
	}
	public static Professeur selectWhereProfesseur(String email) {
		return M_Professeur.selectWhereProfesseur(email);
	}
	public static String updateProfesseur(Professeur unProfesseur){
		return M_Professeur.updateProfesseur(unProfesseur);
	}
	public static ArrayList<Professeur> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_Professeur.selectSearch(attribut, mot);
	}
}
