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

import controleur.Cours;
import controleur.C_Classe;
import controleur.C_Cours;
import controleur.C_Matiere;
import controleur.C_Professeur;
import controleur.C_VSql_Vue_Cours_Details;
import controleur.Classe;
import controleur.Matiere;
import controleur.Professeur;
import controleur.Tableau;
import controleur.VSql_Vue_Cours_Details;

public class P_Cours extends P_Principal implements ActionListener {
	
	private JComboBox<String> cbxIdCl = new JComboBox<String>();
	private JComboBox<String> cbxIdPf = new JComboBox<String>();
	private JComboBox<String> cbxIdM = new JComboBox<String>();


	private JLabel labelDateTS = new JLabel();
	private JLabel labelDuree = new JLabel();
	
	private JTextField txtDateC = new JTextField("DateC");
	private JTextField txtHeureDeb = new JTextField("HeureDeb");
	private JTextField txtHeureFin = new JTextField("HeureFin");
	private JTextField txtSalle = new JTextField("Salle");

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
	
	public P_Cours () {
		super(GREI.color1);
				
		this.PanelForm.setBackground(GREI.color1);
		this.PanelForm.setBounds(20, 60, 370, 380);
		this.PanelForm.setLayout(new GridLayout(10,2));
		this.PanelForm.add(new JLabel("Classe : "));
		this.PanelForm.add(this.cbxIdCl);
		this.PanelForm.add(new JLabel("Professeur : "));
		this.PanelForm.add(this.cbxIdPf);
		this.PanelForm.add(new JLabel("Matiere : "));
		this.PanelForm.add(this.cbxIdM);
		this.PanelForm.add(new JLabel("Date insertion : "));
		this.PanelForm.add(this.labelDateTS);
		this.PanelForm.add(new JLabel("Date Cours : "));
		this.PanelForm.add(this.txtDateC);
		this.PanelForm.add(new JLabel("Heure Debut : "));
		this.PanelForm.add(this.txtHeureDeb);
		this.PanelForm.add(new JLabel("Heure Fin: "));
		this.PanelForm.add(this.txtHeureFin);
		this.PanelForm.add(new JLabel("Duree : "));
		this.PanelForm.add(this.labelDuree);
		this.PanelForm.add(new JLabel("Salle : "));
		this.PanelForm.add(this.txtSalle);
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
		this.cbxFiltre.addItem("matiere");
		this.cbxFiltre.addItem("NomCl");
		this.cbxFiltre.addItem("NomPf");
		this.cbxFiltre.addItem("DateTS");
		this.cbxFiltre.addItem("DateC");
		this.cbxFiltre.addItem("heureDeb");
		this.cbxFiltre.addItem("heureFin");
		this.cbxFiltre.addItem("duree");
		this.cbxFiltre.addItem("salle");
		this.cbxFiltre.addItem("IdCl");
		this.cbxFiltre.addItem("IdPf");
		this.cbxFiltre.addItem("IdM");



		this.add(this.PanelFiltre);
		this.btFiltrer.addActionListener(this);
		
		this.PanelTable.setBackground(GREI.color1);
		this.PanelTable.setBounds(420, 60, 650, 620);
		this.PanelTable.setLayout(null);
		
		String entetes [] = {"matiere", "nom classe", "nom prof", "date creation","date cours", "heure debut","heure fin", "duree", "salle", "idCl", "idPf", "idM"};
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
					int idClasse = Integer.parseInt(uneTable.getValueAt(numLigne, 9).toString());
					int idAdmin = Integer.parseInt(uneTable.getValueAt(numLigne, 10).toString());
					int idMatiere = Integer.parseInt(uneTable.getValueAt(numLigne, 11).toString());
					String dateheure = uneTable.getValueAt(numLigne, 3).toString();

					//suppression dans la BDD
				
					C_Cours.supprimerCours(idAdmin, idClasse, idMatiere, dateheure);
					JOptionPane.showMessageDialog(null, C_Cours.supprimerCours(idAdmin, idClasse, idMatiere, dateheure));
					viderChamps();
					actualiser();
				}
				else if (e.getClickCount() == 1) {
					
					String idcl  = uneTable.getValueAt(numLigne, 9).toString();
					String idpf  = uneTable.getValueAt(numLigne, 10).toString();
					String idm  = uneTable.getValueAt(numLigne, 11).toString();
					String dateTS = uneTable.getValueAt(numLigne, 3).toString();
					String dateC = uneTable.getValueAt(numLigne, 4).toString();
					String heuredeb = uneTable.getValueAt(numLigne, 5).toString();
					String heurefin = uneTable.getValueAt(numLigne, 6).toString();
					String duree = uneTable.getValueAt(numLigne, 7).toString();
					String salle = uneTable.getValueAt(numLigne, 8).toString();


					cbxIdCl.setSelectedItem(whereisItemCBX(idcl,cbxIdCl));
					cbxIdPf.setSelectedItem(whereisItemCBX(idpf,cbxIdPf));
					cbxIdM.setSelectedItem(whereisItemCBX(idm,cbxIdM));
					labelDateTS.setText(dateTS);
					txtDateC.setText(dateC);
					txtHeureDeb.setText(heuredeb);
					txtHeureFin.setText(heurefin);
					labelDuree.setText(duree);
					txtSalle.setText(salle);
					
					cbxIdCl.setEnabled(false);
					cbxIdPf.setEnabled(false);
					cbxIdM.setEnabled(false);
					
					btEnregistrer.setText("Modifier");
				}
			}
		} );
		
	}
	public void remplirCBX(){
		//remplir les clients
		ArrayList<Classe> lesClasses = C_Classe.selectAllClasses();
		ArrayList<Professeur> lesProfs = C_Professeur.selectAllProfesseurs();
		ArrayList<Matiere> lesMatieres = C_Matiere.selectAllMatieres();
		//on vide le CBX
		this.cbxIdCl.removeAllItems();
		this.cbxIdPf.removeAllItems();
		this.cbxIdM.removeAllItems();
		//recuperer les classes
		for(Classe  uneClasse: lesClasses){
			this.cbxIdCl.addItem(uneClasse.getIdCl()+"-"+uneClasse.getNom());
		}
		for(Professeur  unProf: lesProfs){
			this.cbxIdPf.addItem(unProf.getIdU()+"-"+unProf.getNom()+" "+unProf.getPrenom());
		}
		for(Matiere  uneMatiere: lesMatieres){
			this.cbxIdM.addItem(uneMatiere.getIdM()+"-"+uneMatiere.getIntitule());
		}
	}
	public Object whereisItemCBX(String idTab, JComboBox<String> CBX) { //permet de trouver l'index d'un item CBX en fonction d'un ID int
		Object item = new Object();
		for (int i = 0; i < CBX.getItemCount() ; i++) {
			//on recupere l'id de l'item en explosant le texte et en convertissant le premier morceau en int
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
		this.btEnregistrer.setText("Enregistrer");
		this.cbxIdCl.setEnabled(true);
		this.cbxIdPf.setEnabled(true);
		this.cbxIdM.setEnabled(true);
		labelDateTS.setText("");
		txtDateC.setText("");
		txtHeureDeb.setText("");
		txtHeureFin.setText("");
		labelDuree.setText("");
		txtSalle.setText("");
	}
	
	public Object[][] obtenirDonnees(){
		ArrayList<VSql_Vue_Cours_Details> lesVues = C_VSql_Vue_Cours_Details.selectAllVues();
		Object[][] matrice = new Object [lesVues.size()][12];
		int i=0;
		for (VSql_Vue_Cours_Details uneVue : lesVues) {
			matrice[i][0] = uneVue.getMatiere();
			matrice[i][1] = uneVue.getNomCl();
			matrice[i][2] = uneVue.getNomPf();
			matrice[i][3] = uneVue.getDateTS();
			matrice[i][4] = uneVue.getDateC();
			matrice[i][5] = uneVue.getHeureDebut();
			matrice[i][6] = uneVue.getHeureFin();
			matrice[i][7] = uneVue.getDuree();
			matrice[i][8] = uneVue.getSalle();
			matrice[i][9] = uneVue.getIdCl();
			matrice[i][10] = uneVue.getIdPf();
			matrice[i][11] = uneVue.getIdM();
			
			i++;
		}
		return matrice;
	}
	public Object[][] obtenirDonnees(ArrayList<VSql_Vue_Cours_Details> lesVues){
		Object[][] matrice = new Object [lesVues.size()][12];
		int i=0;
		for (VSql_Vue_Cours_Details uneVue : lesVues) {
			matrice[i][0] = uneVue.getMatiere();
			matrice[i][1] = uneVue.getNomCl();
			matrice[i][2] = uneVue.getNomPf();
			matrice[i][3] = uneVue.getDateTS();
			matrice[i][4] = uneVue.getDateC();
			matrice[i][5] = uneVue.getHeureDebut();
			matrice[i][6] = uneVue.getHeureFin();
			matrice[i][7] = uneVue.getDuree();
			matrice[i][8] = uneVue.getSalle();
			matrice[i][9] = uneVue.getIdCl();
			matrice[i][10] = uneVue.getIdPf();
			matrice[i][11] = uneVue.getIdM();
			
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
			String datets = this.labelDateTS.getText();
			String datec = this.txtDateC.getText();
			String heuredeb = this.txtHeureDeb.getText();
			String heurefin = this.txtHeureFin.getText();
			String duree = this.labelDuree.getText();
			String salle = this.txtSalle.getText();
			
			//recuperation des id dans les CBX
			String chaine = this.cbxIdCl.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idcl = Integer.parseInt(tab[0]);
			
			chaine = this.cbxIdPf.getSelectedItem().toString();
			tab = chaine.split("-");
			int idpf = Integer.parseInt(tab[0]);
			
			chaine = this.cbxIdM.getSelectedItem().toString();
			tab = chaine.split("-");
			int idm = Integer.parseInt(tab[0]);
			
			//instancier un Cours
			
			Cours unCours = new Cours(idcl,idpf,idm,datets,datec,heuredeb,heurefin,duree,salle);
			
			//insertion client dans BDD
			JOptionPane.showMessageDialog(this, C_Cours.insertCours(unCours));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String datets = this.labelDateTS.getText();
			String datec = this.txtDateC.getText();
			String heuredeb = this.txtHeureDeb.getText();
			String heurefin = this.txtHeureFin.getText();
			String duree = this.labelDuree.getText();
			String salle = this.txtSalle.getText();
			
			//recuperation des id dans les CBX
			String chaine = this.cbxIdCl.getSelectedItem().toString();
			String tab[] = chaine.split("-");
			int idcl = Integer.parseInt(tab[0]);
			
			chaine = this.cbxIdPf.getSelectedItem().toString();
			tab = chaine.split("-");
			int idpf = Integer.parseInt(tab[0]);
			
			chaine = this.cbxIdM.getSelectedItem().toString();
			tab = chaine.split("-");
			int idm = Integer.parseInt(tab[0]);		
			
			//instanciation d'un cours
			Cours unCours = new Cours(idcl,idpf,idm,datets,datec,heuredeb,heurefin,duree,salle);
			
			//on realise la modif BDD
			JOptionPane.showMessageDialog(this, C_Cours.updateCours(unCours));
			this.viderChamps();
			//actualisation de l'affichage
			this.actualiser();
		}
		else if (e.getSource() == this.btFiltrer) {
			String attribut = this.cbxFiltre.getSelectedItem().toString();
			String mot = this.txtFiltre.getText();
			ArrayList<VSql_Vue_Cours_Details> lesVues = C_VSql_Vue_Cours_Details.selectSearch(attribut,mot);
			Object[][] matrice = this.obtenirDonnees(lesVues);
			//on actualise l'affichage
			
			this.unTableau.setDonnees(matrice);
		}
	}
}
