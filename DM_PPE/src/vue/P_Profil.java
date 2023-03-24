package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		this.setBounds(0, 200, 1080, 430);

		//instanciation administrateur (attribut de classe) (note: toujours APRES le super())
		this.unAdministrateur = unAdministrateur;
		
		//remplir les donnees du administrateur
		this.txtEmail.setText(unAdministrateur.getEmail());
		this.txtTelephone.setText(unAdministrateur.getTelephone());
		this.txtPrenom.setText(unAdministrateur.getPrenom());
		this.txtNom.setText(unAdministrateur.getNom());
		this.txtAdresse.setText(unAdministrateur.getAdresse());
		this.txtURL.setText(unAdministrateur.getUrlSignature());

		
		this.txtInfos.setBounds(100,100,450,200);
		this.txtInfos.setBackground(GREI.color1);

		this.txtInfos.setText(
							"Nom de l'administrateur : "+ unAdministrateur.getNom()
							+"\n\nPrenom de l'administrateur : "+ unAdministrateur.getPrenom()
							+"\n\nAdresse de l'administrateur : "+unAdministrateur.getAdresse()
							+"\n\nTelephone de l'administrateur : "+unAdministrateur.getTelephone()
							+"\n\nEmail de l'administrateur : "+unAdministrateur.getEmail()
							+"\n\nURL signature de l'administrateur : "+unAdministrateur.getUrlSignature()
							);
		this.add(this.txtInfos);
		this.btModifier.setBounds(100,300,120,30);
		this.add(this.btModifier);
		
		
		//placement du panel form
		this.panelForm.setBackground(GREI.color1);
		this.panelForm.setBounds(550,60,500,280);
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
				+"\n\nAdresse administrateur : "+this.unAdministrateur.getAdresse()
				+"\n\nTelephone administrateur : "+this.unAdministrateur.getTelephone()
				+"\n\nEmail administrateur : "+this.unAdministrateur.getEmail()
				+"\n\nURL signature administrateur : "+this.unAdministrateur.getUrlSignature()
				);
		this.txtEmail.setText(this.unAdministrateur.getEmail());
		this.txtAdresse.setText(this.unAdministrateur.getAdresse());
		this.txtPrenom.setText(this.unAdministrateur.getPrenom());
		this.txtTelephone.setText(this.unAdministrateur.getTelephone());
		this.txtURL.setText(this.unAdministrateur.getUrlSignature());
		this.txtNom.setText(this.unAdministrateur.getNom());
	}
	public Boolean verifMDP(String mdp) {
	    
		if (mdp == null || mdp.isBlank()) {
			JOptionPane.showMessageDialog(this, "Erreur MDP: Mot de passe vide");
			return false;
		}
		
		boolean cond = true;
		Pattern patternMin = Pattern.compile("[a-z]");
	    Matcher matcherMin = patternMin.matcher(mdp);
	    Pattern patternMaj = Pattern.compile("[A-Z]");
	    Matcher matcherMaj = patternMaj.matcher(mdp);
	    Pattern patternSpec = Pattern.compile("\\W");
	    Matcher matcherSpec = patternSpec.matcher(mdp);
	    Pattern patternNum = Pattern.compile("[0-9]");
	    Matcher matcherNum = patternNum.matcher(mdp);
		
		if (mdp.length() < 4 && mdp.length() > 15){
			JOptionPane.showMessageDialog(this,"Erreur MDP: Taille du mot de passe incorrecte (entre 3 et 15)");
			cond = false;
		} else if (!matcherMin.find()) {
			JOptionPane.showMessageDialog(this,"Erreur MDP: Il manque une minuscule");
			cond = false;
		} else if (!matcherMaj.find()) {
			JOptionPane.showMessageDialog(this,"Erreur MDP: Il manque une majuscule");
			cond = false;
		} else if (!matcherSpec.find()) {
			JOptionPane.showMessageDialog(this,"Erreur MDP: Il manque un caractere special");
			cond = false;
		} else if (!matcherNum.find()) {
			JOptionPane.showMessageDialog(this,"Erreur MDP: Il manque un chiffre");
			cond = false;
		}
		
		
		if (!cond){
			this.txtMDP.setText("");
		}
		return cond;
	}
	public Boolean verifEmail(String email) {

		if (email == null || email.isBlank()) {
			JOptionPane.showMessageDialog(this, "Erreur Email: Email vide");
			return false;
		}
		
		boolean cond = true;
		Pattern patternDot = Pattern.compile("\\.");
	    Matcher matcherDot = patternDot.matcher(email);
	    Pattern patternAt = Pattern.compile("\\@");
	    Matcher matcherAt = patternAt.matcher(email);
		
	    if (email.length() < 4 && email.length() > 15){
			JOptionPane.showMessageDialog(this,"Erreur Email: Taille du mot de passe incorrecte (entre 3 et 15)");
			cond = false;
		} else if (!matcherAt.find()) {
			JOptionPane.showMessageDialog(this,"Erreur Email: Il manque un @");
			cond = false;
		} else if (!matcherDot.find()) {
			JOptionPane.showMessageDialog(this,"Erreur Email: Il manque un point");
			cond = false;
		}
		// on parcours l'email pour compter les points et les @
		int c1 = 0, c2 = 0;
		for (int i = 0; i < email.length(); i++) {
			if (email.charAt(i) == '@') {
				c1 ++;
			}
			else if (email.charAt(i) == '.') {
				c2++;
			}
		}
		
		if (c1 > 1) {
			JOptionPane.showMessageDialog(this,"Il y a trop d'@");
			cond = false;
		} else if (c2 > 1) {
			JOptionPane.showMessageDialog(this,"Il y a trop de point");
			cond = false;
		}
		
		if (cond == false){
			this.txtEmail.setText("");
		}
		return cond;
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
			
			
			if (this.verifMDP(mdp) == true && this.verifEmail(email)) {
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