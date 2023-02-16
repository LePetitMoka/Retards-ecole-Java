package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;

public class M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet {
	
private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "root");

//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> selectAllVues() {
		ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesuneVues = new ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet>();
		String requete = "select * from Vue_Etudiant_Retard_Perturbation_SansBillet;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVue = new VSql_Vue_Etudiant_Retard_Perturbation_SansBillet(
						desResultats.getInt("IdE"),
						desResultats.getString("date")
						);
				lesuneVues.add(uneVue);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesuneVues;
	}
	// gadget
	public static VSql_Vue_Etudiant_Retard_Perturbation_SansBillet selectWhereVue(int IdE) {
		String requete = "select * from Vue_Etudiant_Retard_Perturbation_SansBillet where IdE = " + IdE + ";";
		VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVue = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_Etudiant_Retard_Perturbation_SansBillet (
						unResultat.getInt("IdE"),
						unResultat.getString("date")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneVue;
	}
}
