package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Perturbation_Ligne;

public class M_VSql_Vue_Perturbation_Ligne  {
		
	//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Perturbation_Ligne> selectAllVues() {
		ArrayList<VSql_Vue_Perturbation_Ligne> lesuneVues = new ArrayList<VSql_Vue_Perturbation_Ligne>();
		String requete = "select * from Vue_Perturbation_Ligne;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
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
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneVue;
	}
	public static ArrayList<VSql_Vue_Perturbation_Ligne> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<VSql_Vue_Perturbation_Ligne> lesVues = new ArrayList<VSql_Vue_Perturbation_Ligne>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Vue_Perturbation_Ligne where nom like '%"+mot+"%' "
						+ "OR type like '%"+mot+"%'"
						+ "OR transporteur like '%"+mot+"%'"
						+ "OR etat like '%"+mot+"%'"
						+ "OR raison like '%"+mot+"%'"
						+ "OR idtp like '%"+mot+"%';";
		
		
		} else {
			requete = "select * from Vue_Perturbation_Ligne where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
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
				//insertion de l'objet vue dans l'arraylist
				lesVues.add(uneVue);
			}
			unStat.close();
			BDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
			return lesVues;
		
	}
}
