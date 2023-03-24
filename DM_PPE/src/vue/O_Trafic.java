package vue;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controleur.VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;


public class O_Trafic extends JPanel implements ActionListener {
	
	private JButton btLPt = new JButton("Lignes perturbees");
	private JButton btERPSB = new JButton("Etudiant Perturbes\n(sans Billet)");
	
	private JPanel panelMenu = new JPanel();
	private P_VueLPt unPanelVueLPt = new P_VueLPt();
	private P_Vue_Etudiant_Retard_Perturbation_SansBillet unPanelVueERPSB = new P_Vue_Etudiant_Retard_Perturbation_SansBillet();

	
	public O_Trafic() {
		
		//config du panel onglet
		this.setLayout(null);

		//placement du Panel menu
		this.panelMenu.setBounds(0, 0, 1080, 40);
		this.panelMenu.setBackground(GREI.color1);
		this.panelMenu.setLayout(new GridLayout(1, 4));
		this.panelMenu.add(this.btLPt);
		this.panelMenu.add(this.btERPSB);
		this.add(this.panelMenu);
		
		//rendre boutons ecoutables
		this.btLPt.addActionListener(this);
		this.btERPSB.addActionListener(this);

		this.add(this.unPanelVueLPt);
		this.add(this.unPanelVueERPSB);

		
		//visibilite du panel par defaut
		this.unPanelVueLPt.setVisible(true);
		this.btLPt.setEnabled(false);
		this.setVisible(true);
	}
	
	public void afficherPanel(int choix) {
		this.unPanelVueLPt.setVisible(false);
		this.btLPt.setEnabled(true);
		this.unPanelVueERPSB.setVisible(false);
		this.btERPSB.setEnabled(true);
		switch(choix) {
			case 1 : this.unPanelVueLPt.setVisible(true);this.btLPt.setEnabled(false);break;
			case 2 : this.unPanelVueERPSB.setVisible(true);this.btERPSB.setEnabled(false);break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btLPt) {
			this.afficherPanel(1);
		}
		if(e.getSource() == this.btERPSB) {
			this.afficherPanel(2);
		}
	}
}
