package com.emmettbrown.mensajes.cliente;

import java.util.ArrayList;
import java.util.Iterator;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.mensajes.Msg;

public class MsgEliminarBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idBomber;
	
	public MsgEliminarBomberman(int idBomber) {
		this.idBomber = idBomber;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		ArrayList<Bomberman> bombermans = cliente.getMapa().obtenerListaBomberman();
		
		Iterator<Bomberman >iter = bombermans.iterator();
		
		while (iter.hasNext()) {
			Bomberman bman = iter.next();
			if (bman.getIdBomberman() == idBomber) {
				iter.remove();
			}
		}
		
		return null;
	}
}
