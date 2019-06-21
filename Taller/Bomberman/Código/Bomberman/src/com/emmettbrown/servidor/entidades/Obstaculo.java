package com.emmettbrown.servidor.entidades;

import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.servidor.entidades.Entidad;
import com.emmettbrown.servidor.mapa.ServerMap;

public class Obstaculo extends Entidad{
	public Obstaculo(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = true;
	}
	
	@Override
	public void explotar(ServerMap map) {
		this.esVisible = false;
		map.removerEntidadDelConjunto(this.ubicacion);
	}	
}
