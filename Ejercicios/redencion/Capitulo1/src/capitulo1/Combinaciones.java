package capitulo1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Combinaciones {

	private int matriz[][];
	private int combinaciones[];
	private HashMap<Vampiro, ArrayList<Movimiento>> soluciones;
	
	public Combinaciones(int matriz[][]) {
		this.matriz = matriz;
		this.combinaciones = new int[6]; //cant de discos concentricos
		this.soluciones = new HashMap<Vampiro, ArrayList<Movimiento>>();
	}
	
	public static void main(String[] args) throws IOException {
		Combinaciones comb = leerArchivo("entrada.in");
		comb.buscarVampiros();
		comb.escribirArchivo("salida.out");
	}
	
	public void buscarVampiros() {
		//Buscamos los vampiros de 4 cifras
		ArrayList<Vampiro> vampiros = hallarVampiros(4);
		
		for (Vampiro vampiro : vampiros) {
			ArrayList<Movimiento> movs = new ArrayList<Movimiento>();
			//Tenemos que mover 2 veces el primer circulo en sentido anti-horario
			//y otras 2 veces el segundo circulo en sentido anti-horario
			//para que el número empiece con 00
			int movimientos = 4; 
			
			combinaciones[0] = 2;
			movs.add(new Movimiento(2, "antihorario"));
			combinaciones[1] = 2;
			movs.add(new Movimiento(2, "antihorario"));

			//Empezamos desde el 3 circulo (cuarto contando desde el ultimo)
			for (int i = 4; i > 0 && movimientos <= 10; i--) {
				//Mover X veces el disco concentrico Y en sentido anti-horario
				int cantMovAnti = calcularMovimientosAntiHorarios(i, vampiro);
				//Mover X veces el disco concentrico Y en sentido horario
				int cantMovHor = calcularMovimientosHorarios(i, vampiro);
				
				int min = Math.min(cantMovAnti, cantMovHor);
				
				if (min == cantMovAnti) 
					movs.add(new Movimiento(cantMovAnti, "antihorario"));
				else 
					movs.add(new Movimiento(cantMovHor, "horario"));
				
				movimientos += min;
				combinaciones[4-i] = min; 
			}
			
			if (movimientos == 10) {
				soluciones.put(vampiro, movs);
				//System.out.println("Hallé algo");
			}
		}
	}
	
	//Busca de 6
	public void resolver() {
		ArrayList<Vampiro> vampiros = hallarVampiros(6);
		//Por cada vampiro hallado, debemos buscar si hay alguna combinación de 10 movimientos en
		//los circulos que me lleve a ese número...
		for (Vampiro vampiro : vampiros) {
			int movimientos = 0;
			ArrayList<Movimiento> movs = new ArrayList<Movimiento>();		
			
			for (int i = 6; i > 0 && movimientos <= 10; i--) {
				//Mover X veces el disco concentrico Y en sentido anti-horario
				int cantMovAnti = calcularMovimientosAntiHorarios(i, vampiro);
				//Mover X veces el disco concentrico Y en sentido horario
				int cantMovHor = calcularMovimientosHorarios(i, vampiro);
				
				int min = Math.min(cantMovAnti, cantMovHor);
				
				if (min == cantMovAnti) 
					movs.add(new Movimiento(cantMovAnti, "antihorario"));
				else 
					movs.add(new Movimiento(cantMovHor, "horario"));
				
				movimientos += min;
				combinaciones[6-i] = min; 
			}
			
			if (movimientos == 10) {
				soluciones.put(vampiro, movs);
				//System.out.println("Hallé algo");
			}
		}
	}
	
	public void escribirArchivo(String path) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(path));
		
		out.println("TODAS ESTAS SOLUCIONES TOMAN COMO PUNTO DE INICIO LAS FLECHAS DEL GRAFICO");
		out.println("A SU VEZ, LA NUMERACION DE LOS CIRCULOS SE TOMA EN CUENTA DESDE EL MAS PEQUEÑO HASTA EL MAS GRANDE");
		out.println("1: CIRCULO CONCENTRICO MAS PEQUEÑO... 6: CIRCULO CONCENTICO MAS GRANDE");
		out.println("PRESTAR ATENCIÓN EN LOS SENTIDOS DE LAS ROTACIONES DEL CIRCULO");
		
		out.println();
		out.println();
		
		for (Entry<Vampiro, ArrayList<Movimiento>> entry : soluciones.entrySet()) {
			out.println("Para el número vampiro " + entry.getKey().getNumero() + ": ");
			ArrayList<Movimiento> movs = entry.getValue();
			
			for (int i = 0; i < movs.size(); i++) {
				out.println("Rotar el círculo concéntrico número " + (i+1) + ", " + movs.get(i).getRotaciones() + " veces "
						+ "en sentido " + movs.get(i).getSentido());
			}
			out.println();
			out.println();
		}
		
		out.close();
	}
	
	public int calcularMovimientosAntiHorarios(int digito, Vampiro vamp) {
		int i = 0;
		int num = (int) (vamp.getNumero()/Math.pow(10, digito-1))%10;
		
		//Mientras que no recorramos los diez números del circulo concentrico, y mientras que
		//el digito que estamos 
		while (i < matriz[0].length && matriz[matriz.length-digito][i] != num) {
			i++;
		}
		
		return i;
	}	
	
	public int calcularMovimientosHorarios(int digito, Vampiro vamp) {
		int i = 0;
		int num = (int) (vamp.getNumero()/Math.pow(10, digito-1))%10;
		
		if (matriz[matriz.length-digito][0] == num)
			return i;
		else
			i = 1;
		
		//Mientras que no recorramos los diez números del circulo concentrico, y mientras que
		//el digito que estamos 
		while (i < matriz[0].length && matriz[matriz.length-digito][matriz[0].length-i] != num) {
			i++;
			//cant++;
		}
		
		return i;
	}
	
	//Devuelve un ArrayList con los N numeros vampiros de cantCifras
	public ArrayList<Vampiro> hallarVampiros(int cantCifras) {
		ArrayList<Vampiro> vampiros = new ArrayList<Vampiro>();
		
		//Los 148 números vampiros de 6 dígitos van a estar comprendidos
		//entre los números 100.000 y 999.999. Para obtenerlos, recorremos ese rango y chequeamos si
		//los números dentro de ese rango son vampiros.
		
		//Start: número de comienzo 
		//Tens: indica si estamos trabajando con decenas, centenas, miles, millones...
		//End: número de finalización (el comienzo por 10)
		
        //long start = (long) Math.pow(10, cantCifras-1), tens = 10, end = start * 10;
		long start = (long) Math.pow(10, cantCifras-1), tens = (long) Math.pow(10, cantCifras-2), end = start * 10;
        
        for (long i = start; i < end; i++) {
        	Vampiro vamp = Vampiro.hallarColmillos(i, tens);
        	
        	if (vamp != null) {
        		vampiros.add(vamp);
            }
        }
        
        return vampiros;
	}
	
	public static Combinaciones leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int fil = sc.nextInt(), col = sc.nextInt();
		int matriz[][] = new int[fil][col];
		
		for (int i = 0; i < fil; i++) {
			for (int j = 0; j < col; j++) {
				matriz[i][j] = sc.nextInt();
			}
		}
		
		sc.close();
		
		return new Combinaciones(matriz);		
	}
	
	
}
