package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Matiere;

public class M_Matiere  {
	
	public static void insertMatiere(Matiere uneMatiere) {
		String requete = "insert into Matiere values(null, '"
				+uneMatiere.getIntitule()+"');";
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
	public static ArrayList<Matiere> selectAllMatieres() {
		ArrayList<Matiere> lesMatieres = new ArrayList<Matiere>();
		String requete = "select * from Matiere;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Matiere uneMatiere = new Matiere(
						desResultats.getInt("idM"),
						desResultats.getString("intitule")
						);
				lesMatieres.add(uneMatiere);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesMatieres;
	}
	public static String supprimerMatiere(int idM) {
		String requete = "delete from Matiere where idM = " + idM;
		String message = "";
			try {
				BDD.seConnecter();		
				Statement unStat = BDD.maConnexion.createStatement();
				unStat.execute(requete);
				unStat.close();
				BDD.seDeConnecter();
				message = "Supprime";
			}catch (SQLException exp){
				message = exp.getMessage();
			}
			return message;
	}
	public static Matiere selectWhereMatiere(int idM) {
		String requete = "select * from Matiere where idM = " + idM;
		Matiere uneMatiere = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				uneMatiere = new Matiere (
						unResultat.getInt("idM"),
						unResultat.getString("intitule")
						);
			}
			unStat.close();
			BDD.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return uneMatiere;
	}
	public static void updateMatiere(Matiere uneMatiere) {
		String requete = "update Matiere set intitule = '"+uneMatiere.getIntitule()+"' where IdM = "+ uneMatiere.getIdM()+";";
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
			BDD.seConnecter();
			//on instancie un curseur qui permet l'execution de la requete
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		} catch (SQLException exp){
			System.out.println("Erreur d'execution de :"+ requete);
			}
		return lesMats;
	}
}
