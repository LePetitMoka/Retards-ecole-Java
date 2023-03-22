package controleur;

import java.util.ArrayList;

import modele.M_VSql_Vue_TotalBilletEleve;

public class C_VSql_Vue_TotalBilletEleve {	
	public static ArrayList<VSql_Vue_TotalBilletEleve> selectAllVues() {
		return M_VSql_Vue_TotalBilletEleve.selectAllVues();
	}
	// gadget
	public static VSql_Vue_TotalBilletEleve selectWhereVue(int IdE) {
		return M_VSql_Vue_TotalBilletEleve.selectWhereVue(IdE);
	}
	public static ArrayList<VSql_Vue_TotalBilletEleve> selectSearch(String attribut, String mot) {
		return M_VSql_Vue_TotalBilletEleve.selectSearch(attribut, mot);
	}
}
