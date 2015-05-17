package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.ModuleModele;
/**
 * Vue Module
 * @author Dylan
 */
public class Module extends JFrame implements ActionListener, MouseListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelNomModule;
	private JLabel labelAbreviation;
	private JLabel labelCouleur;
	private JLabel labelNBSeance;
	private JTextField txtNomModule;
	private JTextField txtAbreviation;
	private JTextField txtCouleur;
	private JTextField txtNBSeance;
	private JPanel panChamp;
	private JPanel panModule;
	private JPanel panPrincipal;
	private JButton boutonCreer;
	private Color couleur;
	private metiers.Module module;
	private ModuleTable moduleTable;
	private ModuleModele moduleModele;
	
	/**
	 * Constructeur
	 * @param moduleTable : paramètre de type ModuleTable
	 */
	public Module(ModuleTable moduleTable){
		this.moduleTable = moduleTable;
		moduleModele = new ModuleModele(moduleTable);
        this.setTitle("Caractéristique d'un module");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        ajouterComposants();
        this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	private void ajouterComposants(){
        //Label permettant d'identifier les champs de saisis
        labelNomModule = new JLabel("* Nom du module :");
        labelNomModule.setFont(new Font(null, Font.PLAIN, 14));
        labelAbreviation = new JLabel("* Abréviation :");
        labelAbreviation.setFont(new Font(null, Font.PLAIN, 14));
        labelCouleur = new JLabel("* Couleur :");
        labelCouleur.setFont(new Font(null, Font.PLAIN, 14));
        labelNBSeance = new JLabel("* Nombre de séances :");
        labelNBSeance.setFont(new Font(null, Font.PLAIN, 14));
        
        //Champs de saisis
		txtNomModule = new JTextField();
		txtAbreviation = new JTextField();  
		txtCouleur = new JTextField();
		txtNBSeance = new JTextField();
		txtCouleur.addMouseListener(this);
		txtCouleur.disable();
		
		//Ajout des label et champs de saisis au panChamp
		panChamp = new JPanel(new GridLayout(4,2,20,5));
		panChamp.add(labelNomModule);
		panChamp.add(txtNomModule);
		panChamp.add(labelAbreviation);
		panChamp.add(txtAbreviation);
		panChamp.add(labelCouleur);
		panChamp.add(txtCouleur);
		panChamp.add(labelNBSeance);
		panChamp.add(txtNBSeance);
		
		panModule = new JPanel(new BorderLayout());
		panModule.add(panChamp, BorderLayout.NORTH);
		
        boutonCreer = new JButton("Créer la module");
		boutonCreer.setPreferredSize(new Dimension(200, 30));
		boutonCreer.addActionListener(this);
		
		panPrincipal = new JPanel();
        panPrincipal.add(panModule);
        panPrincipal.add(boutonCreer);
        this.setContentPane(panPrincipal);
	}
	
	/**
	 * Méthode appelée lors du clique sur le bouton créer
	 * Permet de créer un nouveau module
	 */
	public void actionPerformed(ActionEvent event){
		Object source = event.getSource();
		if(source == boutonCreer){
			//Test pour savoir si les champs sont vides
			if(txtNomModule.getText().isEmpty()== true || txtAbreviation.getText().isEmpty()==true
					||txtNBSeance.getText().isEmpty()==true){
				JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires" ,"Données manquantes",1);
			}else if(moduleModele.isValid(txtNBSeance.getText()) == true){
				String message = moduleModele.verifModule(txtNomModule.getText(), txtAbreviation.getText(), txtCouleur.getBackground());
				if(!message.equals("")){
					//Si le module existe déjà, on affiche un message d'erreur
					JOptionPane.showMessageDialog(this,message,"Module existant",JOptionPane.INFORMATION_MESSAGE);
				}else{
					//Ajout du module si tout est correct
					int nbSeance = new Integer(txtNBSeance.getText());
					module = new metiers.Module(txtNomModule.getText(), txtAbreviation.getText(), txtCouleur.getBackground(), nbSeance);
					moduleTable.addModule(module);
					this.dispose();
				}
			}else{
				//Affichage d'un mesage d'erreur si le nombre de séance n'est pas de type nuémrique
				JOptionPane.showMessageDialog(this, "Le nombre de séance doit être une valeur numérique" ,"Erreur format",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Méthode appelée lors du clique sur le champ pour ajouter une couleur au module
	 */
	public void mouseClicked(MouseEvent arg0) {
		couleur = JColorChooser.showDialog(null, "couleur du fond", Color.WHITE);
		txtCouleur.setBackground(couleur);
	}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}