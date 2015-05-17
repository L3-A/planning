package vues;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * Classe permettant d'attribuer à la cellule contenant la couleur choisit pour le module
 */
public class CouleurModuleRenderer extends DefaultTableCellRenderer {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        Color color = (Color) value;
 
        setText("");
        setBackground(color);
        
        return this;  
    }
}