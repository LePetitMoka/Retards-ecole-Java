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

import controleur.C_Matiere;
import controleur.Matiere;
import controleur.Tableau;

public class P_Matiere extends P_Principal implements ActionListener{
	private JTextField txtIntitule = new JTextField();
	
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
	
	public P_Matiere () {
		super(GREI.color1);

		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 100);
		this.PanelForm.setLayout(new GridLayout(2,2));
		this.PanelForm.add(new JLabel("Intitule : "));
		this.PanelForm.add(this.txtIntitule);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel filtre
		this.PanelFiltre.setBounds(420,30,650,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,4));
		this.PanelFiltre.add(new JLabel ("Filtrer les Trajets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("IdM");
		this.cbxFiltre.addItem("Intitule");


		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"ID Matiere", "Intitule"};
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
					int idMatiere = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
					//suppression dans la BDD
					JOptionPane.showMessageDialog(null, C_Matiere.supprimerMatiere(idMatiere));
					//actualisation de l'affichage
					actualiser();
				}
				else if (e.getClickCount() == 1) {
					
					String intitule = uneTable.getValueAt(numLigne, 1).toString();
					
					txtIntitule.setText(intitule);
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void viderChamps() {
		this.txtIntitule.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Matiere> lesMatieres = C_Matiere.selectAllMatieres();
		Object[][] matrice = new Object [lesMatieres.size()][2];
		int i=0;
		for (Matiere uneMatiere : lesMatieres) {
			matrice[i][0] = uneMatiere.getIdM();
			matrice[i][1] = uneMatiere.getIntitule();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<Matiere> lesMatieres){
		Object[][] matrice = new Object [lesMatieres.size()][2];
		int i=0;
		for (Matiere uneMatiere : lesMatieres) {
			matrice[i][0] = uneMatiere.getIdM();
			matrice[i][1] = uneMatiere.getIntitule();
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
			String intitule = this.txtIntitule.getText();
			
			//instancier une matiere
			
			Matiere uneMatiere = new Matiere(intitule);
			
			//insertion client dans BDD
			C_Matiere.insertMatiere(uneMatiere);			
			this.viderChamps();
			JOptionPane.showMessageDialog(this, "Matiere insere");
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String intitule = this.txtIntitule.getText();
			
			//recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idmatiere = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());

			//instancier une matiere
			Matiere uneMatiere = new Matiere(idmatiere,intitule);
			
			//on realise la modif BDD
			C_Matiere.updateMatiere(uneMatiere);
			//actualisation de l'affichage
			JOptionPane.showMessageDialog(this, "Matiere modifie");
			this.actualiser();
			this.viderChamps();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<Matiere> lesMatieres = C_Matiere.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesMatieres);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}