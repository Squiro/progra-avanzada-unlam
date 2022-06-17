package pk;

import java.util.ArrayList;

public class App {
	private static final String PATH_IN = "./archivos/entrada.in";
	
	public static void main(String[] args) {
		GrafoNoDirigido grafo = new GrafoNoDirigido(PATH_IN);
		int[] conjunto = grafo.colorearNVeces(1000);
		int tama�o = 1;
			while(conjunto[tama�o]!=0) {
				tama�o++;
			}
		System.out.println("Tama�o = " +  tama�o);
		for(int i = 0; i < conjunto.length; i++) {
			System.out.println(conjunto[i]);
		}
	}
}
