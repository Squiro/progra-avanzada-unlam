package com.emmettbrown.mensajes.cliente;

import javax.swing.JOptionPane;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.mensajes.Msg;

public class MsgResContrase�aSala extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean puedeConectarse;
	
	public MsgResContrase�aSala(boolean puedeConectarse) {
		this.puedeConectarse = puedeConectarse;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		
		if (puedeConectarse) {
			cliente.getVentanaInicial().crearLobbyInvitado();
		} else {
			JOptionPane.showMessageDialog(null, "La contrase�a no es correcta", "Error al acceder",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
		return null;
	}

}
