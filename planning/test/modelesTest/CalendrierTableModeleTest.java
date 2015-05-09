package modelesTest;

import java.util.Calendar;

import metiers.Annee;
import metiers.Calendrier;
import modeles.CalendrierModele;
import modeles.CalendrierTableModele;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dylan
 * Classe CalendrierTableModeleTest
 */
public class CalendrierTableModeleTest {
	/**
	 * Attribut CalendrierTableModele calendrierTableModele;
	 */
	private CalendrierTableModele calendrierTableModele;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2016");
		Calendrier calendrier = new Calendrier();
		calendrier.setDimancheOuvrable(true);
		calendrier.setSamediOuvrable(false);
		calendrier.setUneAnnee(uneAnnee);
		
		CalendrierModele calendrierModele = new CalendrierModele();
		Calendar calendar;
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		calendrierTableModele = new CalendrierTableModele(calendar, calendrier);
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Annee uneAnnee = new Annee();
		uneAnnee.setAnnee("2016");
		Calendrier calendrier = new Calendrier();
		calendrier.setDimancheOuvrable(true);
		calendrier.setSamediOuvrable(false);
		calendrier.setUneAnnee(uneAnnee);
		
		CalendrierModele calendrierModele = new CalendrierModele();
		Calendar calendar;
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		
		CalendrierTableModele unCalendrierTableModele = new CalendrierTableModele(calendar, calendrier);
		
		Assert.assertEquals(unCalendrierTableModele.getCalendar().getTime(), calendrierTableModele.getCalendar().getTime());
		Assert.assertEquals(unCalendrierTableModele.getCalendrier().getDimancheOuvrable(), calendrierTableModele.getCalendrier().getDimancheOuvrable());
		Assert.assertEquals(unCalendrierTableModele.getCalendrier().getSamediOuvrable(), calendrierTableModele.getCalendrier().getSamediOuvrable());
		Assert.assertEquals(unCalendrierTableModele.getCalendrier().getUneAnnee().getAnnee(), calendrierTableModele.getCalendrier().getUneAnnee().getAnnee());
	}
	
	/**
	 * Test de la méthode getColumnCount
	 */
	@Test
	public void testGetColumnCount() {
		Assert.assertEquals(7, calendrierTableModele.getColumnCount());
	}

	/**
	 * Test de la méthode getRowCount
	 */
	@Test
	public void testGetRowCount() {
		Assert.assertEquals(2, calendrierTableModele.getRowCount());
	}
	
	/**
	 * Test de la méthode getValueAt
	 */
	@Test
	public void testTestValueAt() {
		Assert.assertEquals("", calendrierTableModele.getValueAt(1, 6));
	}

	/**
	 * Test de la méthode isCellEditable
	 */
	@Test
	public void testIsCellEditable() {
		Assert.assertEquals(false, calendrierTableModele.isCellEditable(0, 6));
	}

	/**
	 * Test de la méthode getColumnName
	 */
	@Test
	public void testGetColumnName() {
		Assert.assertEquals("mardi 6", calendrierTableModele.getColumnName(8));
	}
}