package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Billet;

public class M_Billet {
	
	private static BDD uneBDD = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertBillet(Billet unBillet) {
		String requete = "insert into billet values('"+unBillet.getDateB()+"','"
				+unBillet.getHeureB()+"','"+unBillet.getDureeRetard()+"','"+unBillet.getUrlSignature()+"','"+unBillet.getDateheure()+"','"
				+unBillet.getRaison()+"',"+unBillet.getIdAd()+","
				+unBillet.getIdAd()+");";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de :" + requete);
		}
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
	public static void supprimerBillet(int IdAd, int IdE, String dateheure) {
		String requete = "delete from billet where IdAd = " +
				IdAd +" and IdE = "+IdE+" and dateheure = '"+dateheure+"';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
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
	public static void updateBillet(Billet unBillet) {
		String requete = "update billet set heureB = '"+unBillet.getHeureB()+"', dateB = '"
				+unBillet.getDateB()+"', raison = '"+unBillet.getRaison()+
				"', URLSignature = '"+unBillet.getUrlSignature()+"', dureeRetard = '"+unBillet.getDureeRetard()+"' where IdAd = " +
				unBillet.getIdAd() +" and IdE = "+unBillet.getIdE()+" and dateheure = '"+unBillet.getDateheure()+"';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de :" + requete);
		}
	}
}