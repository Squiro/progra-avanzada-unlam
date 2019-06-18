package vecinos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Enfrentamiento {
	
	private Grafo grafo;
	private Oponente primerOponente;
	private Oponente segOponente;
	
	public Enfrentamiento(Grafo grafo, Oponente pri, Oponente seg) {
		this.grafo = grafo;
		this.primerOponente = pri;
		this.segOponente = seg;
	}
	
	public static void main(String[] args) throws IOException {
		Enfrentamiento enf = leerArchivo("entrada.in");
		enf.resolver();
		System.out.println(enf.primerOponente.getAliados().size());
		enf.escribirArchivo("salida.out");
	}
	
	public void resolver() {
		//Por cada oponente, buscamos sus "potenciales" aliados
		//Es decir, hacemos una búsqueda preliminar con Warshall, buscando todas las conexiones de ese nodo
		//con los demás
		
		//Matriz de clasura transitiva
		boolean mct[][] = grafo.warshall(); 
		hallarAliadosPreliminares(mct, primerOponente, segOponente);
		hallarAliadosPreliminares(mct, segOponente, primerOponente);
		
		//Recorremos todos los aliados del primer oponente
		Iterator<Integer> iter = primerOponente.getAliados().iterator();
		
		while (iter.hasNext()) {
			Integer aliado = iter.next();
			
			//Si un aliado coincide, tenemos que ver en cual de los dos es mayor el peso de la cadena
			if (segOponente.getAliados().contains(aliado)) {
				//Hacemos un BFS desde el aliado Z
				int dist[] = grafo.BFS(aliado);
				
				//Si la distancia de Z a X es mayor... removemos a Z como aliado de Y
				if (dist[primerOponente.getNodoOrigen()] > dist[segOponente.getNodoOrigen()]) {
					segOponente.getAliados().remove(aliado);
				} //En cambio, si la distancia del segundo es mayor... 
				else if (dist[segOponente.getNodoOrigen()] > dist[primerOponente.getNodoOrigen()]) {
					iter.remove();
					//primerOponente.getAliados().remove(aliado);
				} //Si son iguales...
				else {
					//primerOponente.getAliados().remove(aliado);
					iter.remove();
					segOponente.getAliados().remove(aliado);
				}
			}
		}		
	}
	
	public void hallarAliadosPreliminares(boolean mct[][], Oponente vecino, Oponente oponente) {
		for (int i = 0; i < grafo.getCantNodos(); i++) {
			//Si hay una conexión entre los dos, es porque son aliados (preliminarmente!)
			if (mct[vecino.getNodoOrigen()][i] == true) {
				//No queremos contar al mismo nodo como aliado, ni al otro oponente...
				if (vecino.getNodoOrigen() != i && oponente.getNodoOrigen() != i) 
					vecino.addAliado(i);
			}
		}
	}
	
	public static Enfrentamiento leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantVecinos = sc.nextInt(), cantLazos = sc.nextInt();
		Oponente pri = new Oponente(sc.nextInt()-1), seg = new Oponente(sc.nextInt()-1);
		
		int matrizAdy[][] = new int[cantVecinos][cantVecinos];
		
		for (int i = 0; i < cantLazos; i++) {
			int nodoOrig = sc.nextInt()-1, nodoDest = sc.nextInt()-1, costo = sc.nextInt();
			matrizAdy[nodoOrig][nodoDest] = costo;
			matrizAdy[nodoDest][nodoOrig] = costo;
		}
		
		sc.close();
		
		return new Enfrentamiento(new Grafo(matrizAdy), pri, seg);	
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));
		
		out.println(primerOponente.getAliados().size() + " " + segOponente.getAliados().size());
		
		out.close();		
	}
	
	

}
