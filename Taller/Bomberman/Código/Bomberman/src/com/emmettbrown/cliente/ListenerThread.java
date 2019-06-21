package com.emmettbrown.cliente;


import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.principal.Motor;

public class ListenerThread extends Thread {

	private Motor motor;
	
	public ListenerThread(Motor motor) {
		this.motor = motor;
	}
	
	@Override
	public void run() {
		while (true) {
			/* Recibo Consulta de cliente */
			Msg msgRecibido = (Msg) this.motor.getCliente().recibirMsg();
			msgRecibido.realizarAccion(motor);
		}

	}
}
