package ar.edu.unlam.pila;

public interface Pila<T> {
	
	boolean push(T object);
	T pop();
	T peek();
	boolean isEmpty();
	void empty();
}
