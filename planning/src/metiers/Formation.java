package metiers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe métier Formation
 * @author Dylan
 */
public class Formation implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribut string nom
	 */
	private String nom;
	
	/**
	 * Attribut float dureeTypeSeance
	 */
	private float dureeTypeSeance;
	
	/**
	 * Attribut liste de modules
	 */
	private List<Module> modules;
	
	/**
	 * Constructeur par défaut
	 */
	public Formation(){
		modules = new ArrayList<Module>();
	}

	/**
	 * Accesseur en lecture
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Accesseur en écriture
	 * @param nom : paramètre de type String
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Accesseur en lecture
	 * @return dureeTypeSeance
	 */
	public float getDureeTypeSeance() {
		return dureeTypeSeance;
	}

	/**
	 * Accesseur en écriture
	 * @param dureeTypeSeance : paramètre de type Float
	 */
	public void setDureeTypeSeance(float dureeTypeSeance) {
		this.dureeTypeSeance = dureeTypeSeance;
	}

	/**
	 * Accesseur en lecture
	 * @return modules
	 */
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * Accesseur en écriture
	 * @param modules : paramètre de type List Module
	 */
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}