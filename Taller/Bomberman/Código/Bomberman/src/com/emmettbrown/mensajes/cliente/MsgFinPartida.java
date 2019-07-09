package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;

public class MsgFinPartida extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;		
		cliente.getVentanaGrafica().finPartida();		
		return null;
	}

}
