package ar.edu.unlam.ejercicio;

public class Vector {

	/* 

	Dado un vector V de N (N > 1) componentes y un entero positivo M < N 
	V = [a(1),a(2),...,a(M),a(M+1),...,a(N)]
	
	escribir un programa que lo lleve a la forma:
	
	W = [a(M+1),a(M+2),...,a(N),a(1),...,a(M)]
	
	o sea, si consideramos:
	
	A = [a(1),a(2),...,a(M)] B = [a(M+1),a(M+2),...,a(N)]
	
	podemos pensar que se trata de llevar el vector
	
	V = AB
	
	a la forma W = BA

    Nota: Ningún otro vector adicional podrá usarse y deberá hacerse la menor cantidad de reordenamientos de los elementos de V. 
    Llamamos "reordenamiento" a cada sentencia en la que se han intercambiado dos elementos de V.

 */
	
	private int[] arr;
	
	public Vector(int[] arr) {
		this.arr = arr;
	}
	
	public static void main(String[] args) {
		int[] v = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		Vector vec = new Vector(v);
		vec.ordenarVector(8);
	}
	
	public void ordenarVector(int m) {
		if (m > this.arr.length-1) 
			return;
		
		int needle = m-1;
		while (arr[needle] != m) {
			needle++;
		}
		
		int aux, desp = this.arr.length-needle-1, indice = 0, vueltas = 0;
		
		while (arr[arr.length-1] != m) {
			for (int i = indice, j = 1; j <= desp; i++, j++, vueltas++) {
				aux = arr[i];
				arr[i] = arr[needle+j];
				arr[needle+j] = aux;
			}
			indice += desp;			
		}	

		System.out.println("REORDENAMIENTOS: " + vueltas*desp);
		
		mostrarVector();
	}
	
	
	public void mostrarVector() {		
		for (int i = 0; i < this.arr.length; i++)
			System.out.println(arr[i]);
	}		
}
