package modeles;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import metiers.Annee;
import metiers.Calendrier;
import metiers.Deserialiser;
import metiers.Seance;
import metiers.Serialiser;

/**
 * Classe modèle CalendrierModèle
 * @author Dylan
 */
public class CalendrierModele {
	/**
	 * Attribut Calendrier calendrier
	 */
	private Calendrier calendrier;
	
	/**
	 * Constructeur de la classe qui valorise l'attribut calendrier
	 * @param calendrier : paramètre de type Calendirer
	 */
	public CalendrierModele(Calendrier calendrier){
		this.calendrier = calendrier;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public CalendrierModele() {

	}

	/**
	 * Accesseur en lecture
	 * @return calendrier
	 */
	public Calendrier getCalendrier() {
		return calendrier;
	}

	/**
	 * Accesseur en écriture
	 * @param calendrier : paramètre de type Calendrier
	 */
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	
	/**
	 * Méthode qui permet de construire le calendrier pour une année scolaire
	 * @param uneAnnee : paramètre de type Annee
	 * @return calendar
	 */
	public Calendar construireCalendrier(Annee uneAnnee){
		int anneeChoisit;
		int anneeActuelle;
		int anneeDefinitif;
		Calendar calendar;
		AnneeModele anneeModele = new AnneeModele(uneAnnee);
		
		//Convertion de l'année choisit pour la création du calendrier en entier
		
		anneeChoisit = anneeModele.anneeChoisit(uneAnnee);
		
		//Récupération de l'année actuelle
        calendar = Calendar.getInstance();
		anneeActuelle = calendar.get(Calendar.YEAR);
		
		//Comparaison de l'année actuelle avec l'année choisit
		//Si l'année actuelle est avant l'année choisit, on ajoute des années à l'année actuelle pour créer le calendrier
		//Si l'année actuelle est après l'année choisit, on enlève des années à l'année actuelle pour créer le calendrier
		if(anneeActuelle < anneeChoisit){
			anneeDefinitif = anneeChoisit - anneeActuelle; 
			calendar.add(Calendar.YEAR, +anneeDefinitif);
		}else if(anneeActuelle > anneeChoisit){
			anneeDefinitif = anneeActuelle - anneeChoisit;
			calendar.add(Calendar.YEAR, -anneeDefinitif);
		}else{
			calendar.add(Calendar.YEAR, 0);
		}
		
		// on se positionne sur le mois de septembre    	   
        calendar.set(Calendar.MONTH,Calendar.SEPTEMBER); 
        
        // on se positionne sur la première semaine du mois
        calendar.set(Calendar.WEEK_OF_MONTH, 1); 
        
        // on se positionne sur le premier jour officiel
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
 
		return calendar;
	}
	
	/**
	 * Méthode qui permet d'enregistrer un fichier
	 * @param file : paramètre de type File
	 * @return reussi : true si ok sinon false
	 */
	public boolean saveFichier(File file){
		boolean reussi;
		Serialiser serialise ;
		serialise = new Serialiser();
		serialise.setFichier(file);
		serialise.setCalendrier(calendrier);
		reussi = serialise.serialiser();
		return reussi;
	}

	/**
	 * Méthode qui permet d'ouvrir un fichier
	 * @param file : paramètre de type File
	 * @return calendrier 
	 */
	public Calendrier openFichier(File file){
		Calendrier calendrier = new Calendrier();
		Deserialiser deserialise = new Deserialiser();
		deserialise.setFichier(file);
		deserialise.setCalendrier(calendrier);
		calendrier = deserialise.deserialiser();
		return calendrier;
	}
	
	/**
	 * Méthode qui retourne le numéro de la séance lors de son placement sur le planning
	 * @param uneSeance : paramètre de type Seance
	 * @return nbSeance
	 */
	public int nbSeanceAdd(Seance uneSeance){
		int nbSeance = 1;
		for(Seance seance : calendrier.getSeances()){
			if(seance.getModule().getNom().equals(uneSeance.getModule().getNom())){
				nbSeance = nbSeance + 1;
			}
		}
		return nbSeance;
	}
	
	/**
	 * Méthode qui décrémente les séance dont le numéro est supérieur à la séance supprimée
	 * @param uneSeance : paramètre de type Seance
	 */
	public void nbSeanceSup(Seance uneSeance){
		int nbSeance = 0;
		for(Seance seance : calendrier.getSeances()){
			if(seance.getRangSeanceModule() > uneSeance.getRangSeanceModule()){
				nbSeance = seance.getRangSeanceModule() - 1;
				seance.setRangSeanceModule(nbSeance);			
			}
		}
	}
	
	/**
	 * Méthode qui récupère le numéro de la semaine sur laquelle on se trouve
	 * @param calendarPlanning : paramètre de type Calendar
	 * @return int : numéro de la semaine
	 */
    public int getNumSemaine(Calendar calendarPlanning){
    	/* 
    	 * Création d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(calendarPlanning.getTime()); 
    	//On récupère le numéro de la semaine
    	int semaine = calendar.get(Calendar.WEEK_OF_YEAR); 
    	return semaine;
    }
    
    /**
     * Méthode qui retourne la semaine sur laquelle on se trouve
     * pour l'afficher dans le label semaineLabel
     * @param calendarPlanning : paramètre de type Calendar
     * @return le libellé de la semaine 
     */
    public String getSemaineLabel(Calendar calendarPlanning){
    	/* 
    	 * Création d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(calendarPlanning.getTime()); 
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
}