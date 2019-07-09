package com.emmettbrown.mensajes.servidor;

import java.util.ArrayList;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgActualizarListaUsuarios;
import com.emmettbrown.mensajes.cliente.MsgActualizarDatosSala;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.entidades.SvSala;

public class MsgConectarseASala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	private ArrayList<SvSala> listaSalas;

	public MsgConectarseASala(int idSala) {
		this.idSala = idSala;
	}

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		listaSalas = hilo.getSalas();

		SvSala salaSeleccionada = null;

		for (SvSala sala : listaSalas) {
			if (sala.getId() == idSala) {
				salaSeleccionada = sala;
				sala.agregarUsuario(hilo,hilo.getNombreUsuario());				
			}
		}
		
		hilo.setSalaConectada(salaSeleccionada);
		hilo.broadcast(new MsgActualizarDatosSala(idSala, salaSeleccionada.getClientesConectadosSize()), hilo.getUsuariosConectados());
		
		hilo.broadcast(new MsgActualizarListaUsuarios(salaSeleccionada.getId(), salaSeleccionada.obtenerUsuarios()), salaSeleccionada.getOutputStreams());
		return null;
	}

}
