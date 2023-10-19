package New; 
import java.awt.*;      
import javax.swing.*;
import java.awt.event.*;   
import javax.swing.border.*;

 
public class Form {
   
public static void main(String[] args) { 

 EventQueue.invokeLater(new Runnable() { 
    public void run() { 
        Formulaire f = new Formulaire ("VENTE PAR CARTE");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        
        f.setVisible(true);  
    }
 }); 
}

}


 class Formulaire extends JFrame {
     
     private JPanel p,pan,p1;
     private JLabel t1,t2,t3,t4,t5,t6;
     private JTextField z1, z2, z3, z4;
     private JComboBox bx;
     private String [] paiement = {"Master Card","Visa","PayeSafeCard","Paypal","Maestro"};
     private GridBagConstraints c;
     
     
  public Formulaire(String titre){
     super(titre);
     
     pan = new JPanel(new BorderLayout());
     p = new JPanel();
     p.setLayout(new GridBagLayout());
     p.setBackground(Color.YELLOW);
     c  = new GridBagConstraints();
     c.anchor= c.WEST;
     
     p1 = new JPanel();
     p1.setBackground(Color.YELLOW);
     t1 = new JLabel("Achetez maintenant !!");
     p1.add(t1);
     pan.add(p1,BorderLayout.NORTH);
     
     t2 = new JLabel("Nom :");
     c.gridx=0;
     c.gridy=1;
     c.gridwidth=1;
     c.insets= new Insets(0,5,0,0);
     p.add(t2,c);
     
     z1 = new JTextField();
     z1.setPreferredSize(new Dimension(350,20));
     c.gridx=1;
     c.gridy=1;
     p.add(z1,c);
     
     
     
     t3 = new JLabel("Adresse :");
     c.gridx=0;
     c.gridy=2;
     c.gridwidth=1;
     p.add(t3,c);
     
     z2 = new JTextField();
     z2.setPreferredSize(new Dimension(350,20));
     c.gridx=1;
     c.gridy=2;
     p.add(z2,c);
     
     t4 = new JLabel("Ville :");
     c.gridx=0;
     c.gridy=3;
     c.gridwidth=1;
     p.add(t4,c);
     
     z3 = new JTextField();
     z3.setPreferredSize(new Dimension(200,20));
     c.gridx=1;
     c.gridy=3;
     c.gridwidth=1;
     p.add(z3,c);
     
     t5 = new JLabel("Code :");
     c.gridx=2;
     c.gridy=3;
     c.gridwidth=1;
     p.add(t5,c);
     
     z4 = new JTextField();
     z4.setPreferredSize(new Dimension(60,20));
     c.gridx=4;
     c.gridy=3;
     p.add(z4,c);
     
     t6 = new JLabel("Mode de paiement :");
     c.gridx=0;
     c.gridy=4;
     c.gridwidth=1;
     c.insets=new Insets(3,0,20,0);
     p.add(t6,c);
     
     bx = new JComboBox(paiement);
     c.gridx=1;
     c.gridy=4;
     c.insets=new Insets(3,5,20,0);
     p.add(bx,c);
    
     pan.add(p,BorderLayout.CENTER);
     add(pan);
          
  }
}

    


 


     
