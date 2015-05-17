package modeles;

import java.awt.Color;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metiers.Module;
/**
 * Classe mod�le ModuleTableModele
 * @author Dylan
 */
public class ModuleTableModele extends AbstractTableModel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Attribut List Module modules
	 */
	private final List<Module> modules;
	
	/**
	 * Attribut float dureeSeance
	 */
	private float dureeSeance;
	
	/**
	 * Constante contenant les valeurs des ent�tes du tableau
	 */
    private final String[] entetes = {"Nom", "Abr�viation", "Couleur", "Nombre s�ances", "Dur�e Module"};
 
    /**
     * Contructeur de la classe valorisant diff�rents attributs 
     * @param modules : param�tre de type List Module
     * @param dureeSeance : param�tre de type float
     */
    public ModuleTableModele(List<Module> modules, float dureeSeance) {
        super();
        this.modules = modules;
        this.dureeSeance = dureeSeance;
    }
 
    /**
     * M�thode qui retourne le nombre de ligne contenu dans le tableau
     * @return int : nombre de ligne
     */
    public int getRowCount() {
        return modules.size();
    }
 
    /**
     * M�thode qui retourne le nombre de colonne contenu dans le tableau
     * @return int : nombre de colonne
     */
    public int getColumnCount() {
        return entetes.length;
    }
 
    /**
     * M�thode qui retourne le nom des colonnes
     * @return string : nom des colonnes
     */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    /**
     * M�thode qui retourne si la cellule est �ditable ou non
     * @return boolean : true si la cellule est �ditable, false sinon
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 4){
        	return false;
        }else
        	return true;
    }
    
    /**
     * M�thode qui permet de modifier les valeurs contenues dans les cellules
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            Module module = modules.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    module.setNom((String)aValue);
                    break;
                case 1:
                    module.setAbreviation((String)aValue);
                    break;
                case 2:
                    module.setCouleur((Color)aValue);
                    break;
                case 3:
                	String seance = (String)aValue;
                    module.setNbSeance(Integer.parseInt(seance));
                    break;
            }
        }
    }
    
    /**
     * M�thode qui retourne les valeurs contenues dans les cellules
     * @return Object
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
    	if(modules.size() != 0){
        	switch(columnIndex){
        	case 0:
        		return modules.get(rowIndex).getNom();
        	case 1:
        		return modules.get(rowIndex).getAbreviation();
        	case 2:
        		return modules.get(rowIndex).getCouleur();
        	case 3:
        		return modules.get(rowIndex).getNbSeance();
        	case 4:
        		return modules.get(rowIndex).getNbSeance() * dureeSeance;
        	default:
        		return null;
            }
    	}else{
    		return "";
    	}
    }
 
    /**
     * M�thode qui permet de dire � quelle classe correspond chaque colonne
     */
    public Class getColumnClass(int columnIndex){
    	switch(columnIndex){
    		case 2:
    			return Color.class;
    		default:
    			return Object.class;
    	}
    }
    
    /**
     * M�thode qui permet d'ajouter un module � la liste des modules et donc au tableau
     * @param module : module � ajouter
     */
    public void addModule(Module module) {
        modules.add(module);
 
        fireTableRowsInserted(modules.size() -1, modules.size() -1);
    }
 
    /**
     * M�thode qui permet de supprimer un module de la liste des modules et donc au tableau
     * @param rowIndex : index de la ligne o� se trouve le module
     */
    public void removeModule(int rowIndex) {
        modules.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    /**
     * Accesseur en lecture
     * @return modules
     */
    public List<Module> getModules(){
    	return modules;
    }
    
    /**
     * Accesseur en lecture
     * @return durreSeance
     */
	public float getDureeSeance() {
		return dureeSeance;
	}

	/**
	 * Accesseur en �criture
	 * @param dureeSeance : param�tre de type float
	 */
	public void setDureeSeance(float dureeSeance) {
		this.dureeSeance = dureeSeance;
	}
}