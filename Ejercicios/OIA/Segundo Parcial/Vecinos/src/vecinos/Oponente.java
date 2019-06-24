package vecinos;

import java.util.ArrayList;

public class Oponente {
	
	private int nodoOrigen;	
	private int cantAliados;
	
	public Oponente(int nodo) {
		this.nodoOrigen = nodo;
		this.cantAliados = 0;
	}
	
	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public int getCantAliados() {
		return cantAliados;
	}

	public void setCantAliados(int cantAliados) {
		this.cantAliados = cantAliados;
	}

}
