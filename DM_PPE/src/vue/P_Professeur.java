package vue;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controleur.C_Professeur;
import controleur.Professeur;
import controleur.Tableau;

public class P_Professeur extends P_Principal implements ActionListener {
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTelephone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtDiplome = new JTextField();
	private JPasswordField txtMDP = new JPasswordField();
	
	private JComboBox<String> cbxFiltre = new JComboBox<String>();
	private JButton btFiltrer = new JButton("Filtrer");
	private JPanel PanelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Professeur () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 320);
		this.PanelForm.setLayout(new GridLayout(8,2));
		this.PanelForm.add(new JLabel("Nom : "));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("Prenom : "));
		this.PanelForm.add(this.txtPrenom);
		this.PanelForm.add(new JLabel("Adresse : "));
		this.PanelForm.add(this.txtAdresse);
		this.PanelForm.add(new JLabel("Telephone : "));
		this.PanelForm.add(this.txtTelephone);
		this.PanelForm.add(new JLabel("Email : "));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(new JLabel("MDP : "));
		this.PanelForm.add(this.txtMDP);
		this.PanelForm.add(new JLabel("Diplome : "));
		this.PanelForm.add(this.txtDiplome);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		//installation du panel filtre
		this.PanelFiltre.setBounds(420,30,650,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,4));
		this.PanelFiltre.add(new JLabel ("Filtrer les Trajets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("IdPf");
		this.cbxFiltre.addItem("Nom");
		this.cbxFiltre.addItem("Prenom");
		this.cbxFiltre.addItem("Diplome");
		this.cbxFiltre.addItem("email");
		this.cbxFiltre.addItem("telephone");
		this.cbxFiltre.addItem("adresse");

		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Professeur", "Nom", "Prenom", "Adresse","Telephone", "Email","mdp", "Diplome"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 650, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.resizeTable();
		this.add(this.PanelTable);
		//implementation de la supression et de la modification
	
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int numLigne = uneTable.getSelectedRow();
				if (e.getClickCount() == 2) {
					int idProfesseur = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					C_Professeur.supprimerProfesseur(idProfesseur);
					//suppression dans l'affichage
					actualiser();
					JOptionPane.showMessageDialog(null, "Professeur supprime");

				}
				else if (e.getClickCount() == 1) {
					
					String nom = uneTable.getValueAt(numLigne, 1).toString();
					String prenom = uneTable.getValueAt(numLigne, 2).toString();
					String adresse = uneTable.getValueAt(numLigne, 3).toString();
					String telephone = uneTable.getValueAt(numLigne, 4).toString();
					String email = uneTable.getValueAt(numLigne, 5).toString();
					String mdp = uneTable.getValueAt(numLigne, 6).toString();
					String diplome = uneTable.getValueAt(numLigne, 7).toString();

					
					txtNom.setText(nom);
					txtPrenom.setText(prenom);
					txtAdresse.setText(adresse);
					txtEmail.setText(email);
					txtTelephone.setText(telephone);
					txtMDP.setText(mdp);
					txtDiplome.setText(diplome);
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtEmail.setText("");
		this.txtAdresse.setText("");
		this.txtTelephone.setText("");
		this.txtMDP.setText("");
		this.txtDiplome.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Professeur> lesProfesseurs = C_Professeur.selectAllProfesseurs();
		Object[][] matrice = new Object [lesProfesseurs.size()][8];
		int i=0;
		for (Professeur unProfesseur : lesProfesseurs) {
			matrice[i][0] = unProfesseur.getIdU();
			matrice[i][1] = unProfesseur.getNom();
			matrice[i][2] = unProfesseur.getPrenom();
			matrice[i][3] = unProfesseur.getAdresse();
			matrice[i][4] = unProfesseur.getTelephone();
			matrice[i][5] = unProfesseur.getEmail();
			matrice[i][6] = unProfesseur.getMdp();
			matrice[i][7] = unProfesseur.getDiplome();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<Professeur> lesProfs){
		Object[][]matrice = new Object[lesProfs.size()][8];
		int i = 0;
		for (Professeur unProfesseur : lesProfs) {
			matrice[i][0] = unProfesseur.getIdU();
			matrice[i][1] = unProfesseur.getNom();
			matrice[i][2] = unProfesseur.getPrenom();
			matrice[i][3] = unProfesseur.getAdresse();
			matrice[i][4] = unProfesseur.getTelephone();
			matrice[i][5] = unProfesseur.getEmail();
			matrice[i][6] = unProfesseur.getMdp();
			matrice[i][7] = unProfesseur.getDiplome();
			i++;
		}
		return matrice;
	}
	public void actualiser() {
		this.unTableau.setDonnees(this.obtenirDonnees());
		this.resizeTable();
	}
	public void resizeTable() {
		for (int column = 0; column < this.uneTable.getColumnCount(); column++)
		{
		    TableColumn tableColumn = this.uneTable.getColumnModel().getColumn(column);
		    Rectangle tableHeader = this.uneTable.getTableHeader().getHeaderRect(column);
		    //int preferredWidth = tableColumn.getMinWidth();
		    int preferredWidth = (int) Math.ceil(tableHeader.getWidth());
		    int maxWidth = tableColumn.getMaxWidth();

		    for (int row = 0; row < this.uneTable.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = this.uneTable.getCellRenderer(row, column);
		        Component c = this.uneTable.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + this.uneTable.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);

		        //  We've exceeded the maximum width, no need to check other rows

		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }

		    tableColumn.setPreferredWidth( preferredWidth );
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());
			String diplome = this.txtDiplome.getText();

			
			//instancier un etudiant
			
			Professeur unProfesseur = new Professeur(nom, prenom, adresse, telephone, email, mdp, diplome);
			
			//insertion client dans BDD
			
			JOptionPane.showMessageDialog(this, C_Professeur.insertProfesseur(unProfesseur));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText();
			String mdp = new String(this.txtMDP.getPassword());
			String diplome = this.txtDiplome.getText();
			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idprof = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			Professeur unProfesseur = new Professeur(idprof,nom,prenom,adresse,telephone,email,mdp,diplome);
			
			//on realise la modif BDD
			JOptionPane.showMessageDialog(this, C_Professeur.updateProfesseur(unProfesseur));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<Professeur> lesVues = C_Professeur.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesVues);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
