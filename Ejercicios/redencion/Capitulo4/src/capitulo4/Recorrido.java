package capitulo4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Recorrido {

	private Grafo grafo;
	
	public Recorrido(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Recorrido rec = leerArchivo("04.in");
		rec.resolver();
	}
	
	//Su sarcófago está en Snagov, pero sólo se revela a los viajantes que hayan recorrido un camino preciso desde Chisinau.
	
	public void resolver() {
		//Desde Chjisinau hasta Snagov
		int origen = grafo.searchNodeNumber("Chisinau");
		int destino = grafo.searchNodeNumber("Snagov");
		grafo.dijkstra(origen);
		ArrayList<Nodo> camino = grafo.getCamino(origen, destino);
		
		System.out.println("El camino más óptimo desde Chisinau hasta Snagov es pasando por: ");
		
		Nodo ant = camino.get(camino.size()-1);
		int kmTotales = 0;
		for (int i = camino.size()-1; i >= 0; i--) {
			Nodo nodo = camino.get(i);
			kmTotales += grafo.getMatrizAdy()[ant.getNumNodo()][nodo.getNumNodo()];
			System.out.println(nodo.getCiudad() + " - " + kmTotales + "KM totales");
			ant = nodo;
		}
		//La suma da 1476km
	}
	
	public static Recorrido leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantNodos = sc.nextInt();
		sc.nextLine();
		
		ArrayList<Nodo> ciudades = new ArrayList<Nodo>();
		
		for (int i = 0; i < cantNodos; i++) {
			ciudades.add(new Nodo(sc.nextLine(), i));
		}
		
		int matrizAdy[][] = new int[cantNodos][cantNodos];
		
		while (sc.hasNextLine()) {//for (int i = 0; i < cantNodos; i++) {
			int origen = sc.nextInt(), destino = sc.nextInt(), peso = sc.nextInt();		
			
			matrizAdy[origen][destino] = peso;
			//matrizAdy[destino][origen] = peso; Los caminos son unidireccionales
		}
		
		
		sc.close();
		
		return new Recorrido(new Grafo(matrizAdy, ciudades));
	}
	
	
	/*public static int convertirAKm(int distancia) {
		//Si son pulgadas...
		if (distancia/10000 <= 9) 
			return distancia*
		
	}*/
	
	
	
}
