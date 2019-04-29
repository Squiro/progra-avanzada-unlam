package ar.edu.unlam.luchadoresjap;

public class Luchador {
	
	private int peso;
	private int altura;
	
	/** Getters y Setters */
	
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/** Hashcode y Equals */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + altura;
		result = prime * result + peso;
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
		Luchador other = (Luchador) obj;
		if (altura != other.altura)
			return false;
		if (peso != other.peso)
			return false;
		return true;
	}

	/** Constructores */
	
	public Luchador()
	{
		this.peso = 0;
		this.altura = 0;
	}
	
	public Luchador(int peso, int altura) {
		this.peso = peso;
		this.altura = altura;
	}
	
	public boolean domina(Luchador luch) {
		//El luchador no se puede dominar a si mismo
		if (this.equals(luch))
			return false;
		
		if (this.altura >= luch.altura && this.peso >= luch.peso)
			return true;
		else if (this.altura > luch.altura && this.peso == luch.peso)
			return true;
		else if (this.altura == luch.altura && this.peso > luch.peso)
			return true;
		
		return false;
	}
}
