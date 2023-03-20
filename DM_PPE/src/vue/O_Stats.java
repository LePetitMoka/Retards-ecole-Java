package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class O_Stats extends JPanel implements ActionListener {
	
	private JButton btTotalRet = new JButton("Total retards");
	
	private JPanel panelMenu = new JPanel();
	private P_VueLPt unPanelVueTotalRet = new P_VueLPt();
	
	public O_Stats() {
		
		//config du panel onglet
		this.setLayout(null);

		//placement du Panel menu
		this.panelMenu.setBounds(0, 0, 1080, 40);
		this.panelMenu.setBackground(GREI.color1);
		this.panelMenu.setLayout(new GridLayout(1, 4));
		this.panelMenu.add(this.btTotalRet);
		this.add(this.panelMenu);
		
		//rendre boutons ecoutables
		this.btTotalRet.addActionListener(this);

		this.add(this.unPanelVueTotalRet);
		
		//visibilite du panel par defaut
		this.unPanelVueTotalRet.setVisible(true);
		this.btTotalRet.setEnabled(false);
		this.setVisible(true);
	}
	
	public void afficherPanel(int choix) {
		this.unPanelVueTotalRet.setVisible(false);
		this.btTotalRet.setEnabled(true);
		switch(choix) {
			case 1 : this.unPanelVueTotalRet.setVisible(true);this.btTotalRet.setEnabled(false);break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btTotalRet) {
			this.afficherPanel(1);
		}
	}
}
