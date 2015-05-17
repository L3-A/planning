package modeles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import metiers.Annee;
/**
 * Classe Modele AnneeModele
 * @author Dylan
 *
 */
public class AnneeModele {
	/**
	 * Attribut de type Annee annee
	 */
	private Annee annee;
	
	/**
	 * Contrustreur
	 * @param annee : param�tre de type Annee
	 */
	public AnneeModele(Annee annee){
		this.annee = annee;
	}
	
	/**
	 * Accesseur en lecture
	 * @return annee
	 */
	public Annee getAnnee() {
		return annee;
	}

	/**
	 * Accesseur en �criture
	 * @param annee : param�tre de type Annee
	 */
	public void setAnnee(Annee annee) {
		this.annee = annee;
	}
	
	/**
	 * M�thode qui permet de convertir une ann�e de type String en objet Anne
	 * @param anneeListe : param�tre de type String
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
	 * M�thode qui permet de convertir un objet Annee en int
	 * @param uneAnnee : param�tre de type Annee
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