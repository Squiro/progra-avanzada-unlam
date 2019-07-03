package com.emmettbrown.mensajes.cliente;
import java.util.List;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.servidor.entidades.SvBomberman;

public class MsgPonerBomba extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SvBomberman creador;
	private int y;
	private int x;

	public MsgPonerBomba(int x, int y, SvBomberman bomber) {
		this.x = x;
		this.y = y;
		this.creador = bomber;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		List<Bomberman> bomber = cliente.getMapa().obtenerListaBomberman();
		Bomberman creadorCliente = null;
		for (Bomberman b : bomber) {
			if (creador.getIdBomberman() == b.getIdBomberman()) {
				creadorCliente = b;
				break;
			}
		}
		cliente.getMapa().agregarBomba(x, y, creadorCliente);
		return null;
	}

}
