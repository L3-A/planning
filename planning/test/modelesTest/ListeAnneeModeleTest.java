package modelesTest;

import modeles.ListeAnneeModele;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Clase ListeAnneeModeleTest
 */
public class ListeAnneeModeleTest {
	/**
	 * Attribut CalendrierModele calendrierModele;
	 */
	private ListeAnneeModele listeAnneeModele;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera ex�cut�e avant chaque test
	public void setup() {
		listeAnneeModele = new ListeAnneeModele();
	}
	
	/**
	 * Test de la m�thode getElementAt
	 */
	@Test
	public void testGetElementAt() {
		Assert.assertEquals("2016-2017", listeAnneeModele.getElementAt(6));
	}
	
	/**
	 * Test de la m�thode getSize
	 */
	@Test
	public void testGetSize() {
		Assert.assertEquals(10, listeAnneeModele.getSize());
	}
}