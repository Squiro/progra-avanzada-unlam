package pk;

import java.util.ArrayList;
import java.util.Stack;

public abstract class Grafo {
	protected int[][] adyacencia;

	public Grafo() {
	}

	public Grafo(String path) {
		adyacencia = FileManager.leerArchivo(path, this);
	}

	public int[] dijkstra(int nodoOrigen) {
		int[] costosMinimos = adyacencia[nodoOrigen];
		int[] nodosAnalizados = new int[adyacencia.length];

		nodosAnalizados[0] = nodoOrigen;

		for (int i = 1; i < adyacencia.length; i++) {
			int nodoMinimo = getMinimo(costosMinimos, nodosAnalizados);
			nodosAnalizados[i] = nodoMinimo;

			for (int j = 1; j < adyacencia.length; j++) {
				int caminoIntermedio = pasarPorNodoIntermedio(nodoOrigen, j, nodoMinimo);
				if (i != j && caminoIntermedio > 0 && adyacencia[nodoOrigen][nodoMinimo] > caminoIntermedio) {
					costosMinimos[nodoMinimo] = caminoIntermedio;
				}
			}
		}

		return costosMinimos;
	}

	protected int getMinimo(int[] arrayBuscar, int[] arrayAgregar) {
		int minimo = Integer.MAX_VALUE;
		int idxMinimo = 0;

		for (int i = 1; i < arrayBuscar.length; i++) {
			if (arrayBuscar[i] <= minimo) {
				boolean agregar = true;

				for (int j = 1; j < arrayAgregar.length; j++) {

					if (arrayAgregar[j] == i) {
						agregar = false;
						break;
					}
				}

				if (agregar) {
					minimo = arrayBuscar[i];
					idxMinimo = i;
				}
			}
		}

		return idxMinimo;
	}

	protected int pasarPorNodoIntermedio(int nodoOrigen, int nodoIntermedio, int nodoFinal) {
		return adyacencia[nodoOrigen][nodoIntermedio] + adyacencia[nodoIntermedio][nodoFinal];
	}

	protected boolean hayCamino(int nodoOrigen, int nodoFinal) {
		if (adyacencia[nodoOrigen][nodoFinal] != Integer.MAX_VALUE) {
			return true;
		}

		return false;
	}

	public void mostrarMatrizAdyacencia() {
		for (int i = 1; i < adyacencia.length; i++) {
			for (int j = 1; j < adyacencia.length; j++) {
				System.out.print(adyacencia[i][j] + " ");
			}

			System.out.println();
		}
	}

	public void recorrerEnProfundidad(int nodoOrigen) {
		Stack<Integer> pila = new Stack<Integer>();
		boolean[] visitados = new boolean[adyacencia.length];
		
		visitados[nodoOrigen] = true;
		pila.push(nodoOrigen);

		while (!pila.isEmpty()) {
			int nodoActual = pila.pop();
			System.out.print(nodoActual + " ");
			
			for (int i = 1; i < adyacencia.length; i++) {
				if(!visitados[i] && hayCamino(nodoActual, i)) {
					visitados[i] = true;
					pila.push(i);
				}
			}
		}
	}
	
	public void recorrerEnAnchura(int nodoOrigen) {
		ArrayList<Integer> cola = new ArrayList<Integer>();
		boolean[] visitados = new boolean[adyacencia.length];
		
		visitados[nodoOrigen] = true;
		cola.add(nodoOrigen);
		
		while (!cola.isEmpty()) {
			int nodoActual = cola.get(0);
			cola.remove(0);
			System.out.print(nodoActual + " ");
			
			for (int i = 1; i < adyacencia.length; i++) {
				if(!visitados[i] && hayCamino(nodoActual, i)) {
					visitados[i] = true;
					cola.add(i);
				}
			}
		}
	}
}
