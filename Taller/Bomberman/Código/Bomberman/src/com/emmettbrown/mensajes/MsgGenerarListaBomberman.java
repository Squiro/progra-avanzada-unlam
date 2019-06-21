package com.emmettbrown.mensajes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.entidades.Bomberman;
import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.mapa.Mapa;
import com.emmettbrown.mapa.Ubicacion;
import com.emmettbrown.servidor.HiloCliente;

public class MsgGenerarListaBomberman extends Msg {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mapa mapa;

	public MsgGenerarListaBomberman(Mapa m) {
		this.mapa = m;
	}
	@Override
	public String realizarAccion(Object obj) {
		HiloCliente hilo =(HiloCliente) obj;
		ArrayList<Socket> usuariosConectados = hilo.getUsuariosConectados();
		
		for (Socket clientSocket : usuariosConectados) {
			Ubicacion uLibre = mapa.obtenerLugarLibre();
			Bomberman b = new Bomberman(uLibre.getPosX(), uLibre.getPosY(), DefConst.DEFAULTWIDTH,DefConst.DEFAULTHEIGHT);

			try {
				ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
				salidaACliente.writeObject(b);
				mapa.agregarBomberman(b);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		
		return "OK";
	}

}
