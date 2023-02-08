package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Professeur;

public class M_Professeur {
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards2", "root", "");
	
	public static void insertProfesseur(Professeur unProfesseur) {
		String requete = "insert into professeur values(null, '"
				+unProfesseur.getNom()+"', '"
				+unProfesseur.getPrenom()+"', '"
				+unProfesseur.getDiplome()+"', '"
				+unProfesseur.getEmail()+"', '"
				+unProfesseur.getMdp()+"', '"
				+unProfesseur.getAdresse()+"');";
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
	public static ArrayList<Professeur> selectAllProfesseurs() {
		ArrayList<Professeur> lesProfesseurs = new ArrayList<Professeur>();
		String requete = "select * from professeur;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Professeur unProfesseur = new Professeur(
						desResultats.getInt("idPf"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("telephone"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("diplome")
						);
				lesProfesseurs.add(unProfesseur);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesProfesseurs;
	}
	public static void supprimerProfesseur(int idPf) {
		String requete = "delete from professeur where idPf = " + idPf;
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
	public static Professeur selectWhereProfesseur(int idPf) {
		String requete = "select * from professeur where idPf = " + idPf;
		Professeur unProfesseur = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unProfesseur = new Professeur (
						unResultat.getInt("idPf"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getString("telephone"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("diplome")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unProfesseur;
	}
	public static void updateProfesseur(Professeur unProfesseur) {
		String requete = "update professeur set nom = '"+unProfesseur.getNom()
				+"', prenom = '"+unProfesseur.getPrenom()
				+"', diplome = '"+unProfesseur.getDiplome()
				+"', telephone = '"+unProfesseur.getTelephone()
				+"', email = '"+unProfesseur.getEmail()
				+"', mdp = '"+unProfesseur.getMdp()
				+"', adresse = '"+unProfesseur.getAdresse()
				+"' where idPf = " + unProfesseur.getIdU() + ";";
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
}
