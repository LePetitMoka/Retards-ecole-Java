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

import controleur.C_Etudiant;
import controleur.C_Billet;
import controleur.Etudiant;
import controleur.Billet;
import controleur.Tableau;

public class P_Billets extends P_Principal implements ActionListener {
	
	private JComboBox<String> cbxIdEtudiant = new JComboBox<String>();
	private JTextField txtRaison = new JTextField("Raison");
	
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
	
	public P_Billets () {
		super(GREI.color1);
				
		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 120);
		this.PanelForm.setLayout(new GridLayout(3,2));
		this.PanelForm.add(new JLabel("ID Eleve : "));
		this.PanelForm.add(this.cbxIdEtudiant);
		this.PanelForm.add(new JLabel("Raison : "));
		this.PanelForm.add(this.txtRaison);
		this.PanelForm.add(this.btAnnuler);
		this.PanelForm.add(this.btEnregistrer);
		this.add(PanelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//installation du panel filtre
		this.PanelFiltre.setBounds(420,30,650,25);
		this.PanelFiltre.setBackground(GREI.color1);
		this.PanelFiltre.setLayout(new GridLayout(1,4));
		this.PanelFiltre.add(new JLabel ("Filtrer les Billets :"));
		this.PanelFiltre.add(this.cbxFiltre);
		this.PanelFiltre.add(txtFiltre);
		this.PanelFiltre.add(btFiltrer);
		this.cbxFiltre.addItem("Tous");
		this.cbxFiltre.addItem("dateB");
		this.cbxFiltre.addItem("heureB");
		this.cbxFiltre.addItem("dureeRetard");
		this.cbxFiltre.addItem("URLSignature");
		this.cbxFiltre.addItem("dateheure");
		this.cbxFiltre.addItem("raison");
		this.cbxFiltre.addItem("IdAd");
		this.cbxFiltre.addItem("IdE");


		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"date_billet", "heure_billet", "dureeRetard", "URLSignature","dateheure", "raison","ID_Admin", "ID_Etudiant"};
		this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
		this.uneTable = new JTable(this.unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		uneScroll.setBounds(0, 0, 650, 620);
		this.uneTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.PanelTable.add(uneScroll);
		this.resizeTable();
		this.add(this.PanelTable);
		
		//remplir les CBX
		this.remplirCBX();
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
					int idEtudiant = Integer.parseInt(uneTable.getValueAt(numLigne, 7).toString());
					int idAdmin = Integer.parseInt(uneTable.getValueAt(numLigne, 6).toString());
					String dateheure = uneTable.getValueAt(numLigne, 4).toString();

					//suppression dans la BDD
					JOptionPane.showMessageDialog(null, C_Billet.supprimerBillet(idAdmin, idEtudiant, dateheure));
					viderChamps();
					actualiser();

				}
				else if (e.getClickCount() == 1) {
					
					String raison = uneTable.getValueAt(numLigne, 5).toString();
					String ide = uneTable.getValueAt(numLigne, 7).toString();
					txtRaison.setText(raison);
					cbxIdEtudiant.setSelectedItem(whereisItemCBX(ide,cbxIdEtudiant));
					
					cbxIdEtudiant.setEnabled(false);
					
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void remplirCBX(){
		//remplir les eleves
		ArrayList<Etudiant> lesEtudiants = C_Etudiant.selectAllEtudiants();
		//on vide le CBX
		this.cbxIdEtudiant.removeAllItems();
		//recuperer les eleves
		for(Etudiant  unEtudiant: lesEtudiants){
			this.cbxIdEtudiant.addItem(unEtudiant.getIdU()+"-"+unEtudiant.getNom()+" "+unEtudiant.getPrenom());
		}
	}
	public Object whereisItemCBX(String idTab, JComboBox<String> CBX) { //permet de trouver l'index d'un item CBX en fonction d'un ID int
		Object item = new Object();
		for (int i = 0; i < CBX.getItemCount() ; i++) {
			//on recupere l'id de l'item en explosant le texte et en convertissant le premier morceau en string
			String txtItem = CBX.getItemAt(i).toString();
			String tab[] = txtItem.split("-");
			//on le compare avec l'id recherche
			if (tab[0].compareTo(idTab) == 0) {
				item = CBX.getItemAt(i);
			}
		}
		return item;
	}
	public void viderChamps() {
		this.cbxIdEtudiant.setEnabled(true);
		this.txtRaison.setText("");
		this.btEnregistrer.setText("Enregistrer");
				
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<Billet> lesBillets = C_Billet.selectAllBillets();
		Object[][] matrice = new Object [lesBillets.size()][8];
		int i=0;
		for (Billet unBillet : lesBillets) {
			matrice[i][0] = unBillet.getDateB();
			matrice[i][1] = unBillet.getHeureB();
			matrice[i][2] = unBillet.getDureeRetard();
			matrice[i][3] = unBillet.getUrlSignature();
			matrice[i][4] = unBillet.getDateheure();
			matrice[i][5] = unBillet.getRaison();
			matrice[i][6] = unBillet.getIdAd();
			matrice[i][7] = unBillet.getIdE();
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<Billet> lesBillets){
		Object[][] matrice = new Object [lesBillets.size()][8];
		int i=0;
		for (Billet unBillet : lesBillets) {
			matrice[i][0] = unBillet.getDateB();
			matrice[i][1] = unBillet.getHeureB();
			matrice[i][2] = unBillet.getDureeRetard();
			matrice[i][3] = unBillet.getUrlSignature();
			matrice[i][4] = unBillet.getDateheure();
			matrice[i][5] = unBillet.getRaison();
			matrice[i][6] = unBillet.getIdAd();
			matrice[i][7] = unBillet.getIdE();
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
			String raison = this.txtRaison.getText();
			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idetudiant = Integer.parseInt(tab[0]);
			
			//instancier un billet
			
			Billet unBillet = new Billet(V_Generale.leIdAd,idetudiant,raison);
			
			//insertion client dans BDD
			JOptionPane.showMessageDialog(this, C_Billet.insertBillet(unBillet));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String raison = this.txtRaison.getText();
			
			//recuperation des id dans les CBX
			
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idclasse = Integer.parseInt(tab[0]);			
			//instancier un client
			//		recuperer l'id dans le tableau
			int numLigne = this.uneTable.getSelectedRow();
			int idetudiant = Integer.parseInt(this.unTableau.getValueAt(numLigne, 7).toString());
			int idadmin = Integer.parseInt(this.unTableau.getValueAt(numLigne, 6).toString());
			String dateheure = this.unTableau.getValueAt(numLigne, 4).toString();

			Billet unBillet = new Billet(idadmin,idetudiant,dateheure,raison);
			
			//on realise la modif BDD
			JOptionPane.showMessageDialog(this, C_Billet.updateBillet(unBillet));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<Billet> lesBillets = C_Billet.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesBillets);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}