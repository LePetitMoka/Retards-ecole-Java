package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Perturbation;

public class M_Perturbation {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertPerturbation(Perturbation unePerturbation) {
		String requete = "insert into perturbation values('"+unePerturbation.getIdPt()+"','"
				+unePerturbation.getRaisonCourte()+"','"
				+unePerturbation.getRaisonLongue()+"','"
				+unePerturbation.getDateDebMessage()+"','"
				+unePerturbation.getDateFinMessage()+"');";
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
	public static ArrayList<Perturbation> selectAllPerturbations() {
		ArrayList<Perturbation> lesPerturbations = new ArrayList<Perturbation>();
		String requete = "select * from perturbation;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Perturbation unePerturbation = new Perturbation(
						desResultats.getString("IdPt"),
						desResultats.getString("raisonCourte"),
						desResultats.getString("raisonLongue"),
						desResultats.getString("dateDebMessage"),
						desResultats.getString("dateFinMessage")
						);
				lesPerturbations.add(unePerturbation);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesPerturbations;
	}
	public static void supprimerPerturbation(int IdPt) {
		String requete = "delete from perturbation where IdPt = " + IdPt;
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
	public static Perturbation selectWherePerturbation(int IdPt) {
		String requete = "select * from perturbation where IdPt = " + IdPt;
		Perturbation unePerturbation = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unePerturbation = new Perturbation (
						unResultat.getString("IdPt"),
						unResultat.getString("raisonCourte"),
						unResultat.getString("raisonLongue"),
						unResultat.getString("dateDebMessage"),
						unResultat.getString("dateFinMessage")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'ex�cution de : " + requete);
		}
		return unePerturbation;
	}
	public static void updatePerturbation(Perturbation unePerturbation) {
		String requete = "update perturbation set dateDebMessage = '"+unePerturbation.getDateDebMessage()
				+"', dateFinMessage = '"+unePerturbation.getDateFinMessage()
				+"', raisonCourte = '"+unePerturbation.getRaisonCourte()
				+"', raisonLongue = '"+unePerturbation.getRaisonLongue()
				+"' where idPt = " + unePerturbation.getIdPt() + ";";
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