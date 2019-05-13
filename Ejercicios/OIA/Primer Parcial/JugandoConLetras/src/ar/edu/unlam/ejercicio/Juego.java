package ar.edu.unlam.ejercicio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Juego {
	
	private int dimension;
	private int cantPalabras;
	private String[] sopa;
	private String[] palabras;
	
	public Juego(String path) throws FileNotFoundException {
		leerArchivo(path);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Juego jue = new Juego("entrada.in");
		
		jue.mostrarPalabras();
		
		String input = "CERTAMEN";
		
		StringBuilder input1 = new StringBuilder(); 
		  
        // append a string into StringBuilder input1 
        input1.append(input); 
  
        // reverse StringBuilder input1 
        input1 = input1.reverse(); 
  
        // print reversed String 
        System.out.println(input1); 
		
	}
	
	public void leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		this.dimension = sc.nextInt();
		this.cantPalabras = sc.nextInt();
		//Lee el ultimo entero pero no salta de linea
		sc.next();
		
		this.sopa = new String[dimension];
		this.palabras = new String[cantPalabras];		
		
		for (int i = 0; i < this.dimension; i++) {
			this.sopa[i] = sc.nextLine();
		}
		
		for (int i = 0; i < this.cantPalabras; i++) {
			this.palabras[i] = sc.nextLine();
		}		
		sc.close();
	}
	
	public void mostrarPalabras() {		
		for (int i = 0; i < this.cantPalabras; i++) {
			System.out.println(this.palabras[i]);
		}
	}
	
}
