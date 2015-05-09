package modeles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import metiers.Annee;

/**
 * Classe modèle CalendrierModèle
 * @author Dylan
 */
public class CalendrierModele {
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