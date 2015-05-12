package src.edu.esiag.vues;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class FenEtudiant extends JFrame {
private JButton BtnOK = new JButton("OK");
private JLabel LblFormation	 = new JLabel("*Nom de la Formation :");
private JLabel LblModule1 = new JLabel("*Module1 :");
private JLabel LblAbreviation = new JLabel("Abreviation :");
private JLabel LblNbreSeance = new JLabel("*Nombre de seance");
private JLabel LblDuree = new JLabel("Duree :");
private JLabel LblDureeTotale = new JLabel("Durée Totale :");
private JLabel LblCouleur = new JLabel("Choisir une couleur :") ;
private JTextField TxtFormation  = new JTextField(25);
private JTextField TxtModule1  = new JTextField(25);
private JTextField TxtAbreviation  = new JTextField(8);
private JTextField TxtNbreSeance  = new JTextField(8);
private JTextField TxtDuree  = new JTextField(10);
private JTextField TxtDureeTotale  = new JTextField(15);
private JTable tabCouleur =  new JTable();
private JTextArea TxtZone = new JTextArea("Resumé de votre saisie \n",4,15);
        public FenEtudiant(){
            Container c = getContentPane();
             c.setLayout(null);
             c.setBackground(Color.WHITE);
                this.setTitle("Saisie des données");
                this.setSize(500, 500);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setContentPane(c);
                this.setVisible(true);
                LblFormation.setBounds(new Rectangle(10, 20, 550, 10));
                TxtFormation.setBounds(new Rectangle(150, 20, 100, 24));
                LblModule1.setBounds(new Rectangle(10, 55, 200, 10));
                TxtModule1.setBounds(new Rectangle(125, 55, 150, 24));
                LblAbreviation.setBounds(new Rectangle(10, 90, 85, 10));
                TxtAbreviation.setBounds(new Rectangle(125, 90, 50, 24));
                LblNbreSeance.setBounds(new Rectangle(10, 125, 125, 10));
                TxtNbreSeance.setBounds(new Rectangle(125, 125, 100, 24));
                LblDuree.setBounds(new Rectangle(10, 160, 125, 10));
                TxtDuree.setBounds(new Rectangle(125, 160, 100, 24));
                LblDureeTotale.setBounds(new Rectangle(10, 195, 125, 10));
                TxtDureeTotale.setBounds(new Rectangle(125, 195, 90, 24));
                
                BtnOK.setBounds(new Rectangle(150, 270, 90, 24));
                TxtZone.setBounds(new Rectangle(10, 300, 450, 150));
 
                TxtZone.enable(false);
                c.add(LblFormation);
                c.add(TxtFormation);
                c.add(LblModule1);
                c.add(TxtModule1);
                c.add(TxtAbreviation);
                 c.add(LblAbreviation);
                c.add(TxtNbreSeance);
                 c.add(LblNbreSeance);
                c.add(TxtDuree);
                 c.add(LblDuree);
                c.add(TxtDureeTotale);
                 c.add(LblDureeTotale);
               
                c.add(BtnOK);
                 c.add(TxtZone);                 Ecouteur gest = new Ecouteur();
                 BtnOK.addActionListener(gest);
 
     }// fin du construecteur
        public static void main(String args[])// méhode main
         {
          FenEtudiant Fenetre = new FenEtudiant();
         }// fin main
// classe privéé pour implémenter le clic sur le boution
 
class Ecouteur implements ActionListener
{
  public void actionPerformed (ActionEvent ev)
  {
   String ch =null;
 
   if (ev.getSource()==BtnOK)
   {
      // contrôle de la saisie
       if(TxtFormation.getText().isEmpty()== true || TxtModule1.getText().isEmpty()==true ||TxtNbreSeance.getText().isEmpty()==true)
       {
           JOptionPane.showMessageDialog(null, "Veuillez renseigner tous les champs obligatoires" ,"Données manquantes",1);
       }
       else
       {
     ch = TxtFormation.getText() + " "+ TxtModule1.getText()+" " +TxtAbreviation.getText()+" " +TxtNbreSeance.getText()
     +" " +TxtDuree.getText()+" " +TxtDureeTotale.getText();
     TxtZone.append(ch+"\n");
     TxtFormation.setText(null);
     TxtModule1.setText(null);
     TxtAbreviation.setText(null);
     TxtNbreSeance.setText(null);
     TxtDuree.setText(null);
     TxtDureeTotale.setText(null);
     
       }// fin else
   }
  }//fin méthode action performed
 
}// classe privée
}// fin de la classe