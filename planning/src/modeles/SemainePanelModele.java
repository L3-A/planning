package modeles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import metiers.Annee;
import metiers.Calendrier;

/**
 * @author Dylan
 * Classe SemainePanelModele
 */
public class SemainePanelModele {
	/**
	 * Attribut Calendrier calendrier
	 */
	private Calendrier calendrier;
	
	/**
	 * Attribut CalendrierModele calendrierModele
	 */
	private CalendrierModele calendrierModele;
	
	/**
	 * Constructeur
	 */
	public SemainePanelModele(Calendrier calendrier, CalendrierModele calendrierModele){
		this.calendrier = calendrier;
		this.calendrierModele = calendrierModele;
	}
	
	/**
	 * Méthode qui copie le calendar
	 * @param calendar
	 * @return calendar
	 */
	public Calendar copieCalendar(Calendar calendar){
		Calendar copy = Calendar.getInstance();
		copy.setTime(calendar.getTime());
		return copy;
 	}
 
 
	/**
	 * Méthode qui conmpte le nombre de semaine entre la première et la dernière
	 * @param premiereSemaine
	 * @param derniereSemaine
	 * @return le nombre de semaines
	 */
	public int compteSemaines(Calendar premiereSemaine, Calendar derniereSemaine){
        Calendar calendar = copieCalendar(premiereSemaine);
        int count=0;
        //Tant qu'on a pas atteint la dernière semaine
		while(calendar.get(Calendar.WEEK_OF_YEAR)!=derniereSemaine.get(Calendar.WEEK_OF_YEAR)){
			count++; // on compte une semaine de plus
			calendar.add(Calendar.WEEK_OF_YEAR, 1); //On avance d'une semaine
		}
		//Ajoute 1 pour compter la dernière semaine (qu'on a pas compter dans la boucle)
		return count + 1; 
	}
 
	/**
	 * Méthode qui retourne la première semaine d'un mois
	 * @param mois
	 * @return calendar
	 */
	public Calendar getPremiereSemaine(int mois){
    	Annee uneAnnee = new Annee();
    	uneAnnee = calendrier.getUneAnnee();
        
    	Calendar calendar = calendrierModele.construireCalendrier(uneAnnee);
		
    	//On se positionne sur le mois demandé 
        calendar.set(Calendar.MONTH, mois); 
        //On se positionne sur la première semaine du mois
        calendar.set(Calendar.WEEK_OF_MONTH, 1); 
        //On se positionne sur le premier jour officiel
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
 
        return calendar;
	}
 
	/**
	 * Méthode qui retourne la dernière semaine d'un moi
	 * @param mois
	 * @return calendar
	 */
	public Calendar getDerniereSemaine(int mois){
		//On se place sur la première semaine du mois
		Calendar calendar = getPremiereSemaine(mois);
		//On avance d'un an
		calendar.add(Calendar.YEAR, 1);
		//On recule d'une semaine
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		return calendar;
	}

	/**
	 * Méthode qui retourne le libellé d'une semaine
	 * @param semaine
	 * @return libellé d'une semaine
	 */
	public String getSemaineLabel(Calendar semaine) {
 
		Calendar calendar = copieCalendar(semaine);
 
		//Le premier jour de la semaine
		Date premierJour = calendar.getTime();
		int monthPremierJour = calendar.get(Calendar.MONTH);
 
		//Le dernier jour cette semaine
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		Date dernierJour = calendar.getTime();
 
		int monthDernierJour = calendar.get(Calendar.MONTH);
 
        return getSemaineLabel(semaine.get(Calendar.WEEK_OF_YEAR), premierJour, dernierJour, monthPremierJour, monthDernierJour);		
	}
 
	/**
	 * Méthode qui retourne le libellé d'une semaine pour l'afficher dans le panel
	 * @param semaine
	 * @return libellé d'une semaine
	 */
	public static String getSemaineLabel(int week, Date premierJour, Date dernierJour, int monthPremierJour, int monthDernierJour) {
		String debut;
		DateFormat DATE_DEBUT_FORMAT = new SimpleDateFormat("dd");
		DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM");
		if (monthDernierJour == monthPremierJour) {
			debut = DATE_DEBUT_FORMAT.format(premierJour);
		} else {
			debut = DATE_FORMAT.format(premierJour);
		}
 
		String milieu = DATE_FORMAT.format(dernierJour);
 
		String fin = " (" + week + ")";
 
		return debut + " - " + milieu + fin;
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
	 * @param calendrier
	 */
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	/**
	 * Accesseur en lecture
	 * @return calendrierModele
	 */
	public CalendrierModele getCalendrierModele() {
		return calendrierModele;
	}

	/**
	 * Accesseur en écriture
	 * @param calendrierModele
	 */
	public void setCalendrierModele(CalendrierModele calendrierModele) {
		this.calendrierModele = calendrierModele;
	}
}