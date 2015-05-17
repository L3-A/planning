package metiers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Formation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private float dureeTypeSeance;
	private List<Module> modules;
	
	public Formation(){
		modules = new ArrayList<Module>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getDureeTypeSeance() {
		return dureeTypeSeance;
	}

	public void setDureeTypeSeance(float dureeTypeSeance) {
		this.dureeTypeSeance = dureeTypeSeance;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
}