package com.emmettbrown.entidades;

import javax.swing.ImageIcon;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.mapa.Mapa;

public class Muro extends Entidad{
	
	public Muro(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = false;
		this.img = new ImageIcon("./src/resources/game-map/environment.png");
	}

	@Override
	public void explotar(Mapa map) {
		//Los muros no explotan
	}
}
