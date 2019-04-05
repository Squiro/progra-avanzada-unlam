package ar.edu.unlam.holamundo;

public class HolaMundo {

	public static void main(String[] args) {

		// Verificar si está ordenado en forma ascendente
		int[] array = { 1, 2, 3, 4, 4, 5, 6 }; // ordenado
		boolean ordenado = true;
		
		for (int i = 1; ordenado && i < array.length; i++) {
			if (array[i-1] > array[i]) {
				ordenado = false;				
			}
		}
		
		
		System.out.println(ordenado ? "Está ordenado" : "No está ordenado");
		
		/*if (ordenado) {
			System.out.println("El array está ordenado.");
		}
		else {
			System.out.println("El array está desordenado.");
		}	*/	
	}
	
	//private static boolean 
}
