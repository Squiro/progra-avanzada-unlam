package ar.edu.unlam.entidades;

public class Bomba extends Entidad {
	
	private double tiempoExplosion;
	private int rangoExplosion;
	boolean puedeExplotar;
	
	
	//Seteamos true por defecto, ¿para que ibas a crear una bomba que no puede explotar?
	//A menos que decidamos hacer bombas que no explotan para que les de miedo al jugador... 
	public Bomba(double tiempo, int rango) {
		this.tiempoExplosion = tiempo;
		this.rangoExplosion = rango;
		this.puedeExplotar = true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void explotar() {
		
	}
}
