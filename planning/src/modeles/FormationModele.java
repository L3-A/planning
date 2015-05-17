package modeles;

import java.util.List;

import metiers.Formation;
import metiers.Module;

public class FormationModele {
	private Formation formation;
	
	public FormationModele(Formation formation){
		this.formation = formation;
	}
	
	public Formation updateFormation(String nom, float dureeTypeSeance, List<Module> modules){		
		formation.setNom(nom);
		formation.setDureeTypeSeance(dureeTypeSeance);
		formation.setModules(modules);
		
		return formation;
	}
	
	public Formation recupFormation(){
		formation.getNom();
		formation.getDureeTypeSeance();
		
		return formation;
	}
	
	public String convertDureeTypeSeance(float dureeTypeSeance){
		return String.valueOf(dureeTypeSeance);
	}
	
	public boolean isValid(String chaine){
		try{
			chaine = chaine.replace(",", ".");
			new Float(chaine);
			return true;
		}catch(NumberFormatException n){
			return false;
		}
	}
}