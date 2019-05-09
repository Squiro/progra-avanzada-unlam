package ar.edu.unlam.lista;

import ar.edu.unlam.cola.Cola;

public class ColaCL<T> implements Cola<T>{
	
	private Lista<T> lista;

	public ColaCL() {
		lista = new Lista<T>();
	}
	
	@Override
	public boolean offer(T object) {
		this.lista.pushBack(object);
		return true;
	}

	@Override
	public T poll() {
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
