package controleur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modele.M_VSql_Vue_Cours_Details;

public class C_VSql_Vue_Cours_Details {
	public static ArrayList<VSql_Vue_Cours_Details> selectAllVues() {
		return M_VSql_Vue_Cours_Details.selectAllVues();
	}
	public static VSql_Vue_Cours_Details selectWhereVue(int IdCl, int IdM,int IdPf, String dateTS) {
		return M_VSql_Vue_Cours_Details.selectWhereVue(IdCl, IdM, IdPf, dateTS);
	}
	public static ArrayList<VSql_Vue_Cours_Details> selectSearch(String attribut, String mot) {
		// TODO Auto-generated method stub
		return M_VSql_Vue_Cours_Details.selectSearch(attribut, mot);
	}
}
