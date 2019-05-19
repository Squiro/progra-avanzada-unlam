package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.principal.Motor;

public class Muro extends Entidad{
	
	public Muro(int posX, int posY) {
		super(posX, posY, Motor.tileSize, Motor.tileSize);
		this.destructible = false;
		this.img = new ImageIcon("./src/resources/game-map/environment.png");
	}
}
