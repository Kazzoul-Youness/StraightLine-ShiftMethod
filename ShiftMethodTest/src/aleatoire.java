import java.util.Random;

// Script qui genere wp et wq al�atoirement !

public class aleatoire {

	public static void main(String[] args) {
		
		int n = 30; 

		Random random = new Random();

		for (int i = 3; i <= n; i++) {

		    int wp, wq;
//		    generer wp et wq al�atoirement !
//		    do {
//		        wp = random.nextInt(i - 1);
//		        wq = wp + 1 + random.nextInt(i - wp - 1);
//		    } while (wp == wq || wq == i - 1);
//
//		    String wpName = "v" + Integer.toString(wp+1);
//		    String wqName = "v" + Integer.toString(wq+1);
		    
/*		    fixer wp=v1 et wq=vn ce qui signifier qu'on aura � chaque �tape: 
* v1 = � gauche
* vn = au milieu 
* v2 = � droite 
* Autrement dis on autre une liste au milieu
*/		    
		    String wpName = "v" + 1;
		    String wqName = "v" + i ;

		    System.out.println("wpName: " + wpName + ", wqName: " + wqName);

		}

		
	}
}

