package modeles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import metiers.Annee;

/**
 * @author Dylan
 * Classe SemainePanelModele
 */
public class SemainePanelModele {

	
	/**
	 * Attribut CalendrierModele calendrierModele
	 */
	private CalendrierModele calendrierModele;
	
	/**
	 * Constructeur
	 * @param calendrierModele : paramètre de type calendrierModele
	 */
	public SemainePanelModele(CalendrierModele calendrierModele){
		this.calendrierModele = calendrierModele;
	}
	
	/**
	 * Méthode qui copie le calendar
	 * @param calendar : paramètre de type Calendar
	 * @return calendar
	 */
	public Calendar copieCalendar(Calendar calendar){
		Calendar copy = Calendar.getInstance();
		copy.setTime(calendar.getTime());
		return copy;
 	}
 
 
	/**
	 * Méthode qui conmpte le nombre de semaine entre la première et la dernière
	 * @param premiereSemaine : paramètre de type Calendar
	 * @param derniereSemaine : paramètre de type Calendar
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
	 * @param mois : paramètre de type int
	 * @return calendar
	 */
	public Calendar getPremiereSemaine(int mois){
    	Annee uneAnnee = new Annee();
    	uneAnnee = calendrierModele.getCalendrier().getUneAnnee();
        
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
	 * @param mois : paramètre de type int
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
	 * @param semaine : paramètre de type int
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
	 * @param week : paramètre de type int
	 * @param premierJour : paramètre de type Date
	 * @param dernierJour : paramètre de type Date
	 * @param monthPremierJour : paramètre de type int
	 * @param monthDernierJour : paramètre de type int
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
	 * @return calendrierModele
	 */
	public CalendrierModele getCalendrierModele() {
		return calendrierModele;
	}

	/**
	 * Accesseur en écriture
	 * @param calendrierModele : paramètre de type calendrierModele
	 */
	public void setCalendrierModele(CalendrierModele calendrierModele) {
		this.calendrierModele = calendrierModele;
	}
}