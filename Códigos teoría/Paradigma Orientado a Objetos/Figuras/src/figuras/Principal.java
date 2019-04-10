package figuras;

public class Principal {

	public static void main(String[] args) {
		Dibujo dibujo = new Dibujo();
		dibujo.agregarFigura(new Cuadrado(1, "rojo"));
		dibujo.agregarFigura(new Cuadrado(1, "azul"));
		dibujo.agregarFigura(new Cuadrado(1, "rojito"));
		dibujo.agregarFigura(new Cuadrado(1, "celeste"));
		dibujo.agregarFigura(new Cuadrado(1, "casi rojo"));
		dibujo.agregarFigura(new Circulo(3, "verde"));
		dibujo.agregarFigura(new Circulo(0.5, "naranja"));
		dibujo.agregarFigura(new Circulo(0.25, "fucsia"));
		
		/* testeo de igualdad de referencias
		 * 
		 * Circulo c = new Circulo(3, "verde");
		Circulo c1 = new Circulo(3, "verde");
		c = c1;
		System.out.println(c == c1);*/
		
		//Casteo, necesitamos castear a la clase circulo porque el getColor es un metodo
		//PROPIO de circulo, no es polimorfica
		/*
		Figura circulo = new Circulo(2, "rojo");
		System.out.println(((Circulo) circulo).getColor());*/
		
		System.out.println("Cantidad de temperas a comprar: " + dibujo.calcularTemp());
		
		dibujo.mostrar();
		
	}

}
