package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.mensajes.Msg;

public class MsgFinPartida extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		JVentanaGrafica venAct;
		Cliente cliente = (Cliente) obj;		
		venAct = cliente.getVentanaGrafica();	
		venAct.finPartida();
		return null;
	}

}
