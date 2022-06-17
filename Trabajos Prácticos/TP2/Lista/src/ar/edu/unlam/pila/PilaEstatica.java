package ar.edu.unlam.pila;

public class PilaEstatica<T> implements Pila<T> {
	
	private int tamPila = 2;
	private T[] pila;
	private int tope;
	
	public PilaEstatica() {
		this.pila = (T[]) new Object[tamPila];
		this.tope = 0;
	}
	
	@Override
	public boolean push(T object) {
		if (tamPila == tope)
			resizeArray();			
		pila[tope++] = object;
		return true;
	}

	@Override
	public T pop() {
		if (tope == 0)
			return null;
		return elemento(--tope);
	}

	@Override
	public T peek() {
		if (tope == 0)
			return null;
		return elemento(tope-1);
	}

	@Override
	public boolean isEmpty() {
		return tope == 0;
	}

	@Override
	public void empty() {
		tamPila = 2;
		tope = 0;
		pila = (T[]) new Object[2];
	}
	
	private void resizeArray() {
		T[] pila_nueva = (T[]) new Object[pila.length*2];
		
		for (int i = 0; i < pila.length; i++) {
			pila_nueva[i] = pila[i];			
		}		
		pila = pila_nueva;
		tamPila = pila_nueva.length;
	}
	
	@SuppressWarnings("unchecked")
	private T elemento(int indice) {
		return (T)pila[indice];
	}
}
