package com.emmettbrown.mensajes.cliente;

import com.emmettbrown.cliente.Cliente;
import com.emmettbrown.entorno.grafico.DefConst;
import com.emmettbrown.entorno.grafico.JVentanaGrafica;
import com.emmettbrown.mensajes.Msg;

public class MsgIniciarMotor extends Msg {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		Cliente cliente = (Cliente) obj;
		cliente.getLobby().eliminarVentana();
		JVentanaGrafica ventana = new JVentanaGrafica(DefConst.ANCHO+400, DefConst.ALTO, cliente);		
		ventana.setVisible(true);
		cliente.setVentanaGrafica(ventana);
		
		return null;
	}
}
