package com.emmettbrown.servidor.entidades;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.servidor.entidades.SvEntidad;
import com.emmettbrown.servidor.mapa.ServerMap;

public class SvObstaculo extends SvEntidad{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SvObstaculo(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = true;
	}
	
	@Override
	public void explotar(ServerMap map) {
		this.esVisible = false;
		map.removerEntidadDelConjunto(this.ubicacion);
		map.removerObstaculo(this);
	}	
}
