package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgActualizarListaSalas;
import com.emmettbrown.servidor.HiloCliente;

public class MsgObtenerSalas extends Msg {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
				
		for (Sala sala : hilo.getSalas()) {
			hilo.enviarMsg(new MsgActualizarListaSalas(sala));
		}
		
		return null;
	}
	
	

}
