package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Etudiant;
import controleur.Matiere;

public class M_Matiere {
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "");
	
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
			System.out.println("Erreur d'execution de : " + requete);
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
	public static String supprimerMatiere(int idM) {
		String requete = "delete from matiere where idM = " + idM;
		String message = "";
			try {
				uneBdd.seConnecter();		
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeConnecter();
				message = "Supprime";
			}catch (SQLException exp){
				message = exp.getMessage();
			}
			return message;
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
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneMatiere;
	}
	public static void updateMatiere(Matiere uneMatiere) {
		String requete = "update matiere set intitule = '"+uneMatiere.getIntitule()+"' where IdM = "+ uneMatiere.getIdM()+";";
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
	public static ArrayList<Matiere> selectSearch(String attribut, String mot) {
		String requete = "";
		 ArrayList<Matiere> lesMats = new ArrayList<Matiere>();
		 if (attribut.equals("Tous")) {
			requete = "select * from Matiere where IdM like '%"+mot+"%' "
						+ "OR intitule like '%"+mot+"%';";

		
		
		} else {
			requete = "select * from Matiere where "+attribut+" like '%"+mot+"%';";
		}
		try {
			uneBdd.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			//parcourir les resultats et construire des objets vues
			while (desResultats.next()) {
				Matiere uneMat = new Matiere(
						desResultats.getInt("idM"),
						desResultats.getString("intitule")
						);
				//insertion de l'objet vue dans l'arraylist
				lesMats.add(uneMat);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesMats;
	}
}
