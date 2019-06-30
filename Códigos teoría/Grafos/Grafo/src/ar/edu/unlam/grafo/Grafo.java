package ar.edu.unlam.grafo;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Grafo {
	
	private final int infinito = Integer.MAX_VALUE;
	private int matrizAdy[][];
	private int cantNodos;
	private int predecesores[];
	private int nodoInicial;	
	private ArrayList<Arista> aristas;
	
	public Grafo (int matriz[][]) {
		this.matrizAdy = matriz;
		this.cantNodos = matrizAdy.length;
		this.predecesores = new int[cantNodos];
		this.aristas = new ArrayList<Arista>();
	}
	
	public Grafo (int cantNodos) {
		this.cantNodos = cantNodos;
		this.aristas = new ArrayList<Arista>();
	}
	
	/* METODOS PARA EL ALGORITMO DE DIJKSTRA */
		
	//Halla el nodo con menor distancia que no está incluido en el conjunto solución
	public int hallarNodoMinDist(boolean[] conjSol, int[] dist) {
		int minKey = Integer.MAX_VALUE;
		int nodo = 0;
		
		//Desde 0 hasta la cantidad de nodos 
		//Recorro todos los nodos y busco aquel que no pertenezca al conjunto solución
		//y cuya distancia sea la menor de todos los otros
		for (int i = 0; i < matrizAdy.length; i++) {
			//Si no está incluido en el conjunto de solución, y 
			//el valor de su distancia es menor que el valor infinito
			if (conjSol[i] == false && dist[i] < minKey) {
				minKey = dist[i];
				nodo = i;
			}
		}
		//Retornamos el menor de los nodos
		return nodo;
	}
	
	//Djkstra
	public int[] dijkstra(int nodoInicial) {
		//Este array almacena los nodos que ya se encuentran en el conjunto de solución.
		boolean conjSol[] = new boolean[cantNodos];
		//Distancia. Pesos de las aritas en relacion con nodoIni
		int dist[] = new int[cantNodos];
		//int infinidad = Integer.MAX_VALUE;
		this.nodoInicial = nodoInicial;
		
		//Inicializamos todas las distancias en infinito
		//Y todos los nodos predecesores como el nodo inicial
		for (int i = 0; i < cantNodos; i++) {
			dist[i] = infinito;
			predecesores[i] = nodoInicial;
		}
		
		//Seteamos la distancia al nodo inicial en 0
		dist[nodoInicial] = 0;
		
		
		int nodoActual = nodoInicial; 
		//Recorremos todos los nodos
		for (int i = 0; i < cantNodos; i++) {			
			//Añadimos el nodo al conjunto de solución
			conjSol[nodoActual] = true;
			
			//Iteramos por cada nodo adyacente (vecino) del nodoActual
			for (int vecino = 0; vecino < cantNodos; vecino++) {
				//Si existe adyacencia con el nodoActual
				if (matrizAdy[nodoActual][vecino] > 0 && matrizAdy[nodoActual][vecino] != infinito) {
					//Si el vecino no se encuentra en el conj. de solución 
					if (conjSol[vecino] == false ) {
						//El peso del nodoActual, mas el costo de ir desde nodoActual al vecino
						//D[w] + C[w,i]
						int minimoTentativo = dist[nodoActual] + matrizAdy[nodoActual][vecino];
						
						//Si el costo calculado anterior resulta menor que la distancia actual
						//lo actualizamos
						if (minimoTentativo < dist[vecino])	{
							dist[vecino] = minimoTentativo;
							//En la posición del vecino, guardamos el nodoActual (w) como precedencia
							//ya que hubo un cambio en el costo. Quiere decir que, en el camino, el anterior
							//va a ser este nodo que estamos evaluando
							predecesores[vecino] = nodoActual;
						}
					}
				}
				
				//Hallamos el nodo que tenga menor distancia
				nodoActual = hallarNodoMinDist(conjSol, dist);
			}			
		}	
		//Retornamos las distancias
		return dist;
	}
	
	/** Obtiene el camino de menor costo en base a los predecesores. 
	 * 
	 * @param nodoHasta
	 * @return
	 */
	
	public ArrayList<Integer> getCamino(int nodoHasta) {
		int i = nodoHasta;
		ArrayList<Integer> camino = new ArrayList<Integer>();
		camino.add(i);
		
		while (i != nodoInicial) {
			i = predecesores[i];
			camino.add(i);
		}
		
		return camino;
	}
	
	/* METODOS PARA EL ALGORITMO DE KRUSKAL */	
	
	//Kruskal se usa cuando tenemos un grafo que no posee tantas aristas en cada uno de sus nodos
	
	public void agregarArista(int origen, int destino, int costo) {
		Arista ari = new Arista(origen, destino, costo);
		if (!aristas.contains(ari)) {			
			aristas.add(ari);
		}
	}
	
	//Recorre la matriz de adyacencia encontrando y agregando las aristas de la misma
	public void encontrarAristas() {
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				//Si existe una arista...
				if (matrizAdy[i][j] > 0 && matrizAdy[i][j] != infinito) {
					agregarArista(i, j, matrizAdy[i][j]);
				}
			}
		}
	}	
	
	public ArrayList<Arista> kruskal() {
		//Creamos una cola de prioridad. 
		//Las colas de prioridad implementadas en Java están basadas en un montículo mínimo. 
		//Esto nos garantiza que las aristas se ordenen automáticamente al ser insertadas,
		//lo cual nos ahorra tiempo y hace las cosas más eficientes.
		PriorityQueue<Arista> pq = new PriorityQueue<Arista>(aristas.size());
		
		//Añadimos las aristas a la cola de prioridad
		for (Arista arista : aristas) {
			pq.add(arista);
		}
		
		//Array que contendrá los conjuntos disjuntos
		int padre[] = new int[cantNodos];
		
		//Realizamos los conjuntos disjuntos
		makeSet(padre);
		
		//MST = Minimum Spanning Tree = Arbol recubridor minimo
		ArrayList<Arista> mst = new ArrayList<Arista>();
		
		//Indice para que no recorramos de más
		int i = 0;
		
		//El algoritmo de Kruskal se detiene hasta procesar cantNodos-1. 
		//Un nodo es procesado cuando una arista es añadida al MST.
		while (i < cantNodos-1) {
			//Removemos aquella arista que esté primero en la cola de prioridad. 
			//Como está ordenado, será la arista que menor costo tenga.
			Arista ari = pq.remove();
			
			//Chequeamos si añadir esta arista nos forma un ciclo. 
			//Para eso debemos buscar el padre de ambos nodos que conforman la arista y preguntar si es el mismo.
			int padre_x = find(padre, ari.getOrigen());
			int padre_y = find(padre, ari.getDestino());
			
			//Si los padres son diferentes, quiere decir que no se forma un ciclo, 
			//por lo que podemos añadir la arista
			if (padre_x != padre_y) {
				//Añadimos la arista al MST
				mst.add(ari);
				//Aumentamos el indice
				i++;				
				//padre_x y padre_y son los representates de los nodos raíces
				//de dos conjuntos que son disjuntos.
				//Como realizamos una conexión entre ellos, tenemos que unir ambos conjuntos
				union(padre, padre_x, padre_y);
			}
		}		
		return mst;
	}
		
	/** https://en.wikipedia.org/wiki/Disjoint-set_data_structure
	 * 
	 * La función de MakeSet es crear un conjunto por cada nodo que se encuentre presente. Es decir,
	 * en cierta forma, aisla y encapsula a cada nodo de los demás. Así, quedamos con N conjuntos (siendo 
	 * N la cantidad de nodos) conformados únicamente por un nodo.	 * 
	 * 
	 * @param padre
	 */
	
	public void makeSet(int padre[]) {
		for (int i = 0; i < padre.length; i++) {
			padre[i] = i;
		}
	}
	
	/** Busca al padre (raíz) del nodo dado. 
	 * 	
	 * @param padre
	 * @param nodo
	 * @return
	 */
	public int find(int padre[], int nodo) {
		if (padre[nodo] != nodo) {
			return find(padre, padre[nodo]); 
		}
		
		return nodo;
	}
	
	/** Union(x,y) uses Find to determine the roots of the trees x and y belong to.
	 *  If the roots are distinct, the trees are combined by attaching the root of one to the root of the other.  
	 * 
	 * Combina aquellos conjuntos que tengan raíces diferentes, uniendo la raíz de uno con la raíz del otro.
	 * 
	 * @param padre
	 * @param x
	 * @param y
	 */
	
	public void union(int padre[], int x, int y) {
		x = find(padre, x);
		y = find(padre, y);
		
		//Si las raíces son diferentes, hacemos que el padre de Y sea X
		if (x != y) {
			padre[y] = x;
		}
	}
	
	public void mostrarMST(ArrayList<Arista> mst) {
		for (Arista arista : mst) {
			System.out.println("Origen: " + arista.getOrigen() + " Costo: " + arista.getCosto() + "Destino: " + arista.getDestino());
		}
	}
	
	/* METODOS PARA EL ALGORITMO DE PRIM */
	
	//Prim se usa cuando tenemos nodos con una gran cantidad de aristas en cada uno de sos nodos
	
	/** La idea de esta implementación de Prim es usar una cola de prioridad para ir ordenando las aristas automáticamente
	 * de menor a mayor. Lo que hacemos es, cada vez que nos posicionamos en un nodo, añadimos todas las aristas que salen del mismo
	 * a la cola de prioridad. Luego, removemos de la cola el primer elemento. Ese elemento será la arista de menor costo. 
	 * 
	 *  Una vez que obtuvimos la arista de menor costo, marcamos al nodo destino de la misma como visitado, y añadimos todas las aristas
	 *  que salen del mismo. 
	 * 
	 * @param nodoInicial
	 * @return
	 */
	
	public int prim(int nodoInicial) {
		int costo = 0;
		//Este array toma nota de los nodos que ya visitamos
		boolean visitado[] = new boolean[cantNodos];
		//Usamos una cola de prioridad para ordenar las aristas de menor a mayor, lo que nos garantiza tomar las aristas
		//menores en cada pasada. 
		//Acordarse de que la cola de prioridad tiene una complejidad de tiempo O(nlog(n)) tanto como para insertar y eliminar elementos.
		PriorityQueue<Arista> pq = new PriorityQueue<Arista>();
		
		//Marcamos el nodo de inicio como visitado
		visitado[nodoInicial] = true;
		int visitados = 1;
		
		//Añadimos todas las aristas adyacentes del nodo inicial a la cola de prioridad
		agregarAristasAdyacentes(pq, nodoInicial);
		
		//Mientras que queden aristas en la cola de prioridad, y mientras que no hayamos visitado todos los nodos ya
		while (!pq.isEmpty() && visitados != cantNodos) {
			//Removemos la primer arista de la cola, es decir, la que tenga valor minimo
			Arista ari = pq.remove();
			
			//¿Visitamos el nodo de destino ya? Si es así, no nos sirve esta arista que sacamos
			//En caso contrario, nos sirve
			if (!visitado[ari.getDestino()]) {
				//Marcamos como visitado el nodo de destino
				visitado[ari.getDestino()] = true;
				//Sumamos el costo de la arista
				costo += ari.getCosto();
				
				//Añadimos las aristas adyacentes del nodo destino
				agregarAristasAdyacentes(pq, ari.getDestino());
				visitados++;
			}
		}
		
		//Si no visitamos todos los nodos del grafo, retornamos -1 (quiere decir que no pudimos lograr
		//hallar el MST)
		if (visitados < cantNodos)
			return -1;

		//Retornamos el costo del MST
		return costo;		
	}
	
	/** Añade todas las aristas adyacentes (es decir, que "salen") de un nodo a la lista de aristas.
	 * 
	 * @param pq
	 * @param nodo
	 */
	
	public void agregarAristasAdyacentes(PriorityQueue<Arista> pq, int nodo) {
		for (int i = 0; i < cantNodos; i++) {
			//Si es una arista...
			if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
				//La añadimos a la cola de prioridad
				pq.add(new Arista(nodo, i, matrizAdy[nodo][i]));
			}
		}		
	}
	
	/* METODOS PARA EL ALGORITMO DE BFS (Busqueda por anchura) */
	
	
	public void BFS(int nodoInicial) {
		boolean estado[] = new boolean[cantNodos];
		int dist[] = new int[cantNodos]; //Vector de distancias relativas
		int padre[] = new int[cantNodos];
		Queue<Integer> cola = new LinkedList<>();
		
		estado[nodoInicial] = true;
		cola.offer(nodoInicial);
		dist[nodoInicial] = 0;
		
		while (!cola.isEmpty()) {
			//Desencolamos el nodo más proximo de la cola
			int nodo = cola.poll();
			//Recorremos por cada nodo que sea adyacente al nodo actual
			for (int i = 0; i < cantNodos; i++)	{
				//Si son adyacentes
				if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
					//Si no fue visitado
					if (estado[i] == false) {
						//No queremos volver a pasar por este nodo
						estado[i] = true;
						//Sumamos las distancias relativas
						dist[i] = dist[nodo] + 1;
						//Indicamos que el padre del nodo adyacente es el nodo en el que estamos parados
						padre[i] = nodo;
						//Encolamos el nodo adyacente al que estamos analizando
						cola.offer(i);
					}
				}
			}
		}
	}
	
	/* METODOS PARA EL ALGORITMO DE DFS (Busqueda por profundidad) */
	
	public void DFS(int nodoInicial) {
		boolean estado[] = new boolean[cantNodos];
		Stack<Integer> pila = new Stack<Integer>();
				
		estado[nodoInicial] = true;
		pila.push(nodoInicial);
		
		while (!pila.isEmpty()) {
			int nodo = pila.pop();
			//Para cada nodo adyacente al nodo que acabamos de sacar
			for (int i = 0; i < cantNodos; i++) {
				if (matrizAdy[nodo][i] > 0 && matrizAdy[nodo][i] != infinito) {
					//Si no visitamos a ese nodo adyacente ya
					if (estado[i] == false) {
						estado[i] = true;
						pila.push(i);
					}
				}
			}
		}
	}
	
	
	/* METODOS PARA EL ALGORITMO DE FLOYD */ 
	
	public int[][] floyd() {
		int dist[][] = new int[cantNodos][cantNodos];
		dist = matrizAdy.clone();
			
		//Esto no tendría que ser necesario si las matrices son cargadas con ceros en las diag. principales
		for (int i = 0; i < cantNodos; i++) {
			dist[i][i] = 0;
		}
		
		//El algoritmo de Floyd tiene que recorrer "cantNodos matrices" en su totalidad. 
		//Es decir, según lo que vimos en clase, es una matriz "nueva" por nodo recorrido, y se tienen
		//que recorrer todos
		for (int k = 0; k < cantNodos; k++) {
			//Recorremos todos los nodos...
			for (int i = 0; i < cantNodos; i++) {
				//Y seteamos nuestra vista en los demás nodos restantes...
				for (int j = 0; j < cantNodos; j++) {
					//Preguntamos si la distancia pasando por un nodo K es menor que la distancia original 
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		/*Como vimos, no es necesario crear otra matriz nueva. Esto es porque actualizamos la matriz
		 * a medida que avanzamos con el algoritmo. */
		
		return dist;		
	}
	
	/* METODOS PARA EL ALGORITMO DE WARSHALL */ 
	
	public boolean[][] warshall() {
		//Matriz de clausura transitiva
		boolean mct[][] = new boolean[cantNodos][cantNodos];
		
		//Inicializamos la matriz 0 con los valores que se hayan en la matrizAdy
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if (matrizAdy[i][j] > 0 && matrizAdy[i][j] != infinito) {
					mct[i][j] = true;
				} /*else {
					mct[i][j] = false;
				}*/
			}
		}

		for (int k = 0; k < cantNodos; k++) {
			//Recorremos todos los nodos...
			for (int i = 0; i < cantNodos; i++) {
				//Y seteamos nuestra vista en los demás nodos restantes...
				for (int j = 0; j < cantNodos; j++) {
					//Preguntamos si hay alguna conexión
					if (mct[i][j] || (mct[i][k] && mct[k][j]) ) {
						mct[i][j] = true;
					}
				}
			}
		}
		
		return mct;		
	}
	
	/* METODOS PARA EL ALGORITMO DE COLOREO WELSH-POWELL */
	
	//El algoritmo de coloreo no solo me da la cantidad de colores,
	//si no que también me da los nodos que pertenecen a cada uno de sus colores	
	public void coloreo(int cantPasadas) {
		//Lista que contiene los nodos que todavía faltan por pintar
		ArrayList<Nodo> noColoreados = new ArrayList<Nodo>();
		//Lista que contiene los colores usados
		ArrayList<Color> colores = new ArrayList<Color>();
		//Booleana que indica se si halló un nodo adyacente
		boolean encontrado;
		
		//Recorre la matriz de adyacencia y rellena la lista con objetos de la clase Nodo
		//con sus grados ya calculados
		hallarGrados(noColoreados);
		
		for (int i = 0; i < cantPasadas; i++) {
			//Sorteamos la lista en orden descendiente
			Collections.sort(noColoreados, Collections.reverseOrder());		
			
			//Variable que se incrementará luego de cada ciclo para simular el uso de colores diferentes
			int colorActual = 1;
			
			//Ejecutamos este ciclo mientras hayan nodos sin pintar
			while (!noColoreados.isEmpty()) {
				//Instancia de la clase color, la cual contiene un atributo del tipo ArrayList<Nodo>,
				//que guarda una lista de nodos asociados a ese color.
				//Notar cómo la instanciamos con el valor de colorActual
				Color color = new Color(colorActual);
				colores.add(color);
				
				//Obtenemos el primer nodo de la lista de los noColoreados
				Iterator<Nodo> iter = noColoreados.iterator();
				
				//Mientras que el iterator no se quede sin nodos para sacar...
				while (iter.hasNext()) {
					Nodo nodo = iter.next();
					//Encontrado me indica si hay adyacentes del mismo color
					encontrado = false;					
												
					//Este ciclo comprueba si hay algún nodo pintado de COLORACTUAL que se adyacente
					//al nodo que sacamos de la lista. Si hay al menos un adyacente, entonces no podemos pintar ese nodo
					//del mismo color.				
					for (Nodo nodoPintado : color.getNodos()) {
						//¿Es el nodo pintado adyacente al que no está pintado?
						if (matrizAdy[nodo.getNodo()][nodoPintado.getNodo()] > 0 
								&& matrizAdy[nodo.getNodo()][nodoPintado.getNodo()] != infinito) {
							encontrado = true;
						}
					}
					
					//Si no encontré ninguno que sea adyacente, pinto al nodo con colorActual
					if (encontrado == false) {
						//Saco al nodo de los noColoreados
						iter.remove();
						//Lo agrego a la lista de nodos de colorActual
						color.getNodos().add(nodo);
					}
				}			
				colorActual++;
			}

			//Volvemos a buscar los nodos con sus grados
			hallarGrados(noColoreados);
			//Pero esta vez desordenamos el vector para que haga un recorrido ligeramente diferente
			desordenar(noColoreados);
		}
	}
	
	
	public void desordenar(ArrayList<Nodo> lista) {
		int indice1 = (int) Math.floor(Math.random()*lista.size());
		int indice2 = (int) Math.floor(Math.random()*lista.size());
		
		Collections.swap(lista, indice1, indice2);
	}
	
	public void hallarGrados(ArrayList<Nodo> lista) {
		int cant = 0;
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if (matrizAdy[i][j] > 0 && matrizAdy[i][j] != infinito) {
					cant++;
				}
			}			
			Nodo n = new Nodo(i, cant);
			lista.add(n);
			cant = 0;
		}
	}	
}
