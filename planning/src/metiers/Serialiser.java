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
	 * Accesseur en lecture
	 * @return fichier
	 */
	public String getFichier() {
		return fichier;
	}

	/**
	 * Accesseur en écriture
	 * @param fichier
	 */
	public void setFichier(String fichier) {
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
	 * Méthode qui permet de créer un fichier binaire avec les données choisit
	 */
	public boolean serialiser(){
		boolean reussi = false;
		try{
			FileOutputStream file=new FileOutputStream(fichier);
			DataOutputStream data = new DataOutputStream(file);
			
			data.writeUTF(calendrier.getUneAnnee().getAnnee());
			data.writeBoolean(calendrier.getSamediOuvrable());
			data.writeBoolean(calendrier.getDimancheOuvrable());
			data.flush();
			data.close();
	        file.close();
	        reussi = true;
		}  catch(IOException ioe) {
	        ioe.printStackTrace();
		}
		return reussi;
	}
}