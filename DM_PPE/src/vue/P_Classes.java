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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controleur.C_Classe;
import controleur.Classe;
import controleur.Tableau;

public class P_Classes extends P_Principal implements ActionListener {
	private JTextField txtNom = new JTextField();
	private JTextField txtDiplomePrepare = new JTextField();
	private JTextField txtPromotion = new JTextField();
	private JTextField txtEmail = new JTextField();	
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JComboBox<String> cbxFiltre = new JComboBox<String>();
	private JButton btFiltrer = new JButton("Filtrer");
	private JPanel PanelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	
	private JPanel PanelForm = new JPanel();
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Classes () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 220);
		this.PanelForm.setLayout(new GridLayout(5,2));
		this.PanelForm.add(new JLabel("Nom : "));
		this.PanelForm.add(this.txtNom);
		this.PanelForm.add(new JLabel("DiplomePrepare : "));
		this.PanelForm.add(this.txtDiplomePrepare);
		this.PanelForm.add(new JLabel("Promotion : "));
		this.PanelForm.add(this.txtPromotion);
		this.PanelForm.add(new JLabel("Email : "));
		this.PanelForm.add(this.txtEmail);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel filtre
		this.PanelFiltre.setBounds(420,30,650,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,4));
		this.PanelFiltre.add(new JLabel ("Filtrer les classes :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("IdCl");
		this.cbxFiltre.addItem("Nom");
		this.cbxFiltre.addItem("diplomePrepare");
		this.cbxFiltre.addItem("email");
		this.cbxFiltre.addItem("promotion");
		this.cbxFiltre.addItem("NbEtudiants");


		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Classe", "Nom", "DiplomePrepare", "Promotion", "Email","nbEtudiants"};
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
					int idClasse = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					JOptionPane.showMessageDialog(null, C_Classe.supprimerClasse(idClasse));
					viderChamps();
					actualiser();
				}
				else if (e.getClickCount() == 1) {
					
					String promotion = "";
					
					String nom = uneTable.getValueAt(numLigne, 1).toString();
					String diplomeprepare = uneTable.getValueAt(numLigne, 2).toString();
					if (uneTable.getValueAt(numLigne, 3) != null) {
					promotion = uneTable.getValueAt(numLigne, 3).toString();
					}
					String email = uneTable.getValueAt(numLigne, 4).toString();

					
					txtNom.setText(nom);
					txtDiplomePrepare.setText(diplomeprepare);
					txtPromotion.setText(promotion);
					txtEmail.setText(email);
					
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void viderChamps() {
		this.txtNom.setText("");
		this.txtDiplomePrepare.setText("");
		this.txtEmail.setText("");
		this.txtPromotion.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Classe> lesClasses = C_Classe.selectAllClasses();
		Object[][] matrice = new Object [lesClasses.size()][6];
		int i=0;
		for (Classe uneClasse : lesClasses) {
			matrice[i][0] = uneClasse.getIdCl();
			matrice[i][1] = uneClasse.getNom();
			matrice[i][2] = uneClasse.getDiplomePrepare();
			matrice[i][3] = uneClasse.getPromotion();
			matrice[i][4] = uneClasse.getEmail();
			matrice[i][5] = uneClasse.getNbEtudiants();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<Classe> lesClasses){
		Object[][] matrice = new Object [lesClasses.size()][6];
		int i=0;
		for (Classe uneClasse : lesClasses) {
			matrice[i][0] = uneClasse.getIdCl();
			matrice[i][1] = uneClasse.getNom();
			matrice[i][2] = uneClasse.getDiplomePrepare();
			matrice[i][3] = uneClasse.getPromotion();
			matrice[i][4] = uneClasse.getEmail();
			matrice[i][5] = uneClasse.getNbEtudiants();
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
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String diplomeprepare = this.txtDiplomePrepare.getText();
			String promotion = this.txtPromotion.getText();
			String email = this.txtEmail.getText();
			
			//instancier une classe
			
			Classe uneClasse = new Classe(nom, diplomeprepare, promotion, email);
			
			//insertion client dans BDD
			JOptionPane.showMessageDialog(this, C_Classe.insertClasse(uneClasse));
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String diplomeprepare = this.txtDiplomePrepare.getText();
			String promotion = this.txtPromotion.getText();
			String email = this.txtEmail.getText();
			
			//recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idclasse = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

			//instancier une classe
			Classe uneClasse = new Classe(idclasse,nom,diplomeprepare,promotion,email);
			
			//on realise la modif BDD
			JOptionPane.showMessageDialog(this, C_Classe.updateClasse(uneClasse));
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<Classe> lesClasses = C_Classe.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesClasses);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
