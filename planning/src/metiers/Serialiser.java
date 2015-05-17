package metiers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Classe Serialiser
 * @author Dylan
 */
public class Serialiser{
    /**
	 * Attribut String fichier
	 */
	private File fichier;
	
	/**
	 * Attribut Calendrier calendrier
	 */
	private Calendrier calendrier;
	
	/**
	 * Accesseur en lecture
	 * @return fichier
	 */
	public File getFichier() {
		return fichier;
	}

	/**
	 * Accesseur en écriture
	 * @param fw
	 */
	public void setFichier(File fw) {
		this.fichier = fw;
	}

	/**
	 * Accesseur en lecture
	 * @return calendrier
	 */
	public Calendrier getCalendrier() {
		return calendrier;
	}

	/**
	 * Accesseur en écriture
	 * @param calendrier
	 */
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	/**
	 * Méthode qui permet de créer un fichier binaire avec les données choisit
	 */
	public boolean serialiser(){
		boolean reussi = false;
		try{
			FileOutputStream file=new FileOutputStream(fichier);
			ObjectOutputStream object = new ObjectOutputStream(file);
			object.writeObject(calendrier);
			object.close();
	        file.close();
	        reussi = true;
		} catch(IOException ioe){
	        ioe.printStackTrace();
		}
		return reussi;
	}
}