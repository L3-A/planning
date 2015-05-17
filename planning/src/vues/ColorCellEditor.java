package vues;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ColorCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Color couleur;
    private JButton bouton;
    private JColorChooser colorChooser;
    private JDialog dialog;
 
    public ColorCellEditor() {
        super();
 
        bouton = new JButton();
        bouton.setActionCommand("change");
        bouton.addActionListener(this);
        bouton.setBorderPainted(false);
 
        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(bouton, "Pick a Color", true, colorChooser, this, null);
    }
 
    public void actionPerformed(ActionEvent e) {
        if ("change".equals(e.getActionCommand())) {
            bouton.setBackground(couleur);
            colorChooser.setColor(couleur);
            dialog.setVisible(true);
 
            fireEditingStopped();
        } else {
            couleur = colorChooser.getColor();
        }
    }
 
    public Object getCellEditorValue() {
        return couleur;
    }
 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        couleur = (Color)value;
 
        return bouton;
    }
}