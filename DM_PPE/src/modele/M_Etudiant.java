package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Etudiant;

public class M_Etudiant {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertEtudiant(Etudiant unEtudiant) {
		String requete = "insert into etudiant values(null, '"
				+unEtudiant.getNom()+"', '"
				+unEtudiant.getPrenom()+"', '"
				+unEtudiant.getEmail()+"', '"
				+unEtudiant.getTelephone()+"', '"
				+unEtudiant.getMdp()+"', '"
				+unEtudiant.getAdresse()+"', "
				+unEtudiant.getIdCl()+");";
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
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("telephone"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getInt("idCl")
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
		String requete = "delete from etudiant where idE = " + idE + ";";
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
	public static Etudiant selectWhereEtudiant(int idE) {
		String requete = "select * from etudiant where idE = " + idE + ";";
		Etudiant unEtudiant = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unEtudiant = new Etudiant (
						unResultat.getInt("idE"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getString("telephone"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getInt("idCl")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unEtudiant;
	}
	public static void updateEtudiant(Etudiant unEtudiant) {
		String requete = "update etudiant set nom = '"+unEtudiant.getNom()
				+"', prenom = '"+unEtudiant.getPrenom()
				+"', idCl = '"+unEtudiant.getIdCl()
				+"', email = '"+unEtudiant.getEmail()
				+"', telephone = '"+unEtudiant.getTelephone()
				+"', mdp = '"+unEtudiant.getEmail()
				+"', adresse = '"+unEtudiant.getAdresse()
				+"' where idE = " + unEtudiant.getIdU() + ";";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Errer d'execution de : " + requete);
		}
	}
}
