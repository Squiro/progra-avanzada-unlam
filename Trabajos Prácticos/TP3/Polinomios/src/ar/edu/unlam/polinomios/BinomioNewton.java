package ar.edu.unlam.polinomios;

public class BinomioNewton {
	
	private double exponente;
	private double terminoA;
	private double terminoB;
	private double[] coeficientes;
	
	public BinomioNewton(double exponente, double terminoA, double terminoB)  {
		this.exponente = exponente;
		this.terminoA = terminoA;
		this.terminoB = terminoB;
		this.coeficientes = new double[(int) (exponente+1)];
	}	
	
	public double hallarCoeficienteK(double k) {
		return combinatoria(this.exponente, k) * Math.pow(terminoA, k) * Math.pow(this.terminoB, this.exponente-k);
	}	
	
	public double combinatoria(double n, double k) {
		return factorial(n)/(factorial(k)*factorial(n-k));
	}
	
	public int factorial(double m) {
		int resultado = 1;
		for (int i = 1; i <= m; i++) {
			resultado *= i;
		}
		
		return resultado;
	}
	
	public void expandirBinomio() {
		for (int i = 0; i < this.exponente; i++) {
			this.coeficientes[i] = combinatoria(this.exponente, i) * Math.pow(this.terminoA, this.exponente - i) * Math.pow(terminoB, i);
		}
	}	
	
	public double resolverBinomio(double x) {
		return Math.pow(this.terminoA*x+this.terminoB,exponente);
	}
	
	public static void main(String[] args) {
		
		BinomioNewton bin = new BinomioNewton(2, 3, 2);
		System.out.println(bin.resolverBinomio(2));
		
		System.out.println(bin.hallarCoeficienteK(2)); 
	}
}
