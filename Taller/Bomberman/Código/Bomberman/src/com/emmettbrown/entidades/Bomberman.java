package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.mapa.Ubicacion;

public class Bomberman extends Entidad {
	
	public static final int defaultWidth = 70;
	public static final int defaultHeight = 70;
	
	private static int nroBomberman = 0;
	private int idBomberman;
	public static final int VELOCIDAD = 5;

	public Bomberman(int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		idBomberman = nroBomberman++;
		this.destructible = true;
		this.img = new ImageIcon("./src/resources/character/bomberman.png");
	}
	
	/** PARAM
	 * 
	 * @param despX: desplazamiento en el eje X
	 * @param despY: desplazamiento en el eje Y
	 */
	
	public void morir() {
		esVisible = false;
		System.out.println("El bomberman " + idBomberman + " ha muerto");
	}
	
	public void cambiarPosX(double despX) {
		this.x += despX;
	}
	
	public void cambiarPosY(double despY) {
		this.y += despY;
	}
	
	public void cambiarUbicacion(Ubicacion ubic) {
		this.ubicacion = ubic;
	}
}
