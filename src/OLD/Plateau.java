package OLD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Plateau extends JFrame {

	private int x, y;
	private Pan panneau;
	private int[][] t;
	private JPanel PanelControl;
	private JButton bouger, stop;
	private int a, b;
	private Image fond1, fond2;
	private int lon, lar;
	private Timer timer;

	public Plateau(String titre, int longueur, int largeur) {

		a = longueur / 20;
		b = largeur / 20;
		t = new int[b][a]; // Initialisation du tableau des cases visit�es
		for (int i = 0; i < b; i++)
			for (int j = 0; j < a; j++)
				t[i][j] = 0;

		t[0][0] = 1;

		x = 0;
		y = 0;
		lon = longueur;
		lar = largeur;

		// interface du terrain
		panneau = new Pan();
		panneau.setFocusable(true);
		panneau.setPreferredSize(new Dimension(longueur, largeur));

		// panneau de controle
		PanelControl = new JPanel();
		PanelControl.setPreferredSize(new Dimension(100, 30));

		// bouttons de controle
		bouger = new JButton("Bouger");
		stop = new JButton("Stop");
		PanelControl.add(bouger);
		PanelControl.add(stop);

		// algorithme deplacement
		bouger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(500);
					algo1();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				algo1();
			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					public void run() {
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								for (int i = 0; i < a; i++) {
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									algo2();
								}
							}
						});
					}
				}).start();
			}
		});
		// ajout des deux panneaux de l'interface
		this.add(panneau, BorderLayout.CENTER);
		this.add(PanelControl, BorderLayout.SOUTH);

	}

	public void algo1() {

		while (x <= lon - 40) {
			x += 20;
			t[y / 20][x / 20] = 1;
			System.out.println(x);
			this.validate();
			repaint();
		}
	}

	public void algo2() {

		if (x <= lon - 40) {
			x += 20;
			t[y / 20][x / 20] = 1;
			System.out.println(x);
			this.validate();
			repaint();

		}
	}

	class Pan extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			setBackground(new Color(116, 150, 0));
			try {
				fond1 = ImageIO.read(new File("gazon.jpg"));
				fond2 = ImageIO.read(new File("gazon_tondu.jpg"));
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
			// g.fillOval(20 * j + 10, 20 * i + 10, 5, 5); // Si une case a �t�
			// visit�e, dessiner un rond
			// g.drawImage(fond2, x, y, this); // jaune

		}
	}
}