package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.servidor.HiloCliente;

public class MsgConectarseASala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	private int idCliente;
	
	public MsgConectarseASala(int idSala, int idCliente) {
		this.idSala = idSala;
		this.idCliente = idCliente;
	}
	

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		return null;
	}
	
	
	
}
