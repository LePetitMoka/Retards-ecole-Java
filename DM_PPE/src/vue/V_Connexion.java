package vue;

import java.awt.GridLayout;
import java.awt.Image;
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
import controleur.Administrateur;

public class V_Connexion extends JFrame implements ActionListener, KeyListener {
	private JPanel panelForm = new JPanel();
	private JButton btConfig = new JButton();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton ("Connexion");
	
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	
	public V_Connexion() {
		this.setTitle("Gestion des Interventions d'Orange");
		this.setBounds(100, 50, 600, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(GREI.color1);
		this.setLayout(null);
		
		//bouton config
		this.btConfig.setBounds(575, 0, 25, 25);
		ImageIcon Database = new ImageIcon("src/img/database.png");
		Image image = Database.getImage();
		Image newimage = image.getScaledInstance(25, 25, Image.SCALE_AREA_AVERAGING);
		ImageIcon NewDatabase = new ImageIcon(newimage);
		this.btConfig.setIcon(NewDatabase);
		this.add(this.btConfig);
		
		this.panelForm.setBounds(270, 90, 300, 120);
		this.panelForm.setBackground(GREI.color1);
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.add(new JLabel(" Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel(" Mot de passe : "));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btConnexion);
		this.add(panelForm);
		
		ImageIcon uneImage = new ImageIcon("src/img/android-chrome-192x192.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(20, 40, 240, 230);
		this.add(unLogo);
		
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		this.btConfig.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
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
			GREI.creerDetruireVueGenerale(true, unAdministrateur);
		}
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
		else if (e.getSource() == this.btConfig) {
			GREI.creerDetruireVueConfig(true);
			GREI.rendreVisibleVueConnexion(false);
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
