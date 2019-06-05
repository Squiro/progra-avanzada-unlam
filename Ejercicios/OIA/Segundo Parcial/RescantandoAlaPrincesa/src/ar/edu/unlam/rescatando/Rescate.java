package ar.edu.unlam.rescatando;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Rescate {
	
	private int cantClaros;
	private int cantSenderos;
	private int dragones[];
	private int claroPrincipe;
	private int claroPrincesa;
	public int matrizClaros[][];
	
	public Rescate(int cantClaros, int cantSenderos, int dragones[], int claroPrincesa, int claroPrincipe, int mat[][]) {
		this.cantClaros = cantClaros;
		this.cantSenderos = cantSenderos;
		this.dragones = dragones;
		this.claroPrincesa = claroPrincesa;
		this.claroPrincipe = claroPrincipe;
		this.matrizClaros = mat;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Rescate res = leerArchivo("entrada.in");
		res.buscarCamino(res.claroPrincipe);		
		System.out.println("A");
	}
	
	//Halla el nodo minimo que no está incluido en el conjunto solución
	public int hallarNodoMinimo(boolean[] conjSol, int[] key) {
		int minKey = Integer.MAX_VALUE;
		int nodo = 0;
		
		for (int i = 0; i < cantClaros; i++) {
			//Si no está incluido en el conjunto de solución, y 
			//el valor de su distancia es menor que el valor infinito
			if (conjSol[i] == false && minKey > key[i]) {
				minKey = key[i];
				nodo = i;
			}
		}
		//Retornamos el menor de los nodos
		return nodo;
	}
	
	
	//Djkstra
	public void buscarCamino(int claroIni) {
		//Este array almacena los nodos que ya se encuentran en el conjunto de solución.
		boolean conjSol[] = new boolean[cantClaros];
		//Distancia. Pesos de las aritas en relacion con claroIni
		int dist[] = new int[cantClaros];
		int infinidad = Integer.MAX_VALUE;
		int precedencia[] = new int[cantClaros];
		
		//Inicializamos todas las distancias en infinito
		for (int i = 0; i < this.cantClaros; i++) {
			dist[i] = infinidad;
			precedencia[i] = claroIni;
		}
		
		dist[claroIni] = 0;
		
		for (int i = 0; i < cantClaros; i++) {
			int nodo_u = hallarNodoMinimo(conjSol, dist);
			
			//Añadimos el nodo al conjunto de solución
			conjSol[nodo_u] = true;
			
			//Iteramos por cada nodo adyacente del nodo_u
			for (int nodo_v = 0; nodo_v < cantClaros; nodo_v++) {				
				if (matrizClaros[nodo_u][nodo_v] > 0) {
					
					//Si el nodo_v no se encuentra en el conj. de solución, y si existe
					//adyacencia con el nodo_u
					if (conjSol[nodo_v] == false && matrizClaros[nodo_u][nodo_v] != infinidad) {
						//El peso del nodo_u, mas el costo de ir desde nodo_u a nodo_v
						//D[w] + C[w,i]
						int min = dist[nodo_u] + matrizClaros[nodo_u][nodo_v];
						
						//Si el costo calculado anterior resulta menor que la distancia actual
						//lo actualizamos
						if (min < dist[nodo_v])	{
							dist[nodo_v] = min;
							//En la posición del nodo_v, guardamos el nodo_u (w) como precedencia
							//Ya que hubo un cambio en el costo. Quiere decir que el anterior a w
							//va a ser este nodo que estamos evaluando
							precedencia[nodo_v] = nodo_u;
						}
					}
				}
			}			
		}	
		
		for (int i = cantClaros-1; precedencia[i] != claroIni; i--) {
			System.out.println(precedencia[i]);
			i = precedencia[i];
		}
	}
	
	
	public static Rescate leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantC = sc.nextInt(), cantS = sc.nextInt();
		int dragones[] = new int[sc.nextInt()];
		int cf = sc.nextInt(), cm = sc.nextInt();
				
		for (int i = 0; i < dragones.length; i++) {
			dragones[i] = sc.nextInt();
		}
		
		int matriz[][] = new int[cantC][cantC];
		
		for (int i = 0; i < cantC; i++) {
			int claroIni = sc.nextInt(), claroFin = sc.nextInt(), peso = sc.nextInt();
			
			matriz[claroIni][claroFin] = peso;
			matriz[claroFin][claroIni] = peso;			
		}		
		
		sc.close();
		
		return new Rescate(cantC, cantS, dragones, cf, cm, matriz);
	}
	

}
