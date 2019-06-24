package com.emmettbrown.mensajes;

import com.emmettbrown.controles.Movimientos;
import com.emmettbrown.servidor.HiloCliente;

public class MsgMover extends Msg{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Movimientos mov;
	
	public MsgMover (Movimientos valor) {
		this.mov = valor;		
	}

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		hilo.getMovementThread().setMovimiento(mov);		
		return null;
	}
	
	
}
