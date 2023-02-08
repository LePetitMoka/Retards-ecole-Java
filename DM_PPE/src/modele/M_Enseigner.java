package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Enseigner;

public class M_Enseigner {
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards2", "root", "");
	
	public static void insertEnseigner(Enseigner unEnseig) {
		String requete = "insert into enseigner values("
				+unEnseig.getIdM()+", "
				+unEnseig.getIdPf()+");";
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
	public static ArrayList<Enseigner> selectAllEnseigners() {
		ArrayList<Enseigner> lesunEnseigs = new ArrayList<Enseigner>();
		String requete = "select * from enseigner;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Enseigner unEnseig = new Enseigner(
						desResultats.getInt("idM"),
						desResultats.getInt("idPf")
						);
				lesunEnseigs.add(unEnseig);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesunEnseigs;
	}
	public static void supprimerEnseigner(int idM, int idPf) {
		String requete = "delete from enseigner where idM = " + idM + " and idPf = " + idPf + ";";
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
	public static Enseigner selectWhereEnseigner(int idM, int idPf) {
		String requete = "select * from enseigner where idM = " + idM + " and idPf = " + idPf + ";";
		Enseigner unEnseig = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unEnseig = new Enseigner (
						unResultat.getInt("idM"),
						unResultat.getInt("idPf")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unEnseig;
	}
}
