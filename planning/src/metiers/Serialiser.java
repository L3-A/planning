package metiers;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Classe Serialiser
 * @author Dylan
 */
public class Serialiser implements Serializable {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Attribut String fichier
	 */
	private String fichier;
	
	/**
	 * Attribut Calendrier calendrier
	 */
	private Calendrier calendrier;
	
	/**
	 * Constructeur
	 * @param fichier
	 * @param calendrier
	 */
	public Serialiser(String fichier, Calendrier calendrier){
		this.fichier = fichier;
		this.calendrier = calendrier;
	}
	
	/**
	 * Méthode qui permet de créer un fichier binaire avec les données choisit
	 */
	public void serialiser(){
		try{
			FileOutputStream file=new FileOutputStream(fichier);
			DataOutputStream data = new DataOutputStream(file);
			
			data.writeUTF(calendrier.getUneAnnee().getAnnee());
			data.writeBoolean(calendrier.getSamediOuvrable());
			data.writeBoolean(calendrier.getDimancheOuvrable());
			data.writeBoolean(calendrier.getFerieOuvrable());
			data.flush();
			data.close();
	        file.close();
		}  catch(IOException ioe) {
	        ioe.printStackTrace();
	}
}

}