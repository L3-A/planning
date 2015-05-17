package metiers;

import java.io.Serializable;

public class Seance implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private int indexLigne;
	private int indexColonne;
	private int semaine;
	private Module module;
	private Formation formation;
	private int nbSeanceModule;
	public int getIndexLigne() {
		return indexLigne;
	}
	
	public void setIndexLigne(int indexLigne) {
		this.indexLigne = indexLigne;
	}
	
	public int getIndexColonne() {
		return indexColonne;
	}
	
	public void setIndexColonne(int indexColonne) {
		this.indexColonne = indexColonne;
	}
	
	public int getSemaine() {
		return semaine;
	}
	
	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}
	
	public Module getModule() {
		return module;
	}
	
	public void setModule(Module module) {
		this.module = module;
	}
	
	public Formation getFormation() {
		return formation;
	}
	
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	public Module convertirUneSeance(String moduleListe){
		Module module = new Module();
		module.setNom(moduleListe);
		return module;
	}
	
	public String toString(){	
		String chaine = "<html><body> <style color='"+module.getCouleur()+"'><p style='text-align: center;'>"+module.getNom()+"</p>"
				+ "<p style='text-align: center;'>"+module.getAbreviation()+"</p>"
				+ "<p style='text-align: center;'>"+nbSeanceModule+"/"
				+ module.getNbSeance()+"</p></style></body></html>";

		return chaine;
	}

	public int getNbSeanceModule() {
		return nbSeanceModule;
	}

	public void setNbSeanceModule(int nbSeanceModule) {
		this.nbSeanceModule = nbSeanceModule;
	}
}