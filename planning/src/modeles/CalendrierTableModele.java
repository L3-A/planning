package modeles;

import java.util.Calendar;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import metiers.Calendrier;

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
    
    private Calendar calendar;
    /**
     * Constructeur
     * @param calendar
     * @param calendrier
     */
    public CalendrierTableModele(Calendar calendar, Calendrier calendrier){
    	this.calendar = calendar;
    	this.calendrier = calendrier;
    }

    /**
     * M�thode qui retourne le nombre de colonne
     * @return les 7 jours de la semaine
     */
    public int getColumnCount(){
        return 7;
    }

    /**
     * M�thode qui retourne le nombre de ligne 
     * @return les 2 lignes : matin et apr�s-midi
     */
    public int getRowCount(){
    	return 2;
    }

    /**
     * M�thode qui retourne la valeur d'une cellule
     * @param row : index de la ligne
     * @param col : index de la colonne
     * @return Object
     */
    public Object getValueAt(int row, int col){
    	return "";
    }

    /**
     * M�thode qui permet de dire si une cellule est �ditable ou non
     * @param row : index de ligne
     * @param col : index de la colonne
     * @return boolean
     */
    public boolean isCellEditable(int row, int col){
    	boolean editable;
    	
    	switch(col){
    		case 5: // colonne 5 non �ditable
    			editable = !calendrier.getSamediOuvrable(); 
    			break;
    		case 6: // colonne 6 non �ditable 
    			editable = !calendrier.getDimancheOuvrable();
    			break;
    		default: // toutes les autres sont non �ditables
    			editable = true;
    			break;
    	}
    	
    	return editable;
    }

    /**
     * M�thode qui permet d'ajouter une valeur dans une cellule
     * @param row : index de la ligne
     * @param col : index de la colonne
     */
    public void setValueAt(Object value, int row, int col){

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
}