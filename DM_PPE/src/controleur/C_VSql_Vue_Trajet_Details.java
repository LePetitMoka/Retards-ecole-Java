package controleur;

import java.util.ArrayList;

import modele.M_VSql_Vue_Trajet_Details;

public class C_VSql_Vue_Trajet_Details {
	public static ArrayList<VSql_Vue_Trajet_Details> selectAllVues() {
		return M_VSql_Vue_Trajet_Details.selectAllVues();
	}
	// gadget
	public static VSql_Vue_Trajet_Details selectWhereVue(int IdE, String IdSt) {
		return M_VSql_Vue_Trajet_Details.selectWhereVue(IdE, IdSt);
	}
	public static ArrayList<VSql_Vue_Trajet_Details> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_VSql_Vue_Trajet_Details.selectSearch(attribut, mot);
	}
}
