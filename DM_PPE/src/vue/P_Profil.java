package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Administrateur;
import controleur.C_Administrateur;
import modele.BDD;

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
		
		this.setBounds(0, 50, 880, 430);

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
							"Nom de l'administrateur : "+ unAdministrateur.getNom()
							+"\n\nPrenom de l'administrateur : "+ unAdministrateur.getPrenom()
							+"\n\nAdresse de l'administrateur : "+unAdministrateur.getTelephone()
							+"\n\nTelephone de l'administrateur : "+unAdministrateur.getTelephone()
							+"\n\nEmail de l'administrateur : "+unAdministrateur.getEmail()
							+"\n\nURL signature de l'administrateur : "+unAdministrateur.getEmail()
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
		this.panelForm.add(new JLabel ("Nouveau MDP Admin :"));
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
	private void actualiser() {
		this.unAdministrateur = C_Administrateur.selectWhereAdministrateur(this.unAdministrateur.getIdU());
		this.txtInfos.setText(
				"Nom du administrateur : "+ this.unAdministrateur.getNom()
				+"\n\nPrenom du administrateur : "+ this.unAdministrateur.getPrenom()
				+"\n\nAdresse administrateur : "+this.unAdministrateur.getTelephone()
				+"\n\nTelephone administrateur : "+this.unAdministrateur.getTelephone()
				+"\n\nEmail administrateur : "+this.unAdministrateur.getEmail()
				+"\n\nURL signature administrateur :"+this.unAdministrateur.getEmail()
				);
		this.txtEmail.setText(this.unAdministrateur.getEmail());
		this.txtAdresse.setText(this.unAdministrateur.getAdresse());
		this.txtPrenom.setText(this.unAdministrateur.getPrenom());
		this.txtTelephone.setText(this.unAdministrateur.getTelephone());
		this.txtURL.setText(this.unAdministrateur.getUrlSignature());
		this.txtNom.setText(this.unAdministrateur.getNom());
	}
	public Boolean verifMDP(String mdp) {
		boolean cond = true;
		if (mdp == null || mdp.isBlank()) {
			JOptionPane.showMessageDialog(this, "Mot de passe vide");
			cond = false;
		} else if (mdp.length() < 4 && mdp.length() > 15){
			JOptionPane.showMessageDialog(this,"Taille du mot de passe incorrecte (entre 3 et 15)");
			cond = false;
		} else if (!mdp.matches("/[a-z]/")) {
			JOptionPane.showMessageDialog(this,"Il manque une minuscule");
			cond = false;
		} else if (!mdp.matches("/[A-Z]/")) {
			JOptionPane.showMessageDialog(this,"Il manque une majuscule");
			cond = false;
		} else if (!mdp.matches("/\\W/")) {
			JOptionPane.showMessageDialog(this,"Il manque un caractere special");
			cond = false;
		} else if (!mdp.matches("/[0-9]/")) {
			JOptionPane.showMessageDialog(this,"Il manque un chiffre");
			cond = false;
		}
		
		
		if (cond = false){
			this.txtMDP.setText("");
		}
		return true;
	}
	public Boolean verifEmail(String email) {
		boolean cond = true;
		if (email == null || email.isBlank()) {
			JOptionPane.showMessageDialog(this, "Email vide");
			cond = false;
		} else if (email.length() < 4 && email.length() > 15){
			JOptionPane.showMessageDialog(this,"Taille du mot de passe incorrecte (entre 3 et 15)");
			cond = false;
		} else if (!email.matches("/./")) {
			JOptionPane.showMessageDialog(this,"Il manque un point");
			cond = false;
		} else if (!email.matches("/@/")) {
			JOptionPane.showMessageDialog(this,"Il manque un @");
			cond = false;
		}
		
		//email.tr
		
		if (cond = false){
			this.txtEmail.setText("");
		}
		return true;
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
			
			
			if (this.verifMDP(mdp) == true) {
				//instanciation de administrateur
				this.unAdministrateur = new Administrateur(this.unAdministrateur.getIdU(),nom, prenom, adresse, telephone, email, mdp, URLSignature);
				
				//modifications dans la BDD
				try {
					C_Administrateur.updateAdministrateur(this.unAdministrateur);
					JOptionPane.showMessageDialog(this, "L'administrateur a ete modifie");
					this.viderChamps();
					this.actualiser();
				} catch (SQLException exp) {
					//System.out.println("Erreur d'execution de l'update: ");
					switch (exp.getSQLState().toString()){
						case "45001": JOptionPane.showMessageDialog(this, exp.getMessage());break;
						case "45002": JOptionPane.showMessageDialog(this, exp.getMessage());break;
						default: BDD.printSQLException(exp);break;
					}
				}
			}
		}
	}
}