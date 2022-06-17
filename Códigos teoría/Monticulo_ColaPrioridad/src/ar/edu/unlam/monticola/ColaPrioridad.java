package ar.edu.unlam.monticola;

public class ColaPrioridad<T extends Comparable<T>> {
	
	Monticulo<T> mont;
	
	public ColaPrioridad() {
		this.mont = new Monticulo<T>();
	}
	
	public void offer(T dato) {
		mont.insertar(dato);
	}
	
	public T poll() {
		return mont.quitar();
	}
}
