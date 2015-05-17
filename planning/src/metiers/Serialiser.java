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
	 * Accesseur en �criture
	 * @param fw : param�tre de type File
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
	 * Accesseur en �criture
	 * @param calendrier : param�tre de type Calendrier
	 */
	public void setCalendrier(Calendrier calendrier) {
		this.calendrier = calendrier;
	}

	/**
	 * M�thode qui permet de cr�er un fichier binaire avec les donn�es choisit
	 * @return reussi : true si l'enregistrement � r�ussi, false sinon
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