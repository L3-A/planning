package modeles;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import vues.ModuleTable;

public class ModuleModele {
	private ModuleTable moduleTable;
	
	public ModuleModele(ModuleTable moduleTable){
		this.moduleTable = moduleTable;
	}
	
	public ModuleModele(){
		
	}
	
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
	
	public boolean isValid(String chaine){
		try{
			new Integer(chaine);
			return true;
		}catch(NumberFormatException n){
			return false;
		}
	}
}