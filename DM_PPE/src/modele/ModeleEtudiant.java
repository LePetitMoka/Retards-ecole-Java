package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Etudiant;

public class ModeleEtudiant {
	private static BDD uneBdd = new BDD("localhost:3307", "DM_PPE_JAVA", "root", "");
	
	public static void insertEtudiant(Etudiant unEtudiant) {
		String requete = "insert into etudiant values(null, '"
				+unEtudiant.getNom()+"', '"
				+unEtudiant.getPrenom()+"', '"
				+unEtudiant.getEmail()+"', '"
				+unEtudiant.getAdresse()+"', '"
				+unEtudiant.getIdCl()+"');";
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
	public static ArrayList<Etudiant> selectAllEtudiants() {
		ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
		String requete = "select * from etudiant;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Etudiant unEtudiant = new Etudiant(
						desResultats.getInt("idE"),
						desResultats.getInt("idCl"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("email"),
						desResultats.getString("adresse")
						);
				lesEtudiants.add(unEtudiant);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesEtudiants;
	}
	public static void supprimerEtudiant(int idE) {
		String requete = "delete from etudiant where idE = " + idE;
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
	public static Etudiant selectWhereEtudiant(int idE) {
		String requete = "select * from etudiant where idE = " + idE;
		Etudiant unEtudiant = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unEtudiant = new Etudiant (
						unResultat.getInt("idE"),
						unResultat.getInt("idCl"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("email"),
						unResultat.getString("adresse")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'exécution de : " + requete);
		}
		return unEtudiant;
	}
	public static void updateEtudiant(Etudiant unEtudiant) {
		String requete = "update etudiant set nom = '"+unEtudiant.getNom()
				+"', prenom = '"+unEtudiant.getPrenom()
				+"', idCl = '"+unEtudiant.getIdCl()
				+"', email = '"+unEtudiant.getEmail()
				+"', adresse = '"+unEtudiant.getAdresse()
				+"' where idE = " + unEtudiant.getIdE() + ";";
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
