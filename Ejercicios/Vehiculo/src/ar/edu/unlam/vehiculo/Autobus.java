package ar.edu.unlam.vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Autobus extends Vehiculo {

	private List<Persona> pasajeros;
	
	public Autobus(Persona per)
	{
		super.asignarChofer(per);
		pasajeros = new ArrayList<Persona>();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}	
	
	public void añadirPasajeros(Persona per) {
		pasajeros.add(per);
	}
	
	public void cambiarChofer(Persona per) {
		if (this.pasajeros.size() > 0)
			return;
		
		this.asignarChofer(per);
	}
}
