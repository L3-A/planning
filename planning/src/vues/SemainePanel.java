package vues;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import metiers.Annee;
import metiers.Calendrier;

/**
 * Vue SemainePanel intégré au planning
 * @author Dylan
 */
public class SemainePanel extends JPanel {
	 
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private int CALENDAR_START = Calendar.SEPTEMBER;
	private static DateFormat DATE_DEBUT_FORMAT = new SimpleDateFormat("dd");
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMMM");
	private Color COLOR_ODD = Color.WHITE;
	private Color COLOR_SELECTION = Color.YELLOW;
	private Calendar calendar;
	private Calendar selection; 
	private Planning planning;
	private Calendrier calendrier;
	private Annee uneAnnee;

	private JLabel[] semainesLabels;
	private JPanel semainePanel;
	//Sert à retrouver la position d'une semaine dans la liste de label
	private Map<Integer, Integer> weeks=new HashMap<Integer, Integer>();
 
	/**
	 * Constructeur
	 */
	public SemainePanel(Planning planning, Calendar calendar, Calendrier calendrier){
		this.planning = planning;
		this.calendar = calendar;
		this.calendrier = calendrier;

		selection = copieCalendar(calendar);
 
		setLayout(new BorderLayout());
 				
		Calendar premiereSemaine = getPremiereSemaine(CALENDAR_START); 
		Calendar derniereSemaine = getDerniereSemaine(CALENDAR_START);
 
		int nbSemaines = compteSemaines(premiereSemaine, derniereSemaine);
		semainesLabels = new JLabel[nbSemaines];
 
		semainePanel = new JPanel(new GridLayout(1, 0));
 
		Calendar semaine = Calendar.getInstance(); //Parcours des semaines
		semaine.setTime(premiereSemaine.getTime()); //Parcours de la première à la dernière
		for(int i=0; i<nbSemaines; i++) {
			final Calendar week = copieCalendar(semaine);
			weeks.put(week.get(Calendar.WEEK_OF_YEAR), i); //Permet de trouver le numéro d'une semaine dans la liste de label
			JLabel label = new JLabel(getSemaineLabel(semaine), JLabel.CENTER);
			label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			label.setOpaque(true);
			semainesLabels[i]=label;
			setLabelCouleur(week);
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
		setLabelCouleur(selection);

		JScrollPane slider = new JScrollPane(semainePanel);
		slider.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(slider, BorderLayout.CENTER);
	}
 
	/**
	 * Méthode qui copie le calendar
	 * @param calendar
	 * @return calendar
	 */
	private Calendar copieCalendar(Calendar calendar){
		Calendar copy = Calendar.getInstance();
		copy.setTime(calendar.getTime());
		return copy;
 	}
 
 
	/**
	 * Méthode qui conmpte le nombre de semaine entre la première et la dernière
	 * @param premiereSemaine
	 * @param derniereSemaine
	 * @return le nombre de semaines
	 */
	private int compteSemaines(Calendar premiereSemaine, Calendar derniereSemaine){
        Calendar calendar = copieCalendar(premiereSemaine);
        int count=0;
        //Tant qu'on a pas atteint la dernière semaine
		while(calendar.get(Calendar.WEEK_OF_YEAR)!=derniereSemaine.get(Calendar.WEEK_OF_YEAR)){
			count++; // on compte une semaine de plus
			calendar.add(Calendar.WEEK_OF_YEAR, 1); //On avance d'une semaine
		}
		//Ajoute 1 pour compter la dernière semaine (qu'on a pas compter dans la boucle)
		return count + 1; 
	}
 
	/**
	 * Méthode qui retourne la première semaine d'un mois
	 * @param mois
	 * @return calendar
	 */
	public Calendar getPremiereSemaine(int mois){
    	uneAnnee = new Annee();
    	uneAnnee = calendrier.getUneAnnee();
        
    	Calendar calendar = calendrier.construireCalendrier(uneAnnee);
		
    	//On se positionne sur le mois demandé 
        calendar.set(Calendar.MONTH, mois); 
        //On se positionne sur la première semaine du mois
        calendar.set(Calendar.WEEK_OF_MONTH, 1); 
        //On se positionne sur le premier jour officiel
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
 
        return calendar;
	}
 
	/**
	 * Méthode qui retourne la dernière semaine d'un moi
	 * @param mois
	 * @return calendar
	 */
	public Calendar getDerniereSemaine(int mois){
		//On se place sur la première semaine du mois
		Calendar calendar = getPremiereSemaine(mois);
		//On avance d'un an
		calendar.add(Calendar.YEAR, 1);
		//On recule d'une semaine
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		return calendar;
	}
 
	/**
	 * Méthode qui affecte une couleur aux labels des semaines
	 * @param calendar
	 */
	private void setLabelCouleur(Calendar calendar){
		//On utilise la map pour savoir le numéro du label correspondant à la semaine
		JLabel label=semainesLabels[weeks.get(calendar.get(Calendar.WEEK_OF_YEAR))];
		if (calendar.equals(selection) ) {
			label.setBackground(COLOR_SELECTION); //La semaine sélectionnée
		}else{
			label.setBackground(COLOR_ODD);
		} 
	}
 
	/**
	 * Méthode qui modifie la sélection
	 */
	public void updateSelection() {
		Calendar oldSelection = selection;
		selection = copieCalendar(calendar);
		setLabelCouleur(oldSelection);
		setLabelCouleur(selection);
		semainePanel.scrollRectToVisible(semainesLabels[weeks.get(selection.get(Calendar.WEEK_OF_YEAR))].getBounds());
	}
 
	/**
	 * Méthode qui attribut la sélection
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
	 * Méthode qui retourne le libellé d'une semaine
	 * @param semaine
	 * @return libellé d'une semaine
	 */
	private String getSemaineLabel(Calendar semaine) {
 
		Calendar calendar = copieCalendar(semaine);
 
		//Le premier jour de la semaine
		Date premierJour = calendar.getTime();
		int monthPremierJour = calendar.get(Calendar.MONTH);
 
		//Le dernier jour cette semaine
		calendar.add(Calendar.WEEK_OF_YEAR, 1);
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		Date dernierJour = calendar.getTime();
 
		int monthDernierJour = calendar.get(Calendar.MONTH);
 
        return getSemaineLabel(semaine.get(Calendar.WEEK_OF_YEAR), premierJour, dernierJour, monthPremierJour, monthDernierJour);		
	}
 
	/**
	 * Méthode qui retourne le libellé d'une semaine pour l'afficher dans le panel
	 * @param semaine
	 * @return libellé d'une semaine
	 */
	public static String getSemaineLabel(int week, Date premierJour, Date dernierJour, int monthPremierJour, int monthDernierJour) {
		String debut;
		if (monthDernierJour == monthPremierJour) {
			debut = DATE_DEBUT_FORMAT.format(premierJour);
		} else {
			debut = DATE_FORMAT.format(premierJour);
		}
 
		String milieu = DATE_FORMAT.format(dernierJour);
 
		String fin = " (" + week + ")";
 
		return debut + " - " + milieu + fin;
	}
}