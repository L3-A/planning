package html;
import java.io.FileWriter;
import java.io.File;

import javax.swing.JFileChooser;

import metiers.Calendrier;
import metiers.Deserialiser;
public class FichierHtml {
public FichierHtml(){
	
	Deserialiser deserialise = new Deserialiser();	
	Calendrier calendrier = new Calendrier();
	//Affichage de la chooser pour ouvrir d'un planning existant
	JFileChooser chooser = new JFileChooser();
    chooser.setVisible(true);
    chooser.setCurrentDirectory(new File("/Documents"));
    int retrival = chooser.showOpenDialog(null);

    if (retrival == JFileChooser.APPROVE_OPTION) {
        try {
           // File fichier = new File("C:");
        	File fichier = chooser.getSelectedFile();
			//Calendrier calendrier = new Calendrier();

			deserialise.setFichier(fichier);
			//deserialise.setCalendrier(calendrier);
			calendrier = deserialise.deserialiser();
			
			//calendrierModele = new CalendrierModele(calendrier);
			
			System.out.println(deserialise.getCalendrier().getUneAnnee().getAnnee());
			//System.out.println(calendrier.getSeances().get(1));
			
			for(metiers.Seance uneSeance : deserialise.getCalendrier().getSeances()){
					System.out.println( uneSeance.getModule().getNom());
				
			}
			
			
			} catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	
	
	
	
try{
File ff=new File("C:\\Users\\dylan\\Desktop\\Calendrier.html"); // définir le chemin de stockage 
ff.createNewFile();
FileWriter ffw=new FileWriter(ff);
ffw.write("<!DOCTYPE html> ");  // écrire une ligne dans le fichier resultat.txt
ffw.write("\n"); // forcer le passage à la ligne
ffw.write("<html> ");  
ffw.write("\n"); 
ffw.write("<head> ");  
ffw.write("\n"); 
ffw.write("<style> ");  
ffw.write("\n"); 
ffw.write("table, th, td { ");
ffw.write("\n");
ffw.write(" border: 1px solid black;");
ffw.write("\n");
ffw.write(" border-collapse: collapse;");
ffw.write("\n");
ffw.write(" }");
ffw.write("\n");
ffw.write(" th, td {");
ffw.write("\n");
ffw.write(" padding: 5px;");
ffw.write("\n");
ffw.write(" </style>");
ffw.write("\n"); 
ffw.write(" </head>");
ffw.write("\n"); 
ffw.write(" <body>");
ffw.write("\n"); 
ffw.write(" <p>Année : "+ calendrier.getUneAnnee().getAnnee() +"</p>");
ffw.write("\n"); 
ffw.write(" <table style=\"width:100% \"> ");
ffw.write("\n"); 
ffw.write(" <caption>" +calendrier.getUneFormation().getNom()+"</caption>");


ffw.write("\n"); 
ffw.write(" <caption>31 Aout - 6 Septembre (36)</caption>");
ffw.write("\n"); 
ffw.write("  <tr style= \"background-color: #f1f1c1 \" >");
ffw.write("\n"); 
ffw.write("    <th>Lundi</th>");
ffw.write("\n"); 
ffw.write("     <th>Mardi</th>");
ffw.write("\n"); 
ffw.write(" <th>Mercredi</th>");
ffw.write("\n"); 
ffw.write(" <th>Jeudi</th>");
ffw.write("\n"); 
ffw.write("  <th>Vendredi</th>");
ffw.write("\n"); 
ffw.write("  <th>Samedi</th>");
ffw.write("\n"); 
ffw.write("  <th>Diamanche</th>");
ffw.write("\n"); 
ffw.write("   </tr>");
ffw.write("\n"); 
ffw.write(" <tr>");
ffw.write("\n"); 

int i =0;

for(metiers.Seance uneSeance : deserialise.getCalendrier().getSeances()){
	System.out.println( uneSeance.getModule().getNom());
	//System.out.println(uneSeance.getNbSeanceModule());
	

	ffw.write(" <td>" +uneSeance.getModule().getNom()+"</td>"); 	
	ffw.write("\n"); 


	
}
System.out.println(calendrier.getDimancheOuvrable());



ffw.write("  <th rowspan=\"2\"  style=\"background-color: #000000\"></th>	");
ffw.write("\n"); 
ffw.write(" </tr>");
ffw.write("\n"); 
ffw.write(" <tr>");
ffw.write("\n"); 
for(metiers.Seance uneSeance : deserialise.getCalendrier().getSeances()){
	System.out.println( uneSeance.getModule().getNom());
	//System.out.println(uneSeance.getNbSeanceModule());
	//System.out.println(uneSeance.getModule().getNbSeance());

	ffw.write(" <td>" +uneSeance.getModule().getNom()+"</td>"); 	
	ffw.write("\n"); 


	
}
ffw.write(" </tr>");
ffw.write("\n"); 
ffw.write("</table> ");
ffw.write("\n"); 
ffw.write("</body> ");
ffw.write("\n"); 
ffw.write("</html> ");
ffw.close(); // fermer le fichier à la fin des traitements
} catch (Exception e) {}
}
}