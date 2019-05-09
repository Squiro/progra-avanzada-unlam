package ar.edu.unlam.cola;

import java.util.NoSuchElementException;

public class ColaDinamica<T> implements Cola<T> {

	private class Nodo<T> {		
		public T dato;
		public Nodo<T> sig;		
		
		public Nodo() {
			this.dato = null;
			this.sig = null;
		}
	}
	
	private Nodo<T> first;
	private Nodo<T> last;
	
	
	@Override
	public boolean offer(T object) {
		Nodo<T> l = last;
		Nodo<T> nuevo = new Nodo<T>();		
		
		nuevo.dato = object;
		nuevo.sig = null;
		last = nuevo;
		
		if (l == null) 
			first = nuevo;
		else
			l.sig = nuevo;	
		
		return true;
	}

	@Override
	public T poll() {
		if (first == null)
			throw new NoSuchElementException();
		
		T dato = first.dato;
		
		first = first.sig;
		
		if (first == null)
			last = null;
		
		return dato;
	}

	@Override
	public T peek() {
		if (first == null)
			throw new NoSuchElementException();
		
		return first.dato;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public void empty() {
		first = null;
		last = null;		
	}	
}
