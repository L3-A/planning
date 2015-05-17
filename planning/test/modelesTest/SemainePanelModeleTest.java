package modelesTest;

import java.util.Calendar;

import metiers.Annee;
import metiers.Calendrier;
import modeles.CalendrierModele;
import modeles.SemainePanelModele;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Classe SemainePanelTest
 */
public class SemainePanelModeleTest {
	/**
	 * Attribut SemainePanel SemainePanel;
	 */
	private SemainePanelModele semainePanelModele;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2017");
		Calendrier calendrier = new Calendrier();
		calendrier.setDimancheOuvrable(true);
		calendrier.setSamediOuvrable(false);
		calendrier.setUneAnnee(uneAnnee);
		
		CalendrierModele calendrierModele = new CalendrierModele(calendrier);
		
		semainePanelModele = new SemainePanelModele(calendrierModele);
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2017");
		Calendar calendar = semainePanelModele.getCalendrierModele().construireCalendrier(uneAnnee);
		Assert.assertEquals(true, semainePanelModele.getCalendrierModele().getCalendrier().getDimancheOuvrable());
		Assert.assertEquals(false, semainePanelModele.getCalendrierModele().getCalendrier().getSamediOuvrable());
		Assert.assertEquals("2017", semainePanelModele.getCalendrierModele().getCalendrier().getUneAnnee().getAnnee());
		Assert.assertEquals(2017, calendar.getWeekYear());
	}
	
	/**
	 * Test de la méthode copieCalendar
	 */
	@Test
	public void testCopieCalendar() {
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2017");
		Calendar calendar = semainePanelModele.getCalendrierModele().construireCalendrier(uneAnnee);
		Calendar copy = semainePanelModele.copieCalendar(calendar);
		
		Assert.assertEquals(2017, copy.getWeekYear());
	}
}