package ar.edu.unlam.polimorfismo;

public class Flauta extends InstrumentoDeViento {
	
	public String tocar() {
		return "Tocando " + Flauta.class.getSimpleName();
	}
}
