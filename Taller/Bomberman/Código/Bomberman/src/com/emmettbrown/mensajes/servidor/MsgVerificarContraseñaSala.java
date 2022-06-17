package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.cliente.MsgResContrase�aSala;
import com.emmettbrown.servidor.HiloCliente;
import com.emmettbrown.servidor.entidades.SvSala;

public class MsgVerificarContrase�aSala extends Msg {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
	private int idSala;
	
	public MsgVerificarContrase�aSala(String password, int idSala) {
		this.password = password;
		this.idSala = idSala;
	}

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo = (HiloCliente) obj;
		
		boolean matches = false;
		
		for (SvSala sala : hilo.getSalas()) {
			if (sala.getId() == idSala && sala.getPassword().equals(password)) {
				matches = true;
			}
		}
	
		hilo.enviarMsg(new MsgResContrase�aSala(matches));		
		
		return null;
	}

}
