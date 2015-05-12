package src.edu.esiag.model;

import java.util.ArrayList;
import java.util.List;

public class Formation {
	
	private int id;
	private String name;
	private List<Module> list;
	private int nbheur;
	public Formation() {
		super();
		list = new ArrayList<Module>();
	}
	public Formation(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Formation(int id, String name, List<Module> list) {
		super();
		this.id = id;
		this.name = name;
		this.list = list;
	}
	
	public int getNbheur() {
		return nbheur;
	}
	public void setNbheur(int nbheur) {
		this.nbheur = nbheur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Module> getList() {
		return list;
	}
	public void setList(List<Module> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Formattion [id=" + id + ", name=" + name + ", list=" + list
				+ "]";
	}
	
	public void add(Module module) {
		list.add(module);
	}

	public List<Module> getall() {
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

	public boolean update(int id,String name,int ndheur) {
		
	
	if(list==null)
		return false;
		
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getId() == id) {
				list.get(i).setName(name);
				list.get(i).setNdheur(ndheur);
				return true;
			}
		}
		return false;

	}


}
