package controleur;

import java.util.ArrayList;

import modele.M_Perturbation;

public class C_Perturbation {
	public static void insertPerturbation(Perturbation unePerturbation) {
		M_Perturbation.insertPerturbation(unePerturbation);
	}
	public static ArrayList<Perturbation> selectAllPerturbations() {
		return M_Perturbation.selectAllPerturbations();
	}
	public static void supprimerPerturbation(int IdPt) {
		M_Perturbation.supprimerPerturbation(IdPt);
	}
	public static Perturbation selectWherePerturbation(int IdPt) {
		return M_Perturbation.selectWherePerturbation(IdPt);
	}
	public static void updatePerturbation(Perturbation unePerturbation) {
		M_Perturbation.updatePerturbation(unePerturbation);
	}
}
