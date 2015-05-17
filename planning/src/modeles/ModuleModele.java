package modeles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import metiers.Module;
import vues.ModuleTable;
/**
 * Classe modèle ModuleModele
 * @author Dylan
 */
public class ModuleModele {
	/**
	 * Attribut ModuleTable moduleTable
	 */
	private ModuleTable moduleTable;
	
	/**
	 * Constructeur de la classe valorisant l'attribut moduleTable
	 * @param moduleTable : paramètre de type ModuleTable
	 */
	public ModuleModele(ModuleTable moduleTable){
		this.moduleTable = moduleTable;
	}
	
	/**
	 * Constructeur par défaut
	 */
	public ModuleModele(){
		
	}
	
	/**
	 * Méthode qui permet de vérifier s'il existe déjà un module comportant au moins une des caractéristiques du module à ajouté
	 * @param nom : paramètre de type String
	 * @param abreviation : paramètre de type String
	 * @param couleur : paramètre de type Couleur
	 * @return message
	 */
	public String verifModule(String nom, String abreviation, Color couleur){
		String message = "";
		List<metiers.Module> modules = new ArrayList<metiers.Module>();
		modules = moduleTable.getModules();
		for(metiers.Module unModule : modules){
			if(unModule.getNom().equals(nom)){
				message = "Il existe déjà un module avec le même nom !";
			}else if(unModule.getAbreviation().equals(abreviation)){
				message = "Il existe déjà un module avec la même abréviation !";
			}else if(unModule.getCouleur().equals(couleur)){
				message = "Il existe déjà un module avec la même couleur de représentation !";
			}
			break;
		}
		return message;
	}
	
	/**
	 * Méthode qui permet de savoir si on peut convertir un string en int pour le nombre de séance
	 * @param chaine : paramètre de type String
	 * @return true si on peut convertir, false sinon
	 */
	public boolean isValid(String chaine){
		try{
			new Integer(chaine);
			return true;
		}catch(NumberFormatException n){
			return false;
		}
	}
	
	/**
	 * Converti un string en module
	 * @param moduleListe : paramètre de type String
	 * @return module contenant le nom du module
	 */
	public Module convertirUneSeance(String moduleListe){
		Module module = new Module();
		module.setNom(moduleListe);
		return module;
	}
	
	/**
	 * Méthode qui récupère les informations d'un module
	 * @param module : paramètre de type Module
	 * @param modules : paramètre de type List Module
	 * @return module
	 */
    public Module recupInfoModule(Module module, List<Module> modules){
    	for(metiers.Module unModule : modules){
    		if(unModule.getNom().equals(module.getNom())){
    			module.setAbreviation(unModule.getAbreviation());
    			module.setNbSeance(unModule.getNbSeance());
    			module.setCouleur(unModule.getCouleur());
    			break;
    		}
    	}
    	return module;
    }
}