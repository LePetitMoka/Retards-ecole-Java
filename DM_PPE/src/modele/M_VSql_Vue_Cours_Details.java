package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Etudiant;
import controleur.VSql_Vue_Cours_Details;

public class M_VSql_Vue_Cours_Details {
	
	private static BDD uneBdd = new BDD("localhost:3307", "GestRetards", "root", "");

	//Fait reference a une VUE donc pas d'inserts ni update !
		
		public static ArrayList<VSql_Vue_Cours_Details> selectAllVues() {
			ArrayList<VSql_Vue_Cours_Details> lesuneVues = new ArrayList<VSql_Vue_Cours_Details>();
			String requete = "select * from vue_cours_details;";
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				while (desResultats.next()) {
					VSql_Vue_Cours_Details uneVue = new VSql_Vue_Cours_Details(
							desResultats.getString("matiere"),
							desResultats.getString("nomCl"),
							desResultats.getString("nomPf"),
							desResultats.getString("dateTS"),
							desResultats.getString("dateC"),
							desResultats.getString("heureDeb"),
							desResultats.getString("heureFin"),
							desResultats.getString("duree"),
							desResultats.getString("salle"),
							desResultats.getInt("IdCl"),
							desResultats.getInt("IdPf"),
							desResultats.getInt("IdM")
							);
					lesuneVues.add(uneVue);
				}
				unStat.close();
				uneBdd.seDeConnecter();
			}
			catch(SQLException exp){
				System.out.println("Erreur d'execution de : " + requete);
			}
			return lesuneVues;
		}
		// gadget
		public static VSql_Vue_Cours_Details selectWhereVue(int IdCl, int IdM,int IdPf, String dateTS) {
			String requete = "select * from vue_cours_details where IdM = " + IdM + " and IdPf = " + IdPf +" and IdCl ="+IdCl+" and dateTS = '"+dateTS+"';";
			VSql_Vue_Cours_Details uneVue = null;
			try {
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unResultat = unStat.executeQuery(requete);
				if(unResultat.next()) {
					uneVue = new VSql_Vue_Cours_Details (
							unResultat.getString("matiere"),
							unResultat.getString("nomCl"),
							unResultat.getString("nomPf"),
							unResultat.getString("dateTS"),
							unResultat.getString("dateC"),
							unResultat.getString("heureDeb"),
							unResultat.getString("heureFin"),
							unResultat.getString("duree"),
							unResultat.getString("salle"),
							unResultat.getInt("IdCl"),
							unResultat.getInt("IdPf"),
							unResultat.getInt("IdM")
							);
				}
				unStat.close();
				uneBdd.seDeConnecter();
			}
			catch(SQLException exp) {
				System.out.println("Erreur d'execution de : " + requete);
			}
			return uneVue;
		}
		public static ArrayList<VSql_Vue_Cours_Details> selectSearch(String attribut, String mot) {
			String requete = "";
			 ArrayList<VSql_Vue_Cours_Details> lesVues = new ArrayList<VSql_Vue_Cours_Details>();
			 if (attribut.equals("Tous")) {
				requete = "select * from Vue_Cours_Details where matiere like '%"+mot+"%' "
							+ "OR nomcl like '%"+mot+"%'"
							+ "OR dateTS like '%"+mot+"%'"
							+ "OR dateC like '%"+mot+"%'"
							+ "OR heureDeb like '%"+mot+"%'"
							+ "OR heureFin like '%"+mot+"%'"
							+ "OR duree like '%"+mot+"%'"
							+ "OR salle like '%"+mot+"%'"
							+ "OR IdCl like '%"+mot+"%'"
							+ "OR IdPf like '%"+mot+"%'"
							+ "OR IdM like '%"+mot+"%';";

			
			
			} else {
				requete = "select * from Vue_Cours_Details where "+attribut+" like '%"+mot+"%';";
			}
			try {
				uneBdd.seConnecter();
				//on instancie un curseur qui permet l'execution de la requete
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet desResultats = unStat.executeQuery(requete);
				//parcourir les resultats et construire des objets vues
				while (desResultats.next()) {
					VSql_Vue_Cours_Details uneVue = new VSql_Vue_Cours_Details(
							desResultats.getString("matiere"),
							desResultats.getString("nomCl"),
							desResultats.getString("nomPf"),
							desResultats.getString("dateTS"),
							desResultats.getString("dateC"),
							desResultats.getString("heureDeb"),
							desResultats.getString("heureFin"),
							desResultats.getString("duree"),
							desResultats.getString("salle"),
							desResultats.getInt("IdCl"),
							desResultats.getInt("IdPf"),
							desResultats.getInt("IdM")
							);
					//insertion de l'objet vue dans l'arraylist
					lesVues.add(uneVue);
				}
				unStat.close();
				uneBdd.seDeConnecter();
			} catch (SQLException exp){
				System.out.println("Erreur d'execution de :"+ requete);
				}
			return lesVues;
		}
	}
