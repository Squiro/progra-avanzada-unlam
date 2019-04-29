package ar.edu.unlam.complejos;

public class Complejo implements Comparable <Complejo> {

	private Double parteReal;
	private Double parteImaginaria;	
	
	public Complejo() {
		this.parteReal = 0.0;
		this.parteImaginaria = 0.0;
	}
	
	public Double getParteReal() {
		return parteReal;
	}

	public void setParteReal(Double parteReal) {
		this.parteReal = parteReal;
	}

	public Double getParteImaginaria() {
		return parteImaginaria;
	}

	public void setParteImaginaria(Double parteImaginaria) {
		this.parteImaginaria = parteImaginaria;
	}

	public Complejo(double parteReal, double parteImaginaria) {
		this.parteReal = parteReal;
		this.parteImaginaria = parteImaginaria;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Complejo sumar(Complejo comp) {
		double real = this.parteReal + comp.parteReal;
		double imaginaria = this.parteImaginaria + comp.parteImaginaria;
		
		return new Complejo(real, imaginaria);		
	}
	
	public Complejo sumar(int num) {
		return new Complejo(this.parteReal+num, this.parteImaginaria);
	}
	
	public Complejo restar(Complejo comp) {
		double real = this.parteReal - comp.parteReal;
		double imaginaria = this.parteImaginaria - comp.parteImaginaria;
		
		return new Complejo(real, imaginaria);
		
	}
	
	public Complejo restar(int num) {
		return new Complejo(this.parteReal-num, this.parteImaginaria);
	}
	
	public Complejo multiplicarPorEscalar(double num) {
		return new Complejo (this.parteReal*num, this.parteImaginaria);
	}
	
	public Complejo multiplicar(Complejo comp) {
		return new Complejo(this.parteReal*comp.parteReal-this.parteImaginaria*comp.parteImaginaria, this.parteReal*comp.parteImaginaria+this.parteImaginaria*comp.parteReal);
	}
	
	public Double modulo() {
		return Math.sqrt((Math.pow(this.parteReal, 2) + Math.pow(this.parteImaginaria, 2)));
	}
	
	@Override
	public Complejo clone() {
		return new Complejo(this.parteReal, this.parteImaginaria);
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(parteImaginaria);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(parteReal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complejo other = (Complejo) obj;
		if (Double.doubleToLongBits(parteImaginaria) != Double.doubleToLongBits(other.parteImaginaria))
			return false;
		if (Double.doubleToLongBits(parteReal) != Double.doubleToLongBits(other.parteReal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + this.parteReal + " + " + this.parteImaginaria + "i)";
	}

	@Override
	public int compareTo(Complejo otro) {
		return this.modulo().compareTo(otro.modulo());
		
/*		double miMod = this.modulo();
		double otroMod = otro.modulo();
		
		if (miMod > otroMod) {
			return 1; 
		} else if (miMod == otroMod) {
			return 0;
		} else {
			return -1;
		}*/
	}
}
