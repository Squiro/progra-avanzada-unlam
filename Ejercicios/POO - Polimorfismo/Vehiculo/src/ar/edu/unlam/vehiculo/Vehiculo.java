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
	
	public void setKmRecorridos(double km) {
		this.KmRecorridos = km;
	}
	
	public double getKmRecorridos(double km) {
		return this.KmRecorridos;
	}

}
