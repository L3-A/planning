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
import metiers.Calendrier;
import metiers.Deserialiser;
import modeles.CalendrierModele;
import modeles.CalendrierTableModele;
import modeles.SemainePanelModele;

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
	private File file;
	private int anneeSuivante = 0;
	private int semaine;
	private boolean reussi;
	/**
	 * Constructeur
	 * @param calendrier
	 */
	public Planning(CalendrierModele calendrierModele, File file, List<metiers.Seance> seances) {
		this.calendrierModele = calendrierModele;
		this.file = file;
		this.seances = seances;
		seances = calendrierModele.getCalendrier().getSeances();
		
        if(calendrierModele.getCalendrier().getUneFormation() == null){
			uneFormation = new metiers.Formation();
			calendrierModele.getCalendrier().setUneFormation(uneFormation);
		}

		setTitle("Gestion d'emploir du temps");
        this.setLocationRelativeTo(null);
        //Adapte la fenêtre à la taille de l'écran
        this.setExtendedState(this.getExtendedState() | Planning.MAXIMIZED_BOTH);
        
        ajouterMenu();
        
    	uneAnnee = new Annee();
    	uneAnnee = calendrierModele.getCalendrier().getUneAnnee();
    	
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		

    	anneeSuivante = uneAnnee.anneeChoisit(uneAnnee)+1;

        semaine = getNumSemaine();
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
    	//Label indiquant l'année de formation choisit
    	caracFormationLabel = new JLabel("Année : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante));
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
        semaineLabel = new JLabel(getSemaineLabel());
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
 
        table.setDefaultRenderer(Color.class, new CouleurModuleRenderer());

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
            semaineLabel.setText(getSemaineLabel());
        	tableModel.fireTableStructureChanged(); 
        	semainePanel.updateSelection();
        	semaine = getNumSemaine();
        	tableModel.setSemaine(semaine);
        	centerTable();
        	getJoursOuvrable();
    }
    
    /**
     * Méthode qui retourne la semaine sur laquelle on se trouve
     * pour l'afficher dans le label semaineLabel
     * @return le libellé de la semaine 
     */
    private String getSemaineLabel(){
    	/* 
    	 * Création d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(this.calendar.getTime()); 
    	//On récupère le numéro de la semaine
    	int semaine = calendar.get(Calendar.WEEK_OF_YEAR); 
 
        //Le premier jour de la semaine
    	Date premierJour = calendar.getTime();
    	int moisPremierJour = calendar.get(Calendar.MONTH);
 
        //Le dernier jour de la semaine
    	calendar.add(Calendar.WEEK_OF_YEAR, 1);
    	calendar.add(Calendar.DAY_OF_WEEK, -1);
    	Date dernierJour = calendar.getTime();
 
    	int moisDernierJour = calendar.get(Calendar.MONTH);
 
    	return SemainePanelModele.getSemaineLabel(semaine, premierJour, dernierJour, moisPremierJour, moisDernierJour); 
    }

	/**
	 * Méthode qui gère les jours ouvrés
	 */
	private void getJoursOuvrable(){
		PlanningRender mcr = new PlanningRender();

		if(calendrierModele.getCalendrier().getSamediOuvrable() == true){
			table.getColumnModel().getColumn(5).setCellRenderer(mcr);
		}

		if(calendrierModele.getCalendrier().getDimancheOuvrable() == true){
			table.getColumnModel().getColumn(6).setCellRenderer(mcr);
		}
	}

	
	
    public int getNumSemaine(){
    	/* 
    	 * Création d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(this.calendar.getTime()); 
    	//On récupère le numéro de la semaine
    	int semaine = calendar.get(Calendar.WEEK_OF_YEAR); 
    	return semaine;
    }
    
	
	
	
	
	
	
	
	
	
	
	/**
	 * Méthode appelée lors d'un double clic sur une cellule
	 * Créer une classe module placer pour garder l'adresse de la cellule pour le module placé
	 */
	public void mouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			int indice_ligne=table.getSelectedRow();
			int indice_colonne=table.getSelectedColumn();

			if(indice_colonne == 5 && calendrierModele.getCalendrier().getSamediOuvrable() == true){
		        JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvré qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(indice_colonne == 6 && calendrierModele.getCalendrier().getDimancheOuvrable() == true){
		        JOptionPane.showMessageDialog(this,"Il s'agit d'un jours non ouvré qui ne peut pas recevoir de cours !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else if(tableModel.getValueAt(indice_ligne, indice_colonne) != null){
	        	int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le module ?", "Suppression d'un module", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

	        	if(option != JOptionPane.NO_OPTION && 
	        	   option != JOptionPane.CANCEL_OPTION && 
	        	   option != JOptionPane.CLOSED_OPTION){
	        		String valeur = tableModel.getValueAt(indice_ligne, indice_colonne);

	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		tableModel.removeSeance(valeur);
	        		updateIndicationSeance();
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        		
	        	}				
			}else{
				/* 
		    	 * Création d'un nouveau calendar pour pas modifier le calendar 
		    	 * qui sert pour savoir quelle semaine on affiche
		    	*/
		    	Calendar calendar = Calendar.getInstance();
		    	
		    	/*
		    	 * Recopie de la date actuellement mise dans le calendar 
		    	 * qui sert pour savoir quelle semaine on affiche
		    	 */
		    	calendar.setTime(this.calendar.getTime()); 
		    	//On récupère le numéro de la semaine
		    	int semaine = calendar.get(Calendar.WEEK_OF_YEAR); 
	
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
		if(source == nouveauVierge){
			ChoixAnnee choixAnnee = new ChoixAnnee();
			choixAnnee.setVisible(true);
			this.dispose();
		}
		
		if(source == caracFormation){
			Formation formation = new Formation(this, calendrierModele);
			formation.setVisible(true);
		}
		
		if(source == sauvegarder){
			this.saveFichier(file);
		}
		
		if(source == planningExistant){
			//Affichage de la chooser pour ouvrir d'un planning existant
			JFileChooser chooser = new JFileChooser();
		    chooser.setVisible(true);
		    chooser.setCurrentDirectory(new File("/Documents"));
		    int retrival = chooser.showOpenDialog(null);

		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            File fichier = chooser.getSelectedFile();
					Calendrier calendrier = new Calendrier();
					Deserialiser deserialise = new Deserialiser();
					deserialise.setFichier(fichier);
					deserialise.setCalendrier(calendrier);
					calendrier = deserialise.deserialiser();

					calendrierModele = new CalendrierModele(calendrier);
					
					List<metiers.Seance> seances = new ArrayList<metiers.Seance>();
					new Planning(calendrierModele, fichier, seances);

					this.setVisible(false);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		    this.dispose();
		}
	}
	
	public void updateNomFormation(String nom){
		float dureeTotalHeure = calendrierModele.dureeNbHeureFormation();
		float dureeTotalJours = calendrierModele.dureeJoursFormation(calendar);
		caracFormationLabel.setText("Année : "+calendrierModele.getCalendrier().getUneAnnee().getAnnee()+ "-"+(anneeSuivante)+"                 Formation : "+nom+"            Durée Jour : "+dureeTotalJours+" jours                    Durée Heure : "+dureeTotalHeure+" heures");
	}
	
	
	
	public void updateIndicationSeance(){
		for(metiers.Seance uneSeance : calendrierModele.getCalendrier().getSeances()){
			if(uneSeance.getNbSeanceModule() > uneSeance.getModule().getNbSeance()){
				informationSeance.setText("Le nombre de séance pour le module "+ uneSeance.getModule().getNom() +" est dépassé, veuillez le corriger !");
			}else{
				informationSeance.setText("");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications apportées ?", "Fermeture du planning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if(option != JOptionPane.NO_OPTION && 
        		option != JOptionPane.CANCEL_OPTION && 
        		option != JOptionPane.CLOSED_OPTION){
        	
        	this.saveFichier(file);
        	System.exit(0);
        }else {
        	System.exit(0);
        }
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
	
	public void confirmFichier(boolean reussi){
		if(reussi == true){
			JOptionPane.showMessageDialog(this,"Les modifications ont été enregistrées avec succès !", "Information", JOptionPane.INFORMATION_MESSAGE);						
		}else{
			JOptionPane.showMessageDialog(this,"Une erreur inconnue est survenu lors de l'enregistrement du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
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
	        	confirmFichier(reussi);
	        	this.file = file;
	        }
		}else{
			reussi = calendrierModele.saveFichier(file);
			confirmFichier(reussi);
		}
	}
	
	private void centerTable() {
		  DefaultTableCellRenderer custom = new DefaultTableCellRenderer(); 
		  custom.setHorizontalAlignment(JLabel.CENTER); // centre les données de ton tableau
		  for (int i=0 ; i < table.getColumnCount() ; i++) // centre chaque cellule de ton tableau
		   table.getColumnModel().getColumn(i).setCellRenderer(custom); 
		 }
}