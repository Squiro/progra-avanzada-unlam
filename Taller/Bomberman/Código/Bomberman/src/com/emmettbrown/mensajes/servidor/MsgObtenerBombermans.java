package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgAgregarBomberman;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.entidades.SvBomberman;

public class MsgObtenerBombermans extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		
		for (SvBomberman bman : hilo.getMap().obtenerListaBomberman()) {
			hilo.enviarMsg(new MsgAgregarBomberman(bman, -1));
			
		}
		
		
		return null;
	}

}
