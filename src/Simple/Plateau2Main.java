package Simple;

import java.awt.EventQueue;
import javax.swing.JFrame;

import java.util.Scanner;

public class Plateau2Main {
	public static void main(String[] args) throws InterruptedException {
		 
		 /** deux entiers représentant la longueur et largeur du terrain en pixel.*/
		 int longueur,largeur;
		 
		 /**Scanner pour lire les données entrées par l'utilisateur*/
		 Scanner input = new Scanner(System.in);
		 
		 System.out.println("Entrez la longueur de votre terrain en m�tres : ");
		 longueur = input.nextInt() * 60;
		 
		 System.out.println("Entrez la largeur de votre terrain en m�tres : ");
		 largeur = input.nextInt() *60;
		
		//Création du Plateau de simulation
		Plateau2 f = new  Plateau2("ROBOT TONDEUSE SIMUMATION", longueur, largeur);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		//f.algo_spirale();
		f.algo_normal();	
		//f.algo_random();
	}

	
}
