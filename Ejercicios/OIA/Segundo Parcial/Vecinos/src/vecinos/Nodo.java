package vecinos;

import java.util.ArrayList;

public class Nodo {

	private int numNodo;
	private ArrayList<Cadena> cadenas;
	private Cadena cadenaMax;
	
	public Nodo (int nodo) {
		this.numNodo = nodo;
		cadenas = new ArrayList<Cadena>();
	}

	public int getNumNodo() {
		return numNodo;
	}

	public void setNumNodo(int numNodo) {
		this.numNodo = numNodo;
	}

	public ArrayList<Cadena> getCadenas() {
		return cadenas;
	}

	public void setCadenas(ArrayList<Cadena> recorridos) {
		this.cadenas = recorridos;
	}

	public Cadena getCadenaMax() {
		return cadenaMax;
	}

	public void setCadenaMax(Cadena cadenaMax) {
		this.cadenaMax = cadenaMax;
	}
	
}
