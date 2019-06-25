package vecinos;

import java.util.ArrayList;
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
	
	public ArrayList<Nodo> DFS(int nodoInicial, int nodoOponente) {
		boolean estado[] = new boolean[cantNodos];
		//int dist[] = new int[cantNodos];
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
	
	
	
}
