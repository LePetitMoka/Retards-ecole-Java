package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.M_Matiere;

public class C_Matiere {
	public static void insertMatiere(Matiere uneMatiere) {
		M_Matiere.insertMatiere(uneMatiere);
	}
	public static ArrayList<Matiere> selectAllMatieres() {
		return M_Matiere.selectAllMatieres();
	}
	public static String supprimerMatiere(int idM) {
		return M_Matiere.supprimerMatiere(idM);
	}
	public static Matiere selectWhereMatiere(int idM) {
		return M_Matiere.selectWhereMatiere(idM);
	}
	public static void updateMatiere(Matiere uneMatiere) {
		M_Matiere.updateMatiere(uneMatiere);
	}
	public static ArrayList<Matiere> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_Matiere.selectSearch(attribut, mot);
	}
}
