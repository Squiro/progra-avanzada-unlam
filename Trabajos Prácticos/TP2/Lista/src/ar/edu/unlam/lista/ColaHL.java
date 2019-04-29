package ar.edu.unlam.lista;

import ar.edu.unlam.cola.Cola;

public class ColaHL<T> extends Lista<T> implements Cola<T> {

	@Override
	public boolean offer(T object) {
		this.pushBack(object);
		return true;
	}

	@Override
	public T poll() {
		return this.popFront();		
	}

	@Override
	public T peek() {
		this.searchAt(0);
		return null;
	}

}
