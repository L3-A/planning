package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modeles.CalendrierModele;
import modeles.CalendrierTableModele;


public class Seance extends JFrame implements ActionListener, WindowListener{
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
     * @param accueil
     */
    public Seance(Planning planning, List<metiers.Module> modules, int ligne, int colonne, int semaine, CalendrierTableModele calendrierTableModele, CalendrierModele calendrierModele){
    	this.planning = planning;
    	this.modules = modules;
    	this.ligne = ligne;
    	this.colonne = colonne;
    	this.semaine = semaine;
    	this.calendrierTableModele = calendrierTableModele;
    	this.calendrierModele = calendrierModele;
    	
		setTitle("Ajout d'une séaunce");
		setSize(300,250);
        setLocationRelativeTo(null);
        ajouterComposants();
    	this.addWindowListener(this);
    }
    
    /**
     * Méthode qui ajoute les composants graphiques à la vue
     */
	private void ajouterComposants(){
    	//Liste des module
    	liste = new JComboBox();
    	liste.addItem("Veuillez seléctionner un module");
    	for(metiers.Module unModule : modules){
    		liste.addItem(unModule.getNom());
    	}
    	liste.setSelectedIndex(0);
    	
    	//Ajout des différents composants au panel
    	panChoixModule = new JPanel();
    	creer = new JButton("Créer la séance");
    	choixModule = new JLabel("Veuillez choisir un module à placer");
        choixModule.setFont(new Font(null, Font.BOLD, 16));
    	panChoixModule.add(liste);
    	add(choixModule, BorderLayout.NORTH);
    	add(panChoixModule);
    	add(creer, BorderLayout.SOUTH);
    	creer.addActionListener(this);
	}
	
	/**
	 * Méthode appelée lors du clic sur le bouton creer
	 */
    public void actionPerformed(ActionEvent event) {
    	Object valeur = liste.getSelectedItem();
		metiers.Seance uneSeance = new metiers.Seance();
		metiers.Module module = new metiers.Module();
		
		module = uneSeance.convertirUneSeance(valeur.toString());

		module = recupInfoModule(module);

		uneSeance.setIndexColonne(colonne);
		uneSeance.setIndexLigne(ligne);
		uneSeance.setSemaine(semaine);
		uneSeance.setModule(module);
		int nbSeance = calendrierModele.getCalendrier().nbSeance(uneSeance);
		uneSeance.setNbSeanceModule(nbSeance);
		calendrierTableModele.addSeance(uneSeance);

		if(nbSeance > module.getNbSeance()){
			planning.updateIndicationSeance();
		}
		this.dispose();
	}

    public metiers.Module recupInfoModule(metiers.Module module){
    	for(metiers.Module unModule : modules){
    		if(unModule.getNom().equals(module.getNom())){
    			module.setAbreviation(unModule.getAbreviation());
    			module.setNbSeance(unModule.getNbSeance());
    			module.setCouleur(unModule.getCouleur());
    			break;
    		}
    	}
    	return module;
    }
	public void windowActivated(WindowEvent arg0){}
	public void windowClosed(WindowEvent arg0){}
	public void windowClosing(WindowEvent arg0){}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
}