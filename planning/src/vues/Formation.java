package vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modeles.CalendrierModele;
import modeles.FormationModele;
/**
 * Vue Formation
 * @author Dylan
 */
public class Formation extends JFrame implements ActionListener, FocusListener{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private JButton boutonCreer;
	private JButton ajouter;
	private JButton supprimer;
	private JLabel labelNomFormation;
	private JLabel labelDureeTypeSeance;
	private JLabel labelModule;
	private JTextField txtNomFormation;
	private JTextField txtDureeTypeSeance;
	private JPanel panPrincipal;
	private JPanel panFormation;
	private JPanel panChamp;
	private JPanel panModule;
	private JPanel panBoutonModule;

	private ModuleTable moduleTable;
	private Planning planning;
	private metiers.Formation uneFormation;
	private CalendrierModele calendrierModele;
	private FormationModele formationModele;
	
	/**
	 * Constructeur
	 * @param planning : param�tre de type Planning
	 * @param calendrierModele : param�tre de type CalendrierModele
	 */
	public Formation(Planning planning, CalendrierModele calendrierModele){
        this.setTitle("Caract�ristique de la formation");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        
        this.planning = planning;
        this.setCalendrierModele(calendrierModele);
        
        uneFormation = calendrierModele.getCalendrier().getUneFormation();
        formationModele = new FormationModele(uneFormation);
        
		ajouterComposants();
        this.setVisible(true);
	}
	
	/**
	 * M�thode qui ajoute les composants graphiques � la JFrame
	 */
	private void ajouterComposants(){
        //Label caract�risant les champs de saisies
        labelNomFormation = new JLabel("* Nom de la Formation :");
        labelNomFormation.setFont(new Font(null, Font.PLAIN, 14));
        labelDureeTypeSeance = new JLabel("* Dur�e type d'une s�ance :");
        labelDureeTypeSeance.setFont(new Font(null, Font.PLAIN, 14));
    
        //Champs de saisies
		txtNomFormation  = new JTextField();
		txtNomFormation.setText(uneFormation.getNom());
		txtDureeTypeSeance  = new JTextField();    
		txtDureeTypeSeance.setText(formationModele.convertDureeTypeSeance(uneFormation.getDureeTypeSeance()));
		txtDureeTypeSeance.addFocusListener(this);
		
		//Panel contenant les diff�rents champs de saisis
		panChamp = new JPanel(new GridLayout(2,2,20,5));
		panChamp.add(labelNomFormation);
		panChamp.add(txtNomFormation);
		panChamp.add(labelDureeTypeSeance);
		panChamp.add(txtDureeTypeSeance);
		
		//Panel contenant le panChamp
		panFormation = new JPanel(new BorderLayout());
		panFormation.add(panChamp, BorderLayout.NORTH);
        
		labelModule = new JLabel("Liste des modules pour la formation : ");
		labelModule.setFont(new Font(null, Font.BOLD, 16));
		
		//Tableau pour la liste des modules
		moduleTable = new ModuleTable(uneFormation.getModules(), uneFormation.getDureeTypeSeance());
        
		//Boutons pour ajoute ou supprimer un module
		ajouter = new JButton(new AddAction(moduleTable));
        supprimer = new JButton(new RemoveAction(moduleTable));
		
        //Panel contenant les boutons pour la gestion des modules
		panBoutonModule = new JPanel();
		panBoutonModule.add(ajouter);
		panBoutonModule.add(supprimer);
		
		//Panen contenant les composants graphiques pour la gestion des modules
		panModule = new JPanel(new BorderLayout());	
		panModule.add(labelModule, BorderLayout.NORTH);
		panModule.add(moduleTable, BorderLayout.CENTER);
		panModule.add(panBoutonModule, BorderLayout.SOUTH);
		
        boutonCreer = new JButton("Valider les caract�ristiques la formation");
		boutonCreer.addActionListener(this);

		panPrincipal = new JPanel();
        panPrincipal.add(panFormation);
        panPrincipal.add(panModule);
        panPrincipal.add(boutonCreer);
        this.setContentPane(panPrincipal);
	}
	
	/**
	 * Classe interne qui permet de g�rer les actions sur le bouton ajouter un module
	 * @author Dylan
	 */
    private class AddAction extends AbstractAction {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Attribut ModuleTable moduleTable
		 */
		private ModuleTable moduleTable;
		
		/**
		 * Constructeur
		 * @param moduleTable
		 */
		private AddAction(ModuleTable moduleTable) {
			super("Ajouter un module");
			this.moduleTable = moduleTable;
		}
		
		/**
		 * M�thode appel�e lors du clic sur bouton ajouter
		 */
		public void actionPerformed(ActionEvent e) {
			vues.Module module = new vues.Module(moduleTable);
			module.setVisible(true);
		}
	}
    
	/**
	 * Classe interne qui permet de g�rer les actions sur le bouton supprimer un module
	 * @author Dylan
	 */
    private class RemoveAction extends AbstractAction {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * Attribut Moduletable moduleTable
		 */
		private ModuleTable moduleTable;

		/**
		 * constructeur
		 * @param moduleTable
		 */
		private RemoveAction(ModuleTable moduleTable) {
            super("Supprimmer le module");
			this.moduleTable = moduleTable;
        }
 
		/**
		 * M�thode appel�e lors du clic sur le bouton supprimer
		 */
        public void actionPerformed(ActionEvent e) {            
        	moduleTable.removeModule(moduleTable);
        }
    }
	
    /**
     * M�thode appel�e lors du clic sur le bouton cr�er
     * Elle permet d'enregistrer les caract�ristiques de la formation avec ses modules
     */
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == boutonCreer){
			//Test pour savoir si les champs sont vides
			if(txtNomFormation.getText().isEmpty()== true || txtDureeTypeSeance.getText().isEmpty()==true){
				JOptionPane.showMessageDialog(this, "Veuillez renseigner tous les champs obligatoires" ,"Donn�es manquantes",JOptionPane.ERROR_MESSAGE);
			}else if(formationModele.isValid(txtDureeTypeSeance.getText()) == true){
				//Enregistrement de la formation si aucun erreur
				String chaine = txtDureeTypeSeance.getText().replace(",", ".");
				float dureeTypeSeance = new Float(chaine);
				uneFormation = formationModele.updateFormation(txtNomFormation.getText(), dureeTypeSeance, moduleTable.getModules());
				planning.updateNomFormation(txtNomFormation.getText());
				this.dispose();
			}else{
				//Message d'erreur si la dur�e d'une s�ance n'est pas de type num�rique
				JOptionPane.showMessageDialog(this, "La dur�e type d'une s�ance doit �tre une valeur num�rique" ,"Erreur format",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void focusGained(FocusEvent arg0) {}
	
	/**
	 * M�thode appel�e sur la perte du focus du champs dureeTypeSeance
	 */
	public void focusLost(FocusEvent arg0) {
		if (txtDureeTypeSeance.getText().length()>0 && formationModele.isValid(txtDureeTypeSeance.getText()) == true) {
			String chaine = txtDureeTypeSeance.getText().replace(",", ".");
			float dureeTypeSeance = new Float(chaine);
			uneFormation.setDureeTypeSeance(dureeTypeSeance);
			moduleTable.updateDureeTypeSeance(dureeTypeSeance);
		}else{
			JOptionPane.showMessageDialog(this, "Une erreur s'est produite lors de la modification de dur�e type d'une s�ance" ,"Erreur format",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Accesseur en lecture
	 * @return calendrierModele
	 */
	public CalendrierModele getCalendrierModele() {
		return calendrierModele;
	}

	/**
	 * Accesseur en �criture
	 * @param calendrierModele : param�tre de type CalendrierModele
	 */
	public void setCalendrierModele(CalendrierModele calendrierModele) {
		this.calendrierModele = calendrierModele;
	}
}