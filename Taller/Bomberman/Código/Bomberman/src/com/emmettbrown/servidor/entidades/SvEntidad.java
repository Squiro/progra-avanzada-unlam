package com.emmettbrown.servidor.entidades;

import java.io.Serializable;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.servidor.mapa.ServerMap;
import com.sun.javafx.geom.Rectangle;

public abstract class SvEntidad implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Ubicacion ubicacion;
	protected boolean esVisible;
	protected boolean destructible;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public SvEntidad(int posX, int posY, int width, int height)
	{
		this.ubicacion = new Ubicacion(posX/DefConst.TILESIZE, posY/DefConst.TILESIZE);
		this.x = posX;
		this.y = posY;
		this.width = width;
		this.height = height;
		this.esVisible = true;
	}
	
	public SvEntidad(Ubicacion ubic, int width, int height)
	{
		this.ubicacion = ubic;
		this.x = ubicacion.getPosX()*DefConst.TILESIZE;
		this.y = ubicacion.getPosY()*DefConst.TILESIZE;
		this.width = width;
		this.height = height;
		this.esVisible = true;
	}
	
	public boolean estaVivo() {
		return this.esVisible;
	}
	
	public void cambiarVisibilidad() {
		esVisible = !esVisible;
	}
	
	public void setUbicacion(Ubicacion ubic) {
		this.ubicacion = ubic;
	}
	
	public Ubicacion obtenerUbicacion() {
		return this.ubicacion;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x, y, width, height);
	}
	
	public abstract void explotar(ServerMap map);
}