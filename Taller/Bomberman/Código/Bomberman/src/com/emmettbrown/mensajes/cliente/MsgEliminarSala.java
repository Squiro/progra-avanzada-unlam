package com.emmettbrown.mensajes.cliente;

import java.util.Iterator;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgEliminarSala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	
	public MsgEliminarSala(int idSala) {
		this.idSala = idSala;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		
		Iterator<Sala> iter = cliente.getListaSalas().iterator();
		
		while (iter.hasNext()) {
			Sala sala = iter.next();
			
			if (sala.getId() == idSala) {
				iter.remove();
			}
		}
		return null;
	}

}
