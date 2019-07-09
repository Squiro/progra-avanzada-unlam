package com.emmettbrown.servidor.entidades;

import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.servidor.entidades.SvEntidad;
import com.emmettbrown.servidor.mapa.ServerMap;

public class Muro extends SvEntidad{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Muro(int posX, int posY) {
		super(posX, posY, DefConst.TILESIZE, DefConst.TILESIZE);
		this.destructible = false;
	}

	@Override
	public void explotar(ServerMap map) {
		//Los muros no explotan
	}
}