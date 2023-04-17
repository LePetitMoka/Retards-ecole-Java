package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Administrateur;

public class M_Administrateur {
	
	public static String insertAdministrateur(Administrateur unAdministrateur) {
		String message = null;
		String requete = "insert into Administrateur values(null, '"+unAdministrateur.getNom()+"', '"
				+unAdministrateur.getPrenom()+"', '"+unAdministrateur.getEmail()+"', '"
				+unAdministrateur.getTelephone()+"', '"+unAdministrateur.getAdresse()+"', '"
				+unAdministrateur.getMdp()+"', '"+unAdministrateur.getUrlSignature()+"');";
			try{
				BDD.seConnecter();
				Statement unStat = BDD.maConnexion.createStatement();
				unStat.execute(requete);
				unStat.close();
				BDD.seDeConnecter();
				message = "Admin insere";
			} catch (SQLException exp) {
				BDD.printSQLException(exp);
			}
		return message;
	}
	public static ArrayList<Administrateur> selectAllAdministrateurs() {
		ArrayList<Administrateur> lesAdministrateurs = new ArrayList<Administrateur>();
		String requete = "select * from Administrateur;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesAdministrateurs;
	}
	public static void supprimerAdministrateur(int idAd) {
		String requete = "delete from Administrateur where idAd = " + idAd;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
	}
	public static Administrateur selectWhereAdministrateur(int idAd) {
		String requete = "select * from Administrateur where idAd = " + idAd;
		Administrateur unAdministrateur = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
			BDD.printSQLException(exp);
		}
		return unAdministrateur;
	}
	public static Administrateur selectWhereAdministrateur(String email, String mdp) {
		String requete = "select * from Administrateur where email = ? and mdp = sha1(?);";
		Administrateur unAdministrateur = null;
		try {
			BDD.seConnecter();
			PreparedStatement pstmt = BDD.maConnexion.prepareStatement(requete);
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unAdministrateur;
	}
	public static String updateAdministrateur(Administrateur unAdministrateur){
		String message = null;
		String requete = "update Administrateur set nom = '"+unAdministrateur.getNom()+"', prenom = '"
				+unAdministrateur.getPrenom()+"', adresse = '"+unAdministrateur.getAdresse()+
				"', telephone = '"+unAdministrateur.getTelephone()+"', mdp = '"+unAdministrateur.getMdp()+
				"', URLSignature = '"+unAdministrateur.getUrlSignature()+"', email = '"
				+unAdministrateur.getEmail()+"' where idAd = " + unAdministrateur.getIdU() + ";";
		try {
		BDD.seConnecter();
		Statement unStat = BDD.maConnexion.createStatement();
		unStat.execute(requete);
		unStat.close();
		BDD.seDeConnecter();
		message = "Profil modifie";
		} catch (SQLException exp) {
			
			//System.out.println("Erreur d'execution de l'update: ");
			switch (exp.getSQLState().toString()){
				case "45001": message = exp.getMessage();break;
				case "45002": message = exp.getMessage();break;
				default: BDD.printSQLException(exp);break;
			}
		}
		return message;
	}
}
