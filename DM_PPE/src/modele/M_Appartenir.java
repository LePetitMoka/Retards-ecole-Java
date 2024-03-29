package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Appartenir;

public class M_Appartenir  {

	
	public static void insertAppartenir(Appartenir uneAppartenance) {
		String requete = "insert into Appartenir values('"
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
		String requete = "select * from Appartenir;";
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
	public static String supprimerAppartenir(int IdSt, int IdTp) {
		String requete = "delete from Appartenir where IdSt = '" + IdSt + "' and IdTp = '" + IdTp + "';";
		String message = "";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
			message = exp.getMessage();
		}
		return message;
	}
	public static Appartenir selectWhereAppartenir(int IdSt, int IdTp) {
		String requete = "select * from Appartenir where IdSt = '" + IdSt + "' and IdTp = '" + IdTp + "';";
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