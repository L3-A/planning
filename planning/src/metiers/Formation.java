package metiers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe m�tier Formation
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
	 * Constructeur par d�faut
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
	 * Accesseur en �criture
	 * @param nom : param�tre de type String
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
	 * Accesseur en �criture
	 * @param dureeTypeSeance : param�tre de type Float
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
	 * Accesseur en �criture
	 * @param modules : param�tre de type List Module
	 */
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}