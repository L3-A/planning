package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.CalendrierModele;
import modeles.CalendrierTableModele;
import modeles.ModuleModele;
/**
 * Vue Seance
 * @author Dylan
 *
 */
public class Seance extends JFrame implements ActionListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JComboBox liste;
    private JButton creer;
    private JPanel panChoixModule;
    private JLabel choixModule;
    private List<metiers.Module> modules;
    private int ligne;
    private int colonne;
    private int semaine;
    private CalendrierTableModele calendrierTableModele;
	private CalendrierModele calendrierModele;
	private Planning planning;
    
    /**
     * Constructeur
     * @param planning : param�tre de type Planning
     * @param modules : param�tre de type List Module
     * @param ligne : param�tre de type int
     * @param colonne : param�tre de type int
     * @param semaine : param�tre de type int
     * @param calendrierTableModele : param�tre de type CalendrierTableModele
     * @param calendrierModele : param�tre de type CalendrierModele 
     */
    public Seance(Planning planning, List<metiers.Module> modules, int ligne, int colonne, int semaine, CalendrierTableModele calendrierTableModele, CalendrierModele calendrierModele){
    	this.planning = planning;
    	this.modules = modules;
    	this.ligne = ligne;
    	this.colonne = colonne;
    	this.semaine = semaine;
    	this.calendrierTableModele = calendrierTableModele;
    	this.calendrierModele = calendrierModele;
    	
		setTitle("Ajout d'une s�aunce");
		setSize(300,250);
        setLocationRelativeTo(null);
        ajouterComposants();
    }
    
    /**
     * M�thode qui ajoute les composants graphiques � la vue
     */
	private void ajouterComposants(){
    	//Liste des module
    	liste = new JComboBox();
    	liste.addItem("Veuillez sel�ctionner un module");
    	for(metiers.Module unModule : modules){
    		liste.addItem(unModule.getNom());
    	}
    	liste.setSelectedIndex(0);
    	
    	//Ajout des diff�rents composants au panel
    	panChoixModule = new JPanel();
    	creer = new JButton("Cr�er la s�ance");
    	choixModule = new JLabel("Veuillez choisir un module � placer");
        choixModule.setFont(new Font(null, Font.BOLD, 16));
    	panChoixModule.add(liste);
    	add(choixModule, BorderLayout.NORTH);
    	add(panChoixModule);
    	add(creer, BorderLayout.SOUTH);
    	creer.addActionListener(this);
	}
	
	/**
	 * M�thode appel�e lors du clic sur le bouton creer
	 */
    public void actionPerformed(ActionEvent event) {
    	Object valeur = liste.getSelectedItem();
		metiers.Seance uneSeance = new metiers.Seance();
		metiers.Module module = new metiers.Module();
		ModuleModele moduleModele = new ModuleModele();
		
		//Appel de la m�thode pour convertir le nom du module choisi
		module = moduleModele.convertirUneSeance(valeur.toString());

		//Appel de la m�thode qui permet de r�cup�rer les informations du module choisit
		module = moduleModele.recupInfoModule(module, modules);

		//Cr�ation de la s�ance
		uneSeance.setIndexColonne(colonne);
		uneSeance.setIndexLigne(ligne);
		uneSeance.setSemaine(semaine);
		uneSeance.setModule(module);
		int nbSeance = calendrierModele.nbSeanceAdd(uneSeance);
		uneSeance.setRangSeanceModule(nbSeance);
		calendrierTableModele.addSeance(uneSeance);
		
		//Test pour savoir si le rang du module de la s�ance ajout� est sup�rieur au nombre de s�ance du module
		if(nbSeance > module.getNbSeance()){
			planning.updateIndicationSeance();
		}
		this.dispose();
	}
}