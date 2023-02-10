package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

public class V_Generale extends JFrame implements ActionListener{
	
	private JTabbedPane onglets = new JTabbedPane();
	private JButton btQuitter = new JButton ("Quitter");
	private O_Gestion Gestion = new O_Gestion();
	
	public V_Generale() {
		//fenetrage
		this.setTitle("Gestion des Retards IRIS");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 500);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(GREI.color1);
		
		//Parametrage onglets
	    onglets.setBounds(50,50,800,400);
		//Ajout onglets
		this.onglets.add("Gestion",Gestion);
		//ajout a la fenetre
		this.add(onglets);
		
		//ajout boutons
		this.btQuitter.setBounds(830,0,70,50);
		this.add(this.btQuitter);
		
		//rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
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
	}
}
