package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;

public class MsgPosBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idBomber;
	private int x;
	private int y;

	
	public MsgPosBomberman(int idBomber, int x, int y) {
		this.idBomber = idBomber;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		cliente.getMapa().moverBomberman(idBomber, x, y);
		return null;
	}

}
