package com.emmettbrown.entidades;

public class Muro extends Entidad{
	
	public Muro(final int posIniX,final int posIniY) {
		super(posIniX,posIniY);
		super.cambiarVisibilidad();
		this.destructible = false;
	}
}
