package ar.edu.unlam.lista;

import ar.edu.unlam.pila.Pila;

public class PilaCL<T> implements Pila<T> {

	private Lista<T> lista;
	
	public PilaCL() {
		lista = new Lista<T>();
	}
	
	@Override
	public boolean push(T object) {
		this.lista.pushFront(object);
		return true;
	}

	@Override
	public T pop() {
		return this.lista.popFront();
	}

	@Override
	public T peek() {
		return this.lista.searchAt(0);
	}

	@Override
	public boolean isEmpty() {
		return this.lista.isEmpty();
	}

	@Override
	public void empty() {
		this.lista.empty();		
	}
}
