package pk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GrafoNoDirigido extends Grafo {

	public GrafoNoDirigido(String path) {
		adyacencia = FileManager.leerArchivo(path, this);
		espejarMatriz();
	}

	private void espejarMatriz() {
		for (int i = 1; i < adyacencia.length; i++) {
			for (int j = i; j < adyacencia.length; j++) {
				adyacencia[j][i] = adyacencia[i][j];
			}
		}
	}

	public int[] colorearNVeces(int n) {
		int max = 0;
		int[] conjuntoMax = null;
		Integer[] nodos = new Integer[adyacencia.length];
		for (int i = 0; i < adyacencia.length; i++) {
			nodos[i] = i;
		}
		for (int i = 0; i < n; i++) {
			List<Integer> aux = (List<Integer>) Arrays.asList(nodos);

			Collections.shuffle(aux);

			nodos = aux.toArray(new Integer[aux.size()]);

			int[] conjunto = colorearGrafo(nodos);

			if (conjunto.length > max) {
				max = conjunto.length;
				conjuntoMax = conjunto;
			}
		}
		return conjuntoMax;
	}

	private int[] colorearGrafo(Integer[] nodos) {
		boolean[] pintados = new boolean[adyacencia.length];
		ArrayList<int[]> conjuntos = new ArrayList<int[]>();

		for (int i = 0; i < nodos.length; i++) {
			int idx = 0;
			int[] conjunto = new int[nodos.length];
			int nodo = nodos[i];
			if (nodo != 0) {

				if (!pintados[nodo]) {
					pintados[nodo] = true;
					conjunto[idx++] = nodo;
				}
				for (int j = 1; j < adyacencia.length; j++) {
					if (!pintados[j]) {
						if (!esAdyacente(conjunto, j) && nodo != j) {
							pintados[j] = true;
							conjunto[idx++] = j;
						}

					}
				}
				conjuntos.add(conjunto);
			}
		}
		int posMax = buscarConjuntoMaximo(conjuntos);
		return conjuntos.get(posMax);
	}

	private boolean esAdyacente(int[] conjunto, int nodo) {
		for (int i = 0; i < conjunto.length; i++) {
			if (adyacencia[conjunto[i]][nodo] != Integer.MAX_VALUE) {
				return true;
			}
		}
		return false;
	}

	private int buscarConjuntoMaximo(ArrayList<int[]> conjuntos) {
		int maxCon = 0;
		int posMax = 0;
		for (int i = 0; i < conjuntos.size(); i++) {
			int tamaño = 0;
			while (conjuntos.get(i)[tamaño] != 0) {
				tamaño++;
			}
			if (i == 0 || tamaño > maxCon) {
				maxCon = tamaño;
				posMax = i;
			}
		}
		return posMax;
	}

	public int prim() {
		int pesoMinimo = 0;
		int[] visitados = new int[adyacencia.length];
		visitados[1] = 1;

		for (int i = 1; i < adyacencia.length; i++) {
			int minimo = Integer.MAX_VALUE;
			int idxMinimo = 0;

			for (int j = 1; j <= i; j++) {
				int idxAux = getMinimo(adyacencia[visitados[j]], visitados);
				int aux = adyacencia[visitados[j]][idxAux];

				if (aux > 0 && aux < minimo) {
					minimo = aux;
					idxMinimo = idxAux;
				}
			}

			if (minimo != 0 && minimo != Integer.MAX_VALUE) {
				pesoMinimo += minimo;
				visitados[i + 1] = idxMinimo;
			}
		}

		return pesoMinimo;
	}
	/*
	 * public int kruskal() { PriorityQueue<Camino> caminos = new
	 * PriorityQueue<Camino>(); int[] padres = new int[adyacencia.length]; int
	 * pesoMinimo = 0;
	 * 
	 * inicializarPadres(padres); ordenarCaminos(caminos);
	 * 
	 * int cantCaminos = caminos.size();
	 * 
	 * for (int i = 0; i < cantCaminos; i++) { Camino camino = caminos.poll();
	 * 
	 * if (camino != null && find(camino.getNodoOrigen(), padres) !=
	 * find(camino.getNodoDestino(), padres)) { union(camino.getNodoOrigen(),
	 * camino.getNodoDestino(), padres); pesoMinimo += camino.getPeso(); } }
	 * 
	 * return pesoMinimo; }
	 * 
	 * private void inicializarPadres(int[] padres) { for (int i = 1; i <
	 * padres.length; i++) { padres[i] = i; } }
	 * 
	 * private int find(int nodo, int[] padres) { if (nodo == padres[nodo]) { return
	 * nodo; }
	 * 
	 * padres[nodo] = find(padres[nodo], padres); return padres[nodo]; }
	 * 
	 * private void union(int origen, int destino, int[] padres) {
	 * padres[find(origen, padres)] = find(destino, padres); }
	 * 
	 * private void ordenarCaminos(PriorityQueue<Camino> caminos) { for (int i = 1;
	 * i < adyacencia.length; i++) { for (int j = i + 1; j < adyacencia.length; j++)
	 * { if (adyacencia[i][j] != Integer.MAX_VALUE) { caminos.add(new Camino(i, j,
	 * adyacencia[i][j])); } } } }
	 */
}
