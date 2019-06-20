package pk;

import java.util.ArrayList;

public class App {
	private static final String PATH_IN = "./archivos/entrada.in";
	
	public static void main(String[] args) {
		GrafoNoDirigido grafo = new GrafoNoDirigido(PATH_IN);
		int[] conjunto = grafo.colorearNVeces(1000);
		int tamaño = 1;
			while(conjunto[tamaño]!=0) {
				tamaño++;
			}
		System.out.println("Tamaño = " +  tamaño);
		for(int i = 0; i < conjunto.length; i++) {
			System.out.println(conjunto[i]);
		}
	}
}
