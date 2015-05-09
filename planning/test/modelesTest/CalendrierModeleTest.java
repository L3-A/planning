package modelesTest;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Annee;
import modeles.CalendrierModele;

/**
 * @author Dylan
 * Classe CalendrierModeleTest
 */
public class CalendrierModeleTest {
	/**
	 * Attribut CalendrierModele calendrierModele;
	 */
	private CalendrierModele calendrierModele;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		calendrierModele = new CalendrierModele();
	}
	
	/**
	 * Test de la méthode construireCalendrier
	 */
	@Test
	public void testConstruireCalendrier() {
		Annee uneAnnee = new Annee();
		Calendar calendar;

		//Test avec l'année actuelle
		uneAnnee.setAnnee("2015");
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		Assert.assertEquals(2015, calendar.getWeekYear());
		
		//Test avec une année inférieure
		uneAnnee.setAnnee("2013");
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		Assert.assertEquals(2013, calendar.getWeekYear());
		
		//Test avec une année supérieure
		uneAnnee.setAnnee("2018");
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		Assert.assertEquals(2018, calendar.getWeekYear());	
	}
}