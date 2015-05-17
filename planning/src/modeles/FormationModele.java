package modeles;

import java.util.Calendar;
import java.util.List;

import metiers.Calendrier;
import metiers.Formation;
import metiers.Module;
/**
 * Classe modèle FormationModele
 * @author Dylan
 */
public class FormationModele {
	/**
	 * Attribut Formation formation
	 */
	private Formation formation;
	
	/**
	 * Constructeur de la classe qui valorise l'attribut formation
	 * @param formation : paramètre de type Formation
	 */
	public FormationModele(Formation formation){
		this.formation = formation;
	}
	
	/**
	 * Méthode qui permet de modifier une formation
	 * @param nom : paramètre de type String
	 * @param dureeTypeSeance : paramètre de type Float
	 * @param modules : paramètre de type list Module
	 * @return formation
	 */
	public Formation updateFormation(String nom, float dureeTypeSeance, List<Module> modules){		
		formation.setNom(nom);
		formation.setDureeTypeSeance(dureeTypeSeance);
		formation.setModules(modules);
		
		return formation;
	}
	
	/**
	 * Méthode qui converti la dureeTypeSeance en string
	 * @param dureeTypeSeance : paramètre de type float
	 * @return dureeTypeSeance
	 */
	public String convertDureeTypeSeance(float dureeTypeSeance){
		return String.valueOf(dureeTypeSeance);
	}
	
	/**
	 * Méthode qui permet de savoir si on peut convertir un string en float pour la dureeTypeSeance
	 * @param chaine : paramètre de type String
	 * @return retour true si on peut convertir, false sinon
	 */
	public boolean isValid(String chaine){
		try{
			chaine = chaine.replace(",", ".");
			new Float(chaine);
			return true;
		}catch(NumberFormatException n){
			return false;
		}
	}
	
	/**
	 * Méthode qui permet de calculer la durée en heure d'une formation
	 * @return dureeHeures
	 */
	public float dureeNbHeureFormation(){
		float dureeHeures = 0;
		int nbSeanceTotal = 0;
		float dureeSeance = formation.getDureeTypeSeance();
		List<Module> modules = formation.getModules();
		for(Module unModule : modules){
			nbSeanceTotal = nbSeanceTotal + unModule.getNbSeance();
		}
		dureeHeures = nbSeanceTotal * dureeSeance;
		
		return dureeHeures;
	}
	
	/**
	 * Méthode qui permet de calculer la durée en jour d'une formation
	 * @param calendar : paramètre de type Calendar
	 * @param calendrier : paramètre de type Calendrier
	 * @return dureeJours
	 */
	public int dureeJoursFormation(Calendar calendar, Calendrier calendrier){
    	int dureeJours = 0;
    	int nbSemainesAnnee = calendar.getWeeksInWeekYear();
    	boolean dimancheOuvrable = calendrier.getDimancheOuvrable();
    	boolean samediOuvrable = calendrier.getSamediOuvrable();
    	dureeJours = nbSemainesAnnee * 7;
    	
    	//Si les samedis sont non ouvrés, on les suppriment de la durée
    	if(samediOuvrable == true){
    		dureeJours = dureeJours - (nbSemainesAnnee);
    	}
    	
    	//Si les dimmanches sont non ouvrés, on les suppriment de la durée
    	if(dimancheOuvrable == true){
    		dureeJours = dureeJours - (nbSemainesAnnee);
    	}
    	
    	return dureeJours;
	}
}