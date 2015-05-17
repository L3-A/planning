package vues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import modeles.CalendrierModele;
import modeles.SemainePanelModele;

/**
 * Vue SemainePanel int�gr� au planning
 * @author Dylan
 */
public class SemainePanel extends JPanel {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int CALENDAR_START = Calendar.SEPTEMBER;
	private Color COLOR_ODD = Color.WHITE;
	private Color COLOR_SELECTION = Color.YELLOW;
	private Calendar calendar;
	private Calendar selection; 
	private Planning planning;
	private CalendrierModele calendrierModele;
	private SemainePanelModele semainePanelModele;
	
	private JLabel[] semainesLabels;
	private JPanel semainePanel;
	//Sert � retrouver la position d'une semaine dans la liste de label
	private Map<Integer, Integer> weeks=new HashMap<Integer, Integer>();
 
	/**
	 * Constructeur
	 */
	public SemainePanel(Planning planning, Calendar calendar, CalendrierModele calendrierModele){
		this.planning = planning;
		this.calendar = calendar;
		this.calendrierModele = calendrierModele;

		semainePanelModele = new SemainePanelModele(calendrierModele);
		selection = semainePanelModele.copieCalendar(calendar);

		ajouterComposants();
	}
	
	/**
	 * M�thode ajoute qui permet d'ajouter les diff�rents composant au panel des semaines
	 */
	private void ajouterComposants(){
		setLayout(new BorderLayout());
 				
		Calendar premiereSemaine = semainePanelModele.getPremiereSemaine(CALENDAR_START); 
		Calendar derniereSemaine = semainePanelModele.getDerniereSemaine(CALENDAR_START);
 
		int nbSemaines = semainePanelModele.compteSemaines(premiereSemaine, derniereSemaine);
		semainesLabels = new JLabel[nbSemaines];
 
		semainePanel = new JPanel(new GridLayout(1, 0));
 
		Calendar semaine = Calendar.getInstance(); //Parcours des semaines
		semaine.setTime(premiereSemaine.getTime()); //Parcours de la premi�re � la derni�re
		for(int i=0; i<nbSemaines; i++) {
			final Calendar week = semainePanelModele.copieCalendar(semaine);
			weeks.put(week.get(Calendar.WEEK_OF_YEAR), i); //Permet de trouver le num�ro d'une semaine dans la liste de label
			JLabel label = new JLabel(semainePanelModele.getSemaineLabel(semaine), JLabel.CENTER);
			label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			label.setOpaque(true);
			semainesLabels[i]=label;
			this.setLabelCouleur(week);
			label.setEnabled(true);
			label.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					setSelection(week);
				}
			});
			semainePanel.add(label);
			semaine.add(Calendar.WEEK_OF_YEAR, 1); //On avance d'une semaine
		}
		this.setLabelCouleur(selection);

		JScrollPane slider = new JScrollPane(semainePanel);
		slider.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(slider, BorderLayout.CENTER);
	}
	
	/**
	 * M�thode qui affecte une couleur aux labels des semaines
	 * @param calendar
	 */
	private void setLabelCouleur(Calendar calendar){
		//On utilise la map pour savoir le num�ro du label correspondant � la semaine
		JLabel label=semainesLabels[weeks.get(calendar.get(Calendar.WEEK_OF_YEAR))];
		if (calendar.equals(selection) ) {
			label.setBackground(COLOR_SELECTION); //La semaine s�lectionn�e
		}else{
			label.setBackground(COLOR_ODD);
		} 
	}
	
	/**
	 * M�thode qui modifie la s�lection
	 */
	public void updateSelection() {
		Calendar oldSelection = selection;
		selection = semainePanelModele.copieCalendar(calendar);
		setLabelCouleur(oldSelection);
		setLabelCouleur(selection);
		semainePanel.scrollRectToVisible(semainesLabels[weeks.get(selection.get(Calendar.WEEK_OF_YEAR))].getBounds());
	}
	
	/**
	 * M�thode qui attribut la s�lection
	 * @param selection
	 */
	private void setSelection(Calendar selection) {
		Calendar oldSelection = this.selection;
		this.selection = selection;
		setLabelCouleur(oldSelection);
		setLabelCouleur(selection);
		planning.setSemaine(selection);
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
	 * @param calendrierModele
	 */
	public void setCalendrierModele(CalendrierModele calendrierModele) {
		this.calendrierModele = calendrierModele;
	}
}