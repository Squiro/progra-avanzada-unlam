package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarDatosSala extends Msg {
	
	private static final long serialVersionUID = 1L;
	private int idSala;
	private int jugConectados;
	
	public MsgActualizarDatosSala (int idSala, int jugConectados) {
		this.idSala = idSala;
		this.jugConectados = jugConectados;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		
		for (Sala sala : cliente.getListaSalas()) {
			if (sala.getId() == idSala) {		
				sala.setJugConectados(jugConectados);
			}
		} 
		
		return null;
	}
}
