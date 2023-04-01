package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Prof_Matiere;
import controleur.VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;

public class M_VSql_Prof_Matiere {
	
private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "");

//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Prof_Matiere> selectAllVues() {
		ArrayList<VSql_Vue_Prof_Matiere> lesuneVues = new ArrayList<VSql_Vue_Prof_Matiere>();
		String requete = "select * from vue_prof_matiere;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Prof_Matiere uneVue = new VSql_Vue_Prof_Matiere(
						desResultats.getInt("IdPf"),
						desResultats.getInt("IdM"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("intitule")
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
	public static VSql_Vue_Prof_Matiere selectWhereVue(int IdM,int IdPf) {
		String requete = "select * from vue_Prof_Matiere where IdM = " + IdM + " and IdPf = " + IdPf +";";
		VSql_Vue_Prof_Matiere uneVue = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_Prof_Matiere (
						unResultat.getInt("IdPf"),
						unResultat.getInt("IdM"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("intitule")
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
