package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_VSql_Vue_Perturbation_Ligne;
import controleur.C_VSql_Vue_Trajet_Details;
import controleur.Tableau;
import controleur.VSql_Vue_Perturbation_Ligne;
import controleur.VSql_Vue_Trajet_Details;

public class P_VueLPt extends P_Principal implements ActionListener {
	
	private JComboBox<String> cbxFiltre = new JComboBox<String>();
	private JButton btFiltrer = new JButton("Filtrer");
	private JPanel PanelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btAnnuler = new JButton("Annuler"); 
	
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_VueLPt () {
		super(GREI.color1);
				
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(20, 70, 1080, 620);
		this.PanelTable.setLayout(null);
		
		//Panel filtre
		this.PanelFiltre.setBounds(20,35,1040,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,5));
		this.PanelFiltre.add(new JLabel ("Filtrer les Trajets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("IdTp");
		this.cbxFiltre.addItem("nom");
		this.cbxFiltre.addItem("type");
		this.cbxFiltre.addItem("transporteur");
		this.cbxFiltre.addItem("etat");
		this.cbxFiltre.addItem("raison");
		this.PanelFiltre.add(this.btAnnuler);

		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		String entetes [] = {"ID Transport", "Nom", "Type", "Transporteur", "Etat", "Raison"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 1040, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
	}
	public void actualiser() {
		this.unTableau.setDonnees(this.obtenirDonnees());
	}
	public void viderChamps() {
		this.txtFiltre.setText("");
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<VSql_Vue_Perturbation_Ligne> lesVueLPts = C_VSql_Vue_Perturbation_Ligne.selectAllVues();
		Object[][] matrice = new Object [lesVueLPts.size()][6];
		int i=0;
		for (VSql_Vue_Perturbation_Ligne uneVueLPt : lesVueLPts) {
			matrice[i][0] = uneVueLPt.getIdTp();
			matrice[i][1] = uneVueLPt.getNom();
			matrice[i][2] = uneVueLPt.getType();
			matrice[i][3] = uneVueLPt.getTransporteur();
			matrice[i][4] = uneVueLPt.getEtat();
			matrice[i][5] = uneVueLPt.getRaison();
			//matrice[i][5] = uneVueLPt.getPictogramme();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<VSql_Vue_Perturbation_Ligne> lesVues){
		Object[][]matrice = new Object[lesVues.size()][6];
		int i = 0;
		for (VSql_Vue_Perturbation_Ligne uneVueLPt : lesVues) {
			matrice[i][0] = uneVueLPt.getIdTp();
			matrice[i][1] = uneVueLPt.getNom();
			matrice[i][2] = uneVueLPt.getType();
			matrice[i][3] = uneVueLPt.getTransporteur();
			matrice[i][4] = uneVueLPt.getEtat();
			matrice[i][5] = uneVueLPt.getRaison();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<VSql_Vue_Perturbation_Ligne> lesVues = C_VSql_Vue_Perturbation_Ligne.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesVues);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
