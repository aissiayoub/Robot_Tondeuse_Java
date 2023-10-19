
public class Robot {

	/** Coord horizontal du robot */
	private int x;
	/** Coord vertical du robot */
	private int y;
	/** Hauteur de coupe du robot */
	private int hauteurTonteRobot;
	/** Vitesse du robot */
	private double vitesse;
	/** temps de fonctionnement du robot */
	private int temps = 0;
	/** Distance parcouru par le robot */
	private int distance;
	/** Pourcentage de la batterie */
	private double batterie;
	/** Localisation horizontal de la base de recharge du robot */
	private int xLocalisationBase;
	/** Localisation vertical de la base de recharge du robot */
	private int yLocalisationBase;
	/** représentation du robot */
	private int IMGROBOT = -5;
	/** représentation de la base de recharge */
	private int IMGBASE = -3;

	private Terrain t;
	/** Grille du terrain */
	private int[][] g;

	/** diamètre de coupe du robot */
	private double diametreRobot = 0.33333;

	/** variable servant à l'évitement de la base du robot*/
	private boolean boolB = false;
	/** Booleen Case possible à tondre */
	private boolean casePossible = true;
	
	

	/** Constructeur */
	public Robot(int x, int y, int hauteurTonteRobot, double vitesse, double batterie, int xLocalisationBase,
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

	/** Placer le robot sur la grille */
	public int[][] placerRobot(int[][] g) {

		g[y][x] = IMGROBOT;

		return g;

	}

	/** Ajouter 1 à la distance */
	public int distanceUpdate() {
		distance++;

		return distance;
	}

	/** Tondre la case */
	public void tondre(int[][] g, int y, int x) {

		if (xLocalisationBase == 0 && yLocalisationBase == 0) {
			g[0][0] = IMGBASE;

		}

		g[y][x] = hauteurTonteRobot;
		
		updateBatterie();

	}

	/** vérifie si la case est tondable */
	public boolean caseTondable(int[][] g, int y, int x) {

		boolean b = false;

		int[] c = t.caseSuivante(y, x);

		if (c[0] > -1 && c[0] < t.getY() + 1) {
			if (c[1] > -1 && c[1] < t.getX() + 1) {
				if (g[y][x] != getIMGROBOT() && g[y][x] != getIMGBASE() && g[y][x] != t.getOBSTACLE()) {

					b = true;

				}

				else {
					//System.out.println("Déplacement impossible");
					// EviterBase( g, y, x);
				}
			}
		}

		return b;

	}

	/** vérifie si une case est tondu */
	public boolean caseTondu(int[][] g, int y, int x) {

		boolean tondu = false;

		if (g[y][x] == hauteurTonteRobot) {
			tondu = true;
		}

		return tondu;

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
	/**Accesseur*/
	public double getDiametreRobot() {
		return diametreRobot;
	}
	/**Accesseur*/
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
			casePossible = false;
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
		}

	}
	/**Mutateur*/
	public void setCasePossible(boolean casePossible) {
		this.casePossible = casePossible;
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
		}

	}

	/** aller à 1 case en diagonnalde vers bas gauche avec le Robot */
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
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
			casePossible = false;
			// System.out.println("La case suivante n'est pas tondable");
		}
	}
	
	public void updateBatterie(){
		
		
		
		int terrainPente[][] = t.getTerrainPente();
		
		
		if (terrainPente[y][x] == 1) {
			
			batterie = batterie - 0.6;
					
		}
		
		if (terrainPente[y][x] == 2) {
			
			batterie = batterie - 0.65;
		}

		if (terrainPente[y][x] == 3) {
	
			batterie = batterie - 0.7;
		}

		else {
			batterie = batterie - 0.555555;
		}
	}
	/**Accesseur*/
	public boolean getCasePossible() {
		return casePossible;
	}
	/**Accesseur*/
	public int getX() {
		return x;
	}
	/**Accesseur*/
	public int getY() {
		return y;
	}
	/**Accesseur*/
	public int getHauteurTonteRobot() {
		return hauteurTonteRobot;
	}
	/**Accesseur*/
	public double getVitesse() {
		return vitesse;
	}
	/**Accesseur*/
	public int getTemps() {
		return temps;
	}
	/**Accesseur*/
	public int getDistance() {
		return distance;
	}
	/**Accesseur*/
	public double getBatterie() {
		return batterie;
	}
	/**Accesseur*/
	public int getxLocalisationBase() {
		return xLocalisationBase;
	}
	/**Accesseur*/
	public int getyLocalisationBase() {
		return yLocalisationBase;
	}
	/**Accesseur*/
	public int getIMGROBOT() {
		return IMGROBOT;
	}
	/**Accesseur*/
	public int getIMGBASE() {
		return IMGBASE;
	}
	/**Accesseur*/
	public Terrain getT() {
		return t;
	}
	/**Accesseur*/
	public int[][] getG() {
		return g;
	}
	/**Mutateur*/
	public void setX(int x) {
		this.x = x;
	}
	/**Mutateur*/
	public void setY(int y) {
		this.y = y;
	}
	/**Mutateur*/
	public void setHauteurTonteRobot(int hauteurTonteRobot) {
		this.hauteurTonteRobot = hauteurTonteRobot;
	}
	/**Mutateur*/
	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}
	/**Mutateur*/
	public void setTemps(int temps) {
		this.temps = temps;
	}
	/**Mutateur*/
	public void setDistance(int distance) {
		this.distance = distance;
	}
	/**Mutateur*/
	public void setBatterie(int batterie) {
		this.batterie = batterie;
	}
	/**Mutateur*/
	public void setxLocalisationBase(int xLocalisationBase) {
		this.xLocalisationBase = xLocalisationBase;
	}
	/**Mutateur*/
	public void setyLocalisationBase(int yLocalisationBase) {
		this.yLocalisationBase = yLocalisationBase;
	}
	/**Mutateur*/
	public void setIMGROBOT(int iMGROBOT) {
		IMGROBOT = iMGROBOT;
	}
	/**Mutateur*/
	public void setIMGBASE(int iMGBASE) {
		IMGBASE = iMGBASE;
	}
	/**Mutateur*/
	public void setT(Terrain t) {
		this.t = t;
	}
	/**Mutateur*/
	public void setG(int[][] g) {
		this.g = g;
	}
	/**Mutateur*/
	public void setBoolB(boolean boolB) {
		this.boolB = boolB;
	}

}
