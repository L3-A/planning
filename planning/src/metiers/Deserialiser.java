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
	 * Constructeur de la classe
	 * @param fichier
	 * @param calendrier
	 */
	public Deserialiser(File fichier, Calendrier calendrier){
		this.fichier = fichier;
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
	        calendrier.setFerieOuvrable(data.readBoolean());
	        data.close();
	        file.close();
		}  catch(IOException ioe) {
		        ioe.printStackTrace();
		}
		return calendrier;
	}
}