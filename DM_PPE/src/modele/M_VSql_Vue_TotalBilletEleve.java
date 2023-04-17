package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_TotalBilletEleve;

public class M_VSql_Vue_TotalBilletEleve  {

	//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_TotalBilletEleve> selectAllVues() {
		ArrayList<VSql_Vue_TotalBilletEleve> lesuneVues = new ArrayList<VSql_Vue_TotalBilletEleve>();
		String requete = "select * from Vue_TotalBilletEleve;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_TotalBilletEleve uneVue = new VSql_Vue_TotalBilletEleve(
						desResultats.getInt("IdE"),
						desResultats.getInt("nbBillets"),
						desResultats.getString("nom_prenom"),
						desResultats.getString("dureeCumulee")
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
	public static VSql_Vue_TotalBilletEleve selectWhereVue(int IdE) {
		String requete = "select * from Vue_TotalBilletEleve where IdE = " + IdE +"';";
		VSql_Vue_TotalBilletEleve uneVue = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_TotalBilletEleve (
						unResultat.getInt("IdE"),
						unResultat.getInt("nbBillets"),
						unResultat.getString("nom_prenom"),
						unResultat.getString("dureeCumulee")
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
	public static ArrayList<VSql_Vue_TotalBilletEleve> selectSearch(String attribut, String mot) {
		 String requete = "";
		 ArrayList<VSql_Vue_TotalBilletEleve> lesVues = new ArrayList<VSql_Vue_TotalBilletEleve>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Vue_TotalBilletEleve where Ide like '%"+mot+"%' "
						+ "OR nbBillets like '%"+mot+"%'"
						+ "OR nom_prenom like '%"+mot+"%'"
						+ "OR dureeCumulee like '%"+mot+"%';";
		
		
		} else {
			requete = "select * from Vue_TotalBilletEleve where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				VSql_Vue_TotalBilletEleve uneVue = new VSql_Vue_TotalBilletEleve(
						desResultats.getInt("IdE"),
						desResultats.getInt("nbBillets"),
						desResultats.getString("nom_prenom"),
						desResultats.getString("dureeCumulee")
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
