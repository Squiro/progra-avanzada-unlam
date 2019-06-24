package com.emmettbrown.mensajes;

import java.util.HashMap;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.entidades.Entidad;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.entidades.Muro;

public class MsgGenerarMurosExteriores extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		HashMap<Ubicacion, Entidad> conjuntoEntidades = cliente.getMapa().getListaEntidades();
		
		for (int i = 0; i < DefConst.ALTOMAPA; i++) {
			conjuntoEntidades.put(new Ubicacion(0, i), new Muro(0 * DefConst.TILESIZE, i * DefConst.TILESIZE));
			conjuntoEntidades.put(new Ubicacion(i, 0), new Muro(i * DefConst.TILESIZE, 0 * DefConst.TILESIZE));
			conjuntoEntidades.put(new Ubicacion(DefConst.ANCHOMAPA-1, i), new Muro((DefConst.ANCHOMAPA-1) *DefConst.TILESIZE, i * DefConst.TILESIZE));
			conjuntoEntidades.put(new Ubicacion(i, DefConst.ALTOMAPA-1), new Muro(i * DefConst.TILESIZE, (DefConst.ALTOMAPA-1)* DefConst.TILESIZE));
		}
		
		for (int i = 1; i < DefConst.ANCHOMAPA-1; i++) {
			for (int j = 1; j < DefConst.ALTOMAPA-1; j++) {
				if (i % 2 == 0 && j % 2 == 0) { 
					//EN LAS POSICIONES I,J IMPARES PONDREMOS INDESTRUCTIBLES, EN CASO
					//CONTRARIO EVALUAREMOS PONER OBSTACULOS
					conjuntoEntidades.put(new Ubicacion(i, j), new Muro(i * DefConst.TILESIZE, j * DefConst.TILESIZE)); 
				}
			}
		}
		
		return null;
	}

}
