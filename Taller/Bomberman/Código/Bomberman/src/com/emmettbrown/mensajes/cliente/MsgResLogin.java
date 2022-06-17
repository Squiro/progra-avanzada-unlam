package com.emmettbrown.mensajes.cliente;

import javax.swing.JOptionPane;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.JVentanaInicial;
import com.emmettbrown.mensajes.Msg;

public class MsgResLogin extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean resultado;

	public MsgResLogin(boolean result) {
		this.resultado = result;
	}

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		if (!resultado) {
			JOptionPane.showMessageDialog(null, "Datos del usuario incorrectos. Vuelva a ingresarlos", "Datos erroneos", JOptionPane.ERROR_MESSAGE);
		}else {
			JVentanaInicial inicial = new JVentanaInicial(cliente);
			inicial.setVisible(true);
			cliente.getPantallaLogin().dispose();
		}
		return null;
	}

}
