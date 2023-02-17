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

public class V_ConnexionGen extends JFrame implements ActionListener, KeyListener {
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
	
	public V_ConnexionGen() {
		this.setTitle("GREI");
		this.setBounds(100, 50, 800, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAD) {
			GREI.rendreVisibleVueConnexionGen(false);
			GREI.connexion("Administrateur");
		}
		/*
		else if(e.getSource() == this.btPF) {
			GREI.rendreVisibleVueConnexionGen(false);
			GREI.connexion("Professeur");
		}
		else if(e.getSource() == this.btET) {
			GREI.rendreVisibleVueConnexionGen(false);
			GREI.connexion("Etudiant");
		}
		*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
