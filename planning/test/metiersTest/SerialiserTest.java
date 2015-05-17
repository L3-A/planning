package metiersTest;

import java.io.File;

import metiers.Annee;
import metiers.Calendrier;
import metiers.Serialiser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Classe SerialiserTest
 */
public class SerialiserTest {
	/**
	 * Attribut Serialiser serialiser;
	 */
	private Serialiser serialiser;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		serialiser = new Serialiser();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals(null, serialiser.getCalendrier());
		Assert.assertEquals(null, serialiser.getFichier());
	}
	
	/**
	 * Test de la méthode serialiser
	 */
	@Test
	public void testSerialiser() {
		File fichier = new File("C:/Users/Dylan/Documents/Planning_Vierge_2016_2017.dat");
		Annee annee = new Annee();
		annee.setAnnee("2016");
		Calendrier calendrier = new Calendrier();
		calendrier.setDimancheOuvrable(false);
		calendrier.setSamediOuvrable(false);
		calendrier.setUneAnnee(annee);
		serialiser.setFichier(fichier);
		serialiser.setCalendrier(calendrier);
		Assert.assertEquals(true, serialiser.serialiser());
	}
}