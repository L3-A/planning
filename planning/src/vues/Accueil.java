package vues;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import modeles.CalendrierModele;

/**
 * Vue Accueil
 * @author Dylan
 */
public class Accueil extends JFrame implements ActionListener, WindowListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JRadioButton nouveauPlanning;
	private JRadioButton ouvrirPlanning;
	private JPanel panAccueil;
	private ButtonGroup groupChoixAccueil;;
	private JButton buttonOk;
	private JFileChooser chooser;
	
	private ChoixAnnee choixAnnee;
	private CalendrierModele calendrierModele;

	/**
	 * Constructeur
	 */
	public Accueil(){
		setTitle("Gestion d'emploi du temps");
		setSize(500,200);
        this.setLocationRelativeTo(null);
		ajouterComposants();
		this.addWindowListener(this);
	}
	
	/**
	 * Méthode qui ajoute les composants graphiques à la vue
	 */
	private void ajouterComposants(){
        panAccueil = new JPanel();
        panAccueil.setBorder(new TitledBorder("Que voulez-vous faire ?"));
		
        //Gestion des radios boutons
        nouveauPlanning = new JRadioButton("Créer un nouveau planning");
        ouvrirPlanning = new JRadioButton("Ouvrir un planning existant");
        
        //Ajout des boutons radios au groupe de boutton qui permet de sélectionner un seul bouton radio
        groupChoixAccueil = new ButtonGroup();
        groupChoixAccueil.add(nouveauPlanning);
        groupChoixAccueil.add(ouvrirPlanning);

        //Ajout des boutons radios au panel
        panAccueil.add(nouveauPlanning);
        panAccueil.add(ouvrirPlanning);
    	
        //Ajout du panel accueil et du bouton ok au contenu
        Container contenu = getContentPane();
    	contenu.add(panAccueil);
        buttonOk = new JButton("Ok");
    	contenu.add(buttonOk, "South");
    	buttonOk.addActionListener(this);		
	}

	public static void main(String[] args) {
    	Accueil frame = new Accueil();
    	frame.setVisible(true);
    }

	/**
	 * Méthode appelée lors du clic sur le bouton OK
	 */
	public void actionPerformed(ActionEvent arg0) {
		//Si le bouton radio ouvrir un planning existant est coché
		if(ouvrirPlanning.isSelected()){
			//Affichage de la chooser pour ouvrir d'un planning existant
		    chooser = new JFileChooser();
		    chooser.setVisible(true);
		    chooser.setCurrentDirectory(new File("/Documents"));
		    int retrival = chooser.showOpenDialog(null);

		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            File fichier = chooser.getSelectedFile();
		            calendrierModele = new CalendrierModele();
					calendrierModele.setCalendrier(calendrierModele.openFichier(fichier));

					List<metiers.Seance> seances = new ArrayList<metiers.Seance>();
					new Planning(calendrierModele, fichier, seances);

					this.dispose();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
			//Si le bouton radio nouveau un planning est coché
		}else if(nouveauPlanning.isSelected()){
			//Affichage du formulaire de choix de l'année pour la création du planning
			choixAnnee = new ChoixAnnee();
			choixAnnee.setVisible(true);
			this.dispose();
		}else{
			//Pas de choix sélectionné
	        JOptionPane.showMessageDialog(this,"Aucune action n'a été choisit !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}