package ar.edu.unlam.composite;

public class Circulo implements Figura {

	private double radio;
	
	public Circulo(double radio) {
		this.radio = radio;
	}

	@Override
	public double calcularArea() {
		return Math.PI*radio*radio;
	}

	@Override
	public int temperasNecesarias() {
		return (int) Math.round((calcularArea()/Figura.cm2PorPomo));
	}
	
}
