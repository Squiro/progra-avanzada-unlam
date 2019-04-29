package ar.edu.unlam.formandoequipos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Equipo {
	
	private int cantColab; //Cantidad de colaboradores en el grupo
	private int cantPreguntas; //Cantidad de preguntas iguales (empezando desde la primera)
	private String preguntas; //String que contiene las preguntas comunes
	//private List<String> strPreguntas; //Lista de strings que tienen las preguntas respondidas
	
	
	public Equipo (int cantColab, int cantPreguntas, String preguntas)
	{
		this.cantColab = cantColab;
		this.cantPreguntas = cantPreguntas;
		//this.strPreguntas = new ArrayList<String>(preguntasColab);
		this.preguntas = new String(preguntas);
	}

	public static void main(String[] args) throws IOException {			
		int cantPreguntas;
		int cantColab;		
		Scanner sc = new Scanner(new FileReader("equipo1.in"));
		
		cantPreguntas = sc.nextInt();
		cantColab = sc.nextInt();
		//Avanzamos una línea extra, porque nextInt no lee el newline character del final, nos quedamos atrasados una linea
		sc.nextLine();
		
		String preguntas[] = new String[cantColab];
		
		for (int i = 0; i < cantColab; i++) {			
			preguntas[i] = sc.nextLine();
		}	
		
		sc.close();
		
		//List<String> listaColab = new ArrayList<String>();
		int colabs = 1;
		List<Equipo> equipos = new ArrayList<Equipo>();		
		
		while (equipos.isEmpty()) {		
			for (int i = 0; i < cantColab; i++) {
				for (int j = i+1; j < cantColab; j++) {
					if (compararStrings(preguntas[i], preguntas[j], cantPreguntas)) {
						//listaColab.add(preguntas[j]);
						colabs++;
						preguntas[j] = "";
					}
				}
				
				if (colabs > 1) {
					//listaColab.add(preguntas[i]);
					Equipo equi = new Equipo(colabs, cantPreguntas, preguntas[i].substring(0, cantPreguntas));
					preguntas[i] = "";
					equipos.add(equi);
				}			
				
				colabs = 1;
				//listaColab.clear();
			}
			cantPreguntas--;
		}	
		
		for (Equipo eq : equipos)
		{
			eq.escribirArchivoSalida();
		}
	}
	
	public static boolean compararStrings(String s1, String s2, int len) {		
		return s1.regionMatches(0, s2, 0, len);
	}
	
	public int calcularAfinidad() {
		return this.cantColab * (this.cantPreguntas * this.cantPreguntas);
	}
	
	public String obtenerPreguntasComunes() {
		return this.preguntas.substring(0, this.cantPreguntas);
	}
	
	public void escribirArchivoSalida() throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter("salida.out"));
		
		salida.println(this.calcularAfinidad());
		salida.println(this.obtenerPreguntasComunes());		
		salida.close();
	}
}
