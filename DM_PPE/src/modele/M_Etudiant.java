package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Etudiant;

public class M_Etudiant  {	
	
	public static String  insertEtudiant(Etudiant unEtudiant) {
		String message = "";
		String requete = "insert into etudiant values(null, '"
				+unEtudiant.getNom()+"', '"
				+unEtudiant.getPrenom()+"', '"
				+unEtudiant.getEmail()+"', '"
				+unEtudiant.getTelephone()+"', '"
				+unEtudiant.getMdp()+"', '"
				+unEtudiant.getAdresse()+"', "
				+unEtudiant.getIdCl()+");";
		try {
			
		
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "OK";
		}
		catch(SQLException exp){
			message = exp.getMessage();
		}
		return message;
	}
	public static ArrayList<Etudiant> selectAllEtudiants() {
		ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
		String requete = "select * from etudiant;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesEtudiants;
	}
	public static String supprimerEtudiant(int idE) {
		String message = "";
		String requete = "delete from etudiant where idE = " + idE + ";";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			unStat.execute(requete);
			unStat.close();
			BDD.seDeConnecter();
			message = "Supprime";
		}
		catch(SQLException exp) {
			message = exp.getMessage();
		}
		return message;
	}
	public static Etudiant selectWhereEtudiant(int idE) {
		String requete = "select * from etudiant where idE = " + idE + ";";
		Etudiant unEtudiant = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
			
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
			BDD.printSQLException(exp);
		}
		return unEtudiant;
	}
	public static String updateEtudiant(Etudiant unEtudiant) {
			String message = "";
			String requete = "update etudiant set nom = '"+unEtudiant.getNom()
			+"', prenom = '"+unEtudiant.getPrenom()
			+"', idCl = '"+unEtudiant.getIdCl()
			+"', email = '"+unEtudiant.getEmail()
			+"', telephone = '"+unEtudiant.getTelephone()
			+"', mdp = '"+unEtudiant.getMdp()
			+"', adresse = '"+unEtudiant.getAdresse()
			+"' where idE = " + unEtudiant.getIdU() + ";";

			try{
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
	public static Etudiant selectWhereEtudiant(String email) {
		String requete = "select * from etudiant where email = " + email;
		Etudiant unEtudiant = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unEtudiant;
	}
	public static ArrayList<Etudiant> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<Etudiant> lesEtus = new ArrayList<Etudiant>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Etudiant where IdE like '%"+mot+"%' "
						+ "OR prenom like '%"+mot+"%'"
						+ "OR nom like '%"+mot+"%'"
						+ "OR adresse like '%"+mot+"%'"
						+ "OR telephone like '%"+mot+"%'"
						+ "OR email like '%"+mot+"%'"
						+ "OR idCl like '%"+mot+"%';";

		
		
		} else {
			requete = "select * from Etudiant where "+attribut+" like '%"+mot+"%';";
		}
		try {
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				Etudiant unEtu = new Etudiant(
						desResultats.getInt("idE"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("adresse"),
						desResultats.getString("telephone"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getInt("idCl")
						);
				//insertion de l'objet vue dans l'arraylist
				lesEtus.add(unEtu);
			}
			unStat.close();
			BDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesEtus;
	}
}
