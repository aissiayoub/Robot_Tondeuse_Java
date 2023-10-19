import java.util.*;


public class main {

	public static void main(String[] args) {

	// Terrain(int x, int y, int hauteurherbe, boolean affichage)
	Terrain t = new Terrain(5,4,9, true);
	
	//public Robot(int x, int y, int hauteurTonteRobot, double vitesse, int batterie, int xLocalisationBase,int yLocalisationBase, Terrain t) 
	Robot r = new Robot(0, 0, 1, 0.4 ,100, 9, 4, t);
	//public Donnee(Terrain t, Robot r)
	Donnee d = new Donnee(t, r);
	// AlgoSimple(Terrain t, Robot r)
	AlgoSimple aS = new AlgoSimple(t, r);
	// Spirale(Terrain t, Robot r)
	Spirale sp = new Spirale(t, r);
	// AlgoRandom(Terrain t, Robot r, Donnee d)
	AlgoRandom aR = new AlgoRandom(t, r, d);
	
	// initialiser le terrain
	t.initialiser();
	// afficher le terrain
	t.afficher(t.getG());
	
	//placerObstacle(double x1, double y1, double x3, double y3) 
	//x1 et y1 sont les coordonnées du sommet en haut à gauche, x3 et y3 en bas à droite
	t.placerObstacle(1,1,2,2);
	
	
	//r.placerRobotEtBase(t.getG());
	r.placerRobot(t.getG());
	
	t.afficher(t.getG());
	
	
	//aS.utiliserAlgoSimple();
	
	//sp.utiliserSpirale();
	
	//aR.utiliserAlgoRandom();
	
	 t.rectangleATondre(t.getG());
	
	
}}


