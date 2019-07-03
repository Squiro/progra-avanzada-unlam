package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Mapa;

public class Obstaculo extends Entidad{
	public Obstaculo(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = true;
		this.img = new ImageIcon("./src/resources/game-map/brick/obstaculo.png");
	}
	
	@Override	
	public void explotar(Mapa map) {
		this.esVisible = false;
		map.removerEntidadDelConjunto(this.ubicacion);
	}	
}
