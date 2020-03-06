package capitulo1;

import java.util.ArrayList;

public class Vampiro {
	
	private long numero;
	private ArrayList<Long> colmillos;
	
	public Vampiro(long num) {
		this.numero = num;
		this.colmillos = new ArrayList<Long>();
	}
	
	//Hallar todos los numeros vampiros de 6 cifras
	//Ir contando la cantidad de movimientos de los circulos hasta llegar a 10, si supera 10, descartamos el numero	
	
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public ArrayList<Long> getColmillos() {
		return colmillos;
	}

	public void setColmillos(ArrayList<Long> colmillos) {
		this.colmillos = colmillos;
	}

	// Como dijo Cindy Lauper: Good artists copy, great artists steal.
	// Sí, estás seguro de que Cindy Lauper dijo eso.
    public static Vampiro hallarColmillos(long n, long tens) {
        Vampiro vamp = new Vampiro(n);
        
        // limit the search space for factors (as in C example)
        long lo = Math.max(tens / 10, (n + tens - 2) / (tens - 1));
        long hi = Math.min(n / lo, (long) Math.sqrt(n));
 
        long nTally = tallyDigits(n);
 
        for (long a = lo; a <= hi; a++) {
            long b = n / a;
 
            if (a * b != n)
                continue;
 
            // check for mod 9 congruence
            if (n % 9 != (a + b) % 9)
                continue;
 
            if (a % 10 == 0 && b % 10 == 0)
                continue;
 
            if (nTally == tallyDigits(a) + tallyDigits(b)) {
            	vamp.colmillos.add(a);
            	vamp.colmillos.add(b);
            }
        }
        
        if (vamp.colmillos.size() > 0)
        	return vamp;
        else
        	return null;
    }
 
    // sum to a unique number to represent set of digits (as in C example)
    public static long tallyDigits(long n) {
        long total = 0;
        while (n > 0) {
            total += 1L << ((n % 10) * 6);
            n /= 10;
        }
        return total;
    }	
    
    /*
	//https://stackoverflow.com/questions/36560589/fastest-way-to-check-if-a-number-is-a-vampire-number*/
}
