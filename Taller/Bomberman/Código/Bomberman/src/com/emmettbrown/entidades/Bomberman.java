package com.emmettbrown.entidades;

import com.emmettbrown.mapa.*;

public class Bomberman extends Entidad {
	private static int nroBomberman = 0;
	private int idBomberman;
	private Mapa mapa;
	private static final int velocidad = 1;

	public Bomberman(int posIniX,int posIniY, Mapa mapa) {
		super(posIniX, posIniY);
		this.mapa = mapa;
		idBomberman = nroBomberman;
		nroBomberman++;
		this.destructible = true;
	}
	
	/** PARAM
	 * 
	 * @param despX: desplazamiento en el eje X
	 * @param despY: desplazamiento en el eje Y
	 */
	
	public void moverse(int despX, int despY) {
		Ubicacion ubic = this.ubicacion.clone();
		ubic.cambiarPosX(despX);
		ubic.cambiarPosY(despY);
		
		if (puedeMoverse(ubic)) {
			this.ubicacion.cambiarPosX(despX);
			this.ubicacion.cambiarPosY(despY);
		}		
	}
	
	public boolean puedeMoverse(Ubicacion ubic) {
		if (ubic.getPosX() < 0 || ubic.getPosX() >= Mapa.ANCHO) 
			return false;
		if (ubic.getPosY() < 0 || ubic.getPosY() >= Mapa.ALTO)
			return false;
		
		return mapa.estaLibre(ubic);		
	}
	
	public void moverIzquierda() {
		moverse(-velocidad, 0);
	}
	
	public void moverDerecha() {
		moverse(velocidad, 0);
	}
	
	public void moverArriba() {
		moverse(0, -velocidad);
	}
	
	public void moverAbajo() {
		moverse(0, velocidad);
	}
	
	@Override
	public boolean verSiEsVisible() {
		return this.esVisible;
	}

	public void morir() {
		if (this.esVisible == true) {
			esVisible = false;
			System.out.println("El bomberman " + idBomberman + " ha muerto");
		}

	}

	public void ponerBomba() {
		mapa.agregarBomba(ubicacion);
		System.out.println("SE HA PUESTO LA BOMBA");
	}

	public Ubicacion obtenerUbicacion() {
		return this.ubicacion;
	}
}
