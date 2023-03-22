package controleur;

import java.util.ArrayList;

import modele.M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;

public class C_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet {
	public static ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> selectAllVues() {
		return M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet.selectAllVues();
	}
	// gadget
	public static VSql_Vue_Etudiant_Retard_Perturbation_SansBillet selectWhereVue(int IdE) {
		return M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet.selectWhereVue(IdE);
	}
	public static ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> selectSearch(String attribut,
			String mot) {

		return M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet.selectSearch(attribut, mot);
	}
}
