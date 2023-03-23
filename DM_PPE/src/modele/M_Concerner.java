package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Concerner;

public class M_Concerner {
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "root");
	
	public static void insertConcerner(Concerner unConcern) {
		String requete = "insert into concerner values('"
				+unConcern.getIdSt()+"','"
				+unConcern.getIdPt()+"');";
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
	public static ArrayList<Concerner> selectAllConcerners() {
		ArrayList<Concerner> lesConcerns = new ArrayList<Concerner>();
		String requete = "select * from concerner;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Concerner unConcern = new Concerner(
						desResultats.getString("IdSt"),
						desResultats.getString("IdPt")
						);
				lesConcerns.add(unConcern);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesConcerns;
	}
	public static void supprimerConcerner(int IdSt, int IdPt) {
		String requete = "delete from concerner where IdSt = '" + IdSt + "' and IdPt = '" + IdPt + "';";
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
	public static Concerner selectWhereConcerner(int IdSt, int IdPt) {
		String requete = "select * from concerner where IdSt = '" + IdSt + "' and IdPt = '" + IdPt + "';";
		Concerner unConcern = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unConcern = new Concerner (
						unResultat.getString("IdSt"),
						unResultat.getString("IdPt")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unConcern;
	}
}