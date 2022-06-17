package com.emmettbrown.mensajes.servidor;

import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.servidor.HiloCliente;

public class MsgActualizarNombre extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	public MsgActualizarNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente h = (HiloCliente) obj;
		h.setNombreUsuario(this.nombre);
		return null;
	}

}
