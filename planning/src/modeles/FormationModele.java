package modeles;

import java.util.Calendar;
import java.util.List;

import metiers.Calendrier;
import metiers.Formation;
import metiers.Module;
/**
 * Classe mod�le FormationModele
 * @author Dylan
 */
public class FormationModele {
	/**
	 * Attribut Formation formation
	 */
	private Formation formation;
	
	/**
	 * Constructeur de la classe qui valorise l'attribut formation
	 * @param formation : param�tre de type Formation
	 */
	public FormationModele(Formation formation){
		this.formation = formation;
	}
	
	/**
	 * M�thode qui permet de modifier une formation
	 * @param nom : param�tre de type String
	 * @param dureeTypeSeance : param�tre de type Float
	 * @param modules : param�tre de type list Module
	 * @return formation
	 */
	public Formation updateFormation(String nom, float dureeTypeSeance, List<Module> modules){		
		formation.setNom(nom);
		formation.setDureeTypeSeance(dureeTypeSeance);
		formation.setModules(modules);
		
		return formation;
	}
	
	/**
	 * M�thode qui converti la dureeTypeSeance en string
	 * @param dureeTypeSeance : param�tre de type float
	 * @return dureeTypeSeance
	 */
	public String convertDureeTypeSeance(float dureeTypeSeance){
		return String.valueOf(dureeTypeSeance);
	}
	
	/**
	 * M�thode qui permet de savoir si on peut convertir un string en float pour la dureeTypeSeance
	 * @param chaine : param�tre de type String
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
	 * M�thode qui permet de calculer la dur�e en heure d'une formation
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
	 * M�thode qui permet de calculer la dur�e en jour d'une formation
	 * @param calendar : param�tre de type Calendar
	 * @param calendrier : param�tre de type Calendrier
	 * @return dureeJours
	 */
	public int dureeJoursFormation(Calendar calendar, Calendrier calendrier){
    	int dureeJours = 0;
    	int nbSemainesAnnee = calendar.getWeeksInWeekYear();
    	boolean dimancheOuvrable = calendrier.getDimancheOuvrable();
    	boolean samediOuvrable = calendrier.getSamediOuvrable();
    	dureeJours = nbSemainesAnnee * 7;
    	
    	//Si les samedis sont non ouvr�s, on les suppriment de la dur�e
    	if(samediOuvrable == true){
    		dureeJours = dureeJours - (nbSemainesAnnee);
    	}
    	
    	//Si les dimmanches sont non ouvr�s, on les suppriment de la dur�e
    	if(dimancheOuvrable == true){
    		dureeJours = dureeJours - (nbSemainesAnnee);
    	}
    	
    	return dureeJours;
	}
}