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
	private JButton btCours = new JButton ("Cours");
	private JButton btBillets = new JButton ("Billets");
	private JButton btMatiere = new JButton ("Matiere");
	private JButton btTrajet = new JButton ("Trajet");

	
	private JPanel panelMenu = new JPanel();
	private P_Matiere unPanelMatiere = new P_Matiere();
	private P_Professeur unPanelProfesseurs = new P_Professeur();
	private P_Etudiants unPanelEtudiants = new P_Etudiants();
	private P_Classes unPanelClasses = new P_Classes();
	private P_Billets unPanelBillets = new P_Billets();
	private P_Cours unPanelCours = new P_Cours();
	private P_Trajet unPanelTrajet = new P_Trajet();
	
	public O_Gestion() {
		
		this.setLayout(null);

		//placement du Panel
		this.panelMenu.setBounds(0, 0, 1080, 40);
		this.panelMenu.setBackground(GREI.color1);
		this.panelMenu.setLayout(new GridLayout(1, 7));
		this.panelMenu.add(this.btProfesseurs);
		this.panelMenu.add(this.btEtudiants);
		this.panelMenu.add(this.btClasses);
		this.panelMenu.add(this.btMatiere);
		this.panelMenu.add(this.btCours);
		this.panelMenu.add(this.btBillets);
		this.panelMenu.add(this.btTrajet);
		this.add(this.panelMenu);
		
		this.btProfesseurs.addActionListener(this);
		this.btEtudiants.addActionListener(this);
		this.btClasses.addActionListener(this);
		this.btBillets.addActionListener(this);
		this.btMatiere.addActionListener(this);
		this.btCours.addActionListener(this);
		this.btTrajet.addActionListener(this);

		

		this.add(this.unPanelProfesseurs);
		this.add(this.unPanelEtudiants);
		this.add(this.unPanelClasses);
		this.add(this.unPanelBillets);
		this.add(this.unPanelMatiere);
		this.add(this.unPanelCours);
		this.add(this.unPanelTrajet);
		
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
		this.unPanelMatiere.setVisible(false);
		this.btMatiere.setEnabled(true);
		this.unPanelCours.setVisible(false);
		this.btCours.setEnabled(true);
		this.unPanelBillets.setVisible(false);
		this.btBillets.setEnabled(true);
		this.unPanelTrajet.setVisible(false);
		this.btTrajet.setEnabled(true);
		switch(choix) {
			case 1 : this.unPanelProfesseurs.setVisible(true);this.btProfesseurs.setEnabled(false);break;
			case 2 : this.unPanelEtudiants.setVisible(true);this.btEtudiants.setEnabled(false);break;
			case 3 : this.unPanelClasses.setVisible(true);this.btClasses.setEnabled(false);break;
			case 4 : this.unPanelCours.setVisible(true);this.btCours.setEnabled(false);break;
			case 5 : this.unPanelBillets.setVisible(true);this.btBillets.setEnabled(false);break;
			case 6 : this.unPanelMatiere.setVisible(true);this.btMatiere.setEnabled(false);break;
			case 7 : this.unPanelTrajet.setVisible(true);this.btTrajet.setEnabled(false);break;

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
		}
		else if(e.getSource() == this.btCours) {
			this.afficherPanel(4);
		}
		else if(e.getSource() == this.btBillets) {
			this.afficherPanel(5);
		}
		else if(e.getSource() == this.btMatiere) {
			this.afficherPanel(6);
		}
		else if(e.getSource() == this.btTrajet) {
			this.afficherPanel(7);
		}
	}
}
