package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Administrateur;

public class M_Administrateur {
	private static BDD uneBDD = new BDD("localhost:3307", "GestRetards", "root", "");
	
	public static void insertAdministrateur(Administrateur unAdministrateur) throws SQLException{
		String requete = "insert into administrateur values(null, '"+unAdministrateur.getNom()+"', '"
				+unAdministrateur.getPrenom()+"', '"+unAdministrateur.getEmail()+"', '"
				+unAdministrateur.getTelephone()+"', '"+unAdministrateur.getAdresse()+"', '"
				+unAdministrateur.getMdp()+"', '"+unAdministrateur.getUrlSignature()+"');";
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		
	}
	public static ArrayList<Administrateur> selectAllAdministrateurs() {
		ArrayList<Administrateur> lesAdministrateurs = new ArrayList<Administrateur>();
		String requete = "select * from administrateur;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Administrateur unAdministrateur = new Administrateur(
						desResultats.getInt("idAd"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("telephone"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("URLSignature")
						);
				lesAdministrateurs.add(unAdministrateur);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesAdministrateurs;
	}
	public static void supprimerAdministrateur(int idAd) {
		String requete = "delete from administrateur where idAd = " + idAd;
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
	public static Administrateur selectWhereAdministrateur(int idAd) {
		String requete = "select * from administrateur where idAd = " + idAd;
		Administrateur unAdministrateur = null;
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unAdministrateur = new Administrateur (
						unResultat.getInt("idAd"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getString("telephone"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("URLSignature")
						);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unAdministrateur;
	}
	public static Administrateur selectWhereAdministrateur(String email, String mdp) {
		String requete = "select * from administrateur where email = ? and mdp = sha1(?);";
		Administrateur unAdministrateur = null;
		try {
			uneBDD.seConnecter();
			PreparedStatement pstmt = uneBDD.getMaConnexion().prepareStatement(requete);
			pstmt.setString(1,email);  
			pstmt.setString(2,mdp); 
			ResultSet unResultat = pstmt.executeQuery();
			if(unResultat.next()) {
				unAdministrateur = new Administrateur (
						unResultat.getInt("idAd"),
						unResultat.getString("nom"),
						unResultat.getString("prenom"),
						unResultat.getString("adresse"),
						unResultat.getString("telephone"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("URLSignature")
						);
			}
			pstmt.close();
			uneBDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unAdministrateur;
	}
	public static void updateAdministrateur(Administrateur unAdministrateur) throws SQLException{
		String requete = "update administrateur set nom = '"+unAdministrateur.getNom()+"', prenom = '"
				+unAdministrateur.getPrenom()+"', adresse = '"+unAdministrateur.getAdresse()+
				"', telephone = '"+unAdministrateur.getTelephone()+"', mdp = '"+unAdministrateur.getMdp()+
				"', URLSignature = '"+unAdministrateur.getUrlSignature()+"', email = '"
				+unAdministrateur.getEmail()+"' where idAd = " + unAdministrateur.getIdU() + ";";
		uneBDD.seConnecter();
		Statement unStat = uneBDD.getMaConnexion().createStatement();
		unStat.execute(requete);
		unStat.close();
		uneBDD.seDeConnecter();
	}
}
