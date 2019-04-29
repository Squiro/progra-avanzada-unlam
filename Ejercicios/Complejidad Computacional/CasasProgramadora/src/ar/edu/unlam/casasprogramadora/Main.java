package ar.edu.unlam.casasprogramadora;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("NUM: " + buscarNumero(288));
	}
	
	public static long buscarNumero(long n) {
		/*long suma = (num-1)*((num-1)+1)/2;
		long acum = 0;*/
		
		//N es la cantidad maxima de casas
		double x = Math.sqrt( (Math.pow(n, 2) + n) / 2);
		
		
		if (x%1 == 0)
			return (long) x;
		else 
			return -1;
		/*else
			return -1;*/
		
		/*for (long i = num+1; (suma - acum) > 0; i++) {
			acum = (i * (i+1)/2) - (num*(num+1)/2); 
			if (suma == acum)
				return i;
		}*/
		
	}
	
	public static int sumatoriaDesdeHasta(int desde, int hasta) {
		int suma = 0;
		for (int i = desde; i <= hasta; i++)
			suma += i;
		
		return suma;
	}
	

}
