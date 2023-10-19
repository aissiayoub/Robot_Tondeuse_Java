package New;

public class Robot {

	/** Coord horizontal du robot */
	private int x;
	/** Coord vertical du robot */
	private int y;
	private int hauteurTonteRobot;
	private int vitesse;
	private int temps = 0;
	private int distance;
	private int batterie;
	/** Localisation horizontal de la base de recharge du robot */
	private int xLocalisationBase;
	/** Localisation vertical de la base de recharge du robot */
	private int yLocalisationBase;
	/** représentation du robot */
	private int IMGROBOT = -5;
	/** représentation de la base de recharge */
	private int IMGBASE = -3;
	private Terrain t;
	private int[][] g;

	/** diamètre de coupe du robot */
	private double diametreRobot = 0.33333;

	private boolean boolB = false;

	public Robot(int x, int y, int hauteurTonteRobot, int vitesse, int batterie, int xLocalisationBase,
			int yLocalisationBase, Terrain t) {

		this.x = x;
		this.y = y;
		this.hauteurTonteRobot = hauteurTonteRobot;
		this.vitesse = vitesse;
		this.batterie = batterie;
		this.xLocalisationBase = xLocalisationBase - 1; // *3-1;
		this.yLocalisationBase = yLocalisationBase - 1;// *3-1;

		this.t = t;
		g = t.getG();

	}

	/** placer robot et base sur le terrain */
	public int[][] placerRobotEtBase(int[][] g) {

		g[y][x] = IMGROBOT;

		g[yLocalisationBase][xLocalisationBase] = IMGBASE;

		return g;

	}

	public int distanceUpdate() {
		distance++;

		return distance;
	}

	public void tondre(int[][] g, int y, int x) {

		if (g[0][0] != IMGBASE) {
			g[0][0] = IMGBASE;

		}

		g[y][x] = hauteurTonteRobot;

	}

	/** vérifie si la case est tondable */
	public boolean caseTondable(int[][] g, int y, int x) {

		boolean b = false;

		int[] c = t.caseSuivante(y, x);

		if (c[0] > -1 && c[0] < t.getY() + 1) {
			if (c[1] > -1 && c[1] < t.getX() + 1) {
				if (g[y][x] != getIMGROBOT() && g[y][x] != getIMGBASE()) {

					b = true;

				}

				else {
					System.out.println("Evitement de la base !!!!!!!!!!!!!!!");
					EviterBase(g, y, x);
				}
			}
		}

		return b;

	}

	/** contourner la base */
	public void EviterBase(int[][] g, int y, int x) {

		if (xLocalisationBase == 0) {

			if (yLocalisationBase % 2 == 0) {

				D();
				B();
				boolB = true;

			}

			if (yLocalisationBase % 2 == 1) {

				G();
				B();
				boolB = true;

			}

		}

		if (xLocalisationBase == t.getX()) {

			if (yLocalisationBase % 2 == 0) {

				B();
				D();
				boolB = true;

			}

			if (yLocalisationBase % 2 == 1) {

				G();
				B();
				boolB = true;

			}

		}

		// si base sur ligne paire
		if (yLocalisationBase % 2 == 0) {

			B();
			D();
			D();
			H();

		}

		// si base sur ligne impaire
		if (yLocalisationBase % 2 == 1) {

			B();
			G();
			G();
			H();

		}

	}

	public double getDiametreRobot() {
		return diametreRobot;
	}

	public boolean getBoolB() {
		return boolB;
	}

	/** aller à 1 case vers la droite avec le Robot */
	public void D() {

		if (caseTondable(g, y, x + 1) == true) {

			x++;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y, x - 1);
			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case vers la gauche avec le Robot */
	public void G() {

		if (caseTondable(g, y, x - 1) == true) {

			x--;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y, x + 1);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case vers le haut avec le Robot */
	public void H() {

		if (caseTondable(g, y - 1, x) == true) {

			y--;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y + 1, x);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}
	}

	/** aller à 1 case vers le bas avec le Robot */
	public boolean B() {

		if (caseTondable(g, y + 1, x) == true) {

			y++;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y - 1, x);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

		return boolB;
	}

	/** aller à 1 case en diagnonalde vers haut gauche avec le Robot */
	public void DiagHG() {

		if (caseTondable(g, y - 1, x - 1) == true) {

			x--;
			y--;

			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y + 1, x + 1);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case en diagnonalde vers haut droite avec le Robot */
	public void DiagHD() {

		if (caseTondable(g, y - 1, x + 1) == true) {

			x++;
			y--;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y + 1, x - 1);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case en diagnonalde vers bas gauche avec le Robot */
	public void DiagBG() {

		if (caseTondable(g, y + 1, x - 1) == true) {

			x--;
			y++;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y - 1, x + 1);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case en diagnonalde vers bas droite avec le Robot */
	public void DiagBD() {

		if (caseTondable(g, y + 1, x + 1) == true) {

			x++;
			y++;
			g = t.getG();

			g[y][x] = IMGROBOT;

			distanceUpdate();

			tondre(g, y - 1, x - 1);

			t.afficher(g);

		}

		else {
			System.out.println("La case suivante n'est pas tondable");
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHauteurTonteRobot() {
		return hauteurTonteRobot;
	}

	public int getVitesse() {
		return vitesse;
	}

	public int getTemps() {
		return temps;
	}

	public int getDistance() {
		return distance;
	}

	public int getBatterie() {
		return batterie;
	}

	public int getxLocalisationBase() {
		return xLocalisationBase;
	}

	public int getyLocalisationBase() {
		return yLocalisationBase;
	}

	public int getIMGROBOT() {
		return IMGROBOT;
	}

	public int getIMGBASE() {
		return IMGBASE;
	}

	public Terrain getT() {
		return t;
	}

	public int[][] getG() {
		return g;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHauteurTonteRobot(int hauteurTonteRobot) {
		this.hauteurTonteRobot = hauteurTonteRobot;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setBatterie(int batterie) {
		this.batterie = batterie;
	}

	public void setxLocalisationBase(int xLocalisationBase) {
		this.xLocalisationBase = xLocalisationBase;
	}

	public void setyLocalisationBase(int yLocalisationBase) {
		this.yLocalisationBase = yLocalisationBase;
	}

	public void setIMGROBOT(int iMGROBOT) {
		IMGROBOT = iMGROBOT;
	}

	public void setIMGBASE(int iMGBASE) {
		IMGBASE = iMGBASE;
	}

	public void setT(Terrain t) {
		this.t = t;
	}

	public void setG(int[][] g) {
		this.g = g;
	}

	public void setBoolB(boolean boolB) {
		this.boolB = boolB;
	}

}
