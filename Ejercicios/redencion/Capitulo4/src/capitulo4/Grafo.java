package capitulo4;

import java.util.ArrayList;

public class Grafo {
	
	
	private final int infinito = Integer.MAX_VALUE;
	private int matrizAdy[][];
	private int predecesores[];
	private int cantNodos;
	//Contiene los nombres de las ciuades y sus numeros identificadores
	private ArrayList<Nodo> ciudades;
	
	public Grafo(int matriz[][], ArrayList<Nodo> nodos) {
		this.matrizAdy = matriz;
		this.cantNodos = matriz.length;
		this.predecesores = new int[cantNodos];
		this.ciudades = nodos;
	}
	
	public int[][] getMatrizAdy() {
		return this.matrizAdy;
	}
	
	
	public int hallarNodoMinDist(boolean conjSol[], int dist[]) {
		int minKey = infinito;
		int nodo = 0;
		
		//Recorro todos los nodos y busco aquel que no pertenezca al conjunto solución
		//y cuya distancia sea la menor de todos los otros
		for (int i = 0; i < cantNodos; i++) {
			if (conjSol[i] == false && minKey > dist[i]) {
				minKey = dist[i];
				nodo = i;
			}
		}
		
		//Retornamos el nodo que haya quedado seleccionado
		return nodo;
	}
	
	public int[] dijkstra(int nodoInicial) {
		boolean conjSol[] = new boolean[cantNodos];
		int dist[] = new int[cantNodos];
		
		//Inicializamos las distancias en infinito, y el vector de predecesores en el nodo incial
		for (int i = 0; i < cantNodos; i++) {
			dist[i] = infinito;
			predecesores[i] = nodoInicial;
		}
		
		dist[nodoInicial] = 0;
		
		for (int i = 0; i < cantNodos; i++) {
			//Hallo el nodo con menor distancia
			int nodoActual = hallarNodoMinDist(conjSol, dist);
			
			//Añado el nodo al conjunto de solucion
			conjSol[nodoActual] = true;
			
			for (int vecino = 0; vecino < cantNodos; vecino++) {
				//Si hay adyacencia...
				if (matrizAdy[nodoActual][vecino] > 0 && matrizAdy[nodoActual][vecino] < infinito) {
					//Si no se encuentra en el conjunto de solución ya 
					if (conjSol[vecino] == false) {
						int minimoTentativo = dist[nodoActual] + matrizAdy[nodoActual][vecino];
						
						//Si la distancia pasando por un nodo W es menor...
						if (minimoTentativo < dist[vecino]) {
							dist[vecino] = minimoTentativo;
							//El predecesor del vecino va a ser el nodo actual (es decir, el nodo por el que pasamos antes)
							predecesores[vecino] = nodoActual;
						}
						
					}
				}
			}					
		}
		
		return dist;
	}
	
	public ArrayList<Nodo> getCamino(int nodoInicial, int nodoHasta) {
		int i = nodoHasta;
		ArrayList<Nodo> camino = new ArrayList<Nodo>();
		camino.add(ciudades.get(i));
		
		while (i != nodoInicial) {
			i = predecesores[i];
			camino.add(ciudades.get(i));
		}
		
		return camino;
	}
	
	public int searchNodeNumber(String ciudad) {
		for (Nodo nodo : ciudades) {
			if (nodo.getCiudad().equals(ciudad)) {
				return nodo.getNumNodo();
			}
		}
		
		return -1;
	}
	
}
 