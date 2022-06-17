package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarCreadorSala extends Msg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nueIdCreador;
	
	public MsgActualizarCreadorSala(int nueIdCreador){
		this.nueIdCreador = nueIdCreador;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cli = (Cliente) obj;
		cli.getSalaActual().setIdCreador(nueIdCreador);
		return null;
	}

}
