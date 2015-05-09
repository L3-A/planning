package metiers;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Claase Deserialiser
 * @author Dylan
 */
public class Deserialiser implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
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
		try{
	        FileInputStream file = new FileInputStream(fichier);
	        DataInputStream data = new DataInputStream(file);
	        Annee uneAnnee = new Annee();
	        
	        uneAnnee.setAnnee(data.readUTF());
	        calendrier.setUneAnnee(uneAnnee);
	        calendrier.setSamediOuvrable(data.readBoolean());
	        calendrier.setDimancheOuvrable(data.readBoolean());
	        data.close();
	        file.close();
		}  catch(IOException ioe) {
		        ioe.printStackTrace();
		}
		return calendrier;
	}
}