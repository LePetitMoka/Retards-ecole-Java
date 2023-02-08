package controleur;

import java.util.ArrayList;

import modele.M_Classe;

public class C_Classe {
	public static void insertClasse(Classe uneClasse) {
		M_Classe.insertClasse(uneClasse);
	}
	public static ArrayList<Classe> selectAllClasses() {
		return M_Classe.selectAllClasses();
	}
	public static void supprimerClasse(int idCl) {
		M_Classe.supprimerClasse(idCl);
	}
	public static Classe selectWhereClasse(int idCl) {
		return M_Classe.selectWhereClasse(idCl);
	}
	public static void updateClasse(Classe uneClasse) {
		M_Classe.updateClasse(uneClasse);
	}
}
