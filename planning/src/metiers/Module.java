package metiers;

import java.awt.Color;
import java.io.Serializable;

public class Module implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String abreviation;
	private Color couleur;
	private int nbSeance;
	private float dureeModule;
	public Module(String nom, String abreviation, Color couleur, int nbSeance){
		this.nom = nom;
		this.abreviation = abreviation;
		this.couleur = couleur;
		this.nbSeance = nbSeance;
		
	}

	public Module() {
		// TODO Auto-generated constructor stub
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getNbSeance() {
		return nbSeance;
	}

	public void setNbSeance(int nbSeance) {
		this.nbSeance = nbSeance;
	}

	public float getDureeModule() {
		return dureeModule;
	}

	public void setDureeModule(float dureeModule) {
		this.dureeModule = dureeModule;
	}
}