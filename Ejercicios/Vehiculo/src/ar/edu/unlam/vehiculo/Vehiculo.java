package ar.edu.unlam.vehiculo;

public abstract class Vehiculo {

	private double KmRecorridos;
	private Persona chofer;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void asignarChofer(Persona per) {
		this.chofer = per;
	}

}
