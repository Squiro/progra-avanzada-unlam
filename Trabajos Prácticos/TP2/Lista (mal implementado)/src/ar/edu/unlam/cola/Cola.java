package ar.edu.unlam.cola;

public interface Cola<T> {
	
	boolean offer(T object);
	T poll();
	T peek();
	boolean isEmpty();
	void empty();
}
