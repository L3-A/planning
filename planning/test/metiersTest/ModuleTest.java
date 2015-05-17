package metiersTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Module;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class ModuleTest {
	/**
	 * Attribut Module module
	 */
	private Module module;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		module = new Module();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, module.getNom());
		Assert.assertEquals(null, module.getAbreviation());
		Assert.assertEquals(0, module.getNbSeance());
		Assert.assertEquals(null, module.getCouleur());
	}
}