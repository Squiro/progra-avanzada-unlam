package com.emmettbrown.entidades;

public class Obstaculo extends Entidad{
	private static int nroObstaculo = 0;
	public int idObstaculo;
	
	public Obstaculo(final int posIniX,final int posIniY) {
		super(posIniX,posIniY);
		idObstaculo = nroObstaculo;
		nroObstaculo++;
		this.destructible = true;
	}
	
	public void destruir() {
		if(esVisible == true) {
			esVisible = false;
			System.out.println("Obstaculo "+ idObstaculo +" Destruido");
		}
	}
	
	@Override
	public boolean verSiEsVisible() {
		return this.esVisible;
	}
	
}
