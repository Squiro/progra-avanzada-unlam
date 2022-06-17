package ar.edu.unlam.composite;

import org.junit.Assert;
import org.junit.Test;

public class TestFiguras {

	@Test
	public void queCalculaAreaTriang() {
		Triangulo triang = new Triangulo(2, 2);
		Assert.assertEquals(triang.calcularArea(), 2, 0.0);
	}
	
	public void queCalculaTemperas() {
		Circulo circ = new Circulo(2);
		Cuadrado cuad = new Cuadrado(2);
		Triangulo triang = new Triangulo(2, 2);
		FiguraCompuesta comp = new FiguraCompuesta();
		comp.agregarFigura(circ);
		comp.agregarFigura(cuad);
		
		double areaTotal = comp.calcularArea() + triang.calcularArea();
		
		System.out.println(areaTotal/Figura.cm2PorPomo);
	}

}
