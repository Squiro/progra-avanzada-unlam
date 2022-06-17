package ar.edu.unlam.composite;

public class Principal {
	
	public static void main(String[] args) {
		Circulo circ = new Circulo(2);
		Cuadrado cuad = new Cuadrado(2);
		Triangulo triang = new Triangulo(2, 2);
		FiguraCompuesta comp = new FiguraCompuesta();
		comp.agregarFigura(circ);
		comp.agregarFigura(cuad);
		
		FiguraCompuesta comp2 = new FiguraCompuesta();
		comp2.agregarFigura(comp);
		comp2.agregarFigura(triang);
		
		System.out.println(comp2.calcularArea());	
	}
}
