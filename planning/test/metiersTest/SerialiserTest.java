package metiersTest;

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
	@Before // La procedure setup sera ex�cut�e avant chaque test
	public void setup() {
		serialiser = new Serialiser();
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Serialiser uneSerialiser= new Serialiser();
		Assert.assertEquals(uneSerialiser.getCalendrier(), serialiser.getCalendrier());
		Assert.assertEquals(uneSerialiser.getFichier(), serialiser.getFichier());
	}
	
	/**
	 * Test de la m�thode serialiser
	 */
	@Test
	public void testSerialiser() {
		String fichier = "C:/Users/Dylan/Documents/Planning_Vierge_2016_2017.dat";
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