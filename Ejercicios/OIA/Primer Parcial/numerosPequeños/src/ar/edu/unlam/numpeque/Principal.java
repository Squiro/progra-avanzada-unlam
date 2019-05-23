package ar.edu.unlam.numpeque;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
	
	private int numeros[];
	private int pequeños[];
	private int encontrados;
	
	public Principal(int numeros[], int peque[]) {
		this.numeros = numeros;
		this.pequeños = peque;
	}
	
	public static void main(String[] args) throws IOException {
		Principal prin = leerArchivo("./Casos de prueba/Entrada/2_entrada.in");
		
		prin.resolver();		
	}
	
	public void resolver() throws IOException {
		//O(nlog(n))
		Arrays.sort(numeros);
		
		inicializarArray();
		
		int cont = 0;		
		
		//En el peor de los casos (todos los numeros son iguales), O(n*k) (lineal, K seria constante)
		for (int i = 0; i < numeros.length && cont < pequeños.length; i++) {
			if (agregar(numeros[i]))
				cont++;
		}		
		
		encontrados = cont;
		escribirArchivo("salida.out");
	}
	
	public void inicializarArray() {
		for (int i = 0; i < pequeños.length; i++) {
			pequeños[i] = Integer.MAX_VALUE;
		}
	}
	
	public boolean agregar(int num) {
		//O(k)
		for (int j = 0; j < pequeños.length; j++) {
			if (num == pequeños[j])
				return false;
			if (num < pequeños[j]) {
				pequeños[j] = num;
				return true;
			}
		}		
		return false;
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));
		
		out.println(pequeños.length);
		for (int i = 0; i < encontrados; i++) {
			out.println(pequeños[i]);
		}
		
		out.close();
	}
	
	public static Principal leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantTotal = sc.nextInt(), cantPeque = sc.nextInt();
		//Avanzamos a la proxima linea
		sc.nextLine();
		
		int numeros[] = new int[cantTotal];
		
		for (int i = 0; i < cantTotal; i++ ) {
			numeros[i] = sc.nextInt();
		}
		
		return new Principal(numeros, new int[cantPeque]);		
	}
	

}
