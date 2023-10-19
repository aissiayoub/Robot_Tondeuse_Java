import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

public class Spirale  {

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
	public Spirale(Terrain t, Robot r) {
		
		
		this.t= t;
		 xT = t.getX();
		 yT = t.getY();
		 
		 this.r = r;
	
	}

	/**Utiliser l'algorithme*/
	public   int[][] utiliserSpirale()  {
		int[][] g = t.getG();
		int c = 0 ;
		int n = t.nombreCase();
		int a = 0;
		while(c < n) {
		
			if(a ==0) {
		
				for (int j = 0; j < xT+1; j++) {
				r.D();
				c ++;
				
				}
				
				for (int j = yT+1; j > 0; j--) {
					r.B();
					
					c++;
						
					}
				
				for (int j = xT+1; j > 0; j--) {
					r.G();
					
					c++;
				}
				
				yT = yT -2;
					for (int h = yT+1; h > 0; h--) {
						r.H();
						c++;	
					}
			}	
			a++;	
			if(a > 0) {
				xT--;
				for (int j = 0; j < xT+1; j++) {
					r.D();
					c ++;
					}
				yT--;	
				for (int j = yT+1; j > 0; j--) {
					r.B();
					c++;	
					}
				xT--;
				for (int j = xT+1; j > 0; j--) {
					r.G();
					c++;	
					}
				yT--;
				for (int j = yT+1; j > 0; j--) {
					r.H();
					c++;	
					}
			}
		}
		return g;	
	}
			
}
