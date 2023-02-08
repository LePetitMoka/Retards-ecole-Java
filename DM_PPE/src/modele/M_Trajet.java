package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Trajet;

public class M_Trajet {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertTrajet(Trajet unTrajet) {
		String requete = "insert into trajet values('"
				+unTrajet.getIdSt()+"', "
				+unTrajet.getIdE()+");";
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
	public static ArrayList<Trajet> selectAllTrajets() {
		ArrayList<Trajet> lesunTrajets = new ArrayList<Trajet>();
		String requete = "select * from trajet;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Trajet unTrajet = new Trajet(
						desResultats.getString("IdSt"),
						desResultats.getInt("IdE")
						);
				lesunTrajets.add(unTrajet);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesunTrajets;
	}
	public static void supprimerTrajet(int IdSt, int IdE) {
		String requete = "delete from trajet where IdSt = '" + IdSt + "' and IdE = " + IdE + ";";
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
	public static Trajet selectWhereTrajet(int IdSt, int IdE) {
		String requete = "select * from trajet where IdSt = '" + IdSt + "' and IdE = " + IdE + ";";
		Trajet unTrajet = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unTrajet = new Trajet (
						unResultat.getString("IdSt"),
						unResultat.getInt("IdE")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unTrajet;
	}
}
