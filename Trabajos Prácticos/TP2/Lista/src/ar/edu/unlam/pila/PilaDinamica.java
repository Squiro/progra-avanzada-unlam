package ar.edu.unlam.pila;

import java.util.NoSuchElementException;

public class PilaDinamica<T> implements Pila<T> {

	private class Nodo<T> {		
		public T dato;
		public Nodo<T> sig;		
		
		public Nodo() {
			this.dato = null;
			this.sig = null;
		}
	}

	private Nodo<T> first;
	
	public PilaDinamica() {
		first = null;
	}
	
	@Override
	public boolean push(T object) {
		Nodo<T> nuevo = new Nodo<T>();
		
		nuevo.dato = object;
		nuevo.sig = first;
		first = nuevo;
		return true;
	}

	@Override
	public T pop() {
		if (first == null)
			throw new NoSuchElementException();
		
		T dato = first.dato;		
		first = first.sig;
		
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
	}		
}
