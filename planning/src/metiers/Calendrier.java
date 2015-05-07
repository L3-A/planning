package metiers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe m�tier Calendrier
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
	 * Constructeur par d�faut
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
	 * Accesseur en �criture
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
	 * Accesseur en �criture
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
	 * Accesseur en �criture
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
	 * Accesseur en �criture
	 * @param ferieOuvrable
	 */
	public void setFerieOuvrable(boolean ferieOuvrable){
		this.ferieOuvrable = ferieOuvrable;
	}
	
	/**
	 * M�thode qui retourne les jours f�ri�s
	 * @param annee
	 */
	public void joursFeries(Annee annee){
		int anneeChoisit = annee.anneeChoisit(annee);
		
		// On passe l'ann�e choisit au calendar
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, anneeChoisit);
	    
        // On constitue la liste des jours f�ri�s
        List<Calendar> joursFeries = new ArrayList<Calendar>();
        
        // On recherche les jours f�ri�s de l'ann�e pass�e en param�tre
        Calendar jourFerie = Calendar.getInstance();
        jourFerie = (Calendar) calendar.clone();
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 1);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 11);
        joursFeries.add((Calendar) jourFerie.clone());
        
        jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.DECEMBER, 25);
        joursFeries.add((Calendar) jourFerie.clone());
        
        //On ajoute 1 � l'ann�e choisit
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
	 * M�thode qui permet de construire le calendrier pour une ann�e scolaire
	 * @param uneAnnee
	 * @return calendar
	 */
	public Calendar construireCalendrier(Annee uneAnnee){
		int anneeChoisit;
		int anneeActuelle;
		int anneeDefinitif;
		Calendar calendar;
		
		//Convertion de l'ann�e choisit pour la cr�ation du calendrier en entier
		anneeChoisit = uneAnnee.anneeChoisit(uneAnnee);
		
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
}