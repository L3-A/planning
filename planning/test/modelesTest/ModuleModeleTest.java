package modelesTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import vues.ModuleTable;
import metiers.Module;
import modeles.ModuleModele;

/**
 * @author Dylan
 * Classe AnneeTest
 */
public class ModuleModeleTest {
	/**
	 * Attribut ModuleModele moduleModele
	 */
	private ModuleModele moduleModele;
	
	/**
	 * Attribut ModuleTable moduleTable
	 */
	private ModuleTable moduleTable;
	
	/**
	 * Attrribut List Module modules
	 */
	private List<Module> modules;

	/**
	 * Initialisation de l'environnement
	 */
	@Before // La procedure setup sera ex�cut�e avant chaque test
	public void setup() {
		Module module1 = new Module();
		module1.setNom("Base De Donn�es");
		module1.setAbreviation("BDD");
		module1.setNbSeance(10);
		Module module2 = new Module();
		module2.setNom("Programmation Orient� Objet");
		module2.setAbreviation("POO");
		module2.setNbSeance(20);
		modules = new ArrayList<Module>();
		modules.add(module1);
		modules.add(module2);
		moduleTable = new ModuleTable(modules, (float) 3.5);
		moduleModele = new ModuleModele(moduleTable);
	}
	

	
	/**
	 * Test de la m�thode convertirUneSeance
	 */
	@Test
	public void testConvertirUneSeance() {
		Module module = new Module();
		module = moduleModele.convertirUneSeance("BDD");

		Assert.assertEquals("BDD", module.getNom());
	}
	
	/**
	 * Test de la m�thode recupInfoModule
	 */
	@Test
	public void testRecupInfoModule() {
		Module module1 = new Module();
		module1.setNom("Base De Donn�es");
		
		Module module = new Module();
		module = moduleModele.recupInfoModule(module1, modules);

		Assert.assertEquals("Base De Donn�es", module.getNom());
		Assert.assertEquals("BDD", module.getAbreviation());
		Assert.assertEquals(null, module.getCouleur());
		Assert.assertEquals(10, module.getNbSeance());
	}
}