package ar.edu.unlam.pila;

public class PilaEstatica<T> implements Pila<T> {
	
	private int tamPila;
	private T[] pila;
	private int tope;
	
	public PilaEstatica(int tamPila) {
		this.tamPila = tamPila;
		this.tope = 0;
		this.pila = (T[]) new Object[tamPila];
	}			
			
	@Override
	public boolean push(T object) {
		if (tope == tamPila)
			return false;
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
		tope = 0;
	}
	
	@SuppressWarnings("unchecked")
	private T elemento(int indice) {
		return (T)pila[indice];
	}
}
