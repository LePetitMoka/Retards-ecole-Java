package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Classe;
import controleur.C_Etudiant;
import controleur.C_Professeur;
import controleur.Classe;
import controleur.Etudiant;
import controleur.Professeur;
import controleur.Tableau;
import modele.BDD;
import modele.M_Etudiant;


public class P_Etudiants extends P_Principal implements ActionListener {
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JComboBox<String> cbxIdClasse = new JComboBox<String>();
	private JPasswordField txtMDP = new JPasswordField();
	
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
	
	private String tempMDP;
	
	
	public P_Etudiants () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 320);
		this.PanelForm.setLayout(new GridLayout(8,2));
		this.PanelForm.add(new JLabel("Nom : "));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("Prenom : "));
		this.PanelForm.add(this.txtPrenom);
		this.PanelForm.add(new JLabel("Adresse : "));
		this.PanelForm.add(this.txtAdresse);
		this.PanelForm.add(new JLabel("Telephone : "));
		this.PanelForm.add(this.txtTelephone);
		this.PanelForm.add(new JLabel("Email : "));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(new JLabel("MDP : "));
		this.PanelForm.add(this.txtMDP);
		this.PanelForm.add(new JLabel("ID Classe : "));
		this.PanelForm.add(this.cbxIdClasse);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
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
		this.cbxFiltre.addItem("IdE");
		this.cbxFiltre.addItem("Nom");
		this.cbxFiltre.addItem("Prenom");
		this.cbxFiltre.addItem("Adresse");
		this.cbxFiltre.addItem("Telephone");
		this.cbxFiltre.addItem("Email");
		//this.cbxFiltre.addItem("Mdp");
		this.cbxFiltre.addItem("IdCl");


		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Etudiant", "Nom", "Prenom", "Adresse","Telephone", "Email","mdp", "ID Classe"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		/*JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 0, 650, 620);
		this.PanelTable.add(uneScroll);*/
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 650, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
		
		this.tempMDP = "";
		
		//remplir les CBX
		this.remplirCBX();
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
					int idEtudiant = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					C_Etudiant.supprimerEtudiant(idEtudiant);
					//suppression dans l'affichage
					actualiser();
					JOptionPane.showMessageDialog(null, "Etudiant supprime");

				}
				else if (e.getClickCount() == 1) {
					
					String nom = uneTable.getValueAt(numLigne, 1).toString();
					String prenom = uneTable.getValueAt(numLigne, 2).toString();
					String adresse = uneTable.getValueAt(numLigne, 3).toString();
					String telephone = uneTable.getValueAt(numLigne, 4).toString();
					String email = uneTable.getValueAt(numLigne, 5).toString();
					String mdp = uneTable.getValueAt(numLigne, 6).toString();
					String idclasse = uneTable.getValueAt(numLigne, 7).toString();

					
					txtNom.setText(nom);
					txtPrenom.setText(prenom);
					txtAdresse.setText(adresse);
					txtEmail.setText(email);
					txtTelephone.setText(telephone);
					txtMDP.setText(mdp);
					cbxIdClasse.setSelectedItem(whereisItemCBX(idclasse, cbxIdClasse));
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void remplirCBX(){
		//remplir les clients
		ArrayList<Classe> lesClasses = C_Classe.selectAllClasses();
		//on vide le CBX
		this.cbxIdClasse.removeAllItems();
		//recuperer les clients
		for(Classe  uneClasse: lesClasses){
			this.cbxIdClasse.addItem(uneClasse.getIdCl()+"-"+uneClasse.getNom());
		}
	}
	public Object whereisItemCBX(String idTab, JComboBox<String> CBX) { //permet de trouver l'index d'un item CBX en fonction d'un ID int
		Object item = new Object();
		for (int i = 0; i < CBX.getItemCount() ; i++) {
			//on recupere l'id de l'item en explosant le texte et en convertissant le premier morceau en int
			String txtItem = CBX.getItemAt(i).toString();
			String tab[] = txtItem.split("-");
			//on le compare avec l'id recherche
			if (tab[0].compareTo(idTab) == 0) {
				item = CBX.getItemAt(i);
			}
		}
		return item;
	}
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtAdresse.setText("");
		this.txtTelephone.setText("");
		this.txtMDP.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Etudiant> lesEtudiants = C_Etudiant.selectAllEtudiants();
		Object[][] matrice = new Object [lesEtudiants.size()][8];
		int i=0;
		for (Etudiant unEtudiant : lesEtudiants) {
			matrice[i][0] = unEtudiant.getIdU();
			matrice[i][1] = unEtudiant.getNom();
			matrice[i][2] = unEtudiant.getPrenom();
			matrice[i][3] = unEtudiant.getAdresse();
			matrice[i][4] = unEtudiant.getTelephone();
			matrice[i][5] = unEtudiant.getEmail();
			matrice[i][6] = unEtudiant.getMdp();
			matrice[i][7] = unEtudiant.getIdCl();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<Etudiant> lesEtudiants){
		Object[][]matrice = new Object[lesEtudiants.size()][8];
		int i = 0;
		for (Etudiant unEtudiant : lesEtudiants) {
			matrice[i][0] = unEtudiant.getIdU();
			matrice[i][1] = unEtudiant.getNom();
			matrice[i][2] = unEtudiant.getPrenom();
			matrice[i][3] = unEtudiant.getAdresse();
			matrice[i][4] = unEtudiant.getTelephone();
			matrice[i][5] = unEtudiant.getEmail();
			matrice[i][6] = unEtudiant.getMdp();
			matrice[i][7] = unEtudiant.getIdCl();
			i++;
		}
		return matrice;
	}
	public void actualiser() {
		this.unTableau.setDonnees(this.obtenirDonnees());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());

			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdClasse.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclasse = Integer.parseInt(tab[0]);
			
			//instancier un etudiant
			
			Etudiant unEtudiant = new Etudiant(nom, prenom, adresse, telephone, email, mdp, idclasse);
			
			//insertion client dans BDD
			JOptionPane.showMessageDialog(this, C_Etudiant.insertEtudiant(unEtudiant));
			this.viderChamps();
			
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());
			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdClasse.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclasse = Integer.parseInt(tab[0]);			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idetudiant = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			Etudiant unEtudiant = new Etudiant(idetudiant,nom,prenom,adresse,telephone,email,mdp,idclasse);
			
			//on realise la modif BDD
			JOptionPane.showMessageDialog(this, C_Etudiant.updateEtudiant(unEtudiant));
			this.viderChamps();
			
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<Etudiant> lesEtudiants = C_Etudiant.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesEtudiants);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
