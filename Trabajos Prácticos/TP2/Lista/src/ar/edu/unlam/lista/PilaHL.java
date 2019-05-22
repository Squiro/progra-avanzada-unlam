package ar.edu.unlam.lista;

import ar.edu.unlam.pila.Pila;

public class PilaHL<T> extends Lista<T> implements Pila<T> {
	
	@Override
	public boolean push(T object) {
		this.pushFront(object);
		return true;
	}

	@Override
	public T pop() {
		return this.popFront();
	}

	@Override
	public T peek() {		
		return this.searchAt(0);
	}
}
