package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Matiere;

public class ModeleMatiere {
	private static BDD uneBdd = new BDD("localhost:3307", "DM_PPE_JAVA", "root", "");
	
	public static void insertMatiere(Matiere uneMatiere) {
		String requete = "insert into matiere values(null, '"
				+uneMatiere.getIntitule()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Errer d'exécution de : " + requete);
		}
	}
	public static ArrayList<Matiere> selectAllMatieres() {
		ArrayList<Matiere> lesMatieres = new ArrayList<Matiere>();
		String requete = "select * from matiere;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Matiere uneMatiere = new Matiere(
						desResultats.getInt("idM"),
						desResultats.getString("intitule")
						);
				lesMatieres.add(uneMatiere);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesMatieres;
	}
	public static void supprimerMatiere(int idM) {
		String requete = "delete from matiere where idM = " + idM;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : " + requete);
		}
	}
	public static Matiere selectWhereMatiere(int idM) {
		String requete = "select * from matiere where idM = " + idM;
		Matiere uneMatiere = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneMatiere = new Matiere (
						unResultat.getInt("idM"),
						unResultat.getString("intitule")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : " + requete);
		}
		return uneMatiere;
	}
	public static void updateMatiere(Matiere uneMatiere) {
		String requete = "update matiere set intitule = '"+uneMatiere.getIntitule()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Errer d'exécution de : " + requete);
		}
	}
}
