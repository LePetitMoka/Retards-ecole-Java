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

import controleur.C_Classe;
import controleur.Classe;
import controleur.Tableau;
import modele.M_Classe;

public class P_Classes extends P_Principal implements ActionListener {
	private JTextField txtNom = new JTextField();
	private JTextField txtDiplomePrepare = new JTextField();
	private JTextField txtPromotion = new JTextField();
	private JTextField txtEmail = new JTextField();	
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Classes () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 250, 220);
		this.PanelForm.setLayout(new GridLayout(5,2));
		this.PanelForm.add(new JLabel("Nom : "));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("DiplomePrepare : "));
		this.PanelForm.add(this.txtDiplomePrepare);
		this.PanelForm.add(new JLabel("Promotion : "));
		this.PanelForm.add(this.txtPromotion);
		this.PanelForm.add(new JLabel("Email : "));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.PanelTable.setBackground(new Color (246, 111, 34));
		this.PanelTable.setBounds(320, 60, 450, 220);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Classe", "Nom", "DiplomePrepare", "Promotion", "Email","nbEtudiant"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		ChangeName(this.uneTable,0,"ID_Classe");
		ChangeName(this.uneTable,1,"Nom");
		ChangeName(this.uneTable,2,"DiplomePrepare");
		ChangeName(this.uneTable,3,"Promotion");
		ChangeName(this.uneTable,4,"Email");
		ChangeName(this.uneTable,5,"Nb_Etudiants");
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
					int idClasse = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					C_Classe.supprimerClasse(idClasse);
					//suppression dans l'affichage
					unTableau.supprimerLigne(numLigne);
					JOptionPane.showMessageDialog(null, "Classe supprime");

				}
				else if (e.getClickCount() == 1) {
					
					String nom = uneTable.getValueAt(numLigne, 1).toString();
					String diplomeprepare = uneTable.getValueAt(numLigne, 2).toString();
					String adresse = uneTable.getValueAt(numLigne, 3).toString();
					String email = uneTable.getValueAt(numLigne, 4).toString();

					
					txtNom.setText(nom);
					txtDiplomePrepare.setText(diplomeprepare);
					txtPromotion.setText(adresse);
					txtEmail.setText(email);
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void ChangeName(JTable table, int col_index, String col_name){
		  table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
		  }
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtDiplomePrepare.setText("");
		this.txtEmail.setText("");
		this.txtPromotion.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Classe> lesClasses = C_Classe.selectAllClasses();
		Object[][] matrice = new Object [lesClasses.size()][6];
		int i=0;
		for (Classe uneClasse : lesClasses) {
			matrice[i][0] = uneClasse.getIdCl();
			matrice[i][1] = uneClasse.getNom();
			matrice[i][2] = uneClasse.getDiplomePrepare();
			matrice[i][3] = uneClasse.getPromotion();
			matrice[i][4] = uneClasse.getEmail();
			matrice[i][5] = uneClasse.getNbEtudiants();
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
			String diplomeprepare = this.txtDiplomePrepare.getText();
			String adresse = this.txtPromotion.getText();
			String email = this.txtEmail.getText();
			
			//instancier un etudiant
			
			Classe uneClasse = new Classe(nom, diplomeprepare, adresse, email);
			
			//insertion client dans BDD
			C_Classe.insertClasse(uneClasse);
			
			//recuperer l'id du client insere
			uneClasse = M_Classe.selectWhereClasse(email);
			int idclasse = uneClasse.getIdCl();
			int nbEtudiants = uneClasse.getNbEtudiants();
			
			this.viderChamps();
			JOptionPane.showMessageDialog(this, "Classe insere");
			//actualisation de l'affichage
			Object ligne[] = {idclasse,nom,diplomeprepare,adresse,email,nbEtudiants};
			this.unTableau.insertLigne(ligne);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String diplomeprepare = this.txtDiplomePrepare.getText();
			String adresse = this.txtPromotion.getText();
			String email = this.txtEmail.getText();
			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idclasse = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			int nbEtudiants = Integer.parseInt(this.unTableau.getValueAt(numLigne, 5).toString());

			Classe uneClasse = new Classe(idclasse,nom,diplomeprepare,adresse,email);
			
			//on realise la modif BDD
			C_Classe.updateClasse(uneClasse);
			//actualisation de l'affichage
			
			Object ligne []= {idclasse,nom,diplomeprepare,adresse,email,nbEtudiants};
			JOptionPane.showMessageDialog(this, "Classe modifie");
			this.unTableau.modifierLigne(numLigne,ligne);
			this.viderChamps();
		}
	}
}
