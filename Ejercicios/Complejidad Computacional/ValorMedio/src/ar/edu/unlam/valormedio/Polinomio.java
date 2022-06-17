package ar.edu.unlam.valormedio;

public class Polinomio {

	private int grado;
	private double[] coeficientes;
	
	public Polinomio(int grado, double[] coeficientes) {
		this.grado = grado;
		this.coeficientes = coeficientes;
	}
	
	public static void main(String[] args) {
		double[] coef = {-1, -1, 0, 0, 1};
		Polinomio p = new Polinomio(4, coef);
		
		System.out.println(p.hallarRaiz(-1, 1));
	}
	
	public double hallarRaiz(double a, double b) {	
		double c = (Math.abs(a) + Math.abs(b))/2;
	
		while (Math.abs(a-b) >= 0.01) {
			if (evaluarPow(a)*evaluarPow(c) < 0) {
				b = c;
			}
			else {
				a = c;
			}
			
			c = (a+b)/2;			
		}
		
		return c;
	}
	
	
	public double evaluarPow(double x) {
		double resultado = 0;
		
		for (int i = 0; i <= this.grado; i++) {
			resultado += Math.pow(x, i) * this.coeficientes[this.grado-i];
		}
		
		return resultado;
	}
}
