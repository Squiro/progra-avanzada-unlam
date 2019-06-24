package com.emmettbrown.cliente;

import com.emmettbrown.mensajes.Msg;

public class ListenerThread extends Thread {

	private Cliente cliente;
	
	public ListenerThread(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		while (true) {
			/* Recibo Consulta de cliente */
			Msg msgRecibido = (Msg) cliente.recibirMsg();
			msgRecibido.realizarAccion(cliente);
		}
	}
}
