package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Administrateur;
import controleur.C_Administrateur;

public class P_Profil extends P_Principal implements ActionListener {
	
	private JTextArea txtInfos = new JTextArea();
	
	private JButton btModifier = new JButton("Modifier");
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");

	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTelephone= new JTextField();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMDP = new JPasswordField();
	private JTextField txtURL = new JTextField();


	private JPanel panelForm = new JPanel();
	
	private Administrateur unAdministrateur;
	
	public P_Profil(Administrateur unAdministrateur) {
		super(GREI.color1);
		
		//instanciation administrateur (attribut de classe) (note: toujours APRES le super())
		this.unAdministrateur = unAdministrateur;
		
		//remplir les donnees du administrateur
		this.txtEmail.setText(unAdministrateur.getEmail());
		this.txtTelephone.setText(unAdministrateur.getTelephone());
		this.txtPrenom.setText(unAdministrateur.getPrenom());
		this.txtNom.setText(unAdministrateur.getNom());
		this.txtAdresse.setText(unAdministrateur.getAdresse());
		this.txtURL.setText(unAdministrateur.getUrlSignature());

		
		this.txtInfos.setBounds(100,100,300,200);
		this.txtInfos.setBackground(GREI.color1);

		this.txtInfos.setText(
							"Nom du administrateur : "+ unAdministrateur.getNom()
							+"\nPrenom du administrateur : "+ unAdministrateur.getPrenom()
							+"\nAdresse administrateur : "+unAdministrateur.getTelephone()
							+"\nTelephone administrateur : "+unAdministrateur.getTelephone()
							+"\nEmail administrateur : "+unAdministrateur.getEmail()
							+"\nURL signature administrateur : "+unAdministrateur.getEmail()
							);
		this.add(this.txtInfos);
		this.btModifier.setBounds(100,350,120,30);
		this.add(this.btModifier);
		
		
		//placement du panel form
		this.panelForm.setBackground(GREI.color1);
		this.panelForm.setBounds(500,60,300,280);
		this.panelForm.setLayout(new GridLayout (8,2));
		this.panelForm.add(new JLabel ("Nom Admin :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel ("Prenom Admin :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel ("Adresse Admin :"));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel ("Email Admin :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel ("Telephone Admin :"));
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(new JLabel ("MDP Admin :"));
		this.panelForm.add(this.txtMDP);
		this.panelForm.add(new JLabel ("URL Signature :"));
		this.panelForm.add(this.txtURL);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		this.panelForm.setVisible(false);
		
		//rendre les boutons ecoutables
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btModifier.addActionListener(this);
		
		//visibilite
		
		this.setVisible(false);
		
	}
	private void viderChamps() {
		this.txtEmail.setText("");
		this.txtMDP.setText("");
		this.txtAdresse.setText("");
		this.txtPrenom.setText("");
		this.txtTelephone.setText("");
		this.txtURL.setText("");
		this.txtNom.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btModifier) {
			if (this.panelForm.isVisible()) {
				this.panelForm.setVisible(false);
			}else {
				this.panelForm.setVisible(true);
			}
		}
		else if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		}
		else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());
			String URLSignature = this.txtURL.getText();
			
			
			//instanciation de administrateur
			this.unAdministrateur = new Administrateur(unAdministrateur.getIdU(),nom, prenom, adresse, telephone, email, mdp, URLSignature);
			
			//modifications dans la BDD
			C_Administrateur.updateAdministrateur(unAdministrateur);
			this.viderChamps();
			
			//actualisation de l'affichage
			this.txtInfos.setText(
					"Nom du administrateur : "+ unAdministrateur.getNom()
					+"\nPrenom du administrateur :"+ unAdministrateur.getPrenom()
					+"\nAdresse administrateur"+unAdministrateur.getTelephone()
					+"\nTelephone administrateur"+unAdministrateur.getTelephone()
					+"\nEmail administrateur :"+unAdministrateur.getEmail()
					+"\nURL signature administrateur :"+unAdministrateur.getEmail()
					);
			
			JOptionPane.showMessageDialog(this, "L'administrateur a ete insere");
		}
	}
}