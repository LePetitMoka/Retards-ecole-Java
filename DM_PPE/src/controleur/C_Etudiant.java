package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.M_Etudiant;

public class C_Etudiant {

	public static String insertEtudiant(Etudiant unEtudiant){
		return M_Etudiant.insertEtudiant(unEtudiant);
	}
	public static ArrayList<Etudiant> selectAllEtudiants() {
		return M_Etudiant.selectAllEtudiants();
	}
	public static void supprimerEtudiant(int idE) {
		M_Etudiant.supprimerEtudiant(idE);
	}
	public static Etudiant selectWhereEtudiant(int idE) {
		return M_Etudiant.selectWhereEtudiant(idE);
	}
	public static Etudiant selectWhereEtudiant(String email) {
		return M_Etudiant.selectWhereEtudiant(email);
	}
	public static String updateEtudiant(Etudiant unEtudiant) {
		return M_Etudiant.updateEtudiant(unEtudiant);
	}
	public static ArrayList<Etudiant> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_Etudiant.selectSearch(attribut, mot);
	}
}
