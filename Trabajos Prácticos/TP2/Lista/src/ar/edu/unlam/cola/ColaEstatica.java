package ar.edu.unlam.cola;

public class ColaEstatica <T> implements Cola<T> {
	
	private int tamCola = 2;
	private int primero;
	private int ultimo;
	private int cantElem;
	private T[] cola;
	
	
	public ColaEstatica() {
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;
		this.cola = (T[]) new Object[tamCola];
	}
	
	@Override
	public boolean offer(T object) {
		if (cantElem == tamCola)
			resizeArray();
		ultimo = (ultimo+1)%tamCola;
		cantElem++;
		cola[ultimo] = object;
		return true;
	}
	@Override
	public T poll() {
		if (cantElem == 0)
			return null;
		
		T elem = elemento(primero);
		primero = (primero+1)%tamCola;
		cantElem--;
		return elem;
		
	}
	@Override
	public T peek() {
		if (cantElem == 0)
			return null;
		return elemento(primero);
	}
	@Override
	public boolean isEmpty() {
		return cantElem == 0;
	}
	@Override
	public void empty() {
		this.tamCola = 64;
		this.cola = (T[]) new Object[tamCola];
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;		
	}
	
	private void resizeArray() {
		T[] cola_nueva = (T[]) new Object[cola.length*2];
		
		for (int i = 0; i < cola.length; i++) {
			cola_nueva[i] = cola[i];			
		}		
		cola = cola_nueva;
		tamCola = cola_nueva.length;
	}
	
	@SuppressWarnings("unchecked")
	private T elemento(int indice) {
		return (T)cola[indice];
	}
}
