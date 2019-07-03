package com.emmettbrown.mensajes;

public class MsgConectarseASala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSala;
	
	public MsgConectarseASala(int idSala) {
		this.idSala = idSala;
	}
	

	@Override
	public Object realizarAccion(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
