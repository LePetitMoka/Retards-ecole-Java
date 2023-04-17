package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.VSql_Vue_Trajet_Details;

public class M_VSql_Vue_Trajet_Details  {

	//Fait reference a une VUE donc pas d'inserts ni update !
	
	public static ArrayList<VSql_Vue_Trajet_Details> selectAllVues() {
		ArrayList<VSql_Vue_Trajet_Details> lesuneVues = new ArrayList<VSql_Vue_Trajet_Details>();
		String requete = "select * from Vue_Trajet_Details;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Trajet_Details uneVue = new VSql_Vue_Trajet_Details(
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("arret"),
						desResultats.getString("transports"),
						desResultats.getInt("ide"),
						desResultats.getString("idst")
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
	public static VSql_Vue_Trajet_Details selectWhereVue(int IdE, String IdSt) {
		String requete = "select * from vue_trajet_details where IdE = " + IdE + " and IdSt = '" + IdSt +"';";
		VSql_Vue_Trajet_Details uneVue = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneVue = new VSql_Vue_Trajet_Details (
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("arret"),
						unResultat.getString("transports"),
						unResultat.getInt("ide"),
						unResultat.getString("idst")
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
	public static ArrayList<VSql_Vue_Trajet_Details> selectSearch(String attribut, String mot) {
		 String requete = "";
		 ArrayList<VSql_Vue_Trajet_Details> lesVues = new ArrayList<VSql_Vue_Trajet_Details>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Vue_Trajet_Details where nom like '%"+mot+"%' "
						+ "OR prenom like '%"+mot+"%'"
						+ "OR arret like '%"+mot+"%'"
						+ "OR transports like '%"+mot+"%'"
						+ "OR ide like '%"+mot+"%'"
						+ "OR idst like '%"+mot+"%';";
		
		
		} else {
			requete = "select * from Vue_Trajet_Details where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				VSql_Vue_Trajet_Details uneVue = new VSql_Vue_Trajet_Details(
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("arret"),
						desResultats.getString("transports"),
						desResultats.getInt("ide"),
						desResultats.getString("idst")
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

