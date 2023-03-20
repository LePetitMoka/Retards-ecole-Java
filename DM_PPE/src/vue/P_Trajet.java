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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Etudiant;
import controleur.C_Trajet;
import controleur.C_Transport;
import controleur.C_VSql_Arret_Transport;
import controleur.C_VSql_Vue_Trajet_Details;
import controleur.Etudiant;
import controleur.Tableau;
import controleur.Transport;
import controleur.VSql_Vue_Arret_Transport;
import controleur.VSql_Vue_Trajet_Details;

public class P_Trajet extends P_Principal implements ActionListener{

	private JComboBox<String> cbxIdE = new JComboBox<String>();
	private JComboBox<String> cbxIdTp = new JComboBox<String>();
	private JComboBox<String> cbxIdSt1 = new JComboBox<String>();
	private JComboBox<String> cbxIdSt2 = new JComboBox<String>();
	
	private JComboBox<String> cbxFiltre = new JComboBox<String>();
	private JButton btFiltrer = new JButton("Filtrer");
	private JPanel PanelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();

	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
		
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Trajet () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 100);
		this.PanelForm.setLayout(new GridLayout(5,2));
		this.PanelForm.add(new JLabel("Eleve : "));
		this.PanelForm.add(this.cbxIdE);
		this.PanelForm.add(new JLabel("Transport : "));
		this.PanelForm.add(this.cbxIdTp);
		this.PanelForm.add(new JLabel("Station/Arret Depart : "));
		this.PanelForm.add(this.cbxIdSt1);
		this.PanelForm.add(new JLabel("Station/Arret Arrivee : "));
		this.PanelForm.add(this.cbxIdSt2);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.remplirCBX();

		cbxIdSt1.setEnabled(false);
		cbxIdSt2.setEnabled(false);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel filtre
		this.PanelFiltre.setBounds(420,30,650,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,4));
		this.PanelFiltre.add(new JLabel ("Filtrer les Trajets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("Nom");
		this.cbxFiltre.addItem("Prenom");
		this.cbxFiltre.addItem("Arret");
		this.cbxFiltre.addItem("Transports");
		this.cbxFiltre.addItem("IdE");
		this.cbxFiltre.addItem("IdSt");

		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		//Table
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"Nom", "Prenom", "Arret", "Transports", "IdE", "IdSt"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 650, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
		
		
		//implementation de la supression et de la modification
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int numLigne = uneTable.getSelectedRow();
				if (e.getClickCount() == 2) {
					int ide = Integer.parseInt(uneTable.getValueAt(numLigne, 4).toString());
					int idst = Integer.parseInt(uneTable.getValueAt(numLigne, 5).toString());
					//suppression dans la BDD
					JOptionPane.showMessageDialog(null, C_Trajet.supprimerTrajet(idst,ide));
					//actualisation de l'affichage
					actualiser();
				}
			}
		} );
		this.cbxIdTp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			cbxIdSt1.setEnabled(true);
			cbxIdSt2.setEnabled(true);
			String chaine = cbxIdTp.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			String idtp = tab[0];
			remplirCBXST(idtp);
			cbxIdSt1.setEnabled(true);
			cbxIdSt2.setEnabled(true);
			
			}
		});
	}
	public void viderChamps() {
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<VSql_Vue_Trajet_Details> lesVues = C_VSql_Vue_Trajet_Details.selectAllVues();
		Object[][] matrice = new Object [lesVues.size()][6];
		int i=0;
		for (VSql_Vue_Trajet_Details uneVue : lesVues) {
			matrice[i][0] = uneVue.getNom();
			matrice[i][1] = uneVue.getPrenom();
			matrice[i][2] = uneVue.getArret();
			matrice[i][3] = uneVue.getTransports();
			matrice[i][4] = uneVue.getIde();
			matrice[i][5] = uneVue.getIdst();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<VSql_Vue_Trajet_Details> lesVues){
		Object[][]matrice = new Object[lesVues.size()][6];
		int i = 0;
		for (VSql_Vue_Trajet_Details uneVue : lesVues) {
			matrice[i][0] = uneVue.getNom();
			matrice[i][1] = uneVue.getPrenom();
			matrice[i][2] = uneVue.getArret();
			matrice[i][3] = uneVue.getTransports();
			matrice[i][4] = uneVue.getIde();
			matrice[i][5] = uneVue.getIdst();
			i++;
		}
		return matrice;
	}
	public void actualiser() {
		this.unTableau.setDonnees(this.obtenirDonnees());
	}
	public void remplirCBX(){
		//remplir les eleves
		ArrayList<Etudiant> lesEtudiants = C_Etudiant.selectAllEtudiants();
		//on vide le CBX
		this.cbxIdE.removeAllItems();
		//recuperer les eleves
		for(Etudiant  unEtudiant: lesEtudiants){
			this.cbxIdE.addItem(unEtudiant.getIdU()+"-"+unEtudiant.getNom()+" "+unEtudiant.getPrenom());
		}
		
		//remplir les transports
		ArrayList<Transport> lesTransports = C_Transport.selectAllTransports();
		//on vide le CBX
		this.cbxIdTp.removeAllItems();
		//recuperer les eleves
		for(Transport  unTransport: lesTransports){
			this.cbxIdTp.addItem(unTransport.getIdTp()+"-"+unTransport.getNom()+" / "+unTransport.getTransporteur()+" / "+unTransport.getType());
		}
	}
	public void remplirCBXST(String IdTp) {
		//remplir les stations
		ArrayList<VSql_Vue_Arret_Transport> lesVues = C_VSql_Arret_Transport.selectWhereVue(IdTp);
		//on vide le CBX
		this.cbxIdSt1.removeAllItems();
		this.cbxIdSt2.removeAllItems();
		//recuperer les eleves
		for(VSql_Vue_Arret_Transport  uneVue: lesVues){
			this.cbxIdSt1.addItem(uneVue.getIdSt()+"-"+uneVue.getNomArret());
			this.cbxIdSt2.addItem(uneVue.getIdSt()+"-"+uneVue.getNomArret());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			//recuperation des id dans les CBX
			if(this.cbxIdSt1.isEnabled() == true) {
				String chaine2 = this.cbxIdSt1.getSelectedItem().toString();
				String tab2[] = chaine2.split("-");
				String idst1 = tab2[0];
				
				chaine2 = this.cbxIdSt2.getSelectedItem().toString();
				tab2 = chaine2.split("-");
				String idst2 = tab2[0];
				
				chaine2 = this.cbxIdE.getSelectedItem().toString();
				tab2 = chaine2.split("-");
				int ide = Integer.parseInt(tab2[0]);
				
				//insertion trajet dans BDD
				JOptionPane.showMessageDialog(this, C_Trajet.procTrajet(ide, idst1, idst2));
				this.viderChamps();
				//actualisation de l'affichage
				this.actualiser();
			} else 	JOptionPane.showMessageDialog(this, "Choisissez une ligne");

		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<VSql_Vue_Trajet_Details> lesVues = C_VSql_Vue_Trajet_Details.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesVues);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
