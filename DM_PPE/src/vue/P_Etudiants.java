package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.C_Etudiant;
import controleur.Etudiant;
import controleur.Tableau;
import modele.M_Etudiant;

public class P_Etudiants extends P_Principal implements ActionListener {
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtIdCl = new JTextField();
	private JPasswordField txtMDP = new JPasswordField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Etudiants () {
		super(new Color (249, 177, 4));

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 250, 220);
		this.PanelForm.setLayout(new GridLayout(5,2));
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
		this.PanelForm.add(this.txtIdCl);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.PanelTable.setBackground(new Color (246, 111, 34));
		this.PanelTable.setBounds(320, 60, 450, 220);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Etudiant", "Nom", "Prenom", "Adresse","Telephone", "Email","mdp", "ID Classe"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 0, 450, 220);
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
					int idEtudiant = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					C_Etudiant.supprimerEtudiant(idEtudiant);
					//suppression dans l'affichage
					unTableau.supprimerLigne(numLigne);
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
					txtIdCl.setText(idclasse);
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtAdresse.setText("");
		this.txtTelephone.setText("");
		this.txtMDP.setText("");
		this.txtIdCl.setText("");
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
			int idclasse = Integer.parseInt(this.txtIdCl.getText());

			
			//instancier un etudiant
			
			Etudiant unEtudiant = new Etudiant(nom, prenom, adresse, telephone, email, mdp, idclasse);
			
			//insertion client dans BDD
			C_Etudiant.insertEtudiant(unEtudiant);
			
			//recuperer l'id du client insere
			unEtudiant = M_Etudiant.selectWhereEtudiant(email);
			int idclient = unEtudiant.getIdU();
			
			this.viderChamps();
			JOptionPane.showMessageDialog(this, "Etudiant insere");
			//actualisation de l'affichage
			Object ligne[] = {idclient,nom,prenom,adresse,telephone,email,mdp,idclasse};
			this.unTableau.insertLigne(ligne);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtAdresse.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());
			int idclasse = Integer.parseInt(this.txtIdCl.getText());
			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idclient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			Etudiant unEtudiant = new Etudiant(idclient,nom,prenom,adresse,telephone,email,mdp,idclasse);
			
			//on realise la modif BDD
			C_Etudiant.updateEtudiant(unEtudiant);
			//actualisation de l'affichage
			Object ligne []= {idclient,nom,prenom,adresse,telephone,email,mdp,idclasse};
			JOptionPane.showMessageDialog(this, "Etudiant modifie");
			this.unTableau.modifierLigne(numLigne,ligne);;
			this.viderChamps();
		}
	}
}
