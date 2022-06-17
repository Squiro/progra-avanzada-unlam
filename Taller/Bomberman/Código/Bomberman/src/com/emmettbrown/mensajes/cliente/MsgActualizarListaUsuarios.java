package com.emmettbrown.mensajes.cliente;

import java.util.ArrayList;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.Sala;
import com.emmettbrown.mensajes.Msg;

public class MsgActualizarListaUsuarios extends Msg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	private ArrayList<String> usuarios;
	
	public MsgActualizarListaUsuarios(int idSala, ArrayList<String> usuarios) {
		this.idSala = idSala;
		this.usuarios = usuarios;		
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		
		for (Sala sala : cliente.getListaSalas()) {
			if (sala.getId() == idSala) {
				sala.setUsuarios(usuarios);
			}
		}
		return null;
	}

}
