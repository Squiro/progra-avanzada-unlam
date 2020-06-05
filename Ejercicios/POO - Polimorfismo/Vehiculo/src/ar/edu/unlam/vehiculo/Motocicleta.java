package ar.edu.unlam.vehiculo;

public class Motocicleta extends Vehiculo {
	
	private Persona acompaņante; 
	
	public Motocicleta(Persona per) 
	{
		super.asignarChofer(per);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void aņadirAcompaņante(Persona per) {
		this.acompaņante = per;
	}
	
	public void cambiarChofer(Persona per) {
		if (this.acompaņante == null)
			return;
		
		this.asignarChofer(per);
	}
	

}
