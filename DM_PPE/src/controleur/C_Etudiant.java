package controleur;

import java.util.ArrayList;

import modele.M_Etudiant;

public class C_Etudiant {

	public static void insertEtudiant(Etudiant unEtudiant) {
		M_Etudiant.insertEtudiant(unEtudiant);
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
	public static void updateEtudiant(Etudiant unEtudiant) {
		M_Etudiant.updateEtudiant(unEtudiant);
	}
}
