package ar.edu.unlam.acorrer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Maraton {
	
	private Corredor corredores[];
	private Corredor ordLlegada[];
	private int matCatFem[][];
	private int matCatMas[][];
	private int matPodioCatFem[][];
	private int matPodioCatMas[][];
	
	
	public Maraton(int cantCorredores, int cantMeta, int cantCatF, int cantCatM) {
		this.corredores = new Corredor[cantCorredores];
		this.ordLlegada = new Corredor[cantMeta];
		this.matCatFem = new int[cantCatF][2];
		this.matCatMas = new int[cantCatM][2];
		this.matPodioCatFem = new int[cantCatF][3];
		this.matPodioCatMas = new int[cantCatM][3];
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Maraton mar = leerArchivo("entrada.in");
		
		for (int i = 0; i < mar.corredores.length; i++) {
			System.out.println(mar.corredores[i].getSexo());
		}
	}
	
	public void resolver() {
		for (int i = 0; i < ordLlegada.length; i++) {
			if (ordLlegada[i].getSexo() == 'F') {
				for (int j = 0; j < matCatFem.length; j++) {
					if (ordLlegada[i].getEdad() >= matCatFem[j][0] && ordLlegada[i].getEdad() <= matCatFem[j][1]) {
						
					}
				}
			}
		}
	}
	
	
	
	public static Maraton leerArchivo(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(path));
		
		int cantCorredores, cantCatF, cantCatM, cantMeta; 
		
		cantCorredores = sc.nextInt();
		cantCatF = sc.nextInt();
		cantCatM = sc.nextInt();
		cantMeta = sc.nextInt();
		
		Maraton mar = new Maraton(cantCorredores, cantMeta, cantCatF, cantCatM);
		
		//Leemos el rango de edades de las categorías femeninas
		for (int i = 0; i < cantCatF; i++) {
			mar.matCatFem[i][0] = sc.nextInt();
			mar.matCatFem[i][1] = sc.nextInt();
		}
		
		for (int i = 0; i < cantCatM; i++) {
			mar.matCatMas[i][0] = sc.nextInt();
			mar.matCatMas[i][1] = sc.nextInt();
		}
		
		for (int i = 0; i < cantCorredores; i++) {
			Corredor cor = new Corredor(sc.nextInt(), sc.next().charAt(0));
			mar.corredores[i] = cor;			
		}
		
		for (int i = 0; i < cantMeta; i++) {
			mar.ordLlegada[i] = mar.corredores[sc.nextInt()-1];
		}		
		
		sc.close();		
		
		return mar;		
	}	
}
