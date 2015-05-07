package vues;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * Classe permettant de définir les caractéristiques des cellules
 * @author Dylan
 */
public class JTableRender extends DefaultTableCellRenderer {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur 
	 */
	public JTableRender(){
    }

	/**
	 * Méthode qui permet de modifier les caractéristiques des cellules des tableaux selon des conditions
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

          if (column == 5)
          {
        	  component.setBackground(Color.GRAY);
          }
          if (column == 6)
          {
        	  component.setBackground(Color.GRAY);
          }
          return component;
      }
}