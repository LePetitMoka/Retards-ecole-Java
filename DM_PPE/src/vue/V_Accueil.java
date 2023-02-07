package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vue.GREI;

public class V_Accueil extends JFrame implements ActionListener {
	private JButton btProfesseurs = new JButton("Professeurs");
	private JButton btEtudiants = new JButton("Etudiants");
	private JButton btClasses = new JButton ("Classes");
	private JButton btBillets = new JButton ("Billets");
	private JButton btQuitter = new JButton ("Quitter");
	
	private JPanel panelMenu = new JPanel();
	private P_Professeur unPanelProfesseurs = new P_Professeur();
	private P_Etudiants unPanelEtudiants = new P_Etudiants();
	private P_Classes unPanelClasses = new P_Classes();
	private P_Billets unPanelBillets = new P_Billets();
	
	public V_Accueil() {
		this.setTitle("Gestion des Billets d'Orange");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 500);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.ORANGE);
		
		//placement du Panel
		this.panelMenu.setBounds(25, 20, 840, 40);
		this.panelMenu.setBackground(Color.ORANGE);
		this.panelMenu.setLayout(new GridLayout(1, 5));
		this.panelMenu.add(this.btProfesseurs);
		this.panelMenu.add(this.btEtudiants);
		this.panelMenu.add(this.btClasses);
		this.panelMenu.add(this.btBillets);
		this.panelMenu.add(this.btQuitter);
		this.add(this.panelMenu);
		
		this.btProfesseurs.addActionListener(this);
		this.btEtudiants.addActionListener(this);
		this.btClasses.addActionListener(this);
		this.btBillets.addActionListener(this);
		this.btQuitter.addActionListener(this);

		this.add(this.unPanelProfesseurs);
		this.add(this.unPanelEtudiants);
		this.add(this.unPanelClasses);
		this.add(this.unPanelBillets);
		
		this.setVisible(true);
	}
	
	public void afficherPanel(int choix) {
		this.unPanelProfesseurs.setVisible(false);
		this.unPanelEtudiants.setVisible(false);
		this.unPanelClasses.setVisible(false);
		this.unPanelBillets.setVisible(false);
		switch(choix) {
			case 1 : this.unPanelProfesseurs.setVisible(true);break;
			case 2 : this.unPanelEtudiants.setVisible(true);break;
			case 3 : this.unPanelClasses.setVisible(true);break;
			case 4 : this.unPanelBillets.setVisible(true);break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this, 
					"Voulez-vous quitter l'application ?",
					"Quitter l'application", JOptionPane.YES_NO_OPTION);
			if(retour == 0) {
				GREI.creerDetruireVueGenerale(false);
			}
		}
		else if(e.getSource() == this.btProfesseurs) {
			this.afficherPanel(1);
		}/*
		else if(e.getSource() == this.btEtudiants) {
			this.afficherPanel(2);
		}
		else if(e.getSource() == this.btClasses) {
			this.afficherPanel(3);
		}
		else if(e.getSource() == this.btBillets) {
			this.afficherPanel(4);
		}*/
	}
}
