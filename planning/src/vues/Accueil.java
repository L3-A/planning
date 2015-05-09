package vues;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import metiers.Calendrier;
import metiers.Deserialiser;

/**
 * Vue Accueil
 * @author Dylan
 */
public class Accueil extends JFrame implements ActionListener{
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
	
	private Deserialiser deserialise;
	private ChoixAnnee choixAnnee;
	private Calendrier calendrier;

	/**
	 * Constructeur
	 */
	public Accueil(){
		setTitle("Gestion d'emploi du temps");
		setSize(500,200);
        this.setLocationRelativeTo(null);
		ajouterComposants();
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					calendrier = new Calendrier();
					deserialise = new Deserialiser();
					deserialise.setFichier(fichier);
					deserialise.setCalendrier(calendrier);
					calendrier = deserialise.deserialiser();
					new Planning(calendrier);
					this.setVisible(false);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
			//Si le bouton radio nouveau un planning est coché
		}else if(nouveauPlanning.isSelected()){
			//Affichage du formulaire de choix de l'année pour la création du planning
			choixAnnee = new ChoixAnnee(this);
			choixAnnee.setVisible(true);
			this.setVisible(false);
		}else{
			//Pas de choix sélectionné
	        JOptionPane.showMessageDialog(this,"Aucune action n'a été choisit !", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}
}