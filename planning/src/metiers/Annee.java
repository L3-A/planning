package metiers;

import java.io.Serializable;

/**
 * Classe métier Annee
 * @author Dylan
 */
public class Annee implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Attribut String annee
	 */
	private String annee;
	
	/**
	 * Constructeur par défaut
	 */
	public Annee(){
		
	}

	/**
	 * Accesseur en lecture
	 * @return annee
	 */
	public String getAnnee(){
		return annee;
	}

	/**
	 * Accesseur en écriture 
	 * @param annee : paramètre de type String
	 */
	public void setAnnee(String annee){
		this.annee = annee;
	}
}