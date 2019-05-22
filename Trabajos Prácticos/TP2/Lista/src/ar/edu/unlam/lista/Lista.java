package ar.edu.unlam.lista;

import java.util.NoSuchElementException;

public class Lista<T> {	
	
	private class Nodo<T> {		
		public T dato;
		public Nodo<T> sig;		
		
		public Nodo() {
			this.dato = null;
			this.sig = null;
		}
	}
	
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
		Nodo<T> nuevo = new Nodo<T>();		
		
		nuevo.dato = dato;
		nuevo.sig = null;
		last = nuevo;
		
		if (l == null) 
			first = nuevo;
		else
			l.sig = nuevo;		
		
		size++;		
	}
	
	public T popBack() {
		if (first == null)
			throw new NoSuchElementException();
		
		T dato;
		Nodo<T> curr = first, prev = first;
		
		while (curr.sig != null) {
			prev = curr;
			curr = curr.sig;
		}
		
		dato = last.dato;
		prev.sig = null;	
		
		if (curr == first) {
			first = null;
			last = null;
		}
		else 
			last = prev;	
		
		size--;
		
		return dato;		
	}	
	
	public void pushFront(T dato) {
		Nodo<T> f = first;
		Nodo<T> nuevo = new Nodo<T>();
		
		nuevo.dato = dato;
		nuevo.sig = first;		
		first = nuevo;
		
		if (f == null)
			last = nuevo;
		
		size++;
	}
	
	public T popFront() {
		if (first == null)
			throw new NoSuchElementException();
		
		T dato = first.dato;
		
		first = first.sig;
		
		if (first == null)
			last = null;
				
		size--;
		
		return dato;
	}	
	
	public boolean remove(T dato) {
		Nodo<T> prev = first;
		
		for (Nodo<T> x = first; x != null; x = x.sig) {
			if (dato.equals(x.dato)) {
				remover(x, prev);
				return true;
			}
			prev = x;
		}
		
		return false;
	}
	
	public void remover(Nodo<T> act, Nodo<T> prev) {
		prev.sig = act.sig;	
				
		if (act == first) 
			first = act.sig;
		
		if (prev.sig == null) {
			last = prev;
		} 
		
		act.dato = null;
		size--;
	}
	
	
	public void reverse() {
		if(first == null || first == last)
		  return;
		  
		 
		Nodo<T> curr = first.sig, prev = first, aux;
		
		first.sig = null;
		
		first = last;
		last = prev;
		
		while (curr != null) {
			aux = curr.sig;
			curr.sig = prev;
			prev = curr;
			curr = aux;
		}
	}
	
	public boolean insertAt(int pos, T dato) {
		if (pos < 0 || pos > size)
			return false;
		
		Nodo<T> nuevo = new Nodo<T>();
		nuevo.dato = dato;
		
		if(first == null)
		{
			nuevo.sig = null;
			first = last = nuevo;
			return true;
		}
		
		Nodo<T> x = first, prev = first;
		int indice = 0;
		
		while (indice != pos) {
			prev = x;
			x = x.sig;
			indice++;
		}		

		
		nuevo.sig = prev.sig;
		
		prev.sig = nuevo;
		
		if (nuevo.sig == null)
			last = nuevo;	
		
		size++;
		return true;		
	}	
	
	public boolean eraseAt(int pos) {
		if (pos < 0 || pos > size)
			return false;
		
		Nodo<T> x = first, prev = first;
		int indice = 0;
		
		while (indice != pos) {
			prev = x;
			x = x.sig;
			indice++;
		}
		
		remover(x, prev);
		
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
		if (pos < 0 || pos > size || first == null)
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
		
		while (x != null) {
			System.out.println(x.dato);
			x = x.sig;
		}
	}	
}
