package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.servidor.entidades.SvBomberman;

public class MsgAgregarBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private SvBomberman bomberman;

	public MsgAgregarBomberman(SvBomberman bomberman, int idCliente) {
		this.bomberman = bomberman;
		this.idCliente = idCliente;
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = ((Cliente) obj);
		
		Bomberman bomber = new Bomberman(bomberman.getX(), bomberman.getY(), DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT,bomberman.getIdBomberman(),bomberman.getNombre());
		
		if (cliente.getIdCliente() == idCliente) {
			cliente.setBomber(bomber);
		}
		cliente.getMapa().agregarBomberman(bomber);
		
		return null;
	}

}
