package ar.edu.unlam.luchadoresjap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class FileManager {
	
	public static void main(String[] args) throws IOException {	
		/*Sumo sumos = leerArchivo("sumo.in");
		sumos.obtenerResultados();*/
	}	
	
	public static Sumo leerArchivo(String path) throws FileNotFoundException {	
		Sumo sumos = new Sumo();		
		
		Scanner sc = new Scanner(new FileReader(path));
		sc.useLocale(Locale.ENGLISH);
		int lenght = sc.nextInt();
		
		for (int i = 0; i < lenght; i++) {
			Luchador luch = new Luchador(sc.nextInt(), sc.nextInt());
			sumos.addLuchador(luch);
		}
		
		sc.close();
		
		return sumos;
	}
	
	public static void escribirNumeroEnArchivo(String path, int num) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(path, true));	
		salida.println(num);
		salida.close();
	}
	
	public static void crearArchivoSalida(String path) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(path));	
		salida.close();
	}
}
