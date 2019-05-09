package ar.edu.unlam.lista;

public class Nodo<T> {
	
	public Nodo<T> ant;
	public T dato;
	public Nodo<T> sig;
	
	public Nodo(Nodo<T> ant, T dato, Nodo<T> sig) {
		this.ant = ant;
		this.dato = dato;
		this.sig = sig;
	}
	
	/*public Nodo<T> getAnt() {
		return ant;
	}

	public void setAnt(Nodo<T> ant) {
		this.ant = ant;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public Nodo<T> getSig() {
		return sig;
	}

	public void setSig(Nodo<T> sig) {
		this.sig = sig;
	}*/
}
