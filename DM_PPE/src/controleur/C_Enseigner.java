package controleur;

import java.util.ArrayList;

import modele.M_Enseigner;

public class C_Enseigner {
	public static void insertEnseigner(Enseigner unEnseig) {
		M_Enseigner.insertEnseigner(unEnseig);
	}
	public static ArrayList<Enseigner> selectAllEnseigners() {
		return M_Enseigner.selectAllEnseigners();
	}
	public static void supprimerEnseigner(int idM, int idPf) {
		M_Enseigner.supprimerEnseigner(idM, idPf);
	}
	public static Enseigner selectWhereEnseigner(int idM, int idPf) {
		return M_Enseigner.selectWhereEnseigner(idM, idPf);
	}
}
