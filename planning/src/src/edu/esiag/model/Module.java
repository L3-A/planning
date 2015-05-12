package src.edu.esiag.model;

import java.util.ArrayList;
import java.util.List;

public class Module {

		private int id;
		private String name;
		private List<Seance> list;
		private int ndheur;
		
		public Module() {
			super();
			list= new ArrayList<Seance>();
		}
		public Module(int id, String name, List<Seance> list) {
			super();
			this.id = id;
			this.name = name;
			this.list = list;
		}
		public Module(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		public int getNdheur() {
			return ndheur;
		}
		public void setNdheur(int ndheur) {
			this.ndheur = ndheur;
		}
		public Module(int id, String name, List<Seance> list, int ndheur) {
			super();
			this.id = id;
			this.name = name;
			this.list = list;
			this.ndheur = ndheur;
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
		public List<Seance> getList() {
			return list;
		}
		public void setList(List<Seance> list) {
			this.list = list;
		}
		@Override
		public String toString() {
			return "Module [id=" + id + ", name=" + name + ", list=" + list
					+ "]";
		}
		
		public void add(Seance module) {
			list.add(module);
		}

		public List<Seance> getall() {
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
