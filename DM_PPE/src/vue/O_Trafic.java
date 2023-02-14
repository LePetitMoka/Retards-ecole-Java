package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class O_Trafic extends JPanel implements ActionListener {
	
	private JButton btLPt = new JButton("Lignes perturbees");
	
	private JPanel panelMenu = new JPanel();
	private P_VueLPt unPanelVueLPt = new P_VueLPt();
	
	public O_Trafic() {
		
		//config du panel onglet
		this.setLayout(null);

		//placement du Panel menu
		this.panelMenu.setBounds(0, 0, 780, 60);
		this.panelMenu.setBackground(GREI.color1);
		this.panelMenu.setLayout(new GridLayout(1, 4));
		this.panelMenu.add(this.btLPt);
		this.add(this.panelMenu);
		
		//rendre boutons ecoutables
		this.btLPt.addActionListener(this);

		this.add(this.unPanelVueLPt);
		
		//visibilite du panel par defaut
		this.unPanelVueLPt.setVisible(true);
		this.btLPt.setEnabled(false);
		this.setVisible(true);
	}
	
	public void afficherPanel(int choix) {
		this.unPanelVueLPt.setVisible(false);
		this.btLPt.setEnabled(true);
		switch(choix) {
			case 1 : this.unPanelVueLPt.setVisible(true);this.btLPt.setEnabled(false);break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btLPt) {
			this.afficherPanel(1);
		}
	}
}
