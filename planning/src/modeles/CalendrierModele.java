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
 * Classe mod�le CalendrierMod�le
 * @author Dylan
 */
public class CalendrierModele {
	/**
	 * Attribut Calendrier calendrier
	 */
	private Calendrier calendrier;
	
	/**
	 * Constructeur de la classe qui valorise l'attribut calendrier
	 * @param calendrier : param�tre de type Calendirer
	 */
	public CalendrierModele(Calendrier calendrier){
		this.calendrier = calendrier;
	}
	
	/**
	 * Constructeur par d�faut
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
	 * Accesseur en �criture
	 * @param calendrier : param�tre de type Calendrier
	 */
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}
	
	/**
	 * M�thode qui permet de construire le calendrier pour une ann�e scolaire
	 * @param uneAnnee : param�tre de type Annee
	 * @return calendar
	 */
	public Calendar construireCalendrier(Annee uneAnnee){
		int anneeChoisit;
		int anneeActuelle;
		int anneeDefinitif;
		Calendar calendar;
		AnneeModele anneeModele = new AnneeModele(uneAnnee);
		
		//Convertion de l'ann�e choisit pour la cr�ation du calendrier en entier
		
		anneeChoisit = anneeModele.anneeChoisit(uneAnnee);
		
		//R�cup�ration de l'ann�e actuelle
        calendar = Calendar.getInstance();
		anneeActuelle = calendar.get(Calendar.YEAR);
		
		//Comparaison de l'ann�e actuelle avec l'ann�e choisit
		//Si l'ann�e actuelle est avant l'ann�e choisit, on ajoute des ann�es � l'ann�e actuelle pour cr�er le calendrier
		//Si l'ann�e actuelle est apr�s l'ann�e choisit, on enl�ve des ann�es � l'ann�e actuelle pour cr�er le calendrier
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
        
        // on se positionne sur la premi�re semaine du mois
        calendar.set(Calendar.WEEK_OF_MONTH, 1); 
        
        // on se positionne sur le premier jour officiel
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
 
		return calendar;
	}
	
	/**
	 * M�thode qui permet d'enregistrer un fichier
	 * @param file : param�tre de type File
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
	 * M�thode qui permet d'ouvrir un fichier
	 * @param file : param�tre de type File
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
	 * M�thode qui retourne le num�ro de la s�ance lors de son placement sur le planning
	 * @param uneSeance : param�tre de type Seance
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
	 * M�thode qui d�cr�mente les s�ance dont le num�ro est sup�rieur � la s�ance supprim�e
	 * @param uneSeance : param�tre de type Seance
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
	 * M�thode qui r�cup�re le num�ro de la semaine sur laquelle on se trouve
	 * @param calendarPlanning : param�tre de type Calendar
	 * @return int : num�ro de la semaine
	 */
    public int getNumSemaine(Calendar calendarPlanning){
    	/* 
    	 * Cr�ation d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(calendarPlanning.getTime()); 
    	//On r�cup�re le num�ro de la semaine
    	int semaine = calendar.get(Calendar.WEEK_OF_YEAR); 
    	return semaine;
    }
    
    /**
     * M�thode qui retourne la semaine sur laquelle on se trouve
     * pour l'afficher dans le label semaineLabel
     * @param calendarPlanning : param�tre de type Calendar
     * @return le libell� de la semaine 
     */
    public String getSemaineLabel(Calendar calendarPlanning){
    	/* 
    	 * Cr�ation d'un nouveau calendar pour pas modifier le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	*/
    	Calendar calendar = Calendar.getInstance();
    	
    	/*
    	 * Recopie de la date actuellement mise dans le calendar 
    	 * qui sert pour savoir quelle semaine on affiche
    	 */
    	calendar.setTime(calendarPlanning.getTime()); 
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
}