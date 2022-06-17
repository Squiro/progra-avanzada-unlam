package com.emmettbrown.servidor.entidades;
import com.emmettbrown.servidor.entidades.SvBomba;
import com.emmettbrown.servidor.entidades.SvEntidad;
import com.emmettbrown.servidor.entidades.Explosion;
import com.emmettbrown.servidor.mapa.ServerMap;
import com.emmettbrown.mapa.Ubicacion;

import java.io.Serializable;
import java.util.ArrayList;
import com.sun.javafx.geom.Rectangle;

public class SvBomberman extends SvEntidad implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int nroBomberman;
	private int idBomberman;
	private ArrayList<SvBomba> bombas;
	private String nombre;

	public SvBomberman(int posX, int posY, int width, int height, String nombre) {
		super(posX, posY, width, height);
		idBomberman = nroBomberman++;
		this.destructible = true;
		this.nombre = nombre;
		this.bombas = new ArrayList<SvBomba>();
	}
	public boolean estaVivo() {
		return this.esVisible;
	}
	public String getNombre() {
		return nombre;
	}
	public void morir() {
		esVisible = false;
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
	
	public int getIdBomberman() {
		return this.idBomberman;
	}

	@Override
	public void explotar(ServerMap map) {
		
	}
	
	public void actualizarColBomba() {
		for (SvBomba bomba : bombas) {
			Rectangle hitBoxBomber = this.getHitBox();
			Rectangle hitBoxBomba = bomba.getHitBox();
			//Vemos si existe una interseccion entre ambos rectangulos
			Rectangle intersection = hitBoxBomber.intersection(hitBoxBomba);		
			
			if (intersection.isEmpty()) {
				bomba.setIgnorarColisionCreador(false);
			}
		}
		
	}
	
	public boolean manejarColisionCon(SvEntidad ent) {
		if (ent instanceof SvBomba) {
			return ((SvBomba) ent).hayColisionConCreador(this);
		}
		
		if (ent instanceof Explosion) {
			this.morir();
			return true;
		}
		
		return true;
	}
	
	public void agregarBomba(SvBomba bomba) {
		this.bombas.add(bomba);
	}
	
	public void removerBomba(SvBomba bomba) {
		this.bombas.remove(bomba);
	}

	public int obtenerID() {
	
		return this.idBomberman;
	}
	
	public int cantBombasAct() {
		return bombas.size();
	}
}
