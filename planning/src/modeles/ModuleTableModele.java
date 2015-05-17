package modeles;

import java.awt.Color;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metiers.Module;

public class ModuleTableModele extends AbstractTableModel{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final List<Module> modules;
	private float dureeSeance;
    private final String[] entetes = {"Nom", "Abréviation", "Couleur", "Nombre séances", "Durée Module"};
 
    public ModuleTableModele(List<Module> modules, float dureeSeance) {
        super();
        this.modules = modules;
        this.dureeSeance = dureeSeance;
    }
 
    public int getRowCount() {
        return modules.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 4){
        	return false;
        }else
        	return true;
    }
     
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
 
    public Class getColumnClass(int columnIndex){
    	switch(columnIndex){
    		case 2:
    			return Color.class;
    		default:
    			return Object.class;
    	}
    }
    
    public void addModule(Module module) {
        modules.add(module);
 
        fireTableRowsInserted(modules.size() -1, modules.size() -1);
    }
 
    public void removeModule(int rowIndex) {
        modules.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public List<Module> getModules(){
    	return modules;
    }
    
	public float getDureeSeance() {
		return dureeSeance;
	}

	public void setDureeSeance(float dureeSeance) {
		this.dureeSeance = dureeSeance;
	}
}