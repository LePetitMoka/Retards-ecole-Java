package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Concerner;

public class M_Concerner  {
	
	public static void insertConcerner(Concerner unConcern) {
		String requete = "insert into Concerner values('"
				+unConcern.getIdSt()+"','"
				+unConcern.getIdPt()+"');";
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
	public static ArrayList<Concerner> selectAllConcerners() {
		ArrayList<Concerner> lesConcerns = new ArrayList<Concerner>();
		String requete = "select * from Concerner;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Concerner unConcern = new Concerner(
						desResultats.getString("IdSt"),
						desResultats.getString("IdPt")
						);
				lesConcerns.add(unConcern);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesConcerns;
	}
	public static String supprimerConcerner(int IdSt, int IdPt) {
		String requete = "delete from Concerner where IdSt = '" + IdSt + "' and IdPt = '" + IdPt + "';";
		String message = "";
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
		return message;
	}
	public static Concerner selectWhereConcerner(int IdSt, int IdPt) {
		String requete = "select * from Concerner where IdSt = '" + IdSt + "' and IdPt = '" + IdPt + "';";
		Concerner unConcern = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unConcern = new Concerner (
						unResultat.getString("IdSt"),
						unResultat.getString("IdPt")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unConcern;
	}
}