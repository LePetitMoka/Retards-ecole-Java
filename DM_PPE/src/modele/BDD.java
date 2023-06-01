package modele;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDD {
	public static String serveur,bdd,user,mdp;
	public static Connection maConnexion;
	
	public BDD(String serveur, String bdd, String user, String mdp) {
		maConnexion = null;
		BDD.serveur = serveur;
		BDD.bdd = bdd;
		BDD.user = user;
		BDD.mdp = mdp;
	}
	
	public BDD() {
		try {
			maConnexion = null;
			Properties prop = new Properties();
			URL url = getClass().getResource("config.properties");
			FileInputStream input = new FileInputStream("config.properties");
			prop.load(input);
			
			BDD.serveur = prop.getProperty("db.host");
			BDD.bdd= prop.getProperty("db.database");
			BDD.user = prop.getProperty("db.username");
			BDD.mdp = prop.getProperty("db.password");

		    input.close();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void chargerPilote() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException exp){
			System.out.println("Absence de pilote JDBC");
		}
	}
	public static void seConnecter() {
		String url = "jdbc:mysql://"+BDD.serveur+"/"+BDD.bdd;
		url +="?verifyServerCertificate=false&useSSL=false&requireSSL=false"; // desactivation verification du certificat SSL (specifique a macOS)
		chargerPilote();
		try {
			maConnexion = DriverManager.getConnection(url, BDD.user, BDD.mdp);
		}
		catch(SQLException exp) {
			printSQLException(exp);
			System.out.println("Erreur de connexion a "+ url);
			exp.printStackTrace();
		}
	}
	public static void seDeConnecter() {
		try {
			if(maConnexion != null) {
				maConnexion.close();
			}
		} catch (SQLException exp) {
			System.out.println("Erreur de fermeture de la connexion");
			printSQLException(exp);
		}
		
	}
	
	public static boolean ignoreSQLException(String sqlState) {
		// cette classe permet de filtrer les erreurs a ne pas considerer comme telles (liste blanche)
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
		//imprime l'erreur dans la console
	    for (Throwable e : ex) {
	    	// filtre liste blanche
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
	public static void modifConfigProperties (String H,String B,String U,String P) throws IOException {
		
		BDD.serveur = H;
        BDD.bdd = B;
        BDD.user = U;
        BDD.mdp = P;
        
		Properties props = new Properties();
		
		props.setProperty("db.host",BDD.serveur);
		props.setProperty("db.database",BDD.bdd);
		props.setProperty("db.username",BDD.user);
		props.setProperty("db.password",BDD.mdp);
		
		FileOutputStream out = new FileOutputStream("config.properties");
		props.store(out, "Database Configuration");
		out.close();
	}
}

