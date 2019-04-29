package ar.edu.unlam.luchadoresjap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sumo {
	
	private List<Luchador> luchadores; 
	
	public Sumo() {
		this.luchadores = new ArrayList<>();
	}
	
	public Sumo(List<Luchador> luchadores) {
		this.luchadores = new ArrayList<>(luchadores);
	}
	
	public void addLuchador(Luchador luch) {
		this.luchadores.add(luch);
	}
	
	public void printLuchadores() {
		for (Luchador luchador : luchadores) {
			System.out.println(luchador.getAltura());
		}
	}
	
	public void obtenerResultados() throws IOException {
		String path = "sumo.out";		
		//Sobreescribimos el archivo anterior
		FileManager.crearArchivoSalida(path);	
		
		int cant;
		for (Luchador luchador : luchadores) {
			cant = 0;			
			for (int j = 0; j < luchadores.size(); j++) {
				if (luchador.domina(luchadores.get(j))) {
					cant++;
				}								
			}
			//Escribimos un numero en la salida
			FileManager.escribirNumeroEnArchivo(path, cant);
		}
	}
}
