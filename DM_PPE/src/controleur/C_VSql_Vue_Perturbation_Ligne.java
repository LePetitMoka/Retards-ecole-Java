package controleur;

import java.util.ArrayList;

import modele.M_VSql_Vue_Perturbation_Ligne;

public class C_VSql_Vue_Perturbation_Ligne {
	
	public static ArrayList<VSql_Vue_Perturbation_Ligne> selectAllVues() {
		return M_VSql_Vue_Perturbation_Ligne.selectAllVues();
	}
	// gadget
	public static VSql_Vue_Perturbation_Ligne selectWhereVue(int IdTp) {
		return M_VSql_Vue_Perturbation_Ligne.selectWhereVue(IdTp);
	}
	public static ArrayList<VSql_Vue_Perturbation_Ligne> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_VSql_Vue_Perturbation_Ligne.selectSearch(attribut, mot);
	}
}
