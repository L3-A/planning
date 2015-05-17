package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import metiers.Module;
import modeles.ModuleTableModele;

public class ModuleTable extends JPanel implements TableModelListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private ModuleTableModele moduleTableModele;
	private float dureeSeance;
    private JTable tableau;
    
    public ModuleTable(List<Module> modules, float dureeSeance) {
        super();
        this.dureeSeance = dureeSeance;
        moduleTableModele = new ModuleTableModele(modules, dureeSeance);
        
        tableau = new JTable(moduleTableModele);
        tableau.getTableHeader().setBackground(Color.YELLOW);

        //Empêche le déplacement des colonne
        tableau.getTableHeader().setReorderingAllowed(false); 
        
        tableau.setDefaultRenderer(Color.class, new CouleurModuleRenderer());
        tableau.setDefaultEditor(Color.class, new ColorCellEditor());
        
        tableau.getModel().addTableModelListener(this);
        
        add(new JScrollPane(tableau), BorderLayout.NORTH);
    }
    
    public void addModule(metiers.Module module){
    	module.setDureeModule(module.getNbSeance()*dureeSeance);
    	moduleTableModele.addModule(module);
    }
    
    public void removeModule(ModuleTable moduleTable){
        int[] selection = tableau.getSelectedRows();
        if(selection.length > 0){
        	int option = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le module ?", "Suppression d'un module", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        	if(option != JOptionPane.NO_OPTION && 
        	   option != JOptionPane.CANCEL_OPTION && 
        	   option != JOptionPane.CLOSED_OPTION){

                for(int i = selection.length - 1; i >= 0; i--){
                	moduleTableModele.removeModule(selection[i]);
                }
        	}
        }else{
            JOptionPane.showMessageDialog(moduleTable, "Vous n'avez pas seléctionné de module !");
        }
    }
    
	public List<Module> getModules() {
		List<Module> modules = new ArrayList<Module>();
		modules = moduleTableModele.getModules();
		return modules;
	}

	public void tableChanged(TableModelEvent event) {
		int firstRow = event.getFirstRow();
        int lastRow = event.getLastRow();
        int mColIndex = event.getColumn();

        switch (event.getType()) {
          case TableModelEvent.UPDATE:
            if (firstRow == TableModelEvent.HEADER_ROW) {
                if (mColIndex == TableModelEvent.ALL_COLUMNS) {
                    // A column was added
                } else {
                    // Column mColIndex in header changed
                }
            } else {
                // The rows in the range [firstRow, lastRow] changed
                for (int r=firstRow; r<=lastRow; r++) {
                    // Row r was changed

                    if (mColIndex == TableModelEvent.ALL_COLUMNS) {
                        // All columns in the range of rows have changed
                    } else {
                        // Column mColIndex changed
                    }
                }
            }
            break;
        }
	}

	public float getDureeSeance() {
		return dureeSeance;
	}

	public void setDureeSeance(float dureeSeance) {
		this.dureeSeance = dureeSeance;
	}
	
    public void updateDureeTypeSeance(float duree){
    	moduleTableModele.setDureeSeance(duree);
    }
}