package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Appartenir;

public class M_Appartenir  {

	
	public static void insertAppartenir(Appartenir uneAppartenance) {
		String requete = "insert into appartenir values('"
				+uneAppartenance.getIdSt()+"','"
				+uneAppartenance.getIdTp()+"');";
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
	public static ArrayList<Appartenir> selectAllAppartenirs() {
		ArrayList<Appartenir> lesAppartenances = new ArrayList<Appartenir>();
		String requete = "select * from appartenir;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Appartenir uneAppartenance = new Appartenir(
						desResultats.getString("IdSt"),
						desResultats.getString("IdTp")
						);
				lesAppartenances.add(uneAppartenance);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesAppartenances;
	}
	public static void supprimerAppartenir(int IdSt, int IdTp) {
		String requete = "delete from appartenir where IdSt = '" + IdSt + "' and IdTp = '" + IdTp + "';";
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
	public static Appartenir selectWhereAppartenir(int IdSt, int IdTp) {
		String requete = "select * from appartenir where IdSt = '" + IdSt + "' and IdTp = '" + IdTp + "';";
		Appartenir uneAppartenance = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneAppartenance = new Appartenir (
						unResultat.getString("IdSt"),
						unResultat.getString("IdTp")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneAppartenance;
	}
}