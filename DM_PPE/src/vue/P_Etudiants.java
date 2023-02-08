package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
	private JTextField txtMdp = new JTextField();
	private JTextField txtDiplome = new JTextField();
	private JTextField txtIdCl = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Etudiants () {
		super(new Color (249, 177, 4));

		this.PanelForm.setBackground(new Color (249, 177, 4));
		this.PanelForm.setBounds(40, 60, 250, 220);
		this.PanelForm.setLayout(new GridLayout(5,2));
		this.PanelForm.add(new JLabel("Nom Etudiant : "));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("Prenom Etudiant : "));
		this.PanelForm.add(this.txtPrenom);
		this.PanelForm.add(new JLabel("Adresse Etudiant : "));
		this.PanelForm.add(this.txtAdresse);
		this.PanelForm.add(new JLabel("Telephone Etudiant : "));
		this.PanelForm.add(this.txtTelephone);
		this.PanelForm.add(new JLabel("Email Etudiant : "));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(new JLabel("Mot de passe : "));
		this.PanelForm.add(this.txtMdp);
		this.PanelForm.add(new JLabel("Diplome Preparé : "));
		this.PanelForm.add(this.txtDiplome);
		this.PanelForm.add(new JLabel("Classe : "));
		this.PanelForm.add(this.txtIdCl);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.PanelTable.setBackground(new Color (246, 111, 34));
		this.PanelTable.setBounds(350, 60, 450, 220);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Etudiant", "Nom", "Prénom", "Adresse","Telephone", "Email", "ID Classe"};
		/*this.unTableau = new Tableau(this.obtenirDonnees(), entetes);*/
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(0, 0, 450, 220);
		this.PanelTable.add(uneScroll);
		this.add(this.PanelTable);
	}
	
	/*public Object[][] obtenirDonnees(){
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
			matrice[i][6] = unEtudiant.getDiplome();
			matrice[i][7] = unEtudiant.getIdCl();
			i++;
		}
		return matrice;
	}*/
	
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtEmail.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.viderChamps();
		}
		else if(e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = this.txtMdp.getText();
			String diplome = this.txtDiplome.getText();
			/*int idCl = int(this.txtIdCl.getText());
			
			Etudiant unEtudiant = new Etudiant(nom, prenom, adresse, telephone, email, mdp, diplome, idCl);
			C_Etudiant.insertEtudiant(unEtudiant);
			unEtudiant = M_Etudiant.selectWhereEtudiant(email);
			int ide = unEtudiant.getIdU();
			JOptionPane.showMessageDialog(this, "L'Etudiant a bien été ajouté.");
			this.viderChamps();
			Object ligne[] = {ide, nom, prenom, adresse, email};
			this.unTableau.insertLigne(ligne);*/
		}
	}
}
