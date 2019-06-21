package com.emmettbrown.servidor;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.emmettbrown.entidades.DefConst;
import com.emmettbrown.mensajes.Msg;
import com.emmettbrown.mensajes.MsgAgregarBomberman;
import com.emmettbrown.servidor.entidades.Bomberman;
import com.emmettbrown.servidor.mapa.ServerMap;

public class HiloCliente extends Thread {

	private Bomberman bomber;
	private ServerMap map;
	private Socket clientSocket;
	private boolean estaConectado;
	private ArrayList<Socket> usuariosConectados;
	//private ArrayList<Socket> usuariosConectadosXSala;
	
	public HiloCliente(Socket cliente, ArrayList<Socket> usuariosConectados, ServerMap map) {
		this.map = map;
		this.clientSocket = cliente;
		this.usuariosConectados = usuariosConectados;
		this.estaConectado = true;
		this.bomber = new Bomberman(75, 75, DefConst.DEFAULTWIDTH, DefConst.DEFAULTHEIGHT);
		this.inicializarCliente();
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public boolean isEstaConectado() {
		return estaConectado;
	}

	public void setEstaConectado(boolean estaConectado) {
		this.estaConectado = estaConectado;
	}

	public ArrayList<Socket> getUsuariosConectados() {
		return usuariosConectados;
	}

	public void setUsuariosConectados(ArrayList<Socket> usuariosConectados) {
		this.usuariosConectados = usuariosConectados;
	}

	public void inicializarCliente() {
		map.agregarBomberman(bomber);
		this.broadcast(new MsgAgregarBomberman(bomber.getX(), bomber.getY()), usuariosConectados);
	}
	
	public void broadcast(Msg msg, ArrayList<Socket> usuariosConectados) {		
		for (Socket clientSocket : usuariosConectados) {
			try {
				ObjectOutputStream salidaACliente = new ObjectOutputStream(clientSocket.getOutputStream());
				salidaACliente.writeObject(msg);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void run() {

		try {
			ObjectInputStream reciboMsg = new ObjectInputStream(clientSocket.getInputStream());

			while (estaConectado) {
				/* Recibo Consulta de cliente */
				Msg msgRecibo = (Msg) reciboMsg.readObject();
				msgRecibo.realizarAccion(this);
				reciboMsg = new ObjectInputStream(clientSocket.getInputStream());
			}

			reciboMsg.close();
			//salidaACliente.close();
			clientSocket.close();
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Problemas al querer leer otra petición: " + ex.getMessage());
			this.usuariosConectados.remove(clientSocket);
			this.estaConectado = false;
		}
	}	
}
