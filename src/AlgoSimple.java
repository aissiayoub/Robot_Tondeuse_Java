import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class AlgoSimple  {

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
	
	/**Constructeur*/
	public AlgoSimple(Terrain t, Robot r) {
		
		
		this.t= t;
		 xT = t.getX();
		 yT = t.getY();
		 
		 this.r = r;
	
	}

	/**Utiliser l'algorithme*/
	public   int[][] utiliserAlgoSimple()  {
		
		int[][] g = t.getG();
		int c = 0 ;
		
		int n = t.nombreCase();
		
		while(t.TerrainTondu() < 100) {
		while(c < n) {

				for (int j = 0; j < xT; j++) {
				r.D();
				c ++;

				}

			if (r.getBoolB() == false) {
				if(c < n )  {
					r.B();
	
			}
				
			r.setBoolB(false);

			}

			//if(c < n)  {
			for (int j = xT; j > 0; j--) {
				r.G();
				
				c++;
					
				}
			
			
			//if(c < n)  {
			r.B();
		
			c++;
	
		}
		
				
		}

		return g;
	}
		
}
