package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.C_Professeur;
import controleur.Professeur;

public class VueLoginProfesseur extends JFrame implements ActionListener, KeyListener {
	private JPanel formulaire = new JPanel();
	private JButton btnAnnuler = new JButton("Annuler");
	private JButton btnConfirmer = new JButton("Confirmer");
	private JTextField email = new JTextField();
	private JPasswordField mdp = new JPasswordField();
	
	public VueLoginProfesseur() {
		this.setTitle("Connexion Professeur");
		this.setBounds(100, 50, 330, 220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.GRAY);
		this.setLayout(null);
		
		this.formulaire.setBounds(25, 25, 265, 130);
		this.formulaire.setBackground(Color.WHITE);
		this.formulaire.setLayout(new GridLayout(3, 2));
		this.formulaire.add(new JLabel("EMAIL : "));
		this.formulaire.add(email);
		this.formulaire.add(new JLabel("MOT DE PASSE : "));
		this.formulaire.add(mdp);
		this.formulaire.add(btnAnnuler);
		this.formulaire.add(btnConfirmer);
		this.add(formulaire);
		
		this.btnAnnuler.addActionListener(this);
		this.btnConfirmer.addActionListener(this);
		
		this.email.addKeyListener(this);
		this.mdp.addKeyListener(this);
		
		this.setVisible(true);
	}
	
	private void authentification() {
		Professeur userProfesseur = C_Professeur.authentificationProfesseur(this.email.getText(), new String (this.mdp.getPassword()));
		if(userProfesseur != null) {
			JOptionPane.showMessageDialog(this, "Vous etes connect� en tant que " + userProfesseur.getNom() + " " + userProfesseur.getPrenom());
		} else {
			JOptionPane.showMessageDialog(this, "Vos identifiants sont incorrectes");
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
			authentification();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnAnnuler) {
			this.email.setText("");
			this.mdp.setText("");
		} else if(e.getSource() == this.btnConfirmer) {
			authentification();
		}
	}

}
