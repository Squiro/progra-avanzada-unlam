package ar.edu.unlam.nombresrepetidos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
	
	private String[] nombres;
	private Nombre[] nombresFrecuentes;
	
	public Principal(int cantNombres, int cantFrec) {
		this.nombres = new String[cantNombres];
		this.nombresFrecuentes = new Nombre[cantFrec];
	}
	
	public static void main(String[] args) throws IOException {
		Principal prin = leerArchivo("entrada.in");
		
		prin.resolver();
	}
	
	public void resolver() throws IOException {
		//O(n*log(n)) --> quicksort
		Arrays.sort(nombres);
		inicializarArray();
		
		int cont = 0;
		//O(n) en el peor de los casos (sin nombres repetidos, recorriendo uno por uno)
		//En realidad es O(n) en todos los casos, porque si bien nos "salteamos" los repetidos, estamos
		//recorriendolos uno por uno dentro del otro ciclo for
		for (int i = 0; i < nombres.length; i+= cont) {
			cont = 0;
			
			for (int j = i; j < nombres.length && nombres[i].equals(nombres[j]); j++)	{
				cont++;
			}
			
			Nombre nom = new Nombre(nombres[i], cont);			
			//O(nlog(n))
			agregarAFrecuencia(nom);
		}
		
		escribirArchivo("salida.out");
	}
	
	public void inicializarArray() {
		for (int i = 0; i < nombresFrecuentes.length; i++) {
			nombresFrecuentes[i] = new Nombre("", 0);
		}
	}
	public void agregarAFrecuencia(Nombre nombre) {		
		//Si la frecuencia es menor que la minima de todas las frec, ni nos gastamos en revisar
		if (nombresFrecuentes[0] != null && nombre.getFrecuencia() <= nombresFrecuentes[0].getFrecuencia())
			return;
		//Añadimos la frecuencia en la ultima posición
		nombresFrecuentes[0] = nombre;
		
		//Arrays.sort SOLO ordena en orden ascendente... debido a esto tenemos que tener
		//especial precaucion al momento de listar la frecuencia de nombres (es decir, vamos a tener
		//que recorrer el array en orden inverso para mostrarlos en orden descendente)
		Arrays.sort(nombresFrecuentes);		
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("salida.out"));
		
		for (int i = nombresFrecuentes.length-1; i >= 0; i--) {
			out.println(nombresFrecuentes[i].getNombre() + " " + nombresFrecuentes[i].getFrecuencia());
		}
		
		out.close();	
	}
	
	public static Principal leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantChicos =  sc.nextInt(), frec = sc.nextInt();
		//Leemos hasta la prox. linea porque nextInt no nos lleva a la prox
		sc.nextLine();
		
		Principal prin = new Principal(cantChicos, frec);
		
		for (int i = 0; i < cantChicos; i++) {
			prin.nombres[i] = sc.nextLine();
		}

		sc.close();
		return prin;
	}

}
