package com.emmettbrown.mensajes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.servidor.HiloCliente;

public class MsgGenerarMapa extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object realizarAccion(Object obj) {
		HiloCliente hilo =(HiloCliente) obj;
		Mapa m = new Mapa();
		m.generarMapa();
		broadcast(m, hilo.getUsuariosConectados());
		return m;
	}
	
	//TEMPORAL. ESTA LISTA ES LISTA DE USUARIOS DE TODO EL SSERVIDOR. CAMBIAR A LISTA DE USUARIOS CONECTADOS X SALA
	public void broadcast(Mapa m, ArrayList<Socket> usuariosConectados) {		
		for (Socket clientSocket : usuariosConectados) {
			try {
				ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
				salidaACliente.writeObject(m);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
