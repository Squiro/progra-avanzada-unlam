package com.emmettbrown.mensajes;

import com.emmettbrown.principal.*;
import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.DefConst;

public class MsgAgregarBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	public MsgAgregarBomberman(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	@Override
	public Object realizarAccion(Object obj) {
		Motor motor = ((Motor) obj);
		
		motor.getCliente().setBomber(new Bomberman(x, y, DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT));
		motor.getMapa().agregarBomberman(motor.getCliente().getBomber());
		
		return null;
	}

}
