package ar.edu.unlam.composite;

import java.util.ArrayList;

public class FiguraCompuesta implements Figura {

	private ArrayList<Figura> figuras;
	
	public FiguraCompuesta() {
		figuras = new ArrayList<Figura>();
	}
	
	public void agregarFigura(Figura fig) {
		this.figuras.add(fig);
	}
	
	public void eliminarFigura(Figura fig) {
		this.figuras.remove(fig);
	}
	
	@Override
	public double calcularArea() {
		double suma = 0;
		for (Figura figura : figuras) {
			suma += figura.calcularArea();
		}
		
		return suma;
	}

	@Override
	public int temperasNecesarias() {
		return (int) Math.round((calcularArea()/Figura.cm2PorPomo));
	}
	
	
}
