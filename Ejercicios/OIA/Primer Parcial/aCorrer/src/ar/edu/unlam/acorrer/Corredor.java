package ar.edu.unlam.acorrer;

public class Corredor {
	
	private int edad;
	private char sexo;
	
	public Corredor (int edad, char sexo) {
		this.edad = edad;
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	
}
