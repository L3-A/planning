package metiers;

import java.io.Serializable;
/**
 * Classe m�tier Seance
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
	 * Accesseur en �criture
	 * @param indexLigne : param�tre de type int
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
	 * Accesseur en �criture
	 * @param indexColonne : param�tre de type int
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
	 * Accesseur en �criture
	 * @param semaine : param�tre de type int
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
	 * Accesseur en �criture
	 * @param module : param�tre de type Module
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
	 * Accesseur en �criture
	 * @param rangSeanceModule : param�tre de type int
	 */
	public void setRangSeanceModule(int rangSeanceModule) {
		this.rangSeanceModule = rangSeanceModule;
	}
	
	/**
	 * M�thode toString qui contient les caract�ristiques d'une s�ances
	 * @return chaine
	 */
	public String toString(){	

		
		
		String chaine = "<html><body><font color="+module.getCouleur().getRGB()+"><p style='text-align: center;'>"+module.getNom()+"</p>"
				+ "<p style='text-align: center;'>"+module.getAbreviation()+"</p>"
				+ "<p style='text-align: center;'>"+rangSeanceModule+"/"
				+ module.getNbSeance()+"</p></font></body></html>";

		return chaine;
	}
}