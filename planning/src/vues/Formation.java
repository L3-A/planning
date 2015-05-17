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
 * Vue Planning
 * @author Ahmed
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
	public Formation(Planning planning, CalendrierModele calendrierModele){
        this.setTitle("Caractéristique de la formation");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        this.planning = planning;
        this.calendrierModele = calendrierModele;
        uneFormation = calendrierModele.getCalendrier().getUneFormation();
		formationModele = new FormationModele(uneFormation);
		uneFormation = formationModele.recupFormation();
		
        // LABEL
        labelNomFormation = new JLabel("* Nom de la Formation :");
        labelNomFormation.setFont(new Font(null, Font.PLAIN, 14));
        labelDureeTypeSeance = new JLabel("* Durée type d'une séance :");
        labelDureeTypeSeance.setFont(new Font(null, Font.PLAIN, 14));
    
        // TEXT
		txtNomFormation  = new JTextField();
		txtNomFormation.setText(uneFormation.getNom());
		txtDureeTypeSeance  = new JTextField();    
		txtDureeTypeSeance.setText(formationModele.convertDureeTypeSeance(uneFormation.getDureeTypeSeance()));
		txtDureeTypeSeance.addFocusListener(this);
		
		
		
		
		
		panChamp = new JPanel(new GridLayout(2,2,20,5));
		panChamp.add(labelNomFormation);
		panChamp.add(txtNomFormation);
		panChamp.add(labelDureeTypeSeance);
		panChamp.add(txtDureeTypeSeance);
				
		panFormation = new JPanel(new BorderLayout());
		panFormation.add(panChamp, BorderLayout.NORTH);
        
		labelModule = new JLabel("Liste des modules pour la formation : ");
		labelModule.setFont(new Font(null, Font.BOLD, 16));
		
		moduleTable = new ModuleTable(uneFormation.getModules(), uneFormation.getDureeTypeSeance());
		
        ajouter = new JButton(new AddAction(moduleTable));
        supprimer = new JButton(new RemoveAction(moduleTable));
		
		panBoutonModule = new JPanel();
		panBoutonModule.add(ajouter);
		panBoutonModule.add(supprimer);
		
		panModule = new JPanel(new BorderLayout());
		
		panModule.add(labelModule, BorderLayout.NORTH);
		panModule.add(moduleTable, BorderLayout.CENTER);
		panModule.add(panBoutonModule, BorderLayout.SOUTH);
		
        boutonCreer = new JButton("Valider les caractéristiques la formation");
		boutonCreer.addActionListener(this);

		panPrincipal = new JPanel();
        panPrincipal.add(panFormation);
        panPrincipal.add(panModule);
        panPrincipal.add(boutonCreer);
        this.setContentPane(panPrincipal);
	}
	
	
    private class AddAction extends AbstractAction {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		private ModuleTable moduleTable;
		private AddAction(ModuleTable moduleTable) {
			super("Ajouter un module");
			this.moduleTable = moduleTable;
		}
		
		public void actionPerformed(ActionEvent e) {
			vues.Module module = new vues.Module(moduleTable);
			module.setVisible(true);
		}
	}
    
    private class RemoveAction extends AbstractAction {
        /**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 1L;
		private ModuleTable moduleTable;

		private RemoveAction(ModuleTable moduleTable) {
            super("Supprimmer le module");
			this.moduleTable = moduleTable;
        }
 
        public void actionPerformed(ActionEvent e) {            
        	moduleTable.removeModule(moduleTable);
        }
    }
	
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == boutonCreer){
			if(txtNomFormation.getText().isEmpty()== true || txtDureeTypeSeance.getText().isEmpty()==true){
				JOptionPane.showMessageDialog(this, "Veuillez renseigner tous les champs obligatoires" ,"Données manquantes",JOptionPane.ERROR_MESSAGE);
			}else if(formationModele.isValid(txtDureeTypeSeance.getText()) == true){
				String chaine = txtDureeTypeSeance.getText().replace(",", ".");
				float dureeTypeSeance = new Float(chaine);
				uneFormation = formationModele.updateFormation(txtNomFormation.getText(), dureeTypeSeance, moduleTable.getModules());
				calendrierModele.getCalendrier().setUneFormation(uneFormation);
				planning.updateNomFormation(txtNomFormation.getText());
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "La durée type d'une séance doit être une valeur numérique" ,"Erreur format",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void focusGained(FocusEvent arg0) {}
	public void focusLost(FocusEvent arg0) {
		if (txtDureeTypeSeance.getText().length() < 8) {
			String chaine = txtDureeTypeSeance.getText().replace(",", ".");
			float dureeTypeSeance = new Float(chaine);
			uneFormation.setDureeTypeSeance(dureeTypeSeance);
			moduleTable.updateDureeTypeSeance(dureeTypeSeance);
			}
	}
}