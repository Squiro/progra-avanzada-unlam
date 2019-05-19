package com.emmettbrown.entidades;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.emmettbrown.mapa.Ubicacion;
import com.sun.javafx.geom.Rectangle;

public abstract class Entidad {
	
	protected Ubicacion ubicacion;
	protected boolean esVisible;
	protected boolean destructible = true;
	protected ImageIcon img;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Entidad(int posX, int posY, int width, int height)
	{
		this.ubicacion = new Ubicacion(posX, posY);
		this.x = posX;
		this.y = posY;
		this.width = width;
		this.height = height;
		this.esVisible = true;
	}
	
	public Entidad(Ubicacion ubicacionAct)
	{
		this.ubicacion = ubicacionAct;
		this.esVisible = true;
	}
	
	public boolean verSiEsVisible() {
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
	public Image getImagen() {
		return img.getImage();
	}
	
	/*public int getImageWidth() {
		return this.img.getIconWidth();
	}
	
	public int getImageHeight() {
		return this.img.getIconWidth();
	}	*/
	
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
		return new Rectangle(x,  y, width, height);
	}
}
