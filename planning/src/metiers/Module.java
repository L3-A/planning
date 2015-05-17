package metiers;

import java.awt.Color;
import java.io.Serializable;
/**
 * Classe m�tier Module
 * @author Dylan
 */
public class Module implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribut string nom
	 */
	private String nom;
	
	/**
	 * Attribut string abreviation
	 */
	private String abreviation;
	
	/**
	 * Attribut Color couleur
	 */
	private Color couleur;
	
	/**
	 * Attribut int nbSeance
	 */
	private int nbSeance;
	
	/**
	 * Constructeur qui permet de cr�er un nouveau module avec ses caract�ristiques
	 * @param nom : param�tre de type String
	 * @param abreviation : param�tre de type String
	 * @param couleur : param�tre de type Color
	 * @param nbSeance : param�tre de type int
	 */
	public Module(String nom, String abreviation, Color couleur, int nbSeance){
		this.nom = nom;
		this.abreviation = abreviation;
		this.couleur = couleur;
		this.nbSeance = nbSeance;
		
	}

	/**
	 * Constructeur par d�faut
	 */
	public Module() {

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
	 * @return abreviation
	 */
	public String getAbreviation() {
		return abreviation;
	}

	/**
	 * Accesseur en �criture
	 * @param abreviation : param�tre de type String
	 */
	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	/**
	 * Accesseur en lecture
	 * @return couleur
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Accesseur en �criture
	 * @param couleur : param�tre de type Color
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * Accesseur en lecture
	 * @return nbSeance
	 */
	public int getNbSeance() {
		return nbSeance;
	}

	/**
	 * Accesseur en �criture
	 * @param nbSeance : param�tre de type int
	 */
	public void setNbSeance(int nbSeance) {
		this.nbSeance = nbSeance;
	}
}