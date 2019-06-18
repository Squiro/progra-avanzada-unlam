package vecinos;

import java.util.LinkedList;
import java.util.Queue;

public class Grafo {
	
	private int matrizAdy[][];
	private int cantNodos;
	private final int infinito = Integer.MAX_VALUE;
	
	public Grafo(int matrizAdy[][]) {
		this.matrizAdy = matrizAdy;
		this.cantNodos = matrizAdy.length; 
	}
	
	public int getCantNodos() {
		return this.cantNodos;
	}
	
	public int calcularAliados(int nodoInicial) {
		return 1;
	}
	
	public boolean[][] warshall() {
		boolean mct[][] = new boolean[cantNodos][cantNodos];
		
		//Inicialiamos la matriz de clausura transitiva con los valores originales
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if (matrizAdy[i][j] > 0 && matrizAdy[i][j] != infinito) {
					mct[i][j] = true;
				} else {
					mct[i][j] = false;
				}
			}
		}
		
		//Recorremos cantNodos veces
		for (int k = 0; k < cantNodos; k++) {
			//Por cada nodo...
			for (int i = 0; i < cantNodos; i++) {
				//Recorremos sus posibles adyacentes...
				for (int j = 0; j < cantNodos; j++) {
					//Si existe una conexión directa... o si existe un camino alternativo pasando por el nodo K
					if (mct[i][j] || mct[i][k] && mct[k][j]) {
						mct[i][j] = true;
					}
				}
			}
		}
		
		return mct;		
	}
	
	public int[] BFS (int nodoInicial) {
		//Estado que nos indica si ya está recorrido o no
		boolean estado[] = new boolean[cantNodos];
		//Vector de costos/distancias
		int dist[] = new int[cantNodos];
		int padre[] = new int[cantNodos];
		//Cola en la que vamos a almacenar los nodos que hallemos durante el BFS
		Queue<Integer> cola = new LinkedList<>();
		
		estado[nodoInicial] = true;
		cola.offer(nodoInicial);
		dist[nodoInicial] = 0;
		
		//Mientras haya nodos en la cola
		while (!cola.isEmpty()) {
			//Desacolamos el primer nodo
			int nodo = cola.poll();
			
			//Recorremos por cada nodo adyacente
			for (int i = 0; i < cantNodos; i++) {
				if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
					//Si no lo visitamos ya
					if (estado[i] == false) {
						estado[i] = true;
						//ESTO ES REFERIDO AL EJERCICIO:
						//Decimos que la distancia es la distancia del nodo anterior, más el valor 
						//de la arista que conecta estos dos nodos
						dist[i] = dist[nodo] + matrizAdy[nodo][i];
						padre[i] = nodo;
						//Acolamos el nodo adyacente i para poder recorer sus adyacentes luego
						cola.offer(i);						
					}
				}
			}
			
		}
		
		return dist;
		
	}
	
}
