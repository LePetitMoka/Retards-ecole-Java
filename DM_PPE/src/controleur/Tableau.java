package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel{
	private Object donnees [][];
	private String entetes [];
	
	public Tableau(Object[][] donnees, String[] entetes) {
		this.donnees = donnees;
		this.entetes = entetes;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int numLigne, int numColonne) {
		return this.donnees[numLigne][numColonne];
	}
	
	public void insertLigne(Object ligne[]) {
		Object matrice [][] = new Object[this.donnees.length + 1][this.entetes.length];
		int i;
		for (i=0; i<this.donnees.length; i++) {
			matrice[i] = this.donnees[i];
		}
		matrice[this.donnees.length] = ligne;
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	public void supprimerLigne(int numLigne) {
		Object matrice [][] = new Object[this.donnees.length - 1][this.entetes.length];
		int i, j = 0;
		for (i=0; i<this.donnees.length; i++) {
			if(i != numLigne) {
				matrice[j] = this.donnees[i];
				j++;
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	public void modifierLigne(int numLigne, Object ligne[]) {
		Object matrice [][] = new Object[this.donnees.length][this.entetes.length];
		int i;
		for (i=0; i<this.donnees.length; i++) {
			if(i != numLigne) {
				matrice[i] = this.donnees[i];
			}else {
				matrice[i] = ligne;
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
	
	public void setDonnees(Object donnees[][]) {
		this.donnees = donnees;
		this.fireTableDataChanged();
	}
}
