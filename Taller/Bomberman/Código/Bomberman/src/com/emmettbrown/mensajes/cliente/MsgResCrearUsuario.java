package com.emmettbrown.mensajes.cliente;

import javax.swing.JOptionPane;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.JVentanaInicial;
import com.emmettbrown.mensajes.Msg;

public class MsgResCrearUsuario extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean resultado;

	public MsgResCrearUsuario(boolean resultado2) {
		this.resultado = resultado2;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		if (resultado) {
			JVentanaInicial inicial = new JVentanaInicial(cliente);
			inicial.setVisible(true);
			cliente.getPantallaLogin().dispose();
		}else {
			JOptionPane.showMessageDialog(null, "El usuario que intenta crear, ya existe!", "Datos erroneos", JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}

}
