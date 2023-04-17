package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Classe;

public class M_Classe {
	
	public static String insertClasse(Classe uneClasse) {
		String message = "";
		String requete = "insert into Classe values(null, '"
				+uneClasse.getNom()+"', '"
				+uneClasse.getDiplomePrepare()+"', '"
				+uneClasse.getPromotion()+"');";
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
	public static ArrayList<Classe> selectAllClasses() {
		ArrayList<Classe> lesClasses = new ArrayList<Classe>();
		String requete = "select * from Classe;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Classe uneClasse = new Classe(
						desResultats.getInt("idCl"),
						desResultats.getInt("nbEtudiants"),
						desResultats.getString("nom"),
						desResultats.getString("diplomePrepare"),
						desResultats.getString("promotion"),
						desResultats.getString("email")
						);
				lesClasses.add(uneClasse);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesClasses;
	}
	public static String supprimerClasse(int idCl) {
		String message = "";
		String requete = "delete from Classe where idCl = " + idCl + ";";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}catch (SQLException exp) {
			switch(exp.getErrorCode()) {
			case 1451: message = "Erreur: Retirez les eleves de cette classe avant de la supprimer";break;
			default: message = exp.getMessage();break;
			}
		}
		return message;
	}
	public static Classe selectWhereClasse(int idCl) {
		String requete = "select * from Classe where idCl = " + idCl;
		Classe uneClasse = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneClasse = new Classe (
						unResultat.getInt("idCl"),
						unResultat.getInt("nbEtudiants"),
						unResultat.getString("nom"),
						unResultat.getString("diplomePrepare"),
						unResultat.getString("promotion"),
						unResultat.getString("email")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneClasse;
	}
	public static String updateClasse(Classe uneClasse) {
		String message = "";
		String requete = "update Classe set nom = '"+uneClasse.getNom()
				+"', diplomePrepare = '"+uneClasse.getDiplomePrepare()
				+"', promotion = '"+uneClasse.getPromotion()
				+"' where idCl = " + uneClasse.getIdCl() + ";";
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
	public static Classe selectWhereClasse(String email) {
		String requete = "select * from Classe where email = " + email;
		Classe uneClasse = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneClasse = new Classe (
						unResultat.getInt("idCl"),
						unResultat.getInt("nbEtudiants"),
						unResultat.getString("nom"),
						unResultat.getString("diplomePrepare"),
						unResultat.getString("promotion"),
						unResultat.getString("email")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneClasse;
	}
	public static ArrayList<Classe> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<Classe> lesClasses = new ArrayList<Classe>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Classe where IdCl like '%"+mot+"%' "
						+ "OR nbEtudiant like '%"+mot+"%'"
						+ "OR diplomePrepare like '%"+mot+"%'"
						+ "OR nom like '%"+mot+"%'"
						+ "OR promotion like '%"+mot+"%'"
						+ "OR email like '%"+mot+"%';";

		
		
		} else {
			requete = "select * from Classe where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				Classe uneClasse = new Classe(
						desResultats.getInt("idCl"),
						desResultats.getInt("nbEtudiants"),
						desResultats.getString("nom"),
						desResultats.getString("diplomePrepare"),
						desResultats.getString("promotion"),
						desResultats.getString("email")
						);
				//insertion de l'objet vue dans l'arraylist
				lesClasses.add(uneClasse);
			}
			unStat.close();
			BDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesClasses;
	}
}
