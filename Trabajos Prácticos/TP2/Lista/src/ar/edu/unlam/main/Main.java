package ar.edu.unlam.main;

import ar.edu.unlam.cola.ColaDinamica;
import ar.edu.unlam.cola.ColaEstatica;
import ar.edu.unlam.pila.PilaDinamica;
import ar.edu.unlam.pila.PilaEstatica;

public class Main {
	
	public static void main(String[] args) {

		System.out.println("TIEMPO PILA ESTATICA: " + calcularTiempoPilaEstatica());
		System.out.println("TIEMPO PILA DINAMICA: " + calcularTiempoPilaDinamica());
		System.out.println("TIEMPO COLA ESTATICA: " + calcularTiempoColaEstatica());
		System.out.println("TIEMPO COLA DINAMICA: " + calcularTiempoColaDinamica());
	}
	
	static long calcularTiempoPilaEstatica() {
		PilaEstatica<Integer> pilaEst = new PilaEstatica<Integer>(1000000);
		long startTime = System.currentTimeMillis(), endTime;
		
		for (int i = 0; i < 1000000; i++) {
			pilaEst.push(i);
		}
		
		endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}
	
	static long calcularTiempoPilaDinamica() {
		PilaDinamica<Integer> pilaDin = new PilaDinamica<Integer>();
		long startTime = System.currentTimeMillis(), endTime;
		
		for (int i = 0; i < 1000000; i++) {
			pilaDin.push(i);
		}
		
		endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}
	
	static long calcularTiempoColaEstatica() {
		ColaEstatica<Integer> cola = new ColaEstatica<Integer>(1000000);
		long startTime = System.currentTimeMillis(), endTime;
		
		for (int i = 0; i < 1000000; i++) {
			cola.offer(i);
		}
		
		endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}
	
	static long calcularTiempoColaDinamica() {
		ColaDinamica<Integer> cola = new ColaDinamica<Integer>();
		long startTime = System.currentTimeMillis(), endTime;
		
		for (int i = 0; i < 1000000; i++) {
			cola.offer(i);
		}
		
		endTime = System.currentTimeMillis();
		
		return endTime - startTime;
	}

}
