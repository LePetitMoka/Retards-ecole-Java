package controleur;

import java.util.ArrayList;

import modele.M_Station;

public class C_Station {
	public static void insertStation(Station uneStation) {
		M_Station.insertStation(uneStation);
	}
	public static ArrayList<Station> selectAllStations() {
		return M_Station.selectAllStations();
	}
	public static void supprimerStation(int IdSt) {
		M_Station.supprimerStation(IdSt);
	}
	public static Station selectWhereStation(int IdSt) {
		return M_Station.selectWhereStation(IdSt);
	}
	public static void updateStation(Station uneStation) {
		M_Station.updateStation(uneStation);
	}
}
