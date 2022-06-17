package ar.edu.unlam.lista;

import java.util.NoSuchElementException;

public class Lista<T> {
	
	private int size;
	private Nodo<T> first;
	private Nodo<T> last;
	
	public Lista() {
		size = 0;
		first = null;
		last = null;
	}

	public void pushBack(T dato) {
		Nodo<T> l = last;
		Nodo<T> nuevo = new Nodo<T>(l, dato, null);
		
		last = nuevo;
		
		if (l == null) 
			first = nuevo;
		else
			l.sig = nuevo;
		
		size++;		
	}
	
	public T popBack() {
		if (last == null)
			throw new NoSuchElementException();		
		
		T dato = last.dato;
		Nodo<T> ant = last.ant;
		
		last.dato = null;
		last.ant = null;
		last = ant;
		
		if (ant == null)
			first = null;
		else
			ant.sig = null;		
		size--;
		
		return dato;
	}
	
	public void pushFront(T dato) {
		Nodo<T> f = first;
		Nodo<T> nuevo = new Nodo<T>(null, dato, f);
		
		first = nuevo;
		
		if (f == null)
			last = nuevo;
		else 
			f.ant = nuevo;
		size++;
	}
	
	public T popFront() {
		if (first == null)
			throw new NoSuchElementException();
		
		T dato = first.dato;
		Nodo<T> sig = first.sig;
		
		first.dato = null;
		first.sig = null;
		
		first = sig;
		
		if (sig == null)
			last = null;
		else
			sig.ant = null;		
		size--;
		
		return dato;
	}
	
	public boolean remove(T dato) {
		for (Nodo<T> x = first; x != null; x = x.sig) {
			if (dato.equals(x.dato)) {
				remover(x);
				return true;
			}
		}
		
		return false;
	}
	
	public void remover(Nodo<T> x) {
		Nodo<T> ant = x.ant;
		Nodo<T> sig = x.sig;
		
		if (ant == null) {
			first = sig;			
		}
		else {
			ant.sig = sig;
			x.ant = null;
		}
		
		if (sig == null) {
			last = ant;
		} else {
			sig.ant = ant;
			x.ant = null;
		}
		
		x.dato = null;
		size--;
	}
	
	public void reverse() {
		Nodo<T> curr = first;
		Nodo<T> temp = null;
		
		while (curr != null) {
			temp = curr.ant;
			curr.ant = curr.sig;
			curr.sig = temp;
			curr = curr.ant;
		}

	    if (temp != null) { 
	        first = temp.ant; 
	    } 
	}
	
	public boolean insertAt(int pos, T dato) {
		if (pos < 0 || pos > size)
			return false;
		
		Nodo<T> x = first;
		int indice = 0;
		
		while (indice != pos) {
			x = x.sig;
			indice++;
		}		
		
		Nodo<T> ant = x.ant;
		Nodo<T> nuevo = new Nodo<T>(ant, dato, x);
		
		x.ant = nuevo;
		if (ant == null) {
			first = nuevo;
		} else {
			ant.sig = nuevo;			
		}
		
		size++;
		return true;		
	}
	
	public boolean eraseAt(int pos) {
		if (pos < 0 || pos > size)
			return false;
		Nodo<T> x = first;
		int indice = 0;
		
		while (indice != pos) {
			x = x.sig;
			indice++;
		}
		
		remover(x);
		
		return true;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void empty() {
		size = 0;
		first = null;
		last = null;
	}
	
	public int search(T dato) {
		int indice = 0;
		for (Nodo<T> x = first; x != null; x = x.sig) {
            if (dato.equals(x.dato))
                return indice;
            indice++;
        }
    
		return -1;		
	}
	
	public T searchAt(int pos) {
		if (pos < 0 || pos > size)
			return null;
		
		Nodo<T> x = first;
		int indice = 0;
		
		while (indice != pos) {
			x = x.sig;
			indice++;
		}
		
		return x.dato;
	}
	
	public int size() {
		return size;
	}
	
	public void printList() {
		Nodo<T> x = first;
		
		while (x.sig != null) {
			System.out.println(x.dato);
			x = x.sig;
		}
	}
}
