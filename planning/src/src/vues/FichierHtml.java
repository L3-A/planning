package src.vues;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;
public class FichierHtml {
public static void main(String [] args){
try{
File ff=new File("C:\\Users\\Desktop\\Calendrier.html"); // définir le chemin de stockage 
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
ffw.write(" <p>Année : 2015 - 2016</p>");
ffw.write("\n"); 
ffw.write(" <table style=\"width:100% \"> ");
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
ffw.write(" <td>Java</td>");
ffw.write("\n"); 
ffw.write(" <td>Base de donnes</td>");
ffw.write("\n"); 
ffw.write(" <td>Marketing</td>");
ffw.write("\n"); 
ffw.write(" <td>IHM</td>");
ffw.write("\n"); 
ffw.write(" <td>Analyse fi</td>");
ffw.write("\n"); 
ffw.write(" <td>Anglais</td>");
ffw.write("\n"); 
ffw.write("  <th rowspan=\"2\"  style=\"background-color: #000000\"></th>	");
ffw.write("\n"); 
ffw.write(" </tr>");
ffw.write("\n"); 
ffw.write(" <tr>");
ffw.write("\n"); 
ffw.write(" <td>Java</td>");
ffw.write("\n"); 
ffw.write(" <td>Base de donnes</td>");
ffw.write("\n"); 
ffw.write(" <td>Marketing</td>");
ffw.write("\n"); 
ffw.write(" <td>IHM</td>");
ffw.write("\n"); 
ffw.write(" <td>Analyse fi</td>");
ffw.write("\n"); 
ffw.write(" <td>Anglais</td>");
ffw.write("\n"); 
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