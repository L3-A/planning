package modelesTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import metiers.Annee;
import metiers.Calendrier;
import metiers.Seance;
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
		List<Seance> seances = new ArrayList<Seance>();
		CalendrierModele calendrierModele = new CalendrierModele();
		Calendar calendar;
		calendar = calendrierModele.construireCalendrier(uneAnnee);
		calendrierTableModele = new CalendrierTableModele(calendar, calendrier, seances);
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {	
		Assert.assertEquals(2016, calendrierTableModele.getCalendar().getWeekYear());
		Assert.assertEquals(true, calendrierTableModele.getCalendrier().getDimancheOuvrable());
		Assert.assertEquals(false, calendrierTableModele.getCalendrier().getSamediOuvrable());
		Assert.assertEquals("2016", calendrierTableModele.getCalendrier().getUneAnnee().getAnnee());
		Assert.assertEquals(null, calendrierTableModele.getCalendrier().getUneFormation());
		Assert.assertEquals(0, calendrierTableModele.getCalendrier().getSeances().size());

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
		Assert.assertEquals(null, calendrierTableModele.getValueAt(1, 6));
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