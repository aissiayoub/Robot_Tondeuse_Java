package OLD;

import java.awt.EventQueue;
import javax.swing.JFrame;

import InterGraph.Plateau_Princip;

import java.util.Scanner;

public class PlateauMain {
	public static void main(String[] args) throws InterruptedException {
		
		 int longueur,largeur; 
		 Scanner input = new Scanner(System.in);
		 
		 System.out.println("Entrez la longueur de votre terrain en mètres : ");
		 longueur = input.nextInt() * 60;
		 
		 System.out.println("Entrez la largeur de votre terrain en mètres : ");
		 largeur = input.nextInt() *60;
		
		

		Plateau_Princip f = new  Plateau_Princip("ROBOT TONDEUSE SIMUMATION", longueur, largeur);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		f.algo_spirale();
	}

}
