package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Trajet;

public class M_Trajet  {
	
	public static String insertTrajet(Trajet unTrajet) {
		String message = "";
		String requete = "insert into Trajet values('"
				+unTrajet.getIdE()+"', "
				+unTrajet.getIdSt()+");";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Insere";
		}
		catch(SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Trajet> selectAllTrajets() {
		ArrayList<Trajet> lesunTrajets = new ArrayList<Trajet>();
		String requete = "select * from trajet;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Trajet unTrajet = new Trajet(
						desResultats.getString("IdSt"),
						desResultats.getInt("IdE")
						);
				lesunTrajets.add(unTrajet);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesunTrajets;
	}
	public static String supprimerTrajet(int IdSt, int IdE) {
		String message = "";
		String requete = "delete from trajet where IdSt = '" + IdSt + "' and IdE = " + IdE + ";";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}
		catch(SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static Trajet selectWhereTrajet(int IdSt, int IdE) {
		String requete = "select * from trajet where IdSt = '" + IdSt + "' and IdE = " + IdE + ";";
		Trajet unTrajet = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unTrajet = new Trajet (
						unResultat.getString("IdSt"),
						unResultat.getInt("IdE")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unTrajet;
	}
	public static String procTrajet(int IdE, String arretDeb, String arretFin) {
		String message = "";
		String requete = " call insertTj("
				+IdE+", '"
				+arretDeb+"','"+arretFin+"');";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Insere";
		}
		catch(SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
}
