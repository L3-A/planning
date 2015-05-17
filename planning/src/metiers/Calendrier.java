package metiers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe métier Calendrier
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
	 * Attrit List<Seance> seances
	 */
	private List<Seance> seances;
	
	/**
	 * Constructeur par défaut
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

	/**
	 * Accesseur en lecture
	 * @return uneFormation
	 */
	public Formation getUneFormation() {
		return uneFormation;
	}

	/**
	 * Accesseur en écriture
	 * @param uneFormation
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
	 * Accesseur en écriture
	 * @param seances
	 */
	public void setSeances(List<Seance> seances) {
		this.seances = seances;
	}
	
	public int nbSeance(Seance uneSeance){
		int nbSeance = 1;
		for(Seance seance : seances){
			if(seance.getModule().getNom().equals(uneSeance.getModule().getNom())){
				nbSeance = nbSeance + 1;
			}
		}
		return nbSeance;
	}
	
	public void nbSeanceSup(Seance uneSeance){
		int nbSeance = 0;
		for(Seance seance : seances){
			if(seance.getNbSeanceModule() > uneSeance.getNbSeanceModule()){
				nbSeance = seance.getNbSeanceModule() - 1;
				seance.setNbSeanceModule(nbSeance);			
			}
		}
	}
	
}