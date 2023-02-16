package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import controleur.C_Administrateur;
import vue.GREI;
import controleur.Administrateur;

public class V_Connexion extends JFrame implements ActionListener, KeyListener {
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton ("Connexion");
	
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	//

	ImageIcon IconAD = new ImageIcon("src/img/administrateur.png");
	ImageIcon IconPF = new ImageIcon("src/img/professeur.png");
	ImageIcon IconET = new ImageIcon("src/img/aetudiant.png");
	
	private JLabel txt1 = new JLabel("Bienvenu sur GREI");
	private JLabel txt2 = new JLabel("Quel est votre qualification?");
	private JLabel txt3 = new JLabel("Si n'avez pas de compte vous pouvez en creer un sur le site web");
	private JLabel labelAD = new JLabel(this.IconAD);
	private JLabel labelPF = new JLabel(this.IconPF);
	private JLabel labelET = new JLabel(this.IconET);
	
	private JButton btAD = new JButton("Administrateur");
	private JButton btPF = new JButton("Professeur");
	private JButton btET = new JButton("Etudiant");
	
	private JPanel panel1 = new JPanel();
	private JPanel panelAD = new JPanel();
	private JPanel panelPF = new JPanel();
	private JPanel panelET = new JPanel();
	
	public V_Connexion() {
		this.setTitle("GREI");
		this.setBounds(100, 50, 800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		
		this.panelForm.setBounds(270, 90, 300, 120);
		this.panelForm.setBackground(Color.WHITE);
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP : "));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btConnexion);
		//this.add(panelForm);
		/*
		ImageIcon uneImage = new ImageIcon("src/img/android-chrome-192x192.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(20, 40, 240, 230);
		this.add(unLogo);
		
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		*/
		
		JLabel labelAD2 = new JLabel(this.IconAD);
		labelAD2.setPreferredSize(new Dimension(200, 100));
		this.labelPF.setPreferredSize(new Dimension(200, 100));
		this.labelET.setPreferredSize(new Dimension(200, 100));
		
		this.txt1.setBounds(350, 5, 200, 20);
		this.txt2.setBounds(325, 30, 200, 20);
		this.txt3.setBounds(0, 500, 200, 20);
		this.add(txt1);
		this.add(txt2);
		this.add(txt3);

		ImageIcon imageAD = new ImageIcon("src/img/administrateur.png");
		JLabel logoAD = new JLabel(imageAD);
		logoAD.setBounds(0, 0, 200, 200);
		
		this.panelAD.setBackground(Color.white);
		this.panelAD.setLayout(new BorderLayout(2,1));
		this.panelAD.add(logoAD);
		this.panelAD.add(new JLabel("Administrateur"));
		
		this.panelPF.setBackground(Color.red);
		this.panelET.setBackground(Color.green);
		
		this.panel1.setBounds(40, 80, 700, 320);
		this.panel1.setBackground(Color.white);
		this.panel1.setLayout(new GridLayout(1,3));
		/*
		this.panel1.add(this.panelAD);
		this.panel1.add(this.panelPF);
		this.panel1.add(this.panelET);
		*/
		this.panel1.add(this.btAD);
		this.panel1.add(this.btPF);
		this.panel1.add(this.btET);
		this.add(panel1);
		
		this.btAD.addActionListener(this);
		this.btPF.addActionListener(this);
		this.btET.addActionListener(this);
		
		this.setVisible(true);
	}
	
	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String(this.txtMdp.getPassword());
		
		Administrateur unAdministrateur = C_Administrateur.selectWhereAdministrateur(email, mdp);
		if(unAdministrateur == null) {
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
		} else {
			JOptionPane.showMessageDialog(this, "Vous etes connecté en tant que " + unAdministrateur.getNom() + " " + unAdministrateur.getPrenom());
			GREI.rendreVisibleVueConnexion(false);
			GREI.creerDetruireVueGenerale(true);
		}/*
		GREI.rendreVisibleVueConnexion(false);
		GREI.creerDetruireVueGenerale(true);*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btConnexion) {
			this.traitement();
		}
		else if(e.getSource() == this.btAD) {
			
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
