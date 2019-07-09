package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarRonda extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rondaActual;
	
	public MsgActualizarRonda (int ronda) {
		this.rondaActual = ronda;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;		
		Sala sala = cliente.getSalaActual();
		
		if (cliente.getPanelGrafico() != null) {
			//Creamos un nuevo mapa
			cliente.setMapa(new Mapa());
			cliente.getPanelGrafico().actualizarListas();
			cliente.getVentanaGrafica().crearRefreshThread();
		}
			 
		//Seteamos la ronda actual
		sala.setRondaActual(rondaActual);
		//Iniciamos el reloj
		sala.iniciarReloj();
		
		return null;
	}

	
	
}
