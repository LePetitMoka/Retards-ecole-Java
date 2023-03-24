package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.
VSql_Vue_Arret_Transport;

public class M_VSql_Vue_Arret_Transport {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");

	//Fait reference a une VUE donc pas d'inserts ni update !
		
	public static ArrayList<VSql_Vue_Arret_Transport> selectAllVues() {
		ArrayList<VSql_Vue_Arret_Transport> lesVues = new ArrayList<VSql_Vue_Arret_Transport>();
		String requete = "select * from vue_arret_transport;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Arret_Transport uneVue = new VSql_Vue_Arret_Transport(
						desResultats.getString("IdSt"),
						desResultats.getString("IdTp"),
						desResultats.getString("NomArret"),
						desResultats.getString("NomTransport"),
						desResultats.getString("type"),
						desResultats.getString("transporteur")
						);
				lesVues.add(uneVue);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesVues;
	}
	// gadget
	public static ArrayList<VSql_Vue_Arret_Transport> selectWhereVue(String IdTp) {
		ArrayList<VSql_Vue_Arret_Transport> lesVues = new ArrayList<VSql_Vue_Arret_Transport>();
		String requete = "select * from vue_arret_transport where IdTp = '"+IdTp+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				VSql_Vue_Arret_Transport uneVue = new VSql_Vue_Arret_Transport(
						desResultats.getString("IdSt"),
						desResultats.getString("IdTp"),
						desResultats.getString("NomArret"),
						desResultats.getString("NomTransport"),
						desResultats.getString("type"),
						desResultats.getString("transporteur")
						);
				lesVues.add(uneVue);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesVues;
	}
}
