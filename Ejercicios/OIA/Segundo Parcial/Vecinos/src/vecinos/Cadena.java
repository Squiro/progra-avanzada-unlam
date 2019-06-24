package vecinos;

import java.util.ArrayList;

public class Cadena {
	
	private ArrayList<Arista> aristas; 
	private int valor;
	
	public Cadena() {
		this.aristas = new ArrayList<Arista>();
	}

	public ArrayList<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(ArrayList<Arista> aristas) {
		this.aristas = aristas;
	}
	
	public void calcularValor() {
		int min = aristas.get(0).getCosto();
		
		for (Arista arista : aristas) {
			if (arista.getCosto() < min)
				min = arista.getCosto();
		}
		
		this.valor = min;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
