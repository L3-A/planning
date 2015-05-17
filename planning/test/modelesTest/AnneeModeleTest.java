package modelesTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Annee;
import modeles.AnneeModele;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class AnneeModeleTest {
	/**
	 * Attribut AnneeModele anneeModele
	 */
	private AnneeModele anneeModele;
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
		anneeModele = new AnneeModele(annee);
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, annee.getAnnee());
	}
	
	/**
	 * Test de la méthode convertirUneAnnee
	 */
	@Test
	public void testConvertirUneAnnee() {
		annee = anneeModele.convertirUneAnnee("2015-2016");
		Assert.assertEquals("2015", annee.getAnnee());
	}
	
	/**
	 * Test de la méthode anneeChoisit
	 */
	@Test
	public void testAnneeChoisit() {
		annee.setAnnee("2016");
		int anneeConvertie = anneeModele.anneeChoisit(annee);
		Assert.assertEquals(2016, anneeConvertie);
	}
}