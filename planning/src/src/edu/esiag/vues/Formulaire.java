package src.edu.esiag.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
 
public class Formulaire extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel container = new JPanel();
  private JComboBox combo = new JComboBox();
  private JLabel label = new JLabel("Une ComboBox");
  private JTable table = new JTable ();
  public Formulaire(){
    this.setTitle("Formulaire");
    this.setSize(300, 300);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(new BorderLayout());
    combo.setPreferredSize(new Dimension(100, 20));
   	table.setDefaultEditor(Color.class, new ColorCellEditor());
    JPanel top = new JPanel();
    top.add(label);
    top.add(combo);
    top.add(table);
    container.add(top, BorderLayout.NORTH);
    this.setContentPane(container);
    this.setVisible(true);            
  
  
  
  
  
  
  }
  
  

  
public class Fenetre extends JFrame {
  

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Fenetre(){
    //…
    combo.setPreferredSize(new Dimension(100, 20));
    combo.addItem("Option 1");
    combo.addItem("Option 2");
    combo.addItem("Option 3");
    combo.addItem("Option 4");

    //…         
  }
}
}