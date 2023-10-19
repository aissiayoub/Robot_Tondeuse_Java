
import java.util.*;


import java.lang.*;


public class Terrain {
	
	/**Dimension horizontale du terrain en mètre*/
	private int x ;
	/**Dimension verticale du terrain en mètre*/
	private  int y ;
	/**Hauteur de l'herbe*/
	private  int hauteurHerbe;
	
	/**Tableau du terrain*/
	private int[][] g;
	
	private Robot r;
	
	/**Dimension d'un cote d'une case*/
	private final double COTECASE = 0.333;
	/**	Image de l'obstacle*/
	private final int OBSTACLE = 3; 
	
	int a = 1;
	/**Pourcentage tondu du terrain*/
	int  pourcentTerrainTondu = 0;
	/**Le terrain est affiché ou non*/
	private boolean afficherTerrain ;
	
	private int[][] terrainPente;
	
	private int hauteurPente = 0;
	

	

	/**Constructeur*/
	public  Terrain(double a, double b, int hauteurherbe, boolean afficherTerrain) {
		x = (int)conversionCase(a);
		y = (int)conversionCase(b);
		
		this.afficherTerrain = afficherTerrain;
		
		
		this.hauteurHerbe = hauteurherbe;
		
	
		g = new int[y+1][x+1];
		
	}

	/**initialiser les cases du terrain*/
	public void initialiser() {
		for (int i = 0; i < y+1; i++) {
			for (int j = 0; j < x+1 ; j++) {
				g[i][j] = hauteurHerbe;	
			}		
		}	
	}
	

	
	/**Convertir les dimensions du terrain en case*/
	public double conversionCase(double a) {
		
		
		a = Math.round((a*3) -1);
		
		return a;
		
	}
	
	/**Placer obsatcles*/
	public void placerObstacle(double x1, double y1, double x3, double y3) {
		
		x1 =  conversionCase(x1);
		y1 =  conversionCase(y1);
		x3 =  conversionCase(x3);
		y3 =  conversionCase(y3);
		
		
		
		for (int i = 0; i < y+1; i++) {
			for (int j = 0; j < x+1 ; j++) {
				
				
					
					if (y1 <= i && y3 >= i) {
						
						if (x1 <= j && x3 >= j) {
							g[i][j] = OBSTACLE;
							
						}
					}
				
				
				/*
				if (y1 == y4) {
					
					if (y1 <= i && y4 >= i) {
						if (x1 <= j && x4 >= j) {
							g[i][j] = OBSTACLE;
						}
					}
				}	
				*/
			}		
		}
	}

	/**Surface de terrain tondu*/
	public int TerrainTondu() {
		
		int compteur = 0;
		
	
		for (int i = 0; i < y+1; i++) {
			for (int j = 0; j < x+1 ; j++) {
				
				
				if(getCase(i, j) == 1 || getCase(i, j) == -5 || getCase(i, j) == 3)  compteur++;
						
			}
		}
		
		
		
		if(compteur > (nombreCase()*0.5)  )
		{
			

				if(a == 1 || a ==2) {
			pourcentTerrainTondu = 50;
			a++;
			
			
				}
			
		}
		
		if(compteur > (nombreCase()*0.75) ) {
			
			
			
			if(a == 3 || a ==4) {
			pourcentTerrainTondu = 75;
			a++;
			
			}
			
		}
		if(compteur > (nombreCase()*0.9) ) {
			
			
			if(a == 5 || a == 6) {
				pourcentTerrainTondu = 90;
				a++;
				
			}
			
		}
		if(compteur == (nombreCase()) ) {

			pourcentTerrainTondu = 100;
			a++;

		}

		return pourcentTerrainTondu;
	}
	
	
	/**Retourne la plsu grande dimension entre la longueur et la largeur*/
	public int PlusGrandeDimension() {
		
		int a = x;
		
		if(x< y) {
			
			a=y;
		}
		
		if (y < x) {
			a = x;
		}
		
			return a;

	}
	
	public void initialiserPente() {
		
		
		for (int i = 0; i < y+1; i++) {
			for (int j = 0; j < x+1 ; j++) {
				terrainPente[i][j] = hauteurPente;	
			}		
		}	
		
	}
	
	public void changerPente(double x1, double y1, double x3, double  y3, int hauteurHerbe) {
		
		x1 =  conversionCase(x1);
		y1 =  conversionCase(y1);
		x3 =  conversionCase(x3);
		y3 =  conversionCase(y3);
		this.hauteurHerbe = hauteurHerbe;
		
		
		
		for (int i = 0; i < y+1; i++) {
			for (int j = 0; j < x+1 ; j++) {
				
				
					
					if (y1 <= i && y3 >= i) {
						
						if (x1 <= j && x3 >= j) {
							terrainPente[i][j] = hauteurHerbe;
							
						}
					}
			}
			
		}
		
	}
	

	public int[][] getTerrainPente() {
		return terrainPente;
	}

	public void setTerrainPente(int[][] terrainPente) {
		this.terrainPente = terrainPente;
	}

	/**Accesseur*/
	public int getCase(int i,int j) {
		return (g[i][j]);
	}
	
	/**retourne les coordonnées de la case*/
	public int[] caseSuivante(int y, int x) {
		
		int[] coord = {y,x}; 
	    	return coord;
    
	}
	
	/**Comtper le nombre de case du terrain*/
	public int nombreCase() {
		int a = (x+1) * (y+1);
		
		return a;
	}
	
	

	/**afficher le terrain*/
	public  void afficher(int[][] g) {
		
		if(afficherTerrain == true) {
			
		
    for (int l=0; l<y+1  ; l++) {
      for (int c=0; c<x+1 ; c++) {
         System.out.print(g[l][c]);  
      }
      System.out.println();
    }
      System.out.println();
		}
	}
	
	
	/** permet de répertorier des bloc d'herbe sans obstacles*/
	public int[][] rectangleATondre( int[][] g) {
		
		
		int xi = 0;
		int yi = 0;
		int i = 0;
	
		
		int  ligneATondre[] = new int[9999];
		int listeLigneATondre [][]  = new int[9999][4];
		int listeRectangleATondre [][]  = new int[9999][4];
		 
		while(yi < getY()) {
			while(xi < getX() ) {
				while(g[yi][xi] == OBSTACLE) {
						xi++;	
						
					}
				
				ligneATondre[0] = xi;
				ligneATondre[1] = yi;
				
				
				if (xi ==  getX()) {
					ligneATondre[2] = xi;
					ligneATondre[3] = yi;
					
				}
					
				
					if (g[yi][xi] != OBSTACLE && xi < getX()) {
						ligneATondre[0] = xi;
						ligneATondre[1] = yi;
						
						while (g[yi][xi] != OBSTACLE && xi < getX())
						xi++;
						
					}
						
					if (xi == getX()) {
						ligneATondre[2] = xi;
						ligneATondre[3] = yi;
						
						listeLigneATondre[i][0] = ligneATondre[0];
						listeLigneATondre[i][1] = ligneATondre[1];
						listeLigneATondre[i][2] = ligneATondre[2];
						listeLigneATondre[i][3] = ligneATondre[3];
							 
						i++;
						
					
					}
						
					if (g[yi][xi] == OBSTACLE) {
						ligneATondre[2] = xi-1;
						ligneATondre[3] = yi;
							
						listeLigneATondre[i][0] = ligneATondre[0];
						listeLigneATondre[i][1] = ligneATondre[1];
						listeLigneATondre[i][2] = ligneATondre[2];
						listeLigneATondre[i][3] = ligneATondre[3];
							 
						i++;
							 
						while(g[yi][xi] == OBSTACLE && x < getX()) {
							xi++;
						}
					}
			}
			yi++;
			xi = 0;
			
		}
		
		int k = 0;
		int m = 0;
		int p = 0;
		int h = 0;
		
		while(m < getY()+1) {
			
			
			if (listeLigneATondre[m][0] == listeLigneATondre[m+1][0] && listeLigneATondre[m][2] == listeLigneATondre[m+1][2]) {
				listeRectangleATondre[k][0] = listeLigneATondre[m][0];
				listeRectangleATondre[k][1] = listeLigneATondre[m][1];
				
				p = m;
				
				//while (m < 20) {
				while (listeLigneATondre[p][0] == listeLigneATondre[m+1][0] && listeLigneATondre[p][2] == listeLigneATondre[m+1][2]) {
					while(listeLigneATondre[p][1] + h == listeLigneATondre[m+1][1]-1 && listeLigneATondre[p][3] +h == listeLigneATondre[m+1][3]-1) {
						
						listeRectangleATondre[k][2] = listeLigneATondre[m+1][2];
						listeRectangleATondre[k][3] = listeLigneATondre[m+1][3];
						
						h++;
					}
					
					
				//}
				m++;
				
				}
				k++;
			}
			
			else {
				listeRectangleATondre[k][0] = listeLigneATondre[m][0];
				listeRectangleATondre[k][1] = listeLigneATondre[m][1];
				listeRectangleATondre[k][2] = listeLigneATondre[m][2];
				listeRectangleATondre[k][3] = listeLigneATondre[m][3];
				
				k++;
				m++;	
			}
		}
		for(int j=0; j<20; j++)
		{

			for (int a = 0; a < 4; a++) {
				System.out.println("rang: "+j+" rang: "+a+ " résultats: "+listeLigneATondre[j][a]);
			}
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		for(int j=0; j<20; j++)
		{

			for (int a = 0; a < 4; a++) {
				System.out.println("rang: "+j+" rang: "+a+ " résultats: "+listeRectangleATondre[j][a]);
			}
		}
		return listeRectangleATondre;
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
public int getHauteurHerbe() {
	return hauteurHerbe;
}

/**Accesseur*/
public int[][] getG(){

	return g;
}
/**Accesseur*/
public int getOBSTACLE() {
	return OBSTACLE;
}

}

