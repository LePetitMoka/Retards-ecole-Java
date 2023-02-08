package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Cours;

public class M_Cours {
	
	private static BDD uneBDD = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertCours(Cours unCours) {
		String requete = "insert into cours values("+unCours.getIdCl()+","
				+unCours.getIdPf()+", null,'"+unCours.getMatiere()+"', '"
				+unCours.getDateC()+"', '"+unCours.getHeureDeb()+"', '"
				+unCours.getHeureFin()+"', null, '"+unCours.getSalle()+"');";
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
	public static ArrayList<Cours> selectAllCourss() {
		ArrayList<Cours> lesCourss = new ArrayList<Cours>();
		String requete = "select * from cours;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Cours unCours = new Cours(
						desResultats.getInt("idCl"),
						desResultats.getInt("idPf"),
						desResultats.getString("dateTS"),
						desResultats.getString("matiere"),
						desResultats.getString("dateC"),
						desResultats.getString("heureDeb"),
						desResultats.getString("heureFin"),
						desResultats.getString("duree"),
						desResultats.getString("salle")
						);
				lesCourss.add(unCours);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesCourss;
	}
	public static void supprimerCours(int IdPf, int IdCl, String dateTS) {
		String requete = "delete from cours where IdPf = " +
				IdPf +" and IdCl = "+IdCl+" and dateTS = '"+dateTS+"';";
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
	public static Cours selectWhereCours(int IdCl, int IdPf, String dateTS) {
		String requete = "select * from cours where IdCl = " +IdCl+" and IdPf = "+IdPf+" and dateTS = '"+dateTS+"';";
		Cours unCours = null;
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unCours = new Cours (
						unResultat.getInt("idCl"),
						unResultat.getInt("idPf"),
						unResultat.getString("dateTS"),
						unResultat.getString("matiere"),
						unResultat.getString("dateC"),
						unResultat.getString("heureDeb"),
						unResultat.getString("heureFin"),
						unResultat.getString("duree"),
						unResultat.getString("salle")
						);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unCours;
	}
	public static void updateCours(Cours unCours) {
		String requete = "update cours set matiere = '"+unCours.getMatiere()+"', dateC = '"
				+unCours.getDateC()+"', heureDeb = '"+unCours.getHeureDeb()+
				"', heureFin = '"+unCours.getHeureFin()+"', salle = '"+unCours.getSalle()+"' where IdPf = " +
				unCours.getIdPf() +" and IdCl = "+unCours.getIdCl()+" and dateTS = '"+unCours.getDateTS()+"';";
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
