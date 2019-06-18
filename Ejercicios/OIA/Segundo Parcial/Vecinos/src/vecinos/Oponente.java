package vecinos;

import java.util.ArrayList;

public class Oponente {
	
	private int nodoOrigen;	
	private ArrayList<Integer> aliados;
	
	public Oponente(int nodo) {
		this.nodoOrigen = nodo;
		this.aliados = new ArrayList<Integer>();
	}
	
	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public void addAliado(int aliado) {
		this.aliados.add(aliado);
	}
	
	public ArrayList<Integer> getAliados() {
		return this.aliados;
	}
}
