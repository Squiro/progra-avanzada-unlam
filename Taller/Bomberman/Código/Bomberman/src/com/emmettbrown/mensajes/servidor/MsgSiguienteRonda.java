package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.entidades.SvSala;

public class MsgSiguienteRonda extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;		
	
		SvSala sala = hilo.getSalaConectada();		
		
		if (sala.getIdCreador() == hilo.getIdCliente()) {
			sala.nuevaRonda();			
		}
		
		return null;
	}

}
