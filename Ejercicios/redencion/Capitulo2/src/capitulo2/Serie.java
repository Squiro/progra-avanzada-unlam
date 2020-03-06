package capitulo2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Serie {

	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("salida.out"));
		out.println("La serie moldava para el número vampiro 6880 es:");
		//System.out.println(calcularSerieMoldava(6880).toString());
		out.println(calcularSerieMoldava(6880));
		out.close();
	}
		
	/* Serie moldava:
	 * 0 = 0
	 * 1 = -1
	 * 2 =  6
	 * 3 = (-1) + (6) = 5 (suma de los dos anteriores)
	 * 4 = (6) + (5) = 11
	 * ...
	 * 8 = 70
	 * ...
	 */
	
	//La recursión me stackea el overflow.
	//Digo, me overflowea el stack.
	public static BigInteger calcularSerieMoldava(long numero) {
		if(numero <= 0) 
			return new BigInteger("0");
		else if (numero == 1)
			return new BigInteger("-1");
		
		
		BigInteger moldv = new BigInteger("6");
		BigInteger prevMoldv = new BigInteger("-1");
		
		for (long i=2; i < numero; i++) {
			BigInteger temp = new BigInteger(moldv.toString());
			moldv = moldv.add(prevMoldv);
			prevMoldv = temp;
		}
		
		return moldv;
	}
	
}
