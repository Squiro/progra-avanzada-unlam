package ar.edu.unlam.cola;

public class ColaEstatica <T> implements Cola<T> {
	
	private int tamCola;
	private int primero;
	private int ultimo;
	private int cantElem;
	private T[] cola;
	
	public ColaEstatica(int tamCola) {
		this.tamCola = tamCola;
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;
		this.cola = (T[]) new Object[tamCola];
	}
	
	@Override
	public boolean offer(T object) {
		if (cantElem == tamCola)
			return false;
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
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;		
	}
	
	@SuppressWarnings("unchecked")
	private T elemento(int indice) {
		return (T)cola[indice];
	}
	
}
