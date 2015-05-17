package metiers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe m�tier Calendrier
 * @author Dylan
 */
public class Calendrier implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribut boolean samediOuvrable
	 */
	private boolean samediOuvrable;
	
	/**
	 * Attribut boolean dimancheOuvrable
	 */
	private boolean dimancheOuvrable;
	
	/**
	 * Attribut Annee uneAnnee
	 */
	private Annee uneAnnee;
	
	/**
	 * Attribut Formation uneFormation;
	 */
	private Formation uneFormation;
	
	/**
	 * Attribut List Seance seances
	 */
	private List<Seance> seances;
	
	/**
	 * Constructeur par d�faut
	 */
	public Calendrier(){
		seances = new ArrayList<Seance>();
	}

	/**
	 * Accesseur en lecture
	 * @return samediOuvrable
	 */
	public boolean getSamediOuvrable(){
		return samediOuvrable;
	}

	/**
	 * Accesseur en �criture
	 * @param samediOuvrable : param�tre de type boolean
	 */
	public void setSamediOuvrable(boolean samediOuvrable){
		this.samediOuvrable = samediOuvrable;
	}
	
	/**
	 * Accesseur en lecture
	 * @return dimancheOuvrable
	 */
	public boolean getDimancheOuvrable(){
		return dimancheOuvrable;
	}

	/**
	 * Accesseur en �criture
	 * @param dimancheOuvrable : param�tre de type boolean
	 */
	public void setDimancheOuvrable(boolean dimancheOuvrable){
		this.dimancheOuvrable = dimancheOuvrable;
	}
	
	/**
	 * Accesseur en lecture
	 * @return uneAnnee
	 */
	public Annee getUneAnnee(){
		return uneAnnee;
	}

	/**
	 * Accesseur en �criture
	 * @param uneAnnee : param�tre de type Annee
	 */
	public void setUneAnnee(Annee uneAnnee){
		this.uneAnnee = uneAnnee;
	}

	/**
	 * Accesseur en lecture
	 * @return uneFormation
	 */
	public Formation getUneFormation() {
		return uneFormation;
	}

	/**
	 * Accesseur en �criture
	 * @param uneFormation : param�tre de type Formation
	 */
	public void setUneFormation(Formation uneFormation) {
		this.uneFormation = uneFormation;
	}

	/**
	 * Accesseur en lecture
	 * @return seances
	 */
	public List<Seance> getSeances() {
		return seances;
	}

	/**
	 * Accesseur en �criture
	 * @param seances : param�tre de type List Seance
	 */
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
}