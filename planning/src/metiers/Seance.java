package metiers;

import java.io.Serializable;
/**
 * Classe métier Seance
 * @author Dylan
 */
public class Seance implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribut int indexLigne
	 */
	private int indexLigne;
	
	/**
	 * Attribut int indexColonne
	 */
	private int indexColonne;
	
	/**
	 * Attribut int semaine;
	 */
	private int semaine;
	
	/**
	 * Attribut Module module
	 */
	private Module module;
	
	/**
	 * Attribut int rangSeanceModule
	 */
	private int rangSeanceModule;
	
	/**
	 * Accesseur en lecture
	 * @return indexLigne
	 */
	public int getIndexLigne() {
		return indexLigne;
	}
	
	/**
	 * Accesseur en écriture
	 * @param indexLigne : paramètre de type int
	 */
	public void setIndexLigne(int indexLigne) {
		this.indexLigne = indexLigne;
	}
	
	/**
	 * Accesseur en lecture
	 * @return indexColonne
	 */
	public int getIndexColonne() {
		return indexColonne;
	}
	
	/**
	 * Accesseur en écriture
	 * @param indexColonne : paramètre de type int
	 */
	public void setIndexColonne(int indexColonne) {
		this.indexColonne = indexColonne;
	}
	
	/**
	 * Accesseur en lecture
	 * @return semaine
	 */
	public int getSemaine() {
		return semaine;
	}
	
	/**
	 * Accesseur en écriture
	 * @param semaine : paramètre de type int
	 */
	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}
	
	/**
	 * Accesseur en lecture
	 * @return module
	 */
	public Module getModule() {
		return module;
	}
	
	/**
	 * Accesseur en écriture
	 * @param module : paramètre de type Module
	 */
	public void setModule(Module module) {
		this.module = module;
	}
	
	/**
	 * Accesseur en lecture
	 * @return rangSeanceModule
	 */
	public int getRangSeanceModule() {
		return rangSeanceModule;
	}

	/**
	 * Accesseur en écriture
	 * @param rangSeanceModule : paramètre de type int
	 */
	public void setRangSeanceModule(int rangSeanceModule) {
		this.rangSeanceModule = rangSeanceModule;
	}
	
	/**
	 * Méthode toString qui contient les caractéristiques d'une séances
	 * @return chaine
	 */
	public String toString(){	
		//Conversion de la couleur lisible au format html
		String hexRed = Integer.toHexString(module.getCouleur().getRed());
        String hexGreen = Integer.toHexString(module.getCouleur().getGreen());
        String hexBlue = Integer.toHexString(module.getCouleur().getBlue());
        String couleur = "#"+hexRed+hexGreen+hexBlue;

		String chaine = "<html><body style='background:"+couleur+"'><p style='text-align: center;'>"+module.getNom()+"</p>"
				+ "<p style='text-align: center;'>"+module.getAbreviation()+"</p>"
				+ "<p style='text-align: center;'>"+rangSeanceModule+"/"
				+ module.getNbSeance()+"</p></body></html>";

		return chaine;
	}
}