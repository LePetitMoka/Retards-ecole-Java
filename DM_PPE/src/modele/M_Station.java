package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Station;

public class M_Station {
	
	private static BDD uneBDD = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertStation(Station uneStation) {
		String requete = "insert into station values('"+uneStation.getIdSt()+"','"
				+uneStation.getNom()+"','"+uneStation.getVille()+"');";
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
	public static ArrayList<Station> selectAllStations() {
		ArrayList<Station> desStations = new ArrayList<Station>();
		String requete = "select * from station;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Station uneStation = new Station(
						desResultats.getString("IdSt"),
						desResultats.getString("nom"),
						desResultats.getString("ville")
						);
				desStations.add(uneStation);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return desStations;
	}
	public static void supprimerStation(int IdSt) {
		String requete = "delete from station where IdSt = '"+IdSt+"';";
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
	public static Station selectWhereStation(int IdSt) {
		String requete = "select * from station where IdSt = '"+IdSt+"';";
		Station uneStation = null;
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneStation = new Station (
						unResultat.getString("IdSt"),
						unResultat.getString("nom"),
						unResultat.getString("ville")
						);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneStation;
	}
	public static void updateStation(Station uneStation) {
		String requete = "update station set nom = '"+uneStation.getNom()+"', ville = '"+uneStation.getVille()+"' where IdSt = '" +
				uneStation.getIdSt() +"';";
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