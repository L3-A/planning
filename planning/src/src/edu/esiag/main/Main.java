package src.edu.esiag.main;

import src.edu.esiag.gestion.GestionFormation;
import src.edu.esiag.model.Formation;
import src.edu.esiag.model.Module;
import src.edu.esiag.model.Seance;
import src.edu.esiag.vues.FenEtudiant;
import src.edu.esiag.vues.Formulaire;
import src.edu.esiag.vues.FichierHtml;




public class Main {

	public Main() {
		
		Seance s = new Seance(1, "marketing", 2);
		Seance s2 = new Seance(1, "anglais", 2);
		
		Module m = new Module();
		m.setId(1);
		m.setName("isn");
		m.setNdheur(3);
			m.add(s2);
			m.add(s);
		Formation f = new Formation();
		f.setId(1);
		f.setName("Licence 3");
		f.setNbheur(325);
		f.add(m);
		GestionFormation list = new GestionFormation();
		list.add(f);
		
		for (int i = 0; i < list.getall().size(); i++) {
			System.out.println(list.getall().get(i).getName());
		}
		
		
	}

	public static void main(String[] args) {
		
		//new Main();
		
		
		//new FenEtudiant ();
		new FichierHtml();
	
	}

}
