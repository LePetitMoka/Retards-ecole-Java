package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Transport;

public class M_Transport {
	private static BDD uneBdd = new BDD("localhost:8889", "GestRetards", "root", "root");
	
	public static void insertTransport(Transport unTransport) {
		String requete = "insert into transport values('"+unTransport.getIdTp()+"','"
				+unTransport.getNom()+"','"
				+unTransport.getType()+"','"
				+unTransport.getTransporteur()+"','"
				+unTransport.getPictogramme()+"','"
				+unTransport.getEtat()+"');";
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
	public static ArrayList<Transport> selectAllTransports() {
		ArrayList<Transport> lesTransports = new ArrayList<Transport>();
		String requete = "select * from transport order by nom;";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Transport unTransport = new Transport(
						desResultats.getString("IdTp"),
						desResultats.getString("nom"),
						desResultats.getString("type"),
						desResultats.getString("transporteur"),
						desResultats.getString("pictogramme"),
						desResultats.getString("etat")
						);
				lesTransports.add(unTransport);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesTransports;
	}
	public static void supprimerTransport(int IdTp) {
		String requete = "delete from transport where IdTp = " + IdTp;
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
	public static Transport selectWhereTransport(int IdTp) {
		String requete = "select * from transport where IdTp = " + IdTp;
		Transport unTransport = null;
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unTransport = new Transport (
						unResultat.getString("IdTp"),
						unResultat.getString("nom"),
						unResultat.getString("type"),
						unResultat.getString("transporteur"),
						unResultat.getString("pictogramme"),
						unResultat.getString("etat")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + requete);
		}
		return unTransport;
	}
	public static void updateTransport(Transport unTransport) {
		String requete = "update transport set nom = '"+unTransport.getNom()
				+"', type = '"+unTransport.getType()
				+"', transporteur = '"+unTransport.getTransporteur()
				+"', pictogramme = '"+unTransport.getPictogramme()
				+"', etat = '"+unTransport.getEtat()
				+"' where IdTp = " + unTransport.getIdTp() + ";";
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