package controleur;

import java.util.ArrayList;

import modele.BDD;
import modele.M_VSql_Vue_Arret_Transport;

public class C_VSql_Arret_Transport {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");

	//Fait reference a une VUE donc pas d'inserts ni update !
		
	public static ArrayList<VSql_Vue_Arret_Transport> selectAllVues() {
		return M_VSql_Vue_Arret_Transport.selectAllVues();
	}
	// gadget
	public static ArrayList<VSql_Vue_Arret_Transport> selectWhereVue(String IdTp) {
		return M_VSql_Vue_Arret_Transport.selectWhereVue(IdTp);
	}
}
