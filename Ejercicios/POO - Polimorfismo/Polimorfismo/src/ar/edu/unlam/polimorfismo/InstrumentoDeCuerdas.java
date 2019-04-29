package ar.edu.unlam.polimorfismo;

public abstract class InstrumentoDeCuerdas extends Instrumento {
	
	private int cantidadCuerdas;

	public int getCantidadCuerdas() {
		return cantidadCuerdas;
	}

	public void setCantidadCuerdas(int cantidadCuerdas) {
		this.cantidadCuerdas = cantidadCuerdas;
	}	

}
