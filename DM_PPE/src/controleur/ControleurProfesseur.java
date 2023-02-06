package controleur;

import modele.ModeleProfesseur;

public class ControleurProfesseur {
	public static Professeur authentificationProfesseur(String email, String mdp) {
		return ModeleProfesseur.authentificationProfesseur(email, mdp);
	}
}
