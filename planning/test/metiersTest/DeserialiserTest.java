package metiersTest;

import java.io.File;

import metiers.Calendrier;
import metiers.Deserialiser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Classe DeserialiserTest
 */
public class DeserialiserTest {
	/**
	 * Attribut Serialiser serialiser;
	 */
	private Deserialiser deserialiser;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		deserialiser = new Deserialiser();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, deserialiser.getCalendrier());
		Assert.assertEquals(null, deserialiser.getFichier());
	}
	
	/**
	 * Test de la méthode deserialiser
	 */
	@Test
	public void testDeserialiser() {
		String curDir = System.getProperty("user.dir");
		File fichier = new File(curDir+"/documents/Planning_2016_2017.dat");
		Calendrier calendrier = new Calendrier();
		deserialiser.setCalendrier(calendrier);
		deserialiser.setFichier(fichier);
		calendrier = deserialiser.deserialiser();
				
		Assert.assertEquals(false, calendrier.getDimancheOuvrable());
		Assert.assertEquals(false, calendrier.getSamediOuvrable());
		Assert.assertEquals("2016", calendrier.getUneAnnee().getAnnee());
	}
}