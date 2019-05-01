package ar.edu.unlam.ejercicio;

import java.util.Scanner;

public class Logaritmo {
	
	/* 

	Para un número positivo a distinto de 1 y para un número b positivo cualquiera se define el "logaritmo en base a de b" (loga(b)) mediante la relación siguiente: loga(b) = x si y sólo si ax = b
	
	La importancia del logaritmo radica en las siguientes propiedades:
	
	loga(b.c) = loga(b) + loga(c) para b > 0 y c > 0
	
	loga(bc) = c.loga(b) para b > 0
	
	loga(b/c) = loga(b) - loga(c) para b > 0 y c > 0
	
	Escribir un programa que permita el ingreso de un número natural n y encuentre el mayor entero menor o igual que log2(n).
	
	(Ayuda: Si 1 < b < 2 entonces 0 < log2(b) < 1
	
	Para n > 1, log2(n) = 1 + log2(n/2)) */
	
	private int base;
	private int argumento;
	private double logaritmo;
	
	public Logaritmo(int base, int argumento) {
		this.base = base;
		this.argumento = argumento;
		this.logaritmo = 0;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int num;
		
		do  {
			System.out.println("Ingresá un numero > 0:");
		} while ((num = in.nextInt()) < 0);
		
		in.close();
		
		Logaritmo l = new Logaritmo(2, num);
		l.calcularLogaritmo();
		System.out.println(l.logaritmo);
		
	}
	
	public void calcularLogaritmo() {
		if (argumento == 1) {
			return;
		}
		
		logaritmo = log2(argumento);				
	}
	
	double log2(int n) {
		if (n < 2) {
			return 0;
		}
		
		return 1 + log2(n/2);
	}
	


}
