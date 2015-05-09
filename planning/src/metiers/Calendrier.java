package metiers;
/**
 * Classe métier Calendrier
 * @author Dylan
 */
public class Calendrier {
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
	 * Constructeur par défaut
	 */
	public Calendrier(){

	}

	/**
	 * Accesseur en lecture
	 * @return samediOuvrable
	 */
	public boolean getSamediOuvrable(){
		return samediOuvrable;
	}

	/**
	 * Accesseur en écriture
	 * @param samediOuvrable
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
	 * Accesseur en écriture
	 * @param dimancheOuvrable
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
	 * Accesseur en écriture
	 * @param uneAnnee
	 */
	public void setUneAnnee(Annee uneAnnee){
		this.uneAnnee = uneAnnee;
	}
}