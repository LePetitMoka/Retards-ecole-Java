package controleur;

import modele.M_Professeur;

public class C_Professeur {
	public static Professeur authentificationProfesseur(String email, String mdp) {
		return M_Professeur.authentificationProfesseur(email, mdp);
	}
}
