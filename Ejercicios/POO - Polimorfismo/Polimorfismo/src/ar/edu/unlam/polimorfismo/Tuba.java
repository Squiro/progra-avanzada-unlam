package ar.edu.unlam.polimorfismo;

public class Tuba extends InstrumentoDeViento {
	
	public String tocar() {
		return "Tocando " + Tuba.class.getSimpleName();
	}
}
