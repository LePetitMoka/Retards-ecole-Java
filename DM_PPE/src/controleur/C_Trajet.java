package controleur;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.M_Trajet;

public class C_Trajet {
	
	public static void insertTrajet(Trajet unTrajet) {
		M_Trajet.insertTrajet(unTrajet);
	}
	public static ArrayList<Trajet> selectAllTrajets() {
		return M_Trajet.selectAllTrajets();
	}
	public static String supprimerTrajet(int IdSt, int IdE) {
		return M_Trajet.supprimerTrajet(IdSt, IdE);
	}
	public static Trajet selectWhereTrajet(int IdSt, int IdE) {
		return M_Trajet.selectWhereTrajet(IdSt, IdE);
	}
	public static String procTrajet(int IdE, String arretDeb, String arretFin) {
		return M_Trajet.procTrajet(IdE, arretDeb, arretFin);
	}
}
