package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;

public class MsgIdCliente extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nro;
	
	public MsgIdCliente(int nro) {
		this.nro = nro;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;		
		cliente.setIdCliente(nro);
		return null;
	}
	
	

}
