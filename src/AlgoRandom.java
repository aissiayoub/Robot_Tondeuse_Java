
import java.util.Random;

import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class AlgoRandom  {

/**Dimension horizontal du robot*/
private int x;
/**Dimension verticale du robot*/
private int y;

/**Dimension horizontal du terrain*/
private int xT;
/**Dimension verticale du terrain*/
private int yT;

private Terrain t;
private Robot r;
private Donnee d;


	/**Constructeur*/
	public AlgoRandom( Terrain t, Robot r, Donnee d) {

		this.t= t;
		 xT = t.getX();
		 yT = t.getY();
		 
		 this.r = r;
		 
		 this.d = d;
	
	}
	
	
	/** Génère un nombre aléatoire de 1 à dim, dim représentant le nombre maximale*/
	public int NombreAleatoire(int dim) {
	    

	        Random r = new Random();
	        int n = r.nextInt(dim);
	       
	        return n;
	    
	}
	
	/** Génère un nombre pour direction aléatoire*/
	public int NombreAleatoireDirection() {
	    
		int nbDir = t.PlusGrandeDimension();
        Random r = new Random();
        int n = r.nextInt(nbDir);
       
        return n;
    
}
	
	/**Utiliser l'algorithme*/
	public   int[][] utiliserAlgoRandom()  {
		int[][] g = t.getG();
		int n = 100000000;
		int a = 0;
		
		try {
		Thread.sleep(8 * 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
		for(int c = 0; c<n ;) {
			
			int cas = NombreAleatoire(8);
			r.setCasePossible(true);
			/*try {
				Thread.sleep(5 * 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			*/
			switch (cas)
			{
			case 1:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.D();
						
						if(r.getCasePossible() == false) break;
							c ++;
				 }
				 break;

			 case 2:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.G();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
				 
				 break;
				 
			 case 3:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.H();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
				 
				 break;
				 
			 case 4:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.B();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
	 
				 break;
	 
			 case 5:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.DiagBG();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
	 
				 break;
	 
			 case 6:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.DiagHG();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
	 
				 break;
	 
			 case 7:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.DiagHD();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
	 
				 break;
	 
			 case 8:
				 for (int j = 0; j < NombreAleatoireDirection(); j++) {
						r.DiagHD();
						if(r.getCasePossible() == false) break;
						c ++;
						
						}
				 
				 	break;

				}
			
			if(t.TerrainTondu()== 50 ) {
				
				d.setD50(d.afficheDistance());
				d.setT50(d.afficheTemps());
				//break;
				
			}
			
			if(t.TerrainTondu()== 75 ) {
				d.setD75(d.afficheDistance());
				d.setT75(d.afficheTemps());
				//break;
				
			}
			
			if(t.TerrainTondu()== 90 ) {
				d.setD90(d.afficheDistance());
				d.setT90(d.afficheTemps());
				
				}
			
			
			
			if(t.TerrainTondu()== 100) {
				d.setD100(d.afficheDistance());
				d.setT100(d.afficheTemps());
				
				
				
			}
			
			
			if(t.TerrainTondu()== 100) break;
			
		}

		
		
		d.afficherDonnee(d.getD50(),d.getT50());
		d.afficherDonnee(d.getD75(),d.getT75());
		d.afficherDonnee(d.getD90(),d.getT90());
		d.afficherDonnee(d.getD100(),d.getT100());
		
		
		return g;
		
		
	}
		
}
