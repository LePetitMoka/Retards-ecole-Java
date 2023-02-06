package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Classe;

public class ModeleClasse {
	private static BDD uneBdd = new BDD("localhost:3307", "DM_PPE_JAVA", "root", "root");
	
	public static void insertClasse(Classe uneClasse) {
		String requete = "insert into classe values(null, '"
				+uneClasse.getNom()+"', '"
				+uneClasse.getDiplomePrepare()+"', '"
				+uneClasse.getPromotion()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : " + requete);
		}
	}
	public static ArrayList<Classe> selectAllClasses() {
		ArrayList<Classe> lesClasses = new ArrayList<Classe>();
		String requete = "select * from classe;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Classe uneClasse = new Classe(
						desResultats.getInt("idCl"),
						desResultats.getString("nom"),
						desResultats.getString("diplomePrepare"),
						desResultats.getString("promotion")
						);
				lesClasses.add(uneClasse);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesClasses;
	}
	public static void supprimerClasse(int idCl) {
		String requete = "delete from classe where idCl = " + idCl + ";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : " + requete);
		}
	}
	public static Classe selectWhereClasse(int idCl) {
		String requete = "select * from classe where idCl = " + idCl;
		Classe uneClasse = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneClasse = new Classe (
						unResultat.getInt("idCl"),
						unResultat.getString("nom"),
						unResultat.getString("diplomePrepare"),
						unResultat.getString("promotion")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : " + requete);
		}
		return uneClasse;
	}
	public static void updateClasse(Classe uneClasse) {
		String requete = "update classe set nom = '"+uneClasse.getNom()
				+"', diplomePrepare = '"+uneClasse.getDiplomePrepare()
				+"', promotion = '"+uneClasse.getPromotion()
				+"' where idCl = " + uneClasse.getIdCl() + ";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Errer d'ex�cution de : " + requete);
		}
	}
}
