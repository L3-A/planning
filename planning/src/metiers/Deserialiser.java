package metiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Claase Deserialiser
 * @author Dylan
 */
public class Deserialiser{
	/**
	 * Attribut File fichier
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
	 * @param fichier
	 */
	public void setFichier(File fichier) {
		this.fichier = fichier;
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
	 * Méthode sui permet de lire un fichier binaire et d'en extraire les informations contenues
	 * @return calendrier
	 */
	public Calendrier deserialiser(){
		try {
			FileInputStream file = new FileInputStream(fichier);
			ObjectInputStream object = new ObjectInputStream(file);
			calendrier = (Calendrier) object.readObject(); 
			object.close();
			file.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return calendrier;
	}
}