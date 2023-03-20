package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDD {
	private String serveur,bdd,user,mdp;
	Connection maConnexion;
	
	public BDD(String serveur, String bdd, String user, String mdp) {
		this.maConnexion = null;
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
	}
	
	public void chargerPilote() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException exp){
			System.out.println("Absence de pilote JDBC");
		}
	}
	public void seConnecter() {
		String url = "jdbc:mysql://"+this.serveur+"/"+this.bdd;
		url +="?verifyServerCertificate=false&useSSL=false&requireSSL=false"; // desactivation verification du certificat SSL (MAC bruh)
		this.chargerPilote();
		try {
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		}
		catch(SQLException exp) {
			System.out.println("Erreur de connexion a "+ url);
			exp.printStackTrace();
		}
	}
	public void seDeConnecter() {
		try {
			if(this.maConnexion != null) {
				this.maConnexion.close();
			}
		} catch (SQLException exp) {
			System.out.println("Erreur de fermeture de la connexion");
		}
		
	}
	public Connection getMaConnexion() {
		return this.maConnexion;
	}
	
	public static boolean ignoreSQLException(String sqlState) {

	    if (sqlState == null) {
	        System.out.println("The SQL state is not defined!");
	        return false;
	    }

	    // X0Y32: Jar file already exists in schema
	    if (sqlState.equalsIgnoreCase("X0Y32"))
	        return true;

	    // 42Y55: Table already exists in schema
	    if (sqlState.equalsIgnoreCase("42Y55"))
	        return true;

	    return false;
	}
	
	public static void printSQLException(SQLException ex) {

	    for (Throwable e : ex) {
	        if (e instanceof SQLException) {
	            if (ignoreSQLException(
	                ((SQLException)e).
	                getSQLState()) == false) {

	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " +
	                    ((SQLException)e).getSQLState());

	                System.err.println("Error Code: " +
	                    ((SQLException)e).getErrorCode());

	                System.err.println("Message: " + e.getMessage());

	                Throwable t = ex.getCause();
	                while(t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	}
}

