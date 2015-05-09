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
		Annee uneAnnee = new Annee();
		Assert.assertEquals(uneAnnee.getAnnee(), annee.getAnnee());
	}
	
	/**
	 * Test de la méthode convertirUneAnnee
	 */
	@Test
	public void testConvertirUneAnnee() {
		annee = annee.convertirUneAnnee("2015-2016");
		Assert.assertEquals("2015", annee.getAnnee());
	}
	
	/**
	 * Test de la méthode anneeChoisit
	 */
	@Test
	public void testAnneeChoisit() {
		annee.setAnnee("2016");
		int anneeConvertie = annee.anneeChoisit(annee);
		Assert.assertEquals(2016, anneeConvertie);
	}
}