package Simple;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Plateau2 extends JFrame {
	
	/**position actuelle du robot*/
	private int x, y;
	/**le panneau d'affichage de l'interface*/
	private Pan panneau;
	/**tableau des cases du terrain*/
	private int[][] t;
	/**variable pour stocker la longueur et largeur utilisees lors de l'initialisation des cases du terrain */
	private int a, b;
	/**compteur de cases tondues*/
	private int cmt;
	/**Compteur de cases utilise par l'algorihme spirale*/
	private int cmtSpi;
	/**variable representant les bords du terrain, la derniere lettre represente quel bord ( H : Haut, B : bas ...) */
	private int SpiH, SpiB, SpiD, SpiG;
	/**images affichees sur l'interface*/
	private Image fond1, fond2, fond3,fond4;
	/**variable pour stocker la largeur et longueur du terrain en pixel*/
	private int lon, lar;
	/**la derniere case tondable sur le bord a droite*/
	private int  Xmax;
	/**la derniere case tondable sur le bord en haut*/
	private int Ymax;

	/**Constructeur de l'interface*/
	public Plateau2(String titre, int longueur, int largeur) {

		/* Initialisation des variables */
		cmtSpi = 0;
		a = longueur / 20;
		b = largeur / 20;
		t = new int[b][a]; 
		
		// Initialisation du tableau des cases visit�es
		for (int i = 0; i < b; i++)
			for (int j = 0; j < a; j++)
				t[i][j] = 0;
		t[0][0] = 1;
		x = 0;
		y = 0;
		lon = longueur;
		lar = largeur;

		Xmax = longueur - 20;
		Ymax = largeur - 20;

		// creation de l'interface du terrain
		panneau = new Pan();
		panneau.setFocusable(true);
		panneau.setPreferredSize(new Dimension(longueur, largeur));
		this.add(panneau, BorderLayout.CENTER);

	}

	/** algo normal */
	public void algo_normal() throws InterruptedException {

		Thread.sleep(600);
		while (cmt != b) {

			while (x <= lon - 40) {
				Droite();
				Thread.sleep(100);
			}
			Bas();
			while (x != 0) {
				Thread.sleep(100);
				Gauche();
			}
			
			Thread.sleep(100);
			Bas();
			Thread.sleep(100);
			cmt++;
		}
	}

	/** Algo spirale*/
	public void algo_spirale() throws InterruptedException {

		//la limite gauche de l'interface
		SpiG = 0;
		//la limite droite de l'interface
		SpiD = lon - 20;
		//la limite du haut de l'interface
		SpiH = 20;
		//la limite du bas de l'interface
		SpiB = lar - 20;
		
		
		Thread.sleep(1000);
 
		while (cmtSpi != a * b) {
			while (x < SpiD) {
				Droite();
				Thread.sleep(100);
			}
			while (y < SpiB) {
				Bas();
				Thread.sleep(100);
			}
			while (x != SpiG) {
				Gauche();
				Thread.sleep(100);
			}
			while (y != SpiH) {
				Haut();
				Thread.sleep(100);
			} 
			
			//changement des bornes du terrain � tondre (la zone devient plus petite)*/
			SpiG += 20;
			SpiD -= 20;
			SpiH += 20;
			SpiB -= 20;
			 
		}
	}
	/**Algo random, actuellement hors service*/
	public void algo_random() throws InterruptedException {

		int cmpt = 0;

		Random r = new Random();
		int valeur = 0 + r.nextInt(3 - 0);

		while (cmpt < ((lon / 60) * (lar / 60))) {
			
			Thread.sleep(30000);
			while (y < Ymax && x < Xmax) {
				Diago_Bas_Droite();
				Thread.sleep(80);;
		
			}
			while (y>0) {
				Haut();
				Thread.sleep(80);;
			}
			while(y < Ymax && x > 0) {
				Diago_Bas_Gauche();
				Thread.sleep(80);;
			}
			while (y>0) {
				Haut();
				Thread.sleep(80);;
			}
			cmpt++;
		}
	}

	/** deplacement 1 case vers le haut*/
	public void Haut() {

		cmtSpi++;
		y -= 20;
		t[y / 20][x / 20] = 1;
		repaint();

	}

	/** deplacement 1 case vers le bas*/
	public void Bas() {
		cmtSpi++;
		y += 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}

	/** deplacement 1 case vers la droite*/
	public void Droite() {
		cmtSpi++;
		x += 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}

	/** deplacement 1 case vers la gauche*/
	public void Gauche() {
		cmtSpi++;
		x -= 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}
	/**deplacement vers la diagonale bas droite*/
	public void Diago_Bas_Droite() {
		Bas();
		Droite();
		repaint();
	}
	/**deplacement vers la diagonale bas gauche*/
	public void Diago_Bas_Gauche() {
		Bas();
		Gauche();
		repaint();
	}
	/**deplacement vers la diagonale haut gauche */
	public void Diago_Haut_Gauche() {
		Haut();
		Gauche();
		repaint();

	}
	/**deplacement vers la diagonale haut droite*/
	public void Diago_Haut_Droite() {
		Haut();
		Droite();
		repaint();
	}
	
	/**panneau d'affichage des images */
	class Pan extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			setBackground(Color.WHITE);
			g.setColor(Color.BLACK);
			try {
				fond1 = ImageIO.read(new File("gazon_tondu.jpg"));
				fond2 = ImageIO.read(new File("gazon.jpg"));
				fond3 = ImageIO.read(new File("robot2.png"));
				fond4 = ImageIO.read(new File("garage-1.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.setColor(Color.GRAY);

			g.drawImage(fond2, x, y, this);

			for (int i = 0; i < lon; i += 20) {

				for (int j = 0; j < lar; j += 20) {

					if (t[j / 20][i / 20] == 0) {
						g.drawImage(fond1, i, j, this);
					}
					if (t[j / 20][i / 20] == 1) {
						g.drawImage(fond2, i, j, this);
					}

				}

			}

			g.drawImage(fond4, 0, 0, this);
			g.drawImage(fond3, x, y, this);
			

		}

	}
	/** accesseur */
	public Pan getPanneau() {
		return panneau;
	}
	/** mutateur*/
	public void setPanneau(Pan panneau) {
		this.panneau = panneau;
	}
	/** accesseur */
	public int getX() {
		return x;
	}
	/** mutateur*/
	public void setX(int x) {
		this.x = x;
	}
	/** accesseur */
	public int getY() {
		return y;
	}
	/** mutateur*/
	public void setY(int y) {
		this.y = y;
	}
	/** accesseur */
	public int[][] getT() {
		return t;
	}
	/** mutateur*/
	public void setT(int[][] t) {
		this.t = t;
	}
	/** accesseur */
	public int getLon() {
		return lon;
	}
	/** mutateur*/
	public void setLon(int lon) {
		this.lon = lon;
	}
	/** accesseur */
	public int getLar() {
		return lar;
	}
	/** mutateur*/
	public void setLar(int lar) {
		this.lar = lar;
	}

}