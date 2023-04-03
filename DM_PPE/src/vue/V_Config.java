package vue;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modele.BDD;
import modele.M_Administrateur;

public class V_Config extends JFrame implements ActionListener, KeyListener {
	
	private JPanel panelForm = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btModifier = new JButton ("Modifier");
	private JButton btRetour = new JButton();

	
	private JTextField txtHost = new JTextField(BDD.serveur);
	private JTextField txtBase = new JTextField(BDD.bdd);
	private JTextField txtUser = new JTextField("");
	private JPasswordField txtMdp = new JPasswordField("");
	
	public V_Config() {
		this.setTitle("Configuration ");
		this.setBounds(100, 50, 600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(GREI.color1);
		this.setLayout(null);
		
		this.panelForm.setBounds(270, 90, 300, 120);
		this.panelForm.setBackground(GREI.color1);
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel(" Hote : "));
		this.panelForm.add(this.txtHost);
		this.panelForm.add(new JLabel(" Base : "));
		this.panelForm.add(this.txtBase);
		this.panelForm.add(new JLabel(" Utilisateur : "));
		this.panelForm.add(this.txtUser);
		this.panelForm.add(new JLabel(" MDP : "));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btModifier);
		this.add(panelForm);
		
		//bouton retour
		this.btRetour.setBounds(570, 0, 30, 30);
		ImageIcon Back = new ImageIcon("src/img/back.png");
		Image image = Back.getImage();
		Image newimage = image.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon NewBack = new ImageIcon(newimage);
		this.btRetour.setIcon(NewBack);
		this.add(this.btRetour);
		
		ImageIcon uneImage = new ImageIcon("src/img/android-chrome-192x192.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(20, 40, 240, 230);
		this.add(unLogo);
		
		this.btAnnuler.addActionListener(this);
		this.btModifier.addActionListener(this);
		this.btRetour.addActionListener(this);

		this.txtUser.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
			if(!this.txtBase.getText().isBlank() || !this.txtHost.getText().isBlank() || !this.txtUser.getText().isBlank()) {
				String host = this.txtHost.getText();
				String base = this.txtBase.getText();
				String user = this.txtUser.getText();
				String mdp = new String(this.txtMdp.getPassword());
				
				try {
					BDD.modifConfigProperties(host, base, user, mdp);
					GREI.rendreVisibleVueConnexion(true);
					GREI.creerDetruireVueConfig(false);
					System.out.println("rentre");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else {
			System.out.println("touche");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler) {
			this.txtHost.setText("");
			this.txtBase.setText("");
			this.txtUser.setText("");
			this.txtMdp.setText("");
		}
		else if(e.getSource() == this.btModifier) {
			if(!this.txtBase.getText().isBlank() || !this.txtHost.getText().isBlank() || !this.txtUser.getText().isBlank()) {
				String host = this.txtHost.getText();
				String base = this.txtBase.getText();
				String user = this.txtUser.getText();
				String mdp = new String(this.txtMdp.getPassword());
				System.out.println("nani");
				try {
					BDD.modifConfigProperties(host, base, user, mdp);
					GREI.rendreVisibleVueConnexion(true);
					GREI.creerDetruireVueConfig(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}		
		}
		else if (e.getSource() == this.btRetour) {
			GREI.rendreVisibleVueConnexion(true);
			GREI.creerDetruireVueConfig(false);
		}
		
	}
}
