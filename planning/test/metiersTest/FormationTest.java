package metiersTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Formation;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class FormationTest {
	/**
	 * Attribut Formation formation
	 */
	private Formation formation;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		formation = new Formation();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, formation.getNom());
		Assert.assertEquals(0,0, formation.getDureeTypeSeance());
		Assert.assertEquals(0, formation.getModules().size());
	}
}