package ar.edu.unlam.polinomios;


public class Polinomio {
	private int grado;
	public double [] coeficientes;
	
	public Polinomio(int grado, double [] coeficientes) {
		this.grado = grado;
		this.coeficientes = coeficientes;
	}
		
	
	public static void main(String[] args) {
		double[] coef = { 4.0, 2.0, 3.0, 1.0 };
		Polinomio p = new Polinomio(3, coef); 
		long tiempoIni, tiempoFin;
		
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("SUCESIVAS: " + p.evaluarMSucesivas(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Recursiva: " + p.evaluarRecursiva(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();		
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Recursiva Par: " + p.evaluarRecursivaPar(3));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Prog. Dinamica: " + p.evaluarProgDinamica(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Mejorada: " + p.evaluarMejorada(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Mejorada: " + p.evaluarMejorada(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Pow: " + p.evaluarPow(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
		tiempoIni = System.currentTimeMillis();
		System.out.println("Horner: " + p.evaluarHorner(4));
		hacerTiempo();
		tiempoFin = System.currentTimeMillis();
		System.out.println("TIEMPO: " + (tiempoFin - tiempoIni));
		
	}
	
	double evaluarMSucesivas(double x) {
		double resultado = 0;
		for (int i = 0; i <= this.grado; i++) {
			resultado += potenciaSucesiva(x, this.grado-i) * this.coeficientes[i];
		}		
		return resultado;
	}
	
	double potenciaSucesiva(double x, double n) {
		double resultado = 1;
		
		for (int i = 1; i <= n; i++) {
			resultado *= x;
		}
		
		return resultado;
	}
	
	double evaluarRecursiva(double x) {	
		double resultado = 0, potencia = 0;
		
		for (int i = 0; i <= this.grado; i++) {
			potencia = potencia(x, this.grado-i);
			resultado += potencia*this.coeficientes[i];
		}
		
		return resultado;
	}
	
	/** N = exponente */
	double potencia(double x, double n) {
		if (n == 0)
			return 1;		
		return x * potencia(x, n-1);
	}	
	
	double evaluarRecursivaPar(double x) {	
		double resultado = 0, potencia = 0;
		
		for (int i = 0; i <= this.grado; i++) {
			potencia = potenciaPar(x, this.grado-i);
			resultado += potencia*this.coeficientes[i];
		}
		
		return resultado;
	}
	
	double potenciaPar(double x, double n) {
		if (n == 0)
			return 1;
		if (n % 2 == 0)
			return potenciaPar(x*x, n/2);
		else
			return x * potenciaPar(x, n-1);
	}	
	
	
	double evaluarProgDinamica(double x) {
		double[] pot = new double[this.grado+1];		
		
		pot[0] = 1;
		
		for (int i = 1; i <= this.grado; i++) {
			pot[i] = x * pot[i-1];
		}
	
		double resultado = this.coeficientes[this.grado];
		
		for (int i = 0; i < this.grado; i++) {
			resultado += pot[this.grado-i] * this.coeficientes[i];
		}
		
		return resultado;
	}		
	
	double evaluarMejorada(double x) {
		double pot = 1;
		double resultado = 0;
		
		for (int i = 0; i <= this.grado; i++) {
			resultado += pot * this.coeficientes[this.grado-i];
			pot *= x;
		}
		
		return resultado;
	}
	
	double evaluarPow(double x) {
		double resultado = 0;
		
		for (int i = 0; i <= this.grado; i++) {
			resultado += Math.pow(x, i) * this.coeficientes[this.grado-i];
		}
		
		return resultado;
	}
	
	double evaluarHorner(double x) {
		double resultado = this.coeficientes[0]; 
		
		for (int i = 1; i <= this.grado; i++) {
			resultado = resultado*x + this.coeficientes[i];
		}
		
		return resultado;
	}
	
	static void hacerTiempo() {
		for(int i = 0; i < 1000000; i++) {
		}
	}
}
