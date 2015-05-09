package modeles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import metiers.Annee;

/**
 * Classe mod�le CalendrierMod�le
 * @author Dylan
 */
public class CalendrierModele {
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