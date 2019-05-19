package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.principal.Motor;

public class Obstaculo extends Entidad{
	private static int nroObstaculo = 0;
	public int idObstaculo;
	
	public Obstaculo(int posX, int posY) {
		super(posX, posY, Motor.tileSize, Motor.tileSize);
		idObstaculo = nroObstaculo;
		nroObstaculo++;
		this.destructible = true;
		this.img = new ImageIcon("./src/resources/game-map/brick/obstaculo.png");
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
