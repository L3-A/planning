package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import metiers.Annee;
import metiers.Calendrier;
import metiers.Serialiser;
import modeles.ListeAnneeModele;

public class ChoixAnnee extends JFrame implements ActionListener, WindowListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private JList liste;
    private JButton creer;
    private JPanel panChoixAnnee;
    private JLabel choixAnnee;
    private JCheckBox samediCheck;
    private JCheckBox dimancheCheck;
    private JLabel nonOuvres;
    private JPanel checkBox;
    private JScrollPane ascenseur;
    
	private ListeAnneeModele annee;
    private Serialiser serialise;
    private Accueil accueil;
    private Calendrier calendrier;
    private Annee uneAnnee;
    
    /**
     * Constructeur
     * @param accueil
     */
    public ChoixAnnee(Accueil accueil){
    	this.accueil = accueil;
		setTitle("Gestion d'emploi du temps");
		setSize(500,250);
        setLocationRelativeTo(null);
        ajouterComposants();
    	this.addWindowListener(this);
    }
    
    /**
     * Méthode qui ajoute les composants graphiques à la vue
     */
	private void ajouterComposants(){
    	//Cases à cocher pour la gestion des jours ouvrés / non ouvrés
    	nonOuvres = new JLabel("Jours Non-ouvrés");
    	nonOuvres.setFont(new Font(null, Font.BOLD, 12));
    	checkBox = new JPanel();
    	checkBox.add(nonOuvres);
    	samediCheck = new JCheckBox("Samedi");
    	dimancheCheck = new JCheckBox("Dimanche");
    	checkBox.add(samediCheck);
    	checkBox.add(dimancheCheck);

    	//Liste des années
    	annee = new ListeAnneeModele();
    	liste = new JList(annee);
    	liste.setSelectedIndex(5);
    	liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION) ;
    	ascenseur = new JScrollPane(liste);
    	ascenseur.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	
    	//Ajout des différents composants au panel
    	panChoixAnnee = new JPanel();
    	creer = new JButton("Créer le calendrier");
    	choixAnnee = new JLabel("Veuillez choisir une année scolaire pour créer le planning");
        choixAnnee.setFont(new Font(null, Font.BOLD, 16));
    	panChoixAnnee.setLayout(new BorderLayout());
    	panChoixAnnee.add(BorderLayout.CENTER, ascenseur);
    	panChoixAnnee.add(BorderLayout.SOUTH, checkBox);
    	add(choixAnnee, BorderLayout.NORTH);
    	add(panChoixAnnee);
    	add(creer, BorderLayout.SOUTH);
    	creer.addActionListener(this);
	}
	
	/**
	 * Méthode appelée lors du clic sur le bouton creer
	 */
    public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		boolean samedi = false;
		boolean dimanche = false;
		if (source.equals(creer)) {	
			if(samediCheck.isSelected()){
				samedi = true;
			}

			if(dimancheCheck.isSelected()){
				dimanche = true;
			}
			
            Object valeur = liste.getSelectedValue();
			calendrier= new Calendrier();
			uneAnnee = new Annee();
			
			uneAnnee = uneAnnee.convertirUneAnnee(valeur.toString());
			calendrier.setUneAnnee(uneAnnee);
			calendrier.setSamediOuvrable(samedi);
			calendrier.setDimancheOuvrable(dimanche);
			
			int anneeSuivante = uneAnnee.anneeChoisit(uneAnnee);
			anneeSuivante = anneeSuivante +1;
			
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("/Documents"));
		    
		    int retrival = chooser.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            String fw = (chooser.getSelectedFile()+"_Vierge_"+uneAnnee.getAnnee()+"_"+anneeSuivante+".dat");
		            serialise = new Serialiser();
		            serialise.setFichier(fw);
		            serialise.setCalendrier(calendrier);
					boolean reussi = serialise.serialiser();
					if(reussi == true){
				        JOptionPane.showMessageDialog(this,"Le fichier a été enregister dans " +fw+ " !", "Information", JOptionPane.INFORMATION_MESSAGE);						
					}else{
				        JOptionPane.showMessageDialog(this,"Une erreur inconnue est survenu lors de l'enregistrement du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
			new Planning(calendrier);
			this.setVisible(false);
		}
	}

	public void windowActivated(WindowEvent arg0){}
	public void windowClosed(WindowEvent arg0){
		accueil.setVisible(true);
	}
	public void windowClosing(WindowEvent arg0){}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
}