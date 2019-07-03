package com.emmettbrown.entidades;

import java.awt.Image;
import javax.swing.ImageIcon;
import com.sun.javafx.geom.Rectangle;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;


public abstract class Entidad {
	
	protected Ubicacion ubicacion;
	protected boolean esVisible;
	protected boolean destructible;
	protected ImageIcon img;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Entidad(int posX, int posY, int width, int height)
	{
		this.ubicacion = new Ubicacion(posX/DefConst.TILESIZE, posY/DefConst.TILESIZE);
		this.x = posX;
		this.y = posY;
		this.width = width;
		this.height = height;
		this.esVisible = true;
	}
	
	public Entidad(Ubicacion ubic, int width, int height)
	{
		this.ubicacion = ubic;
		this.x = ubicacion.getPosX()*DefConst.TILESIZE;
		this.y = ubicacion.getPosY()*DefConst.TILESIZE;
		this.width = width;
		this.height = height;
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
		return new Ubicacion(this.x/DefConst.TILESIZE, this.y/DefConst.TILESIZE);
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
	public void setImage(ImageIcon act) {
		img = act;
	}
	public abstract void explotar(Mapa map);
}
