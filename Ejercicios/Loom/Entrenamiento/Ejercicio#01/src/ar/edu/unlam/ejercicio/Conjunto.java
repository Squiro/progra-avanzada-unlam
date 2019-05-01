package ar.edu.unlam.ejercicio;

import java.util.Arrays;

public class Conjunto {

	private int[] conjunto;
	
	public Conjunto(int[] conjunto) {
		this.conjunto = conjunto;
	}
	
	public static void main(String[] args) {
		int[] arr = {24, 6, 10, 30, 1, 0, 2, 4, 5, 34, 12, 3 };
		Conjunto con = new Conjunto(arr);
		
		System.out.println(con.existeSubconjunto(10, 4));
	}
	
	public boolean existeSubconjunto(int objetivo, int k) {
		Arrays.sort(this.conjunto);
		
		int index = 0;
		
		while (this.conjunto[index] < objetivo) {
			index++;
		}			

		for (int i = 0; i < index && objetivo != 0; i++) {
			objetivo -= this.conjunto[i];
			System.out.println("NUM: " + this.conjunto[i]);
		}
		
		
		return objetivo == 0;
	}
	
	
}
