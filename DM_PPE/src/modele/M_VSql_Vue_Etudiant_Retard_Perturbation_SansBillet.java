package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;

public class M_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet  {
		
	//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> selectAllVues() {
		ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesuneVues = new ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet>();
		String requete = "select * from vue_etudiant_Retard_Perturbation_sansbillet;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVue = new VSql_Vue_Etudiant_Retard_Perturbation_SansBillet(
						desResultats.getInt("IdE"),
						desResultats.getString("duree"),
						desResultats.getString("nom"),
						desResultats.getString("prenom")
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
	public static VSql_Vue_Etudiant_Retard_Perturbation_SansBillet selectWhereVue(int IdE) {
		String requete = "select * from Vue_Etudiant_Retard_Perturbation_SansBillet where IdE = " + IdE + ";";
		VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVue = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_Etudiant_Retard_Perturbation_SansBillet (
						unResultat.getInt("IdE"),
						unResultat.getString("duree"),
						unResultat.getString("nom"),
						unResultat.getString("prenom")
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
	public static ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> selectSearch(String attribut, String mot) {
		 String requete = "";
		 ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesVues = new ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Vue_Etudiant_Retard_Perturbation_SansBillet where Ide like '%"+mot+"%' "
						+ "OR duree like '%"+mot+"%'"
						+ "OR nom like '%"+mot+"%'"
						+ "OR prenom like '%"+mot+"%';";
		
		
		} else {
			requete = "select * from Vue_Etudiant_Retard_Perturbation_SansBillet where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVue = new VSql_Vue_Etudiant_Retard_Perturbation_SansBillet(
						desResultats.getInt("IdE"),
						desResultats.getString("duree"),
						desResultats.getString("nom"),
						desResultats.getString("prenom")
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
