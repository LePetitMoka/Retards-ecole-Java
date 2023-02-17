package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Administrateur;
import controleur.C_Administrateur;
import controleur.C_Etudiant;
import controleur.C_Professeur;
import controleur.Etudiant;
import controleur.Professeur;

public class V_Connexion extends JFrame implements ActionListener, KeyListener {
	private String role;
	
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnection = new JButton ("Connection");
	private JButton btRetour = new JButton ("Changer de qualification");
	
	private JLabel txt1;
	
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	public V_Connexion(String role) {
		this.role = role;
		this.txt1 = new JLabel("Connexion " + this.role);
		
		this.setTitle("Connexion " + this.role);
		this.setBounds(100, 50, 600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(null);
		
		this.txt1.setBounds(235, 40, 200, 20);
		this.add(this.txt1);
		
		this.panelForm.setBounds(150, 90, 300, 120);
		this.panelForm.setBackground(Color.WHITE);
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP : "));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btConnection);
		this.add(panelForm);
		
		this.btRetour.setBounds(150, 220, 300, 40);
		this.add(btRetour);
		
		this.btAnnuler.addActionListener(this);
		this.btConnection.addActionListener(this);
		this.btRetour.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}
	
	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String(this.txtMdp.getPassword());
		
		switch(this.role) {
			case "Administrateur" : 
				Administrateur unAdministrateur = C_Administrateur.selectWhereAdministrateur(email, mdp);
				if(unAdministrateur == null) {
					JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
				} else {
					JOptionPane.showMessageDialog(this, "Vous etes connecter en temps que " + unAdministrateur.getPrenom() + " " + unAdministrateur.getNom());
					GREI.creerDetruireVueGenerale(true);
					this.dispose();
				}
			break;
			/*
			case "Professeur" : 
				Professeur unProfesseur = C_Professeur.selectWhereProfesseur(email, mdp);
				if(unProfesseur == null) {
					JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
				} else {
					JOptionPane.showMessageDialog(this, "Vous etes connecter en temps que " + unProfesseur.getPrenom() + " " + unProfesseur.getNom());
					GREI.creerDetruireVueGenerale(true);
					this.dispose();
				}
			break;
			case "Etudiant" : 
				Etudiant unEtudiant = C_Etudiant.selectWhereEtudiant(email, mdp);
				if(unEtudiant == null) {
					JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
				} else {
					JOptionPane.showMessageDialog(this, "Vous etes connecter en temps que " + unEtudiant.getPrenom() + " " + unEtudiant.getNom());
					GREI.creerDetruireVueGenerale(true);
					this.dispose();
				}
			break;
			*/
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btConnection) {
			this.traitement();
		}
		else if(e.getSource() == this.btRetour) {
			GREI.rendreVisibleVueConnexionGen(true);
			this.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
