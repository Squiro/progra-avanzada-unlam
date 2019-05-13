package ar.edu.unlam.monticola;

public class Monticulo<T extends Comparable<T>> {

	private int size = 128;
	private int cantElem;
	private T[] monticulo;
	
	public Monticulo() {
		this.cantElem = 0;
		this.monticulo = (T[]) new Comparable[size];
	}
		
	public void insertar(T dato) {
		if (monticulo.length == size) 
			resize();
		
		this.monticulo[cantElem+1] = dato;
		cantElem++;
		reordenarInsertar();		
	}
	
	public T quitar() {
		T dato = this.monticulo[1];
		
		reordenarQuitar();
		//cantElem--;
		
		return dato;
	}
	
	public void reordenarInsertar() {
		if (this.cantElem == 1)
			return; 
		
		int i = cantElem;		
		
		while (this.monticulo[i/2].compareTo(this.monticulo[i]) > 0 && i > 1) {
			swap(i/2, i);
			i = i/2;
		}		
	}
	
	public void reordenarQuitar() {
		if (cantElem == 1)
			return;
		
		this.monticulo[1] = this.monticulo[cantElem];
		cantElem--;
		
		int i = 1;
		int indiceMin = 2*i;
		
		while (indiceMin < cantElem) {		
			//¿Hay hijo a la derecha?
			if (indiceMin+1 < cantElem) {
				//Busco el minimo entre los dos hijos
				indiceMin = minimo(indiceMin, indiceMin+1);
			}
			else {
				indiceMin = 2*i;
			}
			
			//Si es mayor swapeamos
			if (this.monticulo[i].compareTo(this.monticulo[indiceMin]) > 0) {
				swap(i, indiceMin);
			}
			
			//Actualizamos los indices para la proxima busqueda...
			i = indiceMin;		
			indiceMin = 2*i;
		}
	}
	
	public void swap(int i, int j) {
		T aux;
		aux = this.monticulo[i];
		this.monticulo[i] = this.monticulo[j];
		this.monticulo[j] = aux;		
	}
	
	public int minimo(int hi, int hd) {
		if (this.monticulo[hi].compareTo(this.monticulo[hd]) < 0) {
			return hi; 
		} 		
		return hd;		
	}
	
	public void resize() {
		this.size *= 2;
		T[] montiNuevo =  (T[]) new Comparable[size];
				
		for (int i = 0; i < monticulo.length; i++) {
			montiNuevo[i] = monticulo[i];
		}
		
		this.monticulo = montiNuevo;
	}
	
	public void mostrarMonticulo() {
		for (int i = 1; i <= this.cantElem; i++) {
			System.out.println(this.monticulo[i].toString());
		}
	}
		
}
