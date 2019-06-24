package vecinos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	
	public ArrayList<Nodo> DFS(int nodoInicial, int nodoOponente) {
		boolean estado[] = new boolean[cantNodos];
		int dist[] = new int[cantNodos];
		int padre[] = new int[cantNodos];
		Stack<Integer> pila = new Stack<Integer>();
		ArrayList<Nodo> nodos = new ArrayList<Nodo>();
				
		//Creo el arraylist de nodos
		for (int i = 0; i < cantNodos; i++) {
			nodos.add(new Nodo(i));
		}
		
		estado[nodoInicial] = true;
		padre[nodoInicial] = -1;
		estado[nodoOponente] = true;
		pila.push(nodoInicial);
		
		while (!pila.isEmpty()) {
			int nodo = pila.pop();
			//Para cada nodo adyacente al nodo que acabamos de sacar
			for (int i = 0; i < cantNodos; i++) {
				if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
					//Si no visitamos a ese nodo adyacente ya
					if (estado[i] == false) {
						estado[i] = true;
						padre[i] = nodo;
	
						pila.push(i);
					} 
					
					//Lo que hacemos acá adentro es armar la cadena que surge desde el nodoInicial, hasta
					//el nodo adyacente que acabamos de pasar
					if (i != nodoInicial && i != nodoOponente) {
						//Creamos una nueva cadena de amistad
						Cadena cad = new Cadena();
						//Conectamos el nodo en que estamos parados, con el nodo de destino (ultimo eslabon de la cadena)
						cad.getAristas().add(new Arista(nodo, i, matrizAdy[nodo][i]));
						
						//Vamos creando eslabones hasta toparnos con el nodoInicial (o sea, hasta quedarnos sin padre)
						int p = nodo;
						while (padre[p] !=  -1) {
							//Creamos una arista que va desde el padre del nodo en que estamos parado,
							//hasta el nodo en que estamos parado
							cad.getAristas().add(new Arista(padre[p], nodo, matrizAdy[padre[p]][nodo]));
							p = padre[p];
						}
						
						//Calcula el valor de la cadena en base al eslabon minimo
						cad.calcularValor();
						
						nodos.get(i).getCadenas().add(cad);						
					}
					
				}
			}
		}
		
		return nodos;
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
		//Pongo el nodo inicial
		cola.offer(nodoInicial);
		dist[nodoInicial] = 0;
		
		//Mientras haya nodos en la cola
		while (!cola.isEmpty()) {
			//Desacolamos el primer nodo
			int nodo = cola.poll();
			int min;
			
			//Recorremos por cada nodo adyacente
			for (int i = 0; i < cantNodos; i++) {
				if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
					//Si no lo visitamos ya
					if (estado[i] == false) {
						dist[i] =  matrizAdy[nodo][i];						
						estado[i] = true;
						padre[i] = nodo;
						//Acolamos el nodo adyacente i para poder recorer sus adyacentes luego
						cola.offer(i);						
					} else {
						
												
					}
				}
			}
			
		}
		
		return dist;
		
	}
	
}
