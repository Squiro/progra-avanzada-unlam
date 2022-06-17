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
			try {
				//Recibo mensajes del servidor 
				Msg msgRecibido = (Msg) cliente.recibirMsg();
				//Ejecuto la acción
				msgRecibido.realizarAccion(cliente);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
