package metiers;

import java.io.Serializable;

/**
 * Classe m�tier Annee
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
	 * Constructeur par d�faut
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
	 * Accesseur en �criture 
	 * @param annee : param�tre de type String
	 */
	public void setAnnee(String annee){
		this.annee = annee;
	}
}