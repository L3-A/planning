package modeles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import metiers.Module;
import vues.ModuleTable;
/**
 * Classe mod�le ModuleModele
 * @author Dylan
 */
public class ModuleModele {
	/**
	 * Attribut ModuleTable moduleTable
	 */
	private ModuleTable moduleTable;
	
	/**
	 * Constructeur de la classe valorisant l'attribut moduleTable
	 * @param moduleTable : param�tre de type ModuleTable
	 */
	public ModuleModele(ModuleTable moduleTable){
		this.moduleTable = moduleTable;
	}
	
	/**
	 * Constructeur par d�faut
	 */
	public ModuleModele(){
		
	}
	
	/**
	 * M�thode qui permet de v�rifier s'il existe d�j� un module comportant au moins une des caract�ristiques du module � ajout�
	 * @param nom : param�tre de type String
	 * @param abreviation : param�tre de type String
	 * @param couleur : param�tre de type Couleur
	 * @return message
	 */
	public String verifModule(String nom, String abreviation, Color couleur){
		String message = "";
		List<metiers.Module> modules = new ArrayList<metiers.Module>();
		modules = moduleTable.getModules();
		for(metiers.Module unModule : modules){
			if(unModule.getNom().equals(nom)){
				message = "Il existe d�j� un module avec le m�me nom !";
			}else if(unModule.getAbreviation().equals(abreviation)){
				message = "Il existe d�j� un module avec la m�me abr�viation !";
			}else if(unModule.getCouleur().equals(couleur)){
				message = "Il existe d�j� un module avec la m�me couleur de repr�sentation !";
			}
			break;
		}
		return message;
	}
	
	/**
	 * M�thode qui permet de savoir si on peut convertir un string en int pour le nombre de s�ance
	 * @param chaine : param�tre de type String
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
	 * @param moduleListe : param�tre de type String
	 * @return module contenant le nom du module
	 */
	public Module convertirUneSeance(String moduleListe){
		Module module = new Module();
		module.setNom(moduleListe);
		return module;
	}
	
	/**
	 * M�thode qui r�cup�re les informations d'un module
	 * @param module : param�tre de type Module
	 * @param modules : param�tre de type List Module
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