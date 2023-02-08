package controleur;

import java.util.ArrayList;

import modele.M_Transport;

public class C_Transport {
	public static void insertTransport(Transport unTransport) {
		M_Transport.insertTransport(unTransport);
	}
	public static ArrayList<Transport> selectAllTransports() {
		return M_Transport.selectAllTransports();
	}
	public static void supprimerTransport(int IdTp) {
		M_Transport.supprimerTransport(IdTp);
	}
	public static Transport selectWhereTransport(int IdTp) {
		return M_Transport.selectWhereTransport(IdTp);
	}
	public static void updateTransport(Transport unTransport) {
		M_Transport.updateTransport(unTransport);
	}
}
