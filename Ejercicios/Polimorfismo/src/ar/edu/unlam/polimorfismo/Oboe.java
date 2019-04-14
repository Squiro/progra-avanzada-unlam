package ar.edu.unlam.polimorfismo;

public class Oboe extends InstrumentoDeViento implements Afinable {
	
	public void afinar() {
		System.out.println("Un afinador me está afinando el Oboe");
	}
	
	public String tocar() {
		return "Tocando " + Oboe.class.getSimpleName();
	}
}
