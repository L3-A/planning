package modeles;

import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * Classe modèle ListeAnneeModele
 * @author Dylan
 */
public class ListeAnneeModele implements ListModel{
    
	/**
	 * Attribut contenant la liste des années
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
	 * Méthode qui récupère la valeur d'un élément
	 * @param index : index de l'élément à récupérer
	 * @return Object
	 */
	public Object getElementAt(int index){
		return this.annees[index];
	}

	/**
	 * Méthode qui récupère la taille de la liste
	 * @return int : taille de la liste
	 */
	public int getSize(){
		return this.annees.length;
	}

	public void addListDataListener(ListDataListener arg0){}
	
	public void removeListDataListener(ListDataListener arg0){}
}