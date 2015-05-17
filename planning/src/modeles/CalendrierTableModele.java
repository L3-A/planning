package modeles;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import metiers.Calendrier;
import metiers.Seance;

/**
 * Classe mod�le CalendrierTableMod�le
 * @author Dylan
 */
public class CalendrierTableModele extends AbstractTableModel {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
  
	/**
	 * Attribut Calendrier calendrier
	 */
    private Calendrier calendrier;
    
    /**
     * Attribut Calendat calendar
     */
    private Calendar calendar;
    
    /**
     * Attribut List Seance seances
     */
	private List<Seance> seances;
	
	/**
	 * Attribut int semaine
	 */
	private int semaine;
	
	/**
	 * Attribut CalendrierModele calendrierModele
	 */
	private CalendrierModele calendrierModele;
    
	/**
     * Constructeur
     * @param calendar : param�tre de type Calendar
     * @param calendrier : param�tre de type Calendrier
     * @param seances : param�tre de type list Seance
     */
    public CalendrierTableModele(Calendar calendar, Calendrier calendrier, List<Seance> seances){
    	this.calendar = calendar;
    	this.calendrier = calendrier;
    	this.seances = seances;
    	calendrierModele = new CalendrierModele(calendrier);
    }

    /**
     * M�thode qui retourne le nombre de colonne
     * @return int : les 7 jours de la semaine
     */
    public int getColumnCount(){
        return 7;
    }

    /**
     * M�thode qui retourne le nombre de ligne 
     * @return int : les 2 lignes : matin et apr�s-midi
     */
    public int getRowCount(){
    	return 2;
    }

    /**
     * M�thode qui retourne la valeur d'une cellule
     * @param row : index de la ligne
     * @param col : index de la colonne
     * @return String : valeur contenue dans la cellule
     */
    public String getValueAt(int row, int col){
    	String chaine = null;
    	if(seances.size() != 0){
    		for(Seance uneSeance : seances){
    			if(uneSeance.getIndexColonne() == col && uneSeance.getIndexLigne() == row && uneSeance.getSemaine() == semaine){
    				chaine = uneSeance.toString();
    			}
    		}
        	switch(col){
        	case 0:
        		return chaine;
        	case 1:
        		return chaine;
        	case 2:
        		return chaine;
        	case 3:
        		return chaine;
        	case 4:
        		return chaine;
        	case 5:
        		return chaine;
        	case 6:
        		return chaine;
        	default:
        		return null;
            }
    	}else{
    		return null;
    	}
    }
        
    /**
     * M�thode qui permet de dire si une cellule est �ditable ou non
     * @param row : index de ligne
     * @param col : index de la colonne
     * @return boolean : true si �ditable, false sinon
     */
    public boolean isCellEditable(int row, int col){
    	return false;
    }

    /**
     * M�thode qui retourne le nom d'une colonne
     * @param col : index de la colonne
     */
    public String getColumnName(int col) {
        return getDayName(col);
    }
    
    /**
     * M�thode qui retourne nom d'un jours en fran�ais
     * @param day
     * @return le libell� du jours
     */
    private String getDayName(int day) {
    	Calendar calendarDay = Calendar.getInstance();
    	calendarDay.setTime(calendar.getTime());
    	calendarDay.add(Calendar.DATE, day);
    	return calendarDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+" "+calendarDay.get(Calendar.DATE); // bug sur cette ligne calendar au lieu de calendarDay
    }
	
	/**
	 * M�thode qui permet d'ajouter une s�ance � la liste des seances et donc de l'ajouter sur le planning
	 * @param seance : param�tre de type Seance
	 */
    public void addSeance(Seance seance) {
    	seances.add(seance);
    }
    
    /**
     * M�thode qui permet de supprimer s�ance � la liste des s�ances et donc de l'enlever du planning
     * @param seance : param�tre de type Seance
     */
    public void removeSeance(String seance) {
    	for(Seance uneSeance : seances){
    		if(uneSeance.toString().equals(seance)){
    	    	seances.remove(uneSeance);
    	    	calendrierModele.nbSeanceSup(uneSeance);
    	    	this.fireTableDataChanged();
    	    	break;
    		}
    	}
    }
    
    /**
     * Accessuer en lecture
     * @return semaine
     */
	public int getSemaine() {
		return semaine;
	}

	/**
	 * Accesseur en �criture
	 * @param semaine : param�tre de type int
	 */
	public void setSemaine(int semaine) {
		this.semaine = semaine;
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
	 * Accesseur en lecture
	 * @return calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Accesseur en �criture
	 * @param calendar : param�tre de type Calendar
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
}