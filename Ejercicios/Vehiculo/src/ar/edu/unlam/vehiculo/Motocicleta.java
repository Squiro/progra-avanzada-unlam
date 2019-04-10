package ar.edu.unlam.vehiculo;

public class Motocicleta extends Vehiculo {
	
	private Persona acompañante; 
	
	public Motocicleta(Persona per) 
	{
		super.asignarChofer(per);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void añadirAcompañante(Persona per) {
		this.acompañante = per;
	}
	
	public void cambiarChofer(Persona per) {
		if (this.acompañante == null)
			return;
		
		this.asignarChofer(per);
	}
	

}
