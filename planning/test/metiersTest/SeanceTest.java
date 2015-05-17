package metiersTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Seance;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class SeanceTest {
	/**
	 * Attribut Seance seance
	 */
	private Seance seance;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		seance = new Seance();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(0, seance.getIndexColonne());
		Assert.assertEquals(0, seance.getIndexLigne());
		Assert.assertEquals(0, seance.getRangSeanceModule());
		Assert.assertEquals(null, seance.getModule());
		Assert.assertEquals(0, seance.getSemaine());
	}
}