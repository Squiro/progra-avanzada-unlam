package ar.edu.unlam.cola;

public class ColaDinamica<T> implements Cola<T> {

	private int tamCola = 64;
	private int primero;
	private int ultimo;
	private int cantElem;
	private Object[] cola;
	
	
	public ColaDinamica() {
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;
		this.cola = new Object[tamCola];
	}
	
	@Override
	public boolean offer(T object) {
		if (cantElem == tamCola)
			resizeArray();
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
		this.tamCola = 64;
		this.cola = new Object[tamCola];
		this.primero = 0;
		this.ultimo = -1;
		this.cantElem = 0;		
	}
	
	private void resizeArray() {
		Object[] cola_nueva = new Object[cola.length*2];
		
		for (int i = 0; i < cola.length; i++) {
			cola_nueva[i] = cola[i];			
		}		
		cola = cola_nueva;
		tamCola = cola_nueva.length;
	}
	
	@SuppressWarnings("unchecked")
	private T elemento(int indice) {
		return (T)cola[indice];
	}
}
