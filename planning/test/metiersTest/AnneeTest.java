package metiersTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Annee;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class AnneeTest {
	/**
	 * Attribut Annee annee;
	 */
	private Annee annee;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		annee = new Annee();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, annee.getAnnee());
	}
}