package modelesTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import metiers.Formation;
import metiers.Module;
import modeles.FormationModele;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class FormationModeleTest {
	/**
	 * Attribut FormationModele formationModele
	 */
	private FormationModele formationModele;
	/**
	 * Attribut Formation formation;
	 */
	private Formation formation;
	
	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera exécutée avant chaque test
	public void setup() {
		formation = new Formation();
		formation.setNom("Licence 3 Miage");
		formation.setDureeTypeSeance((float) 3.5);
		Module module1 = new Module();
		module1.setNom("Base De Données");
		module1.setAbreviation("BDD");
		module1.setNbSeance(10);
		formation.getModules().add(module1);
		Module module2 = new Module();
		module2.setNom("Programmation Orienté Objet");
		module2.setAbreviation("POO");
		module2.setNbSeance(20);
		formation.getModules().add(module2);
		formationModele = new FormationModele(formation);
	}
	
	/**
	 * Test du constructeur
	 */
	@Test
	public void testConstructeur() {
		Assert.assertEquals("Licence 3 Miage", formation.getNom());
		Assert.assertEquals(3,5, formation.getDureeTypeSeance());
		Assert.assertEquals(2, formation.getModules().size());
	}
	
	/**
	 * Test de la méthode convertDureeTypeSeance
	 */
	@Test
	public void testConvertDureeTypeSeance() {
		Assert.assertEquals("3.5", formationModele.convertDureeTypeSeance(formation.getDureeTypeSeance()));
	}
	
	/**
	 * Test de la méthode dureeNbHeureFormation
	 */
	@Test
	public void testDureeNbHeureFormation() {
		Assert.assertEquals(105,0, formationModele.dureeNbHeureFormation());
	}
}