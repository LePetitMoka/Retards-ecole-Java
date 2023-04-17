package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Billet;

public class M_Billet  {
		
	public static String insertBillet(Billet unBillet) {
		String message = "";
		String requete = "insert into Billet values(null,null,null,null,null,'"
				+unBillet.getRaison()+"',"+unBillet.getIdAd()+","
				+unBillet.getIdE()+");";
		System.out.println(requete);
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Insere";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Billet> selectAllBillets() {
		ArrayList<Billet> lesBillets = new ArrayList<Billet>();
		String requete = "select * from Billet;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Billet unBillet = new Billet(
						desResultats.getInt("IdAd"),
						desResultats.getInt("IdE"),
						desResultats.getString("dateB"),
						desResultats.getString("heureB"),
						desResultats.getString("dureeRetard"),
						desResultats.getString("URLSignature"),
						desResultats.getString("dateHeure"),
						desResultats.getString("raison")
						);
				lesBillets.add(unBillet);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesBillets;
	}
	public static String supprimerBillet(int IdAd, int IdE, String dateheure){
		String message = "";
		String requete = "delete from Billet where IdAd = " +
				IdAd +" and IdE = "+IdE+" and dateheure = '"+dateheure+"';";
		System.out.println(requete);
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static Billet selectWhereBillet(int IdAd, int IdE, String dateheure) {
		String requete = "select * from Billet where IdAd = " +
				IdAd +" and IdE = "+IdE+" and dateheure = '"+dateheure+"';";
		Billet unBillet = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unBillet = new Billet (
						unResultat.getInt("IdAd"),
						unResultat.getInt("IdE"),
						unResultat.getString("dateB"),
						unResultat.getString("heureB"),
						unResultat.getString("dureeRetard"),
						unResultat.getString("URLSignature"),
						unResultat.getString("dateHeure"),
						unResultat.getString("raison")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unBillet;
	}
	public static String updateBillet(Billet unBillet){
		String message = "";
		String requete = "update Billet set raison = '"+unBillet.getRaison()+
				"' where IdAd = " +unBillet.getIdAd() +" and IdE = "+unBillet.getIdE()+" and dateheure = '"+unBillet.getDateheure()+"';";
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Modifie";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Billet> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<Billet> lesBillets = new ArrayList<Billet>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Billet where IdAd like '%"+mot+"%' "
						+ "OR IdE like '%"+mot+"%'"
						+ "OR dateB like '%"+mot+"%'"
						+ "OR heureB like '%"+mot+"%'"
						+ "OR dureeRetard like '%"+mot+"%'"
						+ "OR URLSignature like '%"+mot+"%'"
						+ "OR raison like '%"+mot+"%'"
						+ "OR dateheure like '%"+mot+"%';";

		
		
		} else {
			requete = "select * from Billet where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				Billet unBillet = new Billet(
						desResultats.getInt("IdAd"),
						desResultats.getInt("IdE"),
						desResultats.getString("dateB"),
						desResultats.getString("heureB"),
						desResultats.getString("dureeRetard"),
						desResultats.getString("URLSignature"),
						desResultats.getString("dateHeure"),
						desResultats.getString("raison")
						);
				//insertion de l'objet vue dans l'arraylist
				lesBillets.add(unBillet);
			}
			unStat.close();
			BDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesBillets;
	}
}