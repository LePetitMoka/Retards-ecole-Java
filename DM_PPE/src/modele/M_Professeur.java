package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Professeur;
import controleur.VSql_Vue_Trajet_Details;

public class M_Professeur {
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "");
	
	public static String insertProfesseur(Professeur unProfesseur) {
		String message = "";
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
			message = "Insere";
		}
		catch(SQLException exp) {
			message = exp.getMessage();
		}
		return message;
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
	public static String updateProfesseur(Professeur unProfesseur) {
		String message = "";
		String requete = "update professeur set nom = '"+unProfesseur.getNom()
				+"', prenom = '"+unProfesseur.getPrenom()
				+"', diplome = '"+unProfesseur.getDiplome()
				+"', telephone = '"+unProfesseur.getTelephone()
				+"', email = '"+unProfesseur.getEmail()
				+"', mdp = '"+unProfesseur.getMdp()
				+"', adresse = '"+unProfesseur.getAdresse()
				+"' where idPf = " + unProfesseur.getIdU() + ";";
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();
				message = "Modifie";
			}catch (SQLException exp) {
				message = exp.getMessage();
			}
			return message;
	}
	public static Professeur selectWhereProfesseur(String email) {
		String requete = "select * from professeur where email = " + email;
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
	public static ArrayList<Professeur> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<Professeur> lesProfs = new ArrayList<Professeur>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Professeur where IdPf like '%"+mot+"%' "
						+ "OR prenom like '%"+mot+"%'"
						+ "OR nom like '%"+mot+"%'"
						+ "OR diplome like '%"+mot+"%'"
						+ "OR email like '%"+mot+"%'"
						+ "OR telephone like '%"+mot+"%'"
						+ "OR adresse like '%"+mot+"%';";

		
		
		} else {
			requete = "select * from Professeur where "+attribut+" like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				Professeur uneVue = new Professeur(
						desResultats.getInt("idPf"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("telephone"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("diplome")
						);
				//insertion de l'objet vue dans l'arraylist
				lesProfs.add(uneVue);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesProfs;
	}
}
