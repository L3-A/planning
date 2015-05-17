package metiers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	 * @param annee
	 */
	public void setAnnee(String annee){
		this.annee = annee;
	}
	
	/**
	 * Méthode qui permet de convertir une année de type String en objet Anne
	 * @param anneeListe
	 * @return annee
	 */
	public Annee convertirUneAnnee(String anneeListe){
		Annee annee = new Annee();
		String uneAnnee;
		uneAnnee = anneeListe.substring(0, 4);
		annee.setAnnee(uneAnnee);
		return annee;
	}
	
	/**
	 * Méthode qui permet de convertir un objet Annee en int
	 * @param uneAnnee
	 * @return annee
	 */
	public int anneeChoisit(Annee uneAnnee){
		SimpleDateFormat curFormater = new SimpleDateFormat("yyyy"); 
		Date dateObj = null;
		int annee = 0;
		
		try{
			dateObj = curFormater.parse(uneAnnee.getAnnee());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateObj);
			annee = calendar.get(Calendar.YEAR);
		}catch (ParseException e){
			e.printStackTrace();
		} 
		
		return annee;
	}
}