package ar.edu.unlam.figurageometrica;

import java.util.ArrayList;
import java.util.List;

public class Dibujo {
	
	//1 pomo de tempera = 25cm2 de pintura
	final int centimetrosPorTempera = 25;
	private int temperasAComprar;	
	
	public Dibujo() {
		this.temperasAComprar = 0;
	}

	public static void main(String[] args) {	
		Dibujo dib = new Dibujo();
		
		List<FiguraGeometrica> lista = new ArrayList<FiguraGeometrica>();
		
		FiguraGeometrica fig1 = new FiguraGeometrica(1);
		lista.add(fig1);
		FiguraGeometrica fig2 = new FiguraGeometrica(3);
		lista.add(fig2);
		FiguraGeometrica fig3 = new FiguraGeometrica(0.5);
		lista.add(fig3);
		FiguraGeometrica fig4 = new FiguraGeometrica(0.25);
		lista.add(fig4);	
		
		dib.calcularCantidadTempera(lista);
		System.out.println("Temperas a comprar: " + dib.temperasAComprar);
	}
	
	public void calcularCantidadTempera(List<FiguraGeometrica> lista) {
		for (FiguraGeometrica fig : lista) {
			this.temperasAComprar += Math.ceil(fig.obtenerArea()/centimetrosPorTempera);
		} 
	}
}
