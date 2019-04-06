package ar.edu.unlam.vendedoraspremiadas;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendedora {	
	
	private int cantVentas; //Cantidad de ventas realizada por esta vendedora
	private int importeVentas[]; //Vector de importes de cada una de las ventas de la vendedora
	private int numVendedora; //Número que identifica a la vendedora
	private int importeMaximo; //Importe máximo obtenido de la suma de N ventas consecutivas
	private int cantventasConsideradas; //La cantidad de ventas que se consideraron al momento de decidir el premio
	private static int numVentasConsec; //Número de ventas consecutivas tomadas en cuenta en el concurso
	
	
	public Vendedora(int cantVentas, int importeVentas[], int numVendedora)
	{
		this.cantVentas = cantVentas;
		this.importeVentas = importeVentas;
		this.numVendedora = numVendedora;	
		this.importeMaximo = 0;
	}
	
	public static void main(String[] args) throws IOException {
		int cantVendedoras;	//Almacena la cantidad de vendedoras
		//Creamos un Scanner para leer el archivo de entrada
		Scanner entrada = new Scanner(new FileReader("premio.in"));
		//Leemos la cantidad de vendedoras (primer entero del archivo)
		cantVendedoras = entrada.nextInt();
		
		//Creamos un array del tipo vendedoras teniendo en cuenta la cant. de las mismas
		//Vamos a usar este array para iterar sobre el mismo y encontrar a la vendedora que se lleve el premio
		//Vendedora vendedoras[] = new Vendedora[cantVendedoras];
		
		//O quizás es mejor una lista
		List<Vendedora> vendedoras = new ArrayList<Vendedora>();
		
		//Cargamos el array de vendedoras
		for (int i = 0; i < cantVendedoras; i++) {
			//vendedoras[i] = leerNuevaVendedora(entrada, i);
			vendedoras.add(leerNuevaVendedora(entrada, i));
		}		
		
		//Leemos el último entero, que es el número de ventas consecutivas que se debe tener en cuenta para el concurso
		numVentasConsec = entrada.nextInt();
		
		//Cerramos el archivo de entrada porque ya no nos sirve
		entrada.close();
		
		
		while (vendedoras.size() > 1) {
			
		}
		
		//Recorremos la lista de vendedoras y removemos aquellas que no cuentan con la cantidad necesaria de ventas
		for (Vendedora ven : vendedoras) {
			if (ven.cantVentas < numVentasConsec) {
				vendedoras.remove(ven);		
			}
		}
		
		int importeMax = 0;		
		//Calculamos inicialmente el importe maximo para las vendedoras que pasaron el filtro, con el numVentasConsec original	
		//y a su vez obtenemos el importe maximo
		for (Vendedora ven : vendedoras) {
			ven.hallarImporteMaximo(numVentasConsec);
			if (ven.importeMaximo > importeMax) {
				importeMax = ven.importeMaximo;
			}
				
		}
		
		//Buscamos casos de empate
		boolean empate = false;
		List<Vendedora> empatantes = new ArrayList<Vendedora>();
		
		//TODO: Buscar otro corte. No podes comparar con el size de empatantes, te va a dar siempre un empate
		do {
			//Recorremos la lista de vendedoras
			for (Vendedora ven : vendedoras) {
				//Si encontramos que una o más vendedoras coincide con el importe máximo, la añadimos a la lista de empatantes
				if (ven.importeMaximo == importeMax) {
					empatantes.add(ven);
				}
				else {
					//Caso contrario removemos a la vendedora (¿de que nos sirve tener a alguien que ni siquiera llego al importe max?)
					vendedoras.remove(ven);
				}
			}
			
			if (empatantes.size() > 1) {
				empate = true;
				for (Vendedora ven : vendedoras) {
					ven.hallarImporteMaximo(numVentasConsec++);
				}
			}
			else {
				empate = false;
			}
			
		} while (empate);	
		
	}
	
	//Lee una vendedora desde el archivo y retorna un objeto de la clase Vendedora
	public static Vendedora leerNuevaVendedora(Scanner entrada, int numVendedora) {
		//Le sumamos uno para que el número concuerde con lo que esperaría un usuario normal (indices empezando desde 1)
		numVendedora++;
		
		//Leemos la cantidad de ventas de la vendedora
		int numVentas = entrada.nextInt();
		
		//Creamos un array que va a almacenar los importes de c/u de las ventas realizadas por ella
		int importeVentas[] = new int[numVentas];
		
		//Llenamos el array leyendo las ventas
		for (int i = 0; i < numVentas; i++) {
			importeVentas[i] = entrada.nextInt();
		}	
		
		//Creamos un objeto de clase Vendedora
		Vendedora ven = new Vendedora(numVentas, importeVentas, numVendedora); 
		
		//Lo retornamos a la función que nos llamó
		return ven;		
	}
	
	//Halla el importe máximo teniendo en cuenta las N ventas consecutivas
	//TODO: Chequear por outofbounds?
	public void hallarImporteMaximo(int cantVentas) {
		int aux = 0;
		
		//Tener en cuenta la condición del for, el mismo va a cortar en i+numVentasConsec
		for (int i = 0; i+cantVentas < this.importeVentas.length; i++) {			
			for (int j = 0; j < cantVentas; j++) {
				aux += this.importeVentas[i+j];
			}		
	
			if (aux > this.importeMaximo) {
				this.importeMaximo = aux;
			}
			
			aux = 0;
		}
	}
}