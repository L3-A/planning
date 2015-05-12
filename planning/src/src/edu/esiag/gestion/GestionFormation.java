package src.edu.esiag.gestion;

import java.util.ArrayList;
import java.util.List;

import src.edu.esiag.model.Formation;

public class GestionFormation {

	private List<Formation> list;

	public GestionFormation() {
		super();

		list = new ArrayList<Formation>();
	}

	public void add(Formation formation) {
		list.add(formation);
	}

	public List<Formation> getall() {
		return list;
	}

	public boolean delete(int id) {
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getId() == id) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean update(int id,String name) {
		
	
	if(list==null)
		return false;
		
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getId() == id) {
				list.get(i).setName(name);
				return true;
			}
		}
		return false;

	}

}
