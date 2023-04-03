package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Transport;

public class M_Transport  {
	
	public static void insertTransport(Transport unTransport) {
		String requete = "insert into transport values('"+unTransport.getIdTp()+"','"
				+unTransport.getNom()+"','"
				+unTransport.getType()+"','"
				+unTransport.getTransporteur()+"','"
				+unTransport.getPictogramme()+"','"
				+unTransport.getEtat()+"');";
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
	public static ArrayList<Transport> selectAllTransports() {
		ArrayList<Transport> lesTransports = new ArrayList<Transport>();
		String requete = "select * from transport order by nom;";
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
		}
		catch(SQLException exp){
			System.out.println("Erreur d'execution de : " + requete);
		}
		return lesTransports;
	}
	public static void supprimerTransport(int IdTp) {
		String requete = "delete from transport where IdTp = " + IdTp;
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
	public static Transport selectWhereTransport(int IdTp) {
		String requete = "select * from transport where IdTp = " + IdTp;
		Transport unTransport = null;
		try {
			BDD.seConnecter();
			Statement unStat = BDD.maConnexion.createStatement();
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
			BDD.seDeConnecter();
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
}