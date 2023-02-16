package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controleur.Administrateur;

public class V_Generale extends JFrame implements ActionListener{
	
	private JTabbedPane onglets = new JTabbedPane();
	
	private JButton btQuitter = new JButton ("Quitter");
	private JButton btProfil = new JButton("Profil");
	private JButton btAccueil = new JButton("Accueil");

	
	private P_Profil panelProfil;
	
	private O_Gestion Gestion = new O_Gestion();
	private O_Trafic Trafic = new O_Trafic();
	
	private Administrateur unAdministrateur;
	public static int leIdAd;

	
	public V_Generale(Administrateur unAdministrateur) {
		//fenetrage
		this.setTitle("Gestion des Retards IRIS");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 500);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(GREI.color1);
		
		//instanciation profil
		V_Generale.leIdAd = unAdministrateur.getIdU();
		this.unAdministrateur = unAdministrateur;
		this.panelProfil = new P_Profil(unAdministrateur);
		this.add(this.panelProfil);
		
		//Parametrage onglets
	    this.onglets.setBounds(50,50,800,400);
	    this.onglets.setBackground(GREI.color1);

	    //Ajout onglets
		this.onglets.add("Gestion",Gestion);
		this.onglets.add("Trafic",Trafic);

		//ajout a la fenetre
		this.add(onglets);
		
		//ajout boutons
		this.btQuitter.setBounds(830,0,70,50);
		this.add(this.btQuitter);
		
		this.btProfil.setBounds(780,0,50,50);
		this.add(this.btProfil);
		
		this.btAccueil.setBounds(710,0,70,50);
		this.add(this.btAccueil);
		
		this.btAccueil.setEnabled(false);
		
		//rendre les boutons ecoutables
		this.btQuitter.addActionListener(this);
		this.btProfil.addActionListener(this);
		this.btAccueil.addActionListener(this);


	}
	public void OnOffProfil(){
		if (this.panelProfil.isVisible() == false){
			this.btAccueil.setEnabled(true);
			this.btProfil.setEnabled(false);
			this.onglets.setVisible(false);
			this.panelProfil.setVisible(true);
		} else {
			this.btAccueil.setEnabled(false);
			this.btProfil.setEnabled(true);
			this.onglets.setVisible(true);
			this.panelProfil.setVisible(false);
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
				GREI.creerDetruireVueGenerale(false, null);
			}
		}
		else if (e.getSource() == this.btProfil) {
			this.OnOffProfil();
		}
		else if (e.getSource() == this.btAccueil) {
			this.OnOffProfil();
		}
	}
}
