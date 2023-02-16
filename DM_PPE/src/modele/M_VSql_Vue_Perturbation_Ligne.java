package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Perturbation_Ligne;

public class M_VSql_Vue_Perturbation_Ligne {
	
private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");

//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Perturbation_Ligne> selectAllVues() {
		ArrayList<VSql_Vue_Perturbation_Ligne> lesuneVues = new ArrayList<VSql_Vue_Perturbation_Ligne>();
		String requete = "select * from Vue_Perturbation_Ligne;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Perturbation_Ligne uneVue = new VSql_Vue_Perturbation_Ligne(
						desResultats.getString("IdTp"),
						desResultats.getString("nom"),
						desResultats.getString("type"),
						desResultats.getString("transporteur"),
						desResultats.getString("pictogramme"),
						desResultats.getString("etat"),
						desResultats.getString("raison")
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
	public static VSql_Vue_Perturbation_Ligne selectWhereVue(int IdTp) {
		String requete = "select * from Vue_Perturbation_Ligne where IdTp = '" + IdTp + "' ;";
		VSql_Vue_Perturbation_Ligne uneVue = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_Perturbation_Ligne (
						unResultat.getString("IdTp"),
						unResultat.getString("nom"),
						unResultat.getString("type"),
						unResultat.getString("transporteur"),
						unResultat.getString("pictogramme"),
						unResultat.getString("etat"),
						unResultat.getString("raison")
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
