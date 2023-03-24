package controleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.M_VSql_Prof_Matiere;

public class C_VSql_Prof_Matiere {

	public static ArrayList<VSql_Vue_Prof_Matiere> selectAllVues() {
		return M_VSql_Prof_Matiere.selectAllVues();
	}
	// gadget
	public static VSql_Vue_Prof_Matiere selectWhereVue(int IdM,int IdPf) {
		return M_VSql_Prof_Matiere.selectWhereVue(IdM, IdPf);
	}
}
