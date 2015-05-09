package metiersTest;

import metiers.Calendrier;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Classe CalendrierTest
 */
public class CalendrierTest {
	/**
	 * Attribut Calendrier calendrier;
	 */
	private Calendrier calendrier;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		calendrier = new Calendrier();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Calendrier unCalendrier = new Calendrier();
		Assert.assertEquals(unCalendrier.getDimancheOuvrable(), calendrier.getDimancheOuvrable());
		Assert.assertEquals(unCalendrier.getSamediOuvrable(), calendrier.getSamediOuvrable());
		Assert.assertEquals(unCalendrier.getUneAnnee(), calendrier.getUneAnnee());
	}
}