package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Station;

public class M_Station  {
		
	public static void insertStation(Station uneStation) {
		String requete = "insert into Station values('"+uneStation.getIdSt()+"','"
				+uneStation.getNom()+"','"+uneStation.getVille()+"');";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de :" + requete);
		}
	}
	public static ArrayList<Station> selectAllStations() {
		ArrayList<Station> desStations = new ArrayList<Station>();
		String requete = "select * from Station;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return desStations;
	}
	public static void supprimerStation(int IdSt) {
		String requete = "delete from Station where IdSt = '"+IdSt+"';";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
	}
	public static Station selectWhereStation(int IdSt) {
		String requete = "select * from Station where IdSt = '"+IdSt+"';";
		Station uneStation = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneStation = new Station (
						unResultat.getString("IdSt"),
						unResultat.getString("nom"),
						unResultat.getString("ville")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneStation;
	}
	public static void updateStation(Station uneStation) {
		String requete = "update Station set nom = '"+uneStation.getNom()+"', ville = '"+uneStation.getVille()+"' where IdSt = '" +
				uneStation.getIdSt() +"';";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de :" + requete);
		}
	}
}