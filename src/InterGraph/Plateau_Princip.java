package InterGraph;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Plateau_Princip extends JFrame {

	private int x, y;
	private Pan panneau;
	private int[][] t;
	private int a, b, cmt, cmtSpi, SpiH, SpiB, SpiD, SpiG;
	private Image fond1, fond2;
	private int lon, lar;
	private Plateau_Simulation paneltest;

	public Plateau_Princip(String titre, int longueur, int largeur) throws InterruptedException {
		super();
		/* Initialisation des variables */
		cmtSpi = 0;
		a = longueur / 20;
		b = largeur / 20;
		t = new int[b][a]; // Initialisation du tableau des cases visitï¿½es
		for (int i = 0; i < b; i++)
			for (int j = 0; j < a; j++)
				t[i][j] = 0;
		t[0][0] = 1;
		x = 0;
		y = 0;
		lon = longueur;
		lar = largeur;

		Plateau_Simulation panel = new Plateau_Simulation(longueur, largeur);
		this.add(panel.getPan());
	}

	public void algo_normal() throws InterruptedException {

		Thread.sleep(600);
		while (cmt != b) {

			while (x <= lon - 40) {
				Droite();
				Thread.sleep(80);
			}
			Bas();
			while (x != 0) {
				Thread.sleep(80);
				Gauche();
			}
			Thread.sleep(80);
			Bas();
			Thread.sleep(80);
			cmt++;
		}
	}

	public void algo_spirale() throws InterruptedException {

		SpiG = 0;
		SpiD = lon - 20;
		SpiH = 20;
		SpiB = lar - 20;
		Thread.sleep(600);

		while (cmtSpi != a * b) {
			while (x < SpiD) {
				Droite();
				Thread.sleep(80);
			}
			while (y < SpiB) {
				Bas();
				Thread.sleep(80);
			}
			while (x != SpiG) {
				Gauche();
				Thread.sleep(80);
			}
			while (y != SpiH) {
				Haut();
				Thread.sleep(80);
			}

			SpiG += 20;
			SpiD -= 20;
			SpiH += 20;
			SpiB -= 20;
		}
	}

	// déplacement 1 case vers le haut
	public void Haut() {
		if (t[y / 20][x / 20] == 0)
			cmtSpi++;
		y -= 20;
		t[y / 20][x / 20] = 1;
		repaint();

	}

	// déplacement 1 case vers le bas
	public void Bas() {
		if (t[y / 20][x / 20] == 0)
			cmtSpi++;
		y += 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}

	// déplacement 1 case vers la droite
	public void Droite() {
		if (t[y / 20][x / 20] == 0)
			cmtSpi++;
		x += 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}

	// déplacement 1 case vers la gauche
	public void Gauche() {
		if (t[y / 20][x / 20] == 0)
			cmtSpi++;
		x -= 20;
		t[y / 20][x / 20] = 1;
		repaint();
	}

	class Plateau_Simulation extends JPanel {
		private Pan pan;

		public Plateau_Simulation(int longueur, int largeur) {
			// interface du terrain
			pan = new Pan();
			pan.setFocusable(true);
			pan.setPreferredSize(new Dimension(longueur, largeur));
			pan.addMouseListener(new MouseAdapter() {
		       public void mouseClicked(MouseEvent e) {
		            try {
						algo_normal();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		   }
		});
		}

		public Pan getPan() {
			return pan;
		}

		public void setPan(Pan pan) {
			this.pan = pan;
		}

	}


	class Pan extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			setBackground(new Color(116, 150, 0));
			try {
				fond1 = ImageIO.read(new File("gazon_tondu.jpg"));
				fond2 = ImageIO.read(new File("gazon.jpg"));
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

			g.fillOval(x, y, 14, 14);

			// for (int i = 0; i < b; i++)
			// for (int j = 0; j < a; j++)
			// if (t[i][j] == 1)
			// g.fillOval(20 * j + 10, 20 * i + 10, 5, 5); // Si une case a ï¿½tï¿½
			// visitï¿½e, dessiner un rond
			// g.drawImage(fond2, x, y, this); // jaune

		}
	}

	public Pan getPanneau() {
		return panneau;
	}

	public void setPanneau(Pan panneau) {
		this.panneau = panneau;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int[][] getT() {
		return t;
	}

	public void setT(int[][] t) {
		this.t = t;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLar() {
		return lar;
	}

	public void setLar(int lar) {
		this.lar = lar;
	}
	

}