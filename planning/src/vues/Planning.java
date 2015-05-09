package vues;

import java.util.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

import metiers.Annee;
import metiers.Calendrier;
import modeles.CalendrierModele;
import modeles.CalendrierTableModele;
import modeles.SemainePanelModele;

/**
 * Vue Planning
 * @author Dylan
 */
public class Planning extends JFrame{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
    private SemainePanel semainePanel;
    private JMenuBar barreMenu;
	private JMenu fichier, edition;
	private JMenuItem sauvegarder, fermer, copier, coller;
    private JLabel semaineLabel, anneeLabel;
    private JTable table;
    private JPanel panSouth;
    private JPanel panCalendrier;
    private JPanel panCaractFormation;
    private JPanel panPrincipal;
    
	private Calendar calendar;
    private CalendrierTableModele tableModel;
    private CalendrierModele calendrierModele;
	private Calendrier calendrier;
	private Annee uneAnnee;

	private int anneeSuivante = 0;

	/**
	 * Constructeur
	 * @param calendrier
	 */
	public Planning(Calendrier calendrier) {
		this.calendrier = calendrier;

		setTitle("Gestion d'emploir du temps");
        this.setLocationRelativeTo(null);
        //Adapte la fen�tre � la taille de l'�cran
        this.setExtendedState(this.getExtendedState() | Planning.MAXIMIZED_BOTH);
        
        ajouterMenu();
        
    	uneAnnee = new Annee();
    	uneAnnee = calendrier.getUneAnnee();
    	
    	calendrierModele = new CalendrierModele();
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
    	anneeSuivante = uneAnnee.anneeChoisit(uneAnnee)+1;

        ajouterComposants();  
        getJoursOuvrable();
        
        this.setVisible(true);
    }
    
	/**
	 * M�thode qui permet d'ajouter la barre de menu � la vue
	 */
	private void ajouterMenu(){
        barreMenu = new JMenuBar();
        fichier = new JMenu("Fichier");
        barreMenu.add(fichier);
    	sauvegarder = new JMenuItem("Sauvegarder");
    	fichier.add(sauvegarder);
    	fermer = new JMenuItem("Fermer");
    	fichier.add(fermer);
    	edition = new JMenu("Edition");
    	barreMenu.add(edition);
    	copier = new JMenuItem("Copier");
    	edition.add(copier);
    	coller = new JMenuItem("Coller");
    	edition.add(coller);
        
    	setJMenuBar(barreMenu);
	}
	
	/**
	 * M�thode qui ajoute les composants graphiques � la vue
	 */
    private void ajouterComposants(){
    	
    	//Label indiquant l'ann�e de formation choisit
        anneeLabel = new JLabel("Ann�e : "+calendrier.getUneAnnee().getAnnee()+ "-"+(anneeSuivante));
        anneeLabel.setFont(new Font(null, Font.BOLD, 20));

        //Label qui affiche la semaine sur laquelle on est
        semaineLabel = new JLabel(getSemaineLabel());
        semaineLabel.setFont(new Font(null, Font.PLAIN, 18));
        semaineLabel.setVerticalAlignment(JLabel.CENTER);
        semaineLabel.setHorizontalAlignment(JLabel.CENTER);
        
        //Affichage du tableau, du calendrier
        tableModel = new CalendrierTableModele(calendar, calendrier);
        table = new JTable((TableModel)tableModel);
        table.getTableHeader().setBackground(Color.YELLOW);
        table.setRowHeight(200);
        
        //Emp�che la s�lection d'une colonne et d'une ligne enti�re
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
		
        JScrollPane tableScrollPane = new JScrollPane(table);
 
        //Panel du bas affichant les diff�rentes semaines	
        semainePanel = new SemainePanel(this, calendar, calendrier, calendrierModele);
        panSouth = new JPanel(new BorderLayout());
        panSouth.add(semainePanel, BorderLayout.SOUTH);
        
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
        panCaractFormation.add(anneeLabel, BorderLayout.WEST);
        
        //Panel principal contenant les autres panels
        panPrincipal = new JPanel(new BorderLayout());
        panPrincipal.add(panCaractFormation, BorderLayout.NORTH);
        panPrincipal.add(panCalendrier);
        this.add(panPrincipal);
    }

    /**
     * M�thode qui donnes des caract�ristiques � diff�rents composants selont la selection
     * @param selection : semaine s�lectionn�e
     */
    public void setSemaine(Calendar selection){
    	calendar.setTime( selection.getTime());
            semaineLabel.setText(getSemaineLabel());
        	tableModel.fireTableStructureChanged(); 
        	semainePanel.updateSelection();
        	getJoursOuvrable();
    }
    
    /**
     * M�thode qui retourne la semaine sur laquelle on se trouve
     * pour l'afficher dans le label semaineLabel
     * @return le libell� de la semaine 
     */
    private String getSemaineLabel(){
    	/* 
    	 * Cr�ation d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(this.calendar.getTime()); 
 
    	//On r�cup�re le num�ro de la semaine
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
	 * M�thode qui g�re les jours ouvr�s
	 */
	private void getJoursOuvrable(){
		JTableRender mcr = new JTableRender();

		if(calendrier.getSamediOuvrable() == true){
			table.getColumnModel().getColumn(5).setCellRenderer(mcr);
			tableModel.isCellEditable(0, 5);
		}

		if(calendrier.getDimancheOuvrable() == true){
			table.getColumnModel().getColumn(6).setCellRenderer(mcr);
			tableModel.isCellEditable(0, 6);
		}
	}
}