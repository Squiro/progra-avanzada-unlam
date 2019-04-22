package ar.edu.unlam.luchadoresjap;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Sumo sumos = FileManager.leerArchivo("sumo.in");
		sumos.obtenerResultados();
	}

}
