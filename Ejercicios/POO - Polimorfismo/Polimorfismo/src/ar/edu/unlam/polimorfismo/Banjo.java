package ar.edu.unlam.polimorfismo;

public class Banjo extends InstrumentoDeCuerdas implements Afinable {
	
	public void afinar() {
		System.out.println("Un afinador me est� afinando el Banjo");
	}
	
	public String tocar() {
		return "Tocando " + Banjo.class.getSimpleName();
	}

}
