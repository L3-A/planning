package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import modeles.AnneeModele;
import modeles.CalendrierModele;
import modeles.ListeAnneeModele;
/**
 * Vue ChoixAnnee
 * @author Dylan
 */
public class ChoixAnnee extends JFrame implements ActionListener{
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
	private CalendrierModele calendrierModele;
    private Calendrier calendrier;
    private Annee uneAnnee;
    private AnneeModele anneeModele;
    
    /**
     * Constructeur
     */
    public ChoixAnnee(){
		setTitle("Gestion d'emploi du temps");
		setSize(500,250);
        setLocationRelativeTo(null);
        ajouterComposants();
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
			anneeModele = new AnneeModele(uneAnnee);
			
			uneAnnee = anneeModele.convertirUneAnnee(valeur.toString());
			calendrier.setUneAnnee(uneAnnee);
			calendrier.setSamediOuvrable(samedi);
			calendrier.setDimancheOuvrable(dimanche);
			
			calendrierModele = new CalendrierModele(calendrier);
			
			int anneeSuivante = anneeModele.anneeChoisit(uneAnnee);
			anneeSuivante = anneeSuivante +1;
			
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("/Documents"));
        	
		    File fw = null;
		    File file = new File("Planning_"+uneAnnee.getAnnee()+"_"+anneeSuivante+".dat");
        	chooser.setSelectedFile(file);
		    
        	int retrival = chooser.showSaveDialog(null);
		    if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		            fw = (chooser.getSelectedFile());
					boolean reussi = calendrierModele.saveFichier(fw);
					if(reussi == true){
				        JOptionPane.showMessageDialog(this,"Le fichier a été enregistré dans " +fw+ " !", "Information", JOptionPane.INFORMATION_MESSAGE);						
					}else{
				        JOptionPane.showMessageDialog(this,"Une erreur inconnue est survenu lors de l'enregistrement du fichier !", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		    List<metiers.Seance> seances = new ArrayList<metiers.Seance>();
			new Planning(calendrierModele, fw, seances);
			this.dispose();
		}
	}
}