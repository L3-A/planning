package modeles;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * Classe mod�le ListeAnneeModele
 * @author Dylan
 */
public class ListeAnneeModele implements ListModel{
    
	/**
	 * Attribut contenant la liste des ann�es
	 */
	private String[] annees = 
    	{"2010-2011", 
    		"2011-2012", 
    		"2012-2013", 
    		"2013-2014",
    		"2014-2015", 
    		"2015-2016", 
    		"2016-2017", 
    		"2017-2018", 
    		"2018-2019", 
    		"2019-2020"};

	/**
	 * M�thode qui r�cup�re la valeur d'un �l�ment
	 * @param index : index de l'�l�ment � r�cup�rer
	 * @return Object
	 */
	public Object getElementAt(int index){
		return this.annees[index];
	}

	/**
	 * M�thode qui r�cup�re la taille de la liste
	 * @return int : taille de la liste
	 */
	public int getSize(){
		return this.annees.length;
	}

	public void addListDataListener(ListDataListener arg0){}
	
	public void removeListDataListener(ListDataListener arg0){}
}