package vue;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import controleur.C_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;
import controleur.Tableau;
import controleur.VSql_Vue_Etudiant_Retard_Perturbation_SansBillet;

public class P_Vue_Etudiant_Retard_Perturbation_SansBillet extends P_Principal implements ActionListener {
	private JComboBox<String> cbxFiltre = new JComboBox<String>();
	private JButton btFiltrer = new JButton("Filtrer");
	private JPanel PanelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btAnnuler = new JButton("Annuler"); 
	
	private JPanel PanelTable = new JPanel();
	
	private JTable uneTable;
	private Tableau unTableau;
	
	public P_Vue_Etudiant_Retard_Perturbation_SansBillet () {
		super(GREI.color1);
				
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(20, 70, 1080, 620);
		this.PanelTable.setLayout(null);
		
		//Panel filtre
		this.PanelFiltre.setBounds(20,35,1040,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,5));
		this.PanelFiltre.add(new JLabel ("Filtrer les Trajets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("IdE");
		this.cbxFiltre.addItem("nom");
		this.cbxFiltre.addItem("prenom");
		this.cbxFiltre.addItem("date");
		this.PanelFiltre.add(this.btAnnuler);

		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		String entetes [] = {"ID Etudiant", "Nom Prenom", "nb Billets", "date"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 1040, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.resizeTable();
		this.add(this.PanelTable);
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
	public void viderChamps() {
		this.txtFiltre.setText("");
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesVueLPts = C_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet.selectAllVues();
		Object[][] matrice = new Object [lesVueLPts.size()][4];
		int i=0;
		for (VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVueLPt : lesVueLPts) {
			matrice[i][0] = uneVueLPt.getIdE();
			matrice[i][1] = uneVueLPt.getNom();
			matrice[i][2] = uneVueLPt.getPrenom();
			matrice[i][3] = uneVueLPt.getDuree();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesVues){
		Object[][]matrice = new Object[lesVues.size()][4];
		int i = 0;
		for (VSql_Vue_Etudiant_Retard_Perturbation_SansBillet uneVueLPt : lesVues) {
			matrice[i][0] = uneVueLPt.getIdE();
			matrice[i][1] = uneVueLPt.getNom();
			matrice[i][2] = uneVueLPt.getPrenom();
			matrice[i][3] = uneVueLPt.getDuree();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<VSql_Vue_Etudiant_Retard_Perturbation_SansBillet> lesVues = C_VSql_Vue_Etudiant_Retard_Perturbation_SansBillet.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesVues);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
