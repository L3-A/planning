package metiers;

import java.awt.Color;
import java.io.Serializable;
/**
 * Classe métier Module
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
	 * Constructeur qui permet de créer un nouveau module avec ses caractéristiques
	 * @param nom : paramètre de type String
	 * @param abreviation : paramètre de type String
	 * @param couleur : paramètre de type Color
	 * @param nbSeance : paramètre de type int
	 */
	public Module(String nom, String abreviation, Color couleur, int nbSeance){
		this.nom = nom;
		this.abreviation = abreviation;
		this.couleur = couleur;
		this.nbSeance = nbSeance;
		
	}

	/**
	 * Constructeur par défaut
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
	 * Accesseur en écriture
	 * @param nom : paramètre de type String
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
	 * Accesseur en écriture
	 * @param abreviation : paramètre de type String
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
	 * Accesseur en écriture
	 * @param couleur : paramètre de type Color
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
	 * Accesseur en écriture
	 * @param nbSeance : paramètre de type int
	 */
	public void setNbSeance(int nbSeance) {
		this.nbSeance = nbSeance;
	}
}