package modeles;

import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import metiers.Calendrier;
import metiers.Seance;

/**
 * Classe modèle CalendrierTableModèle
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
    
    private Calendar calendar;
	private final List<Seance> seances;
	private int semaine;
    /**
     * Constructeur
     * @param calendar
     * @param calendrier
     */
    public CalendrierTableModele(Calendar calendar, Calendrier calendrier, List<Seance> seances){
    	this.calendar = calendar;
    	this.calendrier = calendrier;
    	this.seances = seances;
    }

    /**
     * Méthode qui retourne le nombre de colonne
     * @return les 7 jours de la semaine
     */
    public int getColumnCount(){
        return 7;
    }

    /**
     * Méthode qui retourne le nombre de ligne 
     * @return les 2 lignes : matin et après-midi
     */
    public int getRowCount(){
    	return 2;
    }

    /**
     * Méthode qui retourne la valeur d'une cellule
     * @param row : index de la ligne
     * @param col : index de la colonne
     * @return Object
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
    
    
    public Class getColumnClass(int col, int row){
    	Color couleur;
    	if(seances.size() != 0){
    		for(Seance uneSeance : seances){
    			if(uneSeance.getIndexColonne() == col && uneSeance.getIndexLigne() == row && uneSeance.getSemaine() == semaine){
    				couleur = uneSeance.getModule().getCouleur();
    			}
    		}
        	switch(col){
        	case 0:
        		return Color.class;
        	case 1:
        		return Color.class;
        	case 2:
        		return Color.class;
        	case 3:
        		return Color.class;
        	case 4:
        		return Color.class;
        	case 5:
        		return Color.class;
        	case 6:
        		return Color.class;
        	default:
        		return null;
            }
    	}else{
    		return null;
    	}
    }
    
    
    /**
     * Méthode qui permet de dire si une cellule est éditable ou non
     * @param row : index de ligne
     * @param col : index de la colonne
     * @return boolean
     */
    public boolean isCellEditable(int row, int col){
    	return false;
    }


    
    /**
     * Méthode qui retourne le nom d'une colonne
     * @param col : index de la colonne
     */
    public String getColumnName(int col) {
        return getDayName(col);
    }
    
    /**
     * Méthode qui retourne nom d'un jours en français
     * @param day
     * @return le libellé du jours
     */
    private String getDayName(int day) {
    	Calendar calendarDay = Calendar.getInstance();
    	calendarDay.setTime(calendar.getTime());
    	calendarDay.add(Calendar.DATE, day);
    	return calendarDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())+" "+calendarDay.get(Calendar.DATE); // bug sur cette ligne calendar au lieu de calendarDay
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
	 * @return calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * Accesseur en écriture
	 * @param calendar
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	
    public void addSeance(Seance seance) {
    	seances.add(seance);
    }
    
    public void removeSeance(String seance) {
    	for(Seance uneSeance : seances){
    		if(uneSeance.toString().equals(seance)){
    	    	seances.remove(uneSeance);
    	    	calendrier.nbSeanceSup(uneSeance);
    	    	this.fireTableDataChanged();
    	    	break;
    		}
    	}
    }
    
    
    public List<Seance> getSeances(){
    	return seances;
    }

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}
}