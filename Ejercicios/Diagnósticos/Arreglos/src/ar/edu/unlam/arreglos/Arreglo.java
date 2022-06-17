package ar.edu.unlam.arreglos;

public class Arreglo {

	private int[] vector; 
	
	public Arreglo(int[] vec) {
		this.vector = vec;
	}
	
	public static void main(String[] args) {
		
		int[] vec1 = { 1, 2, 3 };		
		Arreglo arr = new Arreglo(vec1);
		
		System.out.println(arr.sumarPosPares());		
		
		/*arr.vector = new int[2];
		arr.vector[0] = 5;
		arr.vector[1] = 6;*/
		/*System.out.println(arr.sumarPosicionesPares(vec1));		
		System.out.println(arr.sumarPosicionesPares2(vec1));*/	
	}	
	
	private int sumarPosPares() {
		int suma = 0;
		
		for (int i = 0; i < this.vector.length; i+=2){
			suma += this.vector[i];
		}
		
		return suma;
	}
	
	private int sumarPosicionesPares(int[] v1) {		
		int suma = 0;
		
		for (int i = 0; i < v1.length; i+=2) {
			suma += v1[i];
		}
		
		return suma;	
	}
	
	/*private int sumarPosicionesPares2(int[] v1) {		
		int suma = 0, i = 0;
		
		while (i < v1.length) {			
			suma += v1[i];	
			i+=2;
		}		
		return suma;	
	}*/
}
