package New;

import java.util.*;

public class main {

	public static void main(String[] args) {

		// Terrain(int x, int y, int hauteurherbe)
		Terrain t = new Terrain(3, 2, 9);
		// public Robot(int x, int y, int hauteurTonteRobot, int vitesse, int batterie,
		// int xLocalisationBase en case, int yLocalisationBase en case,Terrain t)
		Robot r = new Robot(0, 0, 1, 10, 100, 1, 1, t);
		// AlgoSimple(Terrain t, Robot r)
		AlgoSimple aS = new AlgoSimple(t, r);

		// initialiser le terrain
		t.initialiser();
		t.afficher(t.getG());

		// placerRobotEtBase(int[][] g)
		r.placerRobotEtBase(t.getG());

		t.afficher(t.getG());

		aS.utiliserAlgoSimple();

	}

}
