package vues;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import metiers.Annee;
import modeles.AnneeModele;
import modeles.CalendrierModele;
import modeles.CalendrierTableModele;
import modeles.FormationModele;

/**
 * Vue Planning
 * @author Dylan
 */
public class Planning extends JFrame implements MouseListener, ActionListener, WindowListener{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    private SemainePanel semainePanel;
    private JMenuBar barreMenu;
	private JMenu fichier;
	private JMenu edition;
	private JMenu formation;
	private JMenuItem sauvegarder;
	private JMenuItem fermer;
	private JMenuItem copier;
	private JMenuItem coller;
	private JMenuItem nouveauVierge;
	private JMenuItem planningExistant;
	private JMenuItem caracFormation;
    private JLabel semaineLabel;
    private JLabel caracFormationLabel;
    private JLabel nomFormationLabel;
    private JLabel informationSeance;
    private JTable table;
    private JPanel panSouth;
    private JPanel panCalendrier;
    private JPanel panCaractFormation;
    private JPanel panPrincipal;
    private JFileChooser chooser;
	private Calendar calendar;
    private CalendrierTableModele tableModel;
    private CalendrierModele calendrierModele;
	private Annee uneAnnee;
	private metiers.Formation uneFormation;
	private List<metiers.Seance> seances;
	private FormationModele formationModele;
	private AnneeModele anneeModele;
	private File file;
	private int anneeSuivante = 0;
	private int semaine;
	private boolean reussi;
	/**
	 * Constructeur
	 * @param calendrierModele : param�tre de type CalendrierModele
	 * @param file : param�tre de type File
	 * @param seances : param�tre de type List Seance
	 */
	public Planning(CalendrierModele calendrierModele, File file, List<metiers.Seance> seances) {
		this.calendrierModele = calendrierModele;
		this.file = file;
		this.seances = seances;
		
		//R�cup�ration des s�ances du planning
		seances = calendrierModele.getCalendrier().getSeances();
		
		//Test pour savoir si le calendrier contient d�j� une formation
        if(calendrierModele.getCalendrier().getUneFormation() == null){
			uneFormation = new metiers.Formation();
			calendrierModele.getCalendrier().setUneFormation(uneFormation);
		}
        
        //Cr�ation du mod�le formationa avec la formation en param�tre
		formationModele = new FormationModele(calendrierModele.getCalendrier().getUneFormation());

		setTitle("Gestion d'emploir du temps");
        this.setLocationRelativeTo(null);
       
        //Adapte la fen�tre � la taille de l'�cran
        this.setExtendedState(this.getExtendedState() | Planning.MAXIMIZED_BOTH);
        
        ajouterMenu();
        
        //Cr�ation du calendrier
    	uneAnnee = new Annee();
    	uneAnnee = calendrierModele.getCalendrier().getUneAnnee();
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		//R�cup�ration de l'ann�e suivante
		anneeModele = new AnneeModele(uneAnnee);
    	anneeSuivante = anneeModele.anneeChoisit(uneAnnee)+1;

    	//R�cup�ration du num�ro de la semaine actuelle
        semaine = calendrierModele.getNumSemaine(calendar);
        
        //Cr�ation du tableModele
        tableModel = new CalendrierTableModele(calendar, calendrierModele.getCalendrier(), seances);
        tableModel.setSemaine(semaine);
        
        ajouterComposants();  
        
        getJoursOuvrable();
        
        this.setVisible(true);
        this.addWindowListener(this);
    }
    
	/**
	 * M�thode qui permet d'ajouter la barre de menu � la vue
	 */
	private void ajouterMenu(){
        barreMenu = new JMenuBar();
        fichier = new JMenu("Fichier");
        barreMenu.add(fichier);
        nouveauVierge = new JMenuItem("Nouveau planning vierge");
        fichier.add(nouveauVierge);
        nouveauVierge.addActionListener(this);
        planningExistant = new JMenuItem("Ouvrir un planning existant");
        fichier.add(planningExistant);
        planningExistant.addActionListener(this);
    	sauvegarder = new JMenuItem("Sauvegarder le planning");
    	fichier.add(sauvegarder);
    	sauvegarder.addActionListener(this);
    	fermer = new JMenuItem("Fermer");
    	fichier.add(fermer);
    	edition = new JMenu("Edition");
    	barreMenu.add(edition);
    	copier = new JMenuItem("Copier");
    	edition.add(copier);
    	coller = new JMenuItem("Coller");
    	edition.add(coller);
    	formation = new JMenu("Formation");
    	barreMenu.add(formation);
    	caracFormation = new JMenuItem("Caract�ristiques d'une formation");
    	formation.add(caracFormation);
    	caracFormation.addActionListener(this);

    	setJMenuBar(barreMenu);
	}
	
	/**
	 * M�thode qui ajoute les composants graphiques � la vue
	 */
    private void ajouterComposants(){
    	//Label indiquant les caract�ristiques de la formations
    	caracFormationLabel = new JLabel();
        caracFormationLabel.setFont(new Font(null, Font.BOLD, 20));
        if(calendrierModele.getCalendrier().getUneFormation().getNom() == null){
    		caracFormationLabel.setText("Ann�e : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante));
        }else{
        	updateNomFormation(calendrierModele.getCalendrier().getUneFormation().getNom());
        }
    
    	//Label indiquant les erreurs pour les s�ances
        informationSeance = new JLabel();
        informationSeance.setFont(new Font(null, Font.BOLD, 20));
        informationSeance.setForeground(Color.RED);
        updateIndicationSeance();
        
        //Label contenant le nom de la formation
        nomFormationLabel = new JLabel();
        nomFormationLabel.setFont(new Font(null, Font.BOLD, 20));

        //Label qui affiche la semaine sur laquelle on est
        semaineLabel = new JLabel(calendrierModele.getSemaineLabel(calendar));
        semaineLabel.setFont(new Font(null, Font.PLAIN, 18));
        semaineLabel.setVerticalAlignment(JLabel.CENTER);
        semaineLabel.setHorizontalAlignment(JLabel.CENTER);

        //Affichage du tableau, du calendrier
        table = new JTable((tableModel));
        table.getTableHeader().setBackground(Color.YELLOW);
        table.setRowHeight(200);
        
        //Emp�che la s�lection d'une colonne et d'une ligne enti�re
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        
        //Emp�che le d�placement des colonne
        table.getTableHeader().setReorderingAllowed(false); 
        
        //Emp�che de changer la taille des colonne
        table.getTableHeader().setResizingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(table);
 
        //Appel de la m�thode qui permet de centrer les valeurs d'une cellule
        centerTable();
        
        //Panel du bas affichant les diff�rentes semaines	
        semainePanel = new SemainePanel(this, calendar, calendrierModele);
        panSouth = new JPanel(new BorderLayout());
        panSouth.add(semainePanel, BorderLayout.SOUTH);
        panSouth.add(informationSeance, BorderLayout.CENTER);

        /*
         * Panel contenant le libell� de la semaine et le tableau de la semaine choisit
         * le panel contenant la liste des semaine
         */
        panCalendrier = new JPanel(new BorderLayout());
        panCalendrier.setPreferredSize(new Dimension(1400, 600));
        panCalendrier.add(semaineLabel, BorderLayout.NORTH);
        panCalendrier.add(tableScrollPane, BorderLayout.CENTER);
        panCalendrier.add(panSouth, BorderLayout.SOUTH);

        //Panel contenant le libell� de l'ann�e de foramation choisit
        panCaractFormation = new JPanel(new BorderLayout());
        panCaractFormation.add(caracFormationLabel, BorderLayout.WEST);

        //Panel principal contenant les autres panels
        panPrincipal = new JPanel(new BorderLayout());
        panPrincipal.add(panCaractFormation, BorderLayout.NORTH);
        panPrincipal.add(panCalendrier, BorderLayout.CENTER);

        this.add(panPrincipal);
        table.addMouseListener(this);
    }

    /**
     * M�thode qui donnes des caract�ristiques � diff�rents composants selon la selection
     * @param selection : semaine s�lectionn�e
     */
    public void setSemaine(Calendar selection){
    	calendar.setTime( selection.getTime());
    	semaineLabel.setText(calendrierModele.getSemaineLabel(calendar));
    	tableModel.fireTableStructureChanged(); 
    	semainePanel.updateSelection();
    	semaine = calendrierModele.getNumSemaine(calendar);
    	tableModel.setSemaine(semaine);
    	centerTable();
    	getJoursOuvrable();
    }
    
	/**
	 * M�thode qui g�re les jours ouvr�s
	 */
	private void getJoursOuvrable(){
		PlanningRender mcr = new PlanningRender();

		//Si samedit est non ouvr�, on grise la colonne
		if(calendrierModele.getCalendrier().getSamediOuvrable() == true){
			table.getColumnModel().getColumn(5).setCellRenderer(mcr);
		}

		//Si dimanche est non ouvr�, on grise la colonne
		if(calendrierModele.getCalendrier().getDimancheOuvrable() == true){
			table.getColumnModel().getColumn(6).setCellRenderer(mcr);
		}
	}
	
	/**
	 * M�thode appel�e lors d'un double clic sur une cellule
	 * Cr�er une classe module placer pour garder l'adresse de la cellule pour le module plac�
	 */
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			//R�cup�ration de l'indice ligne et colonne de la cellule cliqu�e
			int indice_ligne=table.getSelectedRow();
			int indice_colonne=table.getSelectedColumn();

			if(indice_colonne == 5 && calendrierModele.getCalendrier().getSamediOuvrable() == true){
				//Si l'indice colonne est �gal � 5 et que c'est un jour non ouvr�, on affiche un message
				JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvr� qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(indice_colonne == 6 && calendrierModele.getCalendrier().getDimancheOuvrable() == true){
				//Si l'indice colonne est �gal � 6 et que c'est un jour non ouvr�, on affiche un message
				JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvr� qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(tableModel.getValueAt(indice_ligne, indice_colonne) != null){
				//Si la cellule comporte d�j� une s�ance, on demande � l'utilisateur s'il veut la supprimer
				int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le module ?", "Suppression d'un module", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	        	if(option != JOptionPane.NO_OPTION && 
	        	   option != JOptionPane.CANCEL_OPTION && 
	        	   option != JOptionPane.CLOSED_OPTION){
	        		String valeur = tableModel.getValueAt(indice_ligne, indice_colonne);

	        		tableModel.removeSeance(valeur);
	        		updateIndicationSeance();
	        	}				
			}else{
		    	semaine = calendrierModele.getNumSemaine(calendar);
		    	//Ouverture de la fen�tre pour ins�rer une s�ance
				Seance seance = new Seance(this, calendrierModele.getCalendrier().getUneFormation().getModules(), indice_ligne, indice_colonne, semaine, tableModel, calendrierModele);
				seance.setVisible(true);
			}
		}
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent event) {}
	public void mouseReleased(MouseEvent arg0) {}

	/**
	 * M�thode appel�e lors d'un clic sur un item du menu
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		//Cr�ation d'un nouveau calendrier vierge
		if(source == nouveauVierge){
			ChoixAnnee choixAnnee = new ChoixAnnee();
			choixAnnee.setVisible(true);
			this.dispose();
		}
		
		//Cr�ation des caract�ristiques de la formation
		if(source == caracFormation){
			Formation formation = new Formation(this, calendrierModele);
			formation.setVisible(true);
		}
		
		//Sauvegarder le planning avec les modifications apport�es 
		if(source == sauvegarder){
			this.saveFichier(file);
		}
		
		//Ouverture d'un planning existant
		if(source == planningExistant){
			//Affichage de la chooser pour ouvrir d'un planning existant
			JFileChooser chooser = new JFileChooser();
		    chooser.setVisible(true);
		    chooser.setCurrentDirectory(new File("/Documents"));
		    int retrival = chooser.showOpenDialog(null);

		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            File fichier = chooser.getSelectedFile();
					calendrierModele = new CalendrierModele();
					calendrierModele.setCalendrier(calendrierModele.openFichier(fichier));
					List<metiers.Seance> seances = new ArrayList<metiers.Seance>();
					new Planning(calendrierModele, fichier, seances);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		    this.dispose();
		}
	}
	
	/**
	 * M�thode qui modifie le libell� indiquant les caract�ristiques de la formation
	 * @param nom : param�tre de type String
	 */
	public void updateNomFormation(String nom){
		//R�cup�ration de la dur�e de la formation en nombre d'heure
		float dureeTotalHeure = formationModele.dureeNbHeureFormation();
		//R�cup�ration de la dur�e de la formation en nombre de jours
		float dureeTotalJours = formationModele.dureeJoursFormation(calendar, calendrierModele.getCalendrier());
		
		caracFormationLabel.setText("Ann�e : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante)+"                 Formation : "+nom+"            Dur�e Jour : "+dureeTotalJours+" jours                    Dur�e Heure : "+dureeTotalHeure+" heures");
	}
	
	/**
	 * M�thode qui permet d'indiquer si un module � plus de s�ance que pr�vu
	 */
	public void updateIndicationSeance(){
		for(metiers.Seance uneSeance : calendrierModele.getCalendrier().getSeances()){
			if(uneSeance.getRangSeanceModule() > uneSeance.getModule().getNbSeance()){
				informationSeance.setText("Le nombre de s�ance pour le module "+ uneSeance.getModule().getNom() +" est d�pass�, veuillez le corriger !");
			}else{
				informationSeance.setText("");
			}
		}
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	
	/**
	 * M�thode appel�e lors de la fermure de la fen�tre
	 */
	public void windowClosing(WindowEvent arg0) {
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications apport�es ?", "Fermeture du planning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        //Si l'utilisateur clic sur OUI on enregistre le fichier
        if(option != JOptionPane.NO_OPTION && 
        		option != JOptionPane.CANCEL_OPTION && 
        		option != JOptionPane.CLOSED_OPTION){
        	
        	this.saveFichier(file);
        	System.exit(0);
        }else {
        	//On quitte la ferme et on ferme le programme
        	System.exit(0);
        }
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	/**
	 * M�thode appel�e qui permet de confirmer si le fichier a bien �t� enregistr� ou pas
	 * @param reussi : param�tre de type boolean
	 * @param file : param�tre de type File
	 */
	public void confirmFichier(boolean reussi, File file){
		if(reussi == true){
			JOptionPane.showMessageDialog(this,"Le fichier a �t� enregistr� dans " +file+ " !", "Information", JOptionPane.INFORMATION_MESSAGE);						
		}else{
			JOptionPane.showMessageDialog(this,"Une erreur inconnue est survenu lors de l'enregistrement du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * M�thode appel�e pour enregistrer le fichier
	 * @param file : param�tre de type File
	 */
	public void saveFichier(File file){
		if(file == null){
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("/Documents"));
	        File filePropose = new File("Planning_"+uneAnnee.getAnnee()+"_"+anneeSuivante+".dat");
	        chooser.setSelectedFile(filePropose);
	        
	        int retrival = chooser.showSaveDialog(null);
	        if (retrival == JFileChooser.APPROVE_OPTION) {
	        	file = (chooser.getSelectedFile());
	        	reussi = calendrierModele.saveFichier(file);
	        	confirmFichier(reussi, file);
	        	this.file = file;
	        }
		}else{
			reussi = calendrierModele.saveFichier(file);
			confirmFichier(reussi, file);
		}
	}
	
	/**
	 * M�thode qui permet de centrer les valeurs d'une cellule
	 */
	private void centerTable() {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); // centre les donn�es de ton tableau
		for (int i=0 ; i < table.getColumnCount() ; i++) // centre chaque cellule de ton tableau
			table.getColumnModel().getColumn(i).setCellRenderer(custom); 
	}

	/**
	 * Accesseur en lecture
	 * @return seances
	 */
	public List<metiers.Seance> getSeances() {
		return seances;
	}

	/**
	 * Accesseur en �criture
	 * @param seances : param�tre de type List Seance
	 */
	public void setSeances(List<metiers.Seance> seances) {
		this.seances = seances;
	}
}