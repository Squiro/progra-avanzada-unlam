package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarListaSalas extends Msg {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sala sala;
	
	public MsgActualizarListaSalas(Sala s) {
		this.sala = s;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		cliente.getListaSalas().add(sala);
		//System.out.println("sdasdasd + sdasdasd "+c.getListaSalas().size());
		
		return null;
	}

}
