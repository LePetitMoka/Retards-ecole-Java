package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Billet;
import controleur.Etudiant;

public class M_Billet {
	
	private static BDD uneBDD = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static String insertBillet(Billet unBillet) {
		String message = "";
		String requete = "insert into billet values(null,null,null,null,null,'"
				+unBillet.getRaison()+"',"+unBillet.getIdAd()+","
				+unBillet.getIdE()+");";
		System.out.println(requete);
		try {	
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
			message = "Insere";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Billet> selectAllBillets() {
		ArrayList<Billet> lesBillets = new ArrayList<Billet>();
		String requete = "select * from billet;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
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
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesBillets;
	}
	public static String supprimerBillet(int IdAd, int IdE, String dateheure){
		String message = "";
		String requete = "delete from billet where IdAd = " +
				IdAd +" and IdE = "+IdE+" and dateheure = '"+dateheure+"';";
		System.out.println(requete);
		try {	
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
			message = "Supprime";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static Billet selectWhereBillet(int IdAd, int IdE, String dateheure) {
		String requete = "select * from billet where IdAd = " +
				IdAd +" and IdE = "+IdE+" and dateheure = '"+dateheure+"';";
		Billet unBillet = null;
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
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
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unBillet;
	}
	public static String updateBillet(Billet unBillet){
		String message = "";
		String requete = "update billet set raison = '"+unBillet.getRaison()+
				"' where IdAd = " +unBillet.getIdAd() +" and IdE = "+unBillet.getIdE()+" and dateheure = '"+unBillet.getDateheure()+"';";
		try {	
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
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
			uneBDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = uneBDD.getMaConnexion().createStatement();
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
			uneBDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesBillets;
	}
}