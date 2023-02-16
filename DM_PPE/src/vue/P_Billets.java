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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Classe;
import controleur.C_Etudiant;
import controleur.C_Billet;
import controleur.Classe;
import controleur.Etudiant;
import controleur.Administrateur;
import controleur.Billet;
import controleur.C_Administrateur;
import controleur.Tableau;
import modele.M_Billet;

public class P_Billets extends P_Principal implements ActionListener {
	
	private JComboBox<String> cbxIdEtudiant = new JComboBox<String>();
	private JTextField txtRaison = new JTextField("Raison");
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;	
	
	public P_Billets () {
		super(GREI.color1);
				
		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 250, 220);
		this.PanelForm.setLayout(new GridLayout(3,2));
		this.PanelForm.add(new JLabel("ID Eleve : "));
		this.PanelForm.add(this.cbxIdEtudiant);
		this.PanelForm.add(new JLabel("Raison : "));
		this.PanelForm.add(this.txtRaison);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.PanelTable.setBackground(new Color (246, 111, 34));
		this.PanelTable.setBounds(320, 60, 450, 220);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"date_billet", "heure_billet", "dureeRetard", "URLSignature","dateheure", "raison","ID_Admin", "ID_Etudiant"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		ChangeName(this.uneTable,0,"date_Billet");
		ChangeName(this.uneTable,1,"heure_billet");
		ChangeName(this.uneTable,2,"dureeRetard");
		ChangeName(this.uneTable,3,"URLSignature");
		ChangeName(this.uneTable,4,"dateheure");
		ChangeName(this.uneTable,5,"raison");
		ChangeName(this.uneTable,6,"ID_Admin");
		ChangeName(this.uneTable,7,"ID_Etudiant");
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 0, 450, 220);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
		
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
					int idAdmin = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					String dateheure = uneTable.getValueAt(numLigne, 0).toString();

					//suppression dans la BDD
					C_Billet.supprimerBillet(idAdmin, idEtudiant, dateheure);
					//suppression dans l'affichage
					unTableau.supprimerLigne(numLigne);
					JOptionPane.showMessageDialog(null, "Billet supprime");

				}
				else if (e.getClickCount() == 1) {
					
					String raison = uneTable.getValueAt(numLigne, 1).toString();

					txtRaison.setText(raison);
					
					
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void remplirCBX(){
		//remplir les clients
		ArrayList<Etudiant> lesEtudiants = C_Etudiant.selectAllEtudiants();
		//on vide le CBX
		this.cbxIdEtudiant.removeAllItems();
		//recuperer les clients
		for(Etudiant  unEtudiant: lesEtudiants){
			this.cbxIdEtudiant.addItem(unEtudiant.getIdU()+"-"+unEtudiant.getNom()+" "+unEtudiant.getPrenom());
		}
	}
	public void ChangeName(JTable table, int col_index, String col_name){
		  table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
		  }
	public void viderChamps() {
		this.txtRaison.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Billet> lesBillets = C_Billet.selectAllBillets();
		Object[][] matrice = new Object [lesBillets.size()][7];
		int i=0;
		for (Billet unBillet : lesBillets) {
			matrice[i][0] = unBillet.getDateB();
			matrice[i][1] = unBillet.getIdAd();
			matrice[i][2] = unBillet.getIdE();
			matrice[i][3] = unBillet.getDateheure();
			matrice[i][4] = unBillet.getDureeRetard();
			matrice[i][5] = unBillet.getRaison();
			matrice[i][6] = unBillet.getUrlSignature();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String raison = this.txtRaison.getText();
			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idetudiant = Integer.parseInt(tab[0]);
			
			//instancier un billet
			
			Billet unBillet = new Billet(V_Generale.leIdAd,idetudiant,raison);
			
			//insertion client dans BDD
			C_Billet.insertBillet(unBillet);
			
			//recuperer l'id du billet insere
			//unBillet = M_Billet.selectWhereBillet(V_Generale.leIdAd );
			int idEtudiant = unBillet.getIdE();
			
			this.viderChamps();
			JOptionPane.showMessageDialog(this, "Billet insere");
			//actualisation de l'affichage
			//Object ligne[] = {idEtudiant,nom,prenom,adresse,telephone,email,mdp,idclasse};
			//this.unTableau.insertLigne(ligne);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			//String raison = this.txtNom.getText();
			
			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclasse = Integer.parseInt(tab[0]);			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idetudiant = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			//Billet unBillet = new Billet(idetudiant,idadmin);
			
			//on realise la modif BDD
			//C_Billet.updateBillet(unBillet);
			//actualisation de l'affichage
			//Object ligne []= {idetudiant,nom,prenom,adresse,telephone,email,mdp,idclasse};
			JOptionPane.showMessageDialog(this, "Billet modifie");
			//this.unTableau.modifierLigne(numLigne,ligne);;
			this.viderChamps();
		}
	}
}