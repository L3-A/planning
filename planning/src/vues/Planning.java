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
	 * @param calendrierModele : paramètre de type CalendrierModele
	 * @param file : paramètre de type File
	 * @param seances : paramètre de type List Seance
	 */
	public Planning(CalendrierModele calendrierModele, File file, List<metiers.Seance> seances) {
		this.calendrierModele = calendrierModele;
		this.file = file;
		this.seances = seances;
		
		//Récupération des séances du planning
		seances = calendrierModele.getCalendrier().getSeances();
		
		//Test pour savoir si le calendrier contient déjà une formation
        if(calendrierModele.getCalendrier().getUneFormation() == null){
			uneFormation = new metiers.Formation();
			calendrierModele.getCalendrier().setUneFormation(uneFormation);
		}
        
        //Création du modèle formationa avec la formation en paramètre
		formationModele = new FormationModele(calendrierModele.getCalendrier().getUneFormation());

		setTitle("Gestion d'emploir du temps");
        this.setLocationRelativeTo(null);
       
        //Adapte la fenêtre à la taille de l'écran
        this.setExtendedState(this.getExtendedState() | Planning.MAXIMIZED_BOTH);
        
        ajouterMenu();
        
        //Création du calendrier
    	uneAnnee = new Annee();
    	uneAnnee = calendrierModele.getCalendrier().getUneAnnee();
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		//Récupération de l'année suivante
		anneeModele = new AnneeModele(uneAnnee);
    	anneeSuivante = anneeModele.anneeChoisit(uneAnnee)+1;

    	//Récupération du numéro de la semaine actuelle
        semaine = calendrierModele.getNumSemaine(calendar);
        
        //Création du tableModele
        tableModel = new CalendrierTableModele(calendar, calendrierModele.getCalendrier(), seances);
        tableModel.setSemaine(semaine);
        
        ajouterComposants();  
        
        getJoursOuvrable();
        
        this.setVisible(true);
        this.addWindowListener(this);
    }
    
	/**
	 * Méthode qui permet d'ajouter la barre de menu à la vue
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
    	caracFormation = new JMenuItem("Caractéristiques d'une formation");
    	formation.add(caracFormation);
    	caracFormation.addActionListener(this);

    	setJMenuBar(barreMenu);
	}
	
	/**
	 * Méthode qui ajoute les composants graphiques à la vue
	 */
    private void ajouterComposants(){
    	//Label indiquant les caractéristiques de la formations
    	caracFormationLabel = new JLabel();
        caracFormationLabel.setFont(new Font(null, Font.BOLD, 20));
        if(calendrierModele.getCalendrier().getUneFormation().getNom() == null){
    		caracFormationLabel.setText("Année : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante));
        }else{
        	updateNomFormation(calendrierModele.getCalendrier().getUneFormation().getNom());
        }
    
    	//Label indiquant les erreurs pour les séances
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
        
        //Empêche la sélection d'une colonne et d'une ligne entière
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        
        //Empêche le déplacement des colonne
        table.getTableHeader().setReorderingAllowed(false); 
        
        //Empêche de changer la taille des colonne
        table.getTableHeader().setResizingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(table);
 
        //Appel de la méthode qui permet de centrer les valeurs d'une cellule
        centerTable();
        
        //Panel du bas affichant les différentes semaines	
        semainePanel = new SemainePanel(this, calendar, calendrierModele);
        panSouth = new JPanel(new BorderLayout());
        panSouth.add(semainePanel, BorderLayout.SOUTH);
        panSouth.add(informationSeance, BorderLayout.CENTER);

        /*
         * Panel contenant le libellé de la semaine et le tableau de la semaine choisit
         * le panel contenant la liste des semaine
         */
        panCalendrier = new JPanel(new BorderLayout());
        panCalendrier.setPreferredSize(new Dimension(1400, 600));
        panCalendrier.add(semaineLabel, BorderLayout.NORTH);
        panCalendrier.add(tableScrollPane, BorderLayout.CENTER);
        panCalendrier.add(panSouth, BorderLayout.SOUTH);

        //Panel contenant le libellé de l'année de foramation choisit
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
     * Méthode qui donnes des caractéristiques à différents composants selon la selection
     * @param selection : semaine sélectionnée
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
	 * Méthode qui gère les jours ouvrés
	 */
	private void getJoursOuvrable(){
		PlanningRender mcr = new PlanningRender();

		//Si samedit est non ouvré, on grise la colonne
		if(calendrierModele.getCalendrier().getSamediOuvrable() == true){
			table.getColumnModel().getColumn(5).setCellRenderer(mcr);
		}

		//Si dimanche est non ouvré, on grise la colonne
		if(calendrierModele.getCalendrier().getDimancheOuvrable() == true){
			table.getColumnModel().getColumn(6).setCellRenderer(mcr);
		}
	}
	
	/**
	 * Méthode appelée lors d'un double clic sur une cellule
	 * Créer une classe module placer pour garder l'adresse de la cellule pour le module placé
	 */
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			//Récupération de l'indice ligne et colonne de la cellule cliquée
			int indice_ligne=table.getSelectedRow();
			int indice_colonne=table.getSelectedColumn();

			if(indice_colonne == 5 && calendrierModele.getCalendrier().getSamediOuvrable() == true){
				//Si l'indice colonne est égal à 5 et que c'est un jour non ouvré, on affiche un message
				JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvré qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(indice_colonne == 6 && calendrierModele.getCalendrier().getDimancheOuvrable() == true){
				//Si l'indice colonne est égal à 6 et que c'est un jour non ouvré, on affiche un message
				JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvré qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(tableModel.getValueAt(indice_ligne, indice_colonne) != null){
				//Si la cellule comporte déjà une séance, on demande à l'utilisateur s'il veut la supprimer
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
		    	//Ouverture de la fenêtre pour insérer une séance
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
	 * Méthode appelée lors d'un clic sur un item du menu
	 */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		//Création d'un nouveau calendrier vierge
		if(source == nouveauVierge){
			ChoixAnnee choixAnnee = new ChoixAnnee();
			choixAnnee.setVisible(true);
			this.dispose();
		}
		
		//Création des caractéristiques de la formation
		if(source == caracFormation){
			Formation formation = new Formation(this, calendrierModele);
			formation.setVisible(true);
		}
		
		//Sauvegarder le planning avec les modifications apportées 
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
	 * Méthode qui modifie le libellé indiquant les caractéristiques de la formation
	 * @param nom : paramètre de type String
	 */
	public void updateNomFormation(String nom){
		//Récupération de la durée de la formation en nombre d'heure
		float dureeTotalHeure = formationModele.dureeNbHeureFormation();
		//Récupération de la durée de la formation en nombre de jours
		float dureeTotalJours = formationModele.dureeJoursFormation(calendar, calendrierModele.getCalendrier());
		
		caracFormationLabel.setText("Année : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante)+"                 Formation : "+nom+"            Durée Jour : "+dureeTotalJours+" jours                    Durée Heure : "+dureeTotalHeure+" heures");
	}
	
	/**
	 * Méthode qui permet d'indiquer si un module à plus de séance que prévu
	 */
	public void updateIndicationSeance(){
		for(metiers.Seance uneSeance : calendrierModele.getCalendrier().getSeances()){
			if(uneSeance.getRangSeanceModule() > uneSeance.getModule().getNbSeance()){
				informationSeance.setText("Le nombre de séance pour le module "+ uneSeance.getModule().getNom() +" est dépassé, veuillez le corriger !");
			}else{
				informationSeance.setText("");
			}
		}
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	
	/**
	 * Méthode appelée lors de la fermure de la fenêtre
	 */
	public void windowClosing(WindowEvent arg0) {
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications apportées ?", "Fermeture du planning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        
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
	 * Méthode appelée qui permet de confirmer si le fichier a bien été enregistré ou pas
	 * @param reussi : paramètre de type boolean
	 * @param file : paramètre de type File
	 */
	public void confirmFichier(boolean reussi, File file){
		if(reussi == true){
			JOptionPane.showMessageDialog(this,"Le fichier a été enregistré dans " +file+ " !", "Information", JOptionPane.INFORMATION_MESSAGE);						
		}else{
			JOptionPane.showMessageDialog(this,"Une erreur inconnue est survenu lors de l'enregistrement du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Méthode appelée pour enregistrer le fichier
	 * @param file : paramètre de type File
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
	 * Méthode qui permet de centrer les valeurs d'une cellule
	 */
	private void centerTable() {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
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
	 * Accesseur en écriture
	 * @param seances : paramètre de type List Seance
	 */
	public void setSeances(List<metiers.Seance> seances) {
		this.seances = seances;
	}
}