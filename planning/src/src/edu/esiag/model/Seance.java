package src.edu.esiag.model;

public class Seance {
	
	private int id;
	private String name;
	private int ndheur;
	public Seance() {
		super();
	}
	public Seance(int id, String name, int ndheur) {
		super();
		this.id = id;
		this.name = name;
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
	public int getNdheur() {
		return ndheur;
	}
	public void setNdheur(int ndheur) {
		this.ndheur = ndheur;
	}
	@Override
	public String toString() {
		return "Seance [id=" + id + ", name=" + name + ", ndheur=" + ndheur
				+ "]";
	}
	
	

}
