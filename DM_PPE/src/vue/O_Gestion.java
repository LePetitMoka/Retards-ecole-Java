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

public class O_Gestion extends JPanel implements ActionListener {
	
	private JButton btProfesseurs = new JButton("Professeurs");
	private JButton btEtudiants = new JButton("Etudiants");
	private JButton btClasses = new JButton ("Classes");
	private JButton btBillets = new JButton ("Billets");
	
	private JPanel panelMenu = new JPanel();
	private P_Professeur unPanelProfesseurs = new P_Professeur();
	private P_Etudiants unPanelEtudiants = new P_Etudiants();
	private P_Classes unPanelClasses = new P_Classes();
	private P_Billets unPanelBillets = new P_Billets();
	
	public O_Gestion() {
		
		this.setLayout(null);

		//placement du Panel
		this.panelMenu.setBounds(0, 0, 780, 40);
		this.panelMenu.setBackground(GREI.color1);
		this.panelMenu.setLayout(new GridLayout(1, 4));
		this.panelMenu.add(this.btProfesseurs);
		this.panelMenu.add(this.btEtudiants);
		this.panelMenu.add(this.btClasses);
		this.panelMenu.add(this.btBillets);
		this.add(this.panelMenu);
		
		this.btProfesseurs.addActionListener(this);
		this.btEtudiants.addActionListener(this);
		this.btClasses.addActionListener(this);
		this.btBillets.addActionListener(this);

		this.add(this.unPanelProfesseurs);
		this.add(this.unPanelEtudiants);
		this.add(this.unPanelClasses);
		this.add(this.unPanelBillets);
		
		this.unPanelProfesseurs.setVisible(true);
		this.btProfesseurs.setEnabled(false);
		this.setVisible(true);
	}
	
	public void afficherPanel(int choix) {
		this.unPanelProfesseurs.setVisible(false);
		this.btProfesseurs.setEnabled(true);
		this.unPanelEtudiants.setVisible(false);
		this.btEtudiants.setEnabled(true);
		this.unPanelClasses.setVisible(false);
		this.btClasses.setEnabled(true);
		this.unPanelBillets.setVisible(false);
		this.btBillets.setEnabled(true);
		switch(choix) {
			case 1 : this.unPanelProfesseurs.setVisible(true);this.btProfesseurs.setEnabled(false);break;
			case 2 : this.unPanelEtudiants.setVisible(true);this.btEtudiants.setEnabled(false);break;
			case 3 : this.unPanelClasses.setVisible(true);this.btClasses.setEnabled(false);break;
			case 4 : this.unPanelBillets.setVisible(true);this.btBillets.setEnabled(false);break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btProfesseurs) {
			this.afficherPanel(1);
		}
		else if(e.getSource() == this.btEtudiants) {
			this.afficherPanel(2);
		}
		else if(e.getSource() == this.btClasses) {
			this.afficherPanel(3);
		}/*
		else if(e.getSource() == this.btBillets) {
			this.afficherPanel(4);
		}*/
	}
}
