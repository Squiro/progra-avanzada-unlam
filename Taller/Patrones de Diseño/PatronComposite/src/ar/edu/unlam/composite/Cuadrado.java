package ar.edu.unlam.composite;

public class Cuadrado implements Figura {
	
	private double lado;
	
	public Cuadrado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calcularArea() {
		return lado*lado;
	}

	@Override
	public int temperasNecesarias() {
		return (int) Math.round((calcularArea()/Figura.cm2PorPomo));
	}
}
