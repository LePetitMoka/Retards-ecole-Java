package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.C_VSql_Vue_Perturbation_Ligne;
import controleur.Tableau;
import controleur.VSql_Vue_Perturbation_Ligne;

public class P_VueLPt extends P_Principal implements ActionListener {
	
	
	//private JButton btAnnuler = new JButton("Annuler"); // pe pour fonction de recherche + tard
	
	//private JTextField txtIdE = new JTextField("Id Etudiant");
	
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_VueLPt () {
		super(GREI.color1);
				
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(20, 20, 800, 720);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Transport", "Nom", "Transporteur", "Etat", "Raison"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		ChangeName(this.uneTable,0,"ID_Transport");
		ChangeName(this.uneTable,1,"Nom");
		ChangeName(this.uneTable,2,"Transporteur");
		ChangeName(this.uneTable,3,"Etat");
		ChangeName(this.uneTable,4,"Raison");
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 0, 740, 280);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
	}
	public void ChangeName(JTable table, int col_index, String col_name){
		  table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
		  }
	public void viderChamps() {
		//this.txtIdE.setText("");				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<VSql_Vue_Perturbation_Ligne> lesVueLPts = C_VSql_Vue_Perturbation_Ligne.selectAllVues();
		Object[][] matrice = new Object [lesVueLPts.size()][5];
		int i=0;
		for (VSql_Vue_Perturbation_Ligne uneVueLPt : lesVueLPts) {
			matrice[i][0] = uneVueLPt.getIdTp();
			matrice[i][1] = uneVueLPt.getNom();
			matrice[i][2] = uneVueLPt.getTransporteur();
			matrice[i][3] = uneVueLPt.getEtat();
			matrice[i][4] = uneVueLPt.getRaison();
			//matrice[i][5] = uneVueLPt.getPictogramme();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
