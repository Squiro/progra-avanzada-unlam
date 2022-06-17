package ar.edu.unlam.vehiculo;

public class Motocicleta extends Vehiculo {
	
	private Persona acompa�ante; 
	
	public Motocicleta(Persona per) 
	{
		super.asignarChofer(per);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void a�adirAcompa�ante(Persona per) {
		this.acompa�ante = per;
	}
	
	public void cambiarChofer(Persona per) {
		if (this.acompa�ante == null)
			return;
		
		this.asignarChofer(per);
	}
	

}
