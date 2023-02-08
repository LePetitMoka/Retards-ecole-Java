package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Station;

public class M_Station {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertStation(Station uneStation) {
		String requete = "insert into station values('"+uneStation.getIdSt()+"','"
				+uneStation.getNom()+"','"
				+uneStation.getType()+"','"
				+uneStation.getTransporteur()+"','"
				+uneStation.getPictogramme()+"','"
				+uneStation.getEtat()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
	}
	public static ArrayList<Station> selectAllStations() {
		ArrayList<Station> lesStations = new ArrayList<Station>();
		String requete = "select * from station;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Station uneStation = new Station(
						desResultats.getString("IdSt"),
						desResultats.getString("nom"),
						desResultats.getString("type"),
						desResultats.getString("transporteur"),
						desResultats.getString("pictogramme"),
						desResultats.getString("etat")
						);
				lesStations.add(uneStation);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesStations;
	}
	public static void supprimerStation(int IdSt) {
		String requete = "delete from station where IdSt = " + IdSt;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
	}
	public static Station selectWhereStation(int IdSt) {
		String requete = "select * from station where IdSt = " + IdSt;
		Station uneStation = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneStation = new Station (
						unResultat.getString("IdSt"),
						unResultat.getString("nom"),
						unResultat.getString("type"),
						unResultat.getString("transporteur"),
						unResultat.getString("pictogramme"),
						unResultat.getString("etat")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : " + requete);
		}
		return uneStation;
	}
	public static void updateStation(Station uneStation) {
		String requete = "update station set nom = '"+uneStation.getNom()
				+"', type = '"+uneStation.getType()
				+"', transporteur = '"+uneStation.getTransporteur()
				+"', pictogramme = '"+uneStation.getPictogramme()
				+"', etat = '"+uneStation.getEtat()
				+"' where IdSt = " + uneStation.getIdSt() + ";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
	}
}