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
		Assert.assertEquals(false, calendrier.getDimancheOuvrable());
		Assert.assertEquals(false, calendrier.getSamediOuvrable());
		Assert.assertEquals(null, calendrier.getUneAnnee());
		Assert.assertEquals(0, calendrier.getSeances().size());

	}
}