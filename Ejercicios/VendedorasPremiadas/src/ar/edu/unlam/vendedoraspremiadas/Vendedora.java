package ar.edu.unlam.vendedoraspremiadas;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Vendedora {	
	
	private int cantVentas; //Cantidad de ventas realizada por esta vendedora
	private int importeVentas[]; //Vector de importes de cada una de las ventas de la vendedora
	private int numVendedora; //Número que identifica a la vendedora
	private int importeMaximo; //Importe máximo obtenido de la suma de N ventas consecutivas
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
		Scanner entrada = new Scanner(new FileReader("premio1.in"));
		//Leemos la cantidad de vendedoras (primer entero del archivo)
		cantVendedoras = entrada.nextInt();
		
		//Creamos una lista del tipo vendedoras
		List<Vendedora> vendedoras = new ArrayList<Vendedora>();
		
		//Cargamos la lista de vendedoras con las que se lean del archivo
		for (int i = 0; i < cantVendedoras; i++) {
			vendedoras.add(leerNuevaVendedora(entrada, i));
		}		
		
		//Leemos el último entero, que es el número de ventas consecutivas que se debe tener en cuenta para el concurso
		numVentasConsec = entrada.nextInt();
		
		//Cerramos el archivo de entrada porque ya no nos sirve
		entrada.close();
			
		//Recorremos la lista de vendedoras y removemos aquellas que no cuentan con la cantidad necesaria de ventas
		Iterator<Vendedora> iter = vendedoras.iterator();
		
		//Mientras que exista un elemento en la lista
		while (iter.hasNext()) {
			//Lo asignamos a una variable
		    Vendedora ven = iter.next();
		    //Y chequeamos si la cant de ventas de la vendedoras es menor a la cantidad requerida
		    if (ven.cantVentas < numVentasConsec) {
		    	iter.remove();
		    }		        
		}
		
		/* Lamentablemente el foreach no funciona igual que en C#, hay que usar un iterator
		 * for (Vendedora ven : vendedoras) {
			if (ven.cantVentas < numVentasConsec) {
				vendedoras.remove(ven);		
			}
		}*/		
		
		//Declaramos el PrintWriter que vamos a usar para escribir en la salida
		PrintWriter salida = new PrintWriter(new FileWriter("ganadoras.out"));
		
		if (vendedoras.size() > 0) {			
			int importeMax = 0;
			int maxVentas = 0;	
			
			//Calculamos inicialmente el "importe maximo" para c/u de las vendedoras que pasaron el filtro, con el numVentasConsec original	
			//y a su vez obtenemos el mayor de todos los importes maximos
			importeMax = buscarImporteMayorEnLista(vendedoras);
			maxVentas = hallarMaxVentasEnLista(vendedoras);			
			
			List<Vendedora> empatantes = new ArrayList<Vendedora>();		
			
			//Recorremos la lista de vendedoras
			iter = vendedoras.iterator();
			
			while (iter.hasNext()) {
				Vendedora ven = iter.next();
				//Si encontramos que una o más vendedoras coincide con el importe máximo, la añadimos a la lista de empatantes
				if (ven.importeMaximo == importeMax) {
					empatantes.add(ven);
				}
				else {
					//Caso contrario removemos a la vendedora (¿de que nos sirve tener a alguien que ni siquiera llego al importe max?)
					iter.remove();
				}				
			}		
		
			//Refinamos la lista de empatantes buscando eliminar el empate
			desempatar(empatantes, maxVentas);		
			
			if (empatantes.size() > 1) {
				System.out.println("No se pudo desempatar");
				salida.println("No se pudo desempatar");
			}
			else {
				System.out.println("Hay ganador");
				salida.println(empatantes.get(0).numVendedora);
				salida.println(numVentasConsec + " " + empatantes.get(0).importeMaximo);
			}
		}
		else {
			salida.println("No hay ganadoras");
			System.out.println("No hay ganadoras");
		}			
		
		salida.close();		
	}	
	
	/** Refina la lista de empatantes hasta que la misma quede vacía, o ya no exista forma de desempatar
	 *  a las vendedoras que quedaron en la lista.
	 *  
	 * @param empatantes: ArrayList del tipo Vendedora
	 * @param maxVentas: cantidad mayor de ventas realizada por una de las vendedoras, en comparación con las ventas de las demás
	 */
	
	public static void desempatar(List<Vendedora> empatantes, int maxVentas) {
		boolean empate = true;
		int importeMay = 0;
		
		//Iterator que vamos a usar para remover elementos de la lista de empatantes sin caer en una exception
		Iterator<Vendedora> iter; 
		
		//Iteramos mientras que haya empate, y la cantidad de ventas consecutivas actuales sea menor a la cantidad máxima de ventas 
		while (empate && numVentasConsec <= maxVentas) {
			//Seteamos (y reseteamo en cada iteración) el iterator al inicio de la lista
			iter = empatantes.iterator();
			//Si la lista de empatantes tiene más de un elemento, quiere decir que hubo empate y tenemos que desempatar
			if (empatantes.size() > 1) {
				//empate = true;
				//Aumentamos en 1 la cantidad de ventas consecutivas que vamos a tomar en cuenta para calcular los importes
				numVentasConsec++;
				//Calculamos los importes máximos de cada
				importeMay =  buscarImporteMayorEnLista(empatantes);			
				
				//Mientras que exista un elemento en la lista al que ir
				while (iter.hasNext()) {
					//Lo agarramos
					Vendedora ven = iter.next();
					//Y si su importe es menor que el importe mayor que de calcular, lo borramos de la lista
					if (ven.importeMaximo < importeMay) {
						iter.remove();
					}					
				}
				
			}
			else {
				//Self-explanatory
				empate = false;
			}
		} 	
	}
	
	/** Recorre la lista que recibe por parametro y calcula el importeMaximo de cada uno de los elementos de esa lista.
	 *  Compara ese importe máximo con los calculados para hallar el mayor.
	 *  
	 *  (Sí, este método se debería simplificar para que solo haga una cosa, pero eso implicaría hacer otro método con otro foreach adentro,
	 *  lo que significa recorrer dos veces la lista... por ahora lo dejo así)
	 * 
	 * @param lista
	 * @return importeMay
	 */
	public static int buscarImporteMayorEnLista(List<Vendedora> lista) {
		int importeMay = 0;
		
		for (Vendedora ven : lista) {
			ven.hallarImporteMaximo(numVentasConsec);
			
			if (ven.importeMaximo > importeMay) {
				importeMay = ven.importeMaximo;
			}
		}
		
		return importeMay;
	}
	
	/** Recorre la lista que recibe por parametro y halla la mayor cantidad de ventas que fueron realizadas por una vendedora dentro
	 *  de la lista.
	 *  
	 * @param lista
	 * @return
	 */	
	public static int hallarMaxVentasEnLista(List<Vendedora> lista) {
		int maxVentas = 0;
		
		for (Vendedora ven : lista) {			
			if (ven.cantVentas > maxVentas) {
				maxVentas = ven.cantVentas;
			}				
		}	
	
		return maxVentas;		
	}
	
	/** Lee una vendedora desde el archivo y retorna un objeto de la clase Vendedora con los datos de la vendedora leida
	 * 
	 * @param entrada: Scanner usado para leer el archivo de entrada
	 * @param numVendedora: valor actual de la variable utilizada para iterar entre la cantidad de vendedoras
	 * @return
	 */
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
	

	/** Halla el importe máximo teniendo en cuenta las N ventas consecutivas
	 * 
	 * @param cantVentas: cantidad de ventas consecutivas a tener en cuenta
	 */
	public void hallarImporteMaximo(int cantVentas) {
		int aux = 0;
		
		//Tener en cuenta la condición del for, el mismo va a cortar en i+numVentasConsec == importeVentas
		for (int i = 0; i+cantVentas <= this.importeVentas.length; i++) {			
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