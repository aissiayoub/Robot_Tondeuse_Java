package New;

import java.util.*;

import java.lang.*;

public class Terrain {

	/** Dimension horizontale du terrain en mètre */
	private int x;
	/** Dimension verticale du terrain en mètre */
	private int y;

	private int hauteurHerbe;

	/** Tableau du terrain */
	private int[][] g;

	private Robot r;

	/** Dimension d'un cote d'une case */
	private final double COTECASE = 0.333;

	public Terrain(int a, int b, int hauteurherbe) {
		x = (a * 3) - 1;

		this.y = (b * 3) - 1;

		this.hauteurHerbe = hauteurherbe;

		this.r = r;

		g = new int[y + 1][x + 1];

	}

	/** initialiser les cases du terrain */
	public void initialiser() {
		for (int i = 0; i < y + 1; i++) {
			for (int j = 0; j < x + 1; j++) {
				g[i][j] = hauteurHerbe;

			}

		}

	}

	public int getCase(int i, int j) {
		return (g[i][j]);
	}

	/** retourne les coordonnées de la case */
	public int[] caseSuivante(int y, int x) {

		int[] coord = { y, x };
		return coord;

	}

	/** Comtper le nombre de case du terrain */
	public int nombreCase() {
		int a = (x + 1) * (y + 1);

		return a;
	}

	/** afficher le terrain */
	public void afficher(int[][] g) {
		for (int l = 0; l < y + 1; l++) {
			for (int c = 0; c < x + 1; c++) {
				System.out.print(g[l][c]);

			}
			System.out.println();
		}
		System.out.println();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHauteurHerbe() {
		return hauteurHerbe;
	}

	public int[][] getG() {

		return g;
	}

}
