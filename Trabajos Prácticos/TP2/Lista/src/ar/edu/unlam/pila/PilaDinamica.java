package ar.edu.unlam.pila;

public class PilaDinamica<T> implements Pila<T> {

	private int tamPila = 64;
	private Object[] pila;
	private int tope;
	
	public PilaDinamica() {
		this.pila = new Object[tamPila];
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
		tamPila = 64;
		tope = 0;
		pila = new Object[64];
	}
	
	private void resizeArray() {
		Object[] pila_nueva = new Object[pila.length*2];
		
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
