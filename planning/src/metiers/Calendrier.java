package metiers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe métier Calendrier
 * @author Dylan
 */
public class Calendrier {
	/**
	 * Attribut boolean samediOuvrable
	 */
	private boolean samediOuvrable;
	
	/**
	 * Attribut boolean dimancheOuvrable
	 */
	private boolean dimancheOuvrable;
	
	/**
	 * Attribut boolean ferieOuvrable
	 */
	private boolean ferieOuvrable;
	
	/**
	 * Attribut Annee uneAnnee
	 */
	private Annee uneAnnee;
	
	/**
	 * Constructeur par défaut
	 */
	public Calendrier(){

	}

	/**
	 * Accesseur en lecture
	 * @return samediOuvrable
	 */
	public boolean getSamediOuvrable(){
		return samediOuvrable;
	}

	/**
	 * Accesseur en écriture
	 * @param samediOuvrable
	 */
	public void setSamediOuvrable(boolean samediOuvrable){
		this.samediOuvrable = samediOuvrable;
	}
	
	/**
	 * Accesseur en lecture
	 * @return dimancheOuvrable
	 */
	public boolean getDimancheOuvrable(){
		return dimancheOuvrable;
	}

	/**
	 * Accesseur en écriture
	 * @param dimancheOuvrable
	 */
	public void setDimancheOuvrable(boolean dimancheOuvrable){
		this.dimancheOuvrable = dimancheOuvrable;
	}
	
	/**
	 * Accesseur en lecture
	 * @return uneAnnee
	 */
	public Annee getUneAnnee(){
		return uneAnnee;
	}

	/**
	 * Accesseur en écriture
	 * @param uneAnnee
	 */
	public void setUneAnnee(Annee uneAnnee){
		this.uneAnnee = uneAnnee;
	}

	/**
	 * Accesseur en lecture
	 * @return ferieOuvrable
	 */
	public boolean getFerieOuvrable(){
		return ferieOuvrable;
	}

	/**
	 * Accesseur en écriture
	 * @param ferieOuvrable
	 */
	public void setFerieOuvrable(boolean ferieOuvrable){
		this.ferieOuvrable = ferieOuvrable;
	}
	
	/**
	 * Méthode qui retourne les jours fériés
	 * @param annee
	 */
	public void joursFeries(Annee annee){
		int anneeChoisit = annee.anneeChoisit(annee);
		
		// On passe l'année choisit au calendar
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, anneeChoisit);
	    
        // On constitue la liste des jours fériés
        List<Calendar> joursFeries = new ArrayList<Calendar>();
        
        // On recherche les jours fériés de l'année passée en paramètre
        Calendar jourFerie = Calendar.getInstance();
        jourFerie = (Calendar) calendar.clone();
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 1);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 11);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.DECEMBER, 25);
        joursFeries.add((Calendar) jourFerie.clone());
        
        //On ajoute 1 à l'année choisit
        jourFerie.add(Calendar.YEAR, 1);
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.JANUARY, 1);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.MAY, 1);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.MAY, 8);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.JULY, 14);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.AUGUST, 15);
        joursFeries.add((Calendar) jourFerie.clone());
    }
	
	/**
	 * Méthode qui permet de construire le calendrier pour une année scolaire
	 * @param uneAnnee
	 * @return calendar
	 */
	public Calendar construireCalendrier(Annee uneAnnee){
		int anneeChoisit;
		int anneeActuelle;
		int anneeDefinitif;
		Calendar calendar;
		
		//Convertion de l'année choisit pour la création du calendrier en entier
		anneeChoisit = uneAnnee.anneeChoisit(uneAnnee);
		
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
}