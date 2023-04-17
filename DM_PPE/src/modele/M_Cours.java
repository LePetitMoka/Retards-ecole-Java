package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Cours;

public class M_Cours  {
		
	public static String insertCours(Cours unCours){
		String message = "";
		String requete = "insert into Cours values("+unCours.getIdCl()+","
				+unCours.getIdPf()+","+unCours.getIdM()+", null, '"
				+unCours.getDateC()+"', '"+unCours.getHeureDeb()+"', '"
				+unCours.getHeureFin()+"', null, '"+unCours.getSalle()+"');";
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Insere";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Cours> selectAllCourss() {
		ArrayList<Cours> lesCourss = new ArrayList<Cours>();
		String requete = "select * from Cours;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Cours unCours = new Cours(
						desResultats.getInt("idCl"),
						desResultats.getInt("idPf"),
						desResultats.getInt("idM"),
						desResultats.getString("dateTS"),
						desResultats.getString("dateC"),
						desResultats.getString("heureDeb"),
						desResultats.getString("heureFin"),
						desResultats.getString("duree"),
						desResultats.getString("salle")
						);
				lesCourss.add(unCours);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesCourss;
	}
	public static String supprimerCours(int IdPf, int IdCl, int IdM, String dateTS) {
		String message = "";
		String requete = "delete from Cours where IdPf = " +
				IdPf +" and IdCl = "+IdCl+" and IdM = "+IdM+" and dateTS = '"+dateTS+"';";
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static Cours selectWhereCours(int IdCl, int IdPf, String dateTS) {
		String requete = "select * from Cours where IdCl = " +IdCl+" and IdPf = "+IdPf+" and dateTS = '"+dateTS+"';";
		Cours unCours = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unCours = new Cours (
						unResultat.getInt("idCl"),
						unResultat.getInt("idPf"),
						unResultat.getInt("idM"),
						unResultat.getString("dateTS"),
						unResultat.getString("dateC"),
						unResultat.getString("heureDeb"),
						unResultat.getString("heureFin"),
						unResultat.getString("duree"),
						unResultat.getString("salle")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unCours;
	}
	public static String updateCours(Cours unCours){
		String message = "";
		String requete = "update Cours set dateC = '"
				+unCours.getDateC()+"', heureDeb = '"+unCours.getHeureDeb()+
				"', heureFin = '"+unCours.getHeureFin()+"', salle = '"+unCours.getSalle()+"' where IdPf = " +
				unCours.getIdPf() +" and IdCl = "+unCours.getIdCl()+" and idM = "+unCours.getIdM()+" and dateTS = '"+unCours.getDateTS()+"';";
		try {	
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Modifie";
		}catch (SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
}
