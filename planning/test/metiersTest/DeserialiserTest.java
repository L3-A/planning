package metiersTest;

import java.io.File;

import metiers.Annee;
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
		Deserialiser uneDeserialiser= new Deserialiser();
		Assert.assertEquals(uneDeserialiser.getCalendrier(), deserialiser.getCalendrier());
		Assert.assertEquals(uneDeserialiser.getFichier(), deserialiser.getFichier());
	}
	
	/**
	 * Test de la méthode deserialiser
	 */
	@Test
	public void testDeserialiser() {
		File fichier = new File("C:/Users/Dylan/Documents/Planning_Vierge_2016_2017.dat");
		Calendrier calendrier = new Calendrier();
		deserialiser.setCalendrier(calendrier);
		deserialiser.setFichier(fichier);
		calendrier = deserialiser.deserialiser();
		
		Calendrier calendrier2 = new Calendrier();
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2016");
		calendrier2.setDimancheOuvrable(false);
		calendrier2.setSamediOuvrable(false);
		calendrier2.setUneAnnee(uneAnnee);
		
		Assert.assertEquals(calendrier2.getDimancheOuvrable(), calendrier.getDimancheOuvrable());
		Assert.assertEquals(calendrier2.getSamediOuvrable(), calendrier.getSamediOuvrable());
		Assert.assertEquals(calendrier2.getUneAnnee().getAnnee(), calendrier.getUneAnnee().getAnnee());
	}
}