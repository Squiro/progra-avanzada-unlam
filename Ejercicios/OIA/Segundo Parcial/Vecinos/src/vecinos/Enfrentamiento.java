package vecinos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Enfrentamiento {
	
	private Grafo grafo;
	private int priOponente;
	private int segOponente;
	private int aliadosX;
	private int aliadosY;

	
	public Enfrentamiento(Grafo grafo, int pri, int seg) {
		this.grafo = grafo;
		this.priOponente = pri;
		this.segOponente = seg;

	}
	
	public static void main(String[] args) throws IOException {
		Enfrentamiento enf = leerArchivo("entrada.in");
		enf.resolver();
		enf.escribirArchivo("salida.out");
	}
	
	public void resolver() {
		ArrayList<Nodo> posiblesAliadosDeX = grafo.BFS(priOponente, segOponente);
		ArrayList<Nodo> posiblesAliadosDeY = grafo.BFS(segOponente, priOponente);
		
		hallarCadenasMaximasPorNodo(posiblesAliadosDeX);
		hallarCadenasMaximasPorNodo(posiblesAliadosDeY);
		
		//int aliadosX = 0, aliadosY = 0;
		
		for (int i = 0; i < posiblesAliadosDeX.size(); i++) {
			if (i != priOponente && i != segOponente) {
				//Si las dos cadenas existen, debemos decidir a quien pertenece el aliado
				if(posiblesAliadosDeX.get(i).getCadenaMax() != null && posiblesAliadosDeY.get(i).getCadenaMax() != null) {
					if (posiblesAliadosDeX.get(i).getCadenaMax().getValor() > posiblesAliadosDeY.get(i).getCadenaMax().getValor()) {
						aliadosX++;
					} else if (posiblesAliadosDeX.get(i).getCadenaMax().getValor() < posiblesAliadosDeY.get(i).getCadenaMax().getValor()){
						aliadosY++;
					}
				}
				//En cambio si una de las cadenas no existe y la otra sí, debemos ver a qué aliado pertenece esa cadena
				else {
					if(posiblesAliadosDeX.get(i).getCadenaMax() == null && posiblesAliadosDeY.get(i).getCadenaMax() != null)
						aliadosY++;
					else if (posiblesAliadosDeX.get(i).getCadenaMax() != null && posiblesAliadosDeY.get(i).getCadenaMax() == null)
						aliadosX++;
				}
			}
			

		}
	}
	
	public void hallarCadenasMaximasPorNodo(ArrayList<Nodo> nodos) {
		for (Nodo nodo : nodos) {			
			if (nodo.getCadenas().size() > 0) {
				Cadena cadMax = nodo.getCadenas().get(0);						
			
				for (Cadena cad : nodo.getCadenas()) {
					if (cad.getValor()> cadMax.getValor()) 
						cadMax = cad;
					
				}				
				nodo.setCadenaMax(cadMax);
			}			
		}
	}
	
	public static Enfrentamiento leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantVecinos = sc.nextInt(), cantLazos = sc.nextInt();
		//Oponente pri = new Oponente(sc.nextInt()-1), seg = new Oponente(sc.nextInt()-1);
		int pri = sc.nextInt()-1, seg = sc.nextInt()-1;
		
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
		
		out.println(aliadosX + " " + aliadosY);
		
		out.close();		
	}
	
	

}
