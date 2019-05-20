package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.mapa.Mapa;
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
	
	@Override
	public void explotar(Mapa map) {
		System.out.println("Obstaculo "+ idObstaculo +" Destruido");
		this.esVisible = false;
		map.removerEntidadDelConjunto(this.ubicacion);
	}	
}
